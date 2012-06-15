package com.addynamo.moip;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MoIPXmlParser {
	
	private Logger log = LoggerFactory.getLogger(MoIPXmlParser.class);
	private MoIPResponse responseObj = new MoIPResponse();

	public MoIPXmlParser() {
	}

	/**
	 * Parse the direct payment transaction response received from the server
	 * Response template:
	 * 
	 * <pre>
	 *  <ns1:EnviarInstrucaoUnicaResponse xmlns:ns1="http://www.moip.com.br/ws/alpha/">
	 *    <Resposta>
	 * 	    <ID>000000000000000000</ID>
	 * 	    <Status>Falha</Status>
	 * 	    <Erro Codigo="123">Message1</Erro>
	 * 	    <Erro Codigo="456">Message2</Erro>
	 *   </Resposta>
	 * </ns1:EnviarInstrucaoUnicaResponse>
	 * 
	 * 
	 * <ns1:EnviarInstrucaoUnicaResponse xmlns:ns1="https://desenvolvedor.moip.com.br/sandbox/">
	 *  <Resposta>
	 *    <ID>200807272314444710000000000022</ID>
	 *    <Status>Sucesso</Status>
	 *    <Token>T2N0L0X8E0S71217U2H3W1T4F4S4G4K731D010V0S0V0S080M010E0Q082X2</Token>
	 *     <RespostaPagamentoDireto>
	 *       <TotalPago>213.25</TotalPago>
	 *       <TaxaMoIP>15.19</TaxaMoIP>
	 *       <Status>EmAnalise</Status>
	 *       <CodigoMoIP>0000.0006.9922</CodigoMoIP>
	 *       <Mensagem>Transação com Sucesso</Mensagem>
	 *       <CodigoAutorizacao>396822</CodigoAutorizacao>
	 *       <CodigoRetorno>0</CodigoRetorno>
	 *    </RespostaPagamentoDireto>
	 *  </Resposta>
	 * </ns1:EnviarInstrucaoUnicaResponse>
	 * </pre>
	 * 
	 * @param msg
	 *            the InputStream received from the server
	 * @return true or false
	 * @throws XmlException 
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws DOMException
	 */
	public boolean parseDirectPaymentResponse(InputStream msg) throws XmlException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(msg);
			
			XPathFactory factory = XPathFactory.newInstance();
		    XPath xpath = factory.newXPath();
			
			if (log.isDebugEnabled()){ 
				logResponse(doc);
			}
			
			responseObj.setId(getTagValue(doc, "ID"));
			
			String status = getXpathValue(xpath, doc, "//Resposta/Status/text()");
			
			if (status != null) {
				if (status.equalsIgnoreCase("Sucesso")) {
					responseObj.setSuccess(true);
					responseObj.setToken(getTagValue(doc, "Token"));
					responseObj.setAmount(getTagValue(doc, "TotalPago"));
					responseObj.setMoIPTax(getTagValue(doc, "TaxaMoIP"));
					responseObj.setTransactionStatus(getXpathValue(xpath, doc, "//RespostaPagamentoDireto/Status/text()"));
					responseObj.setMoIPCode(getTagValue(doc, "CodigoMoIP"));
					responseObj.setReturnCode(getTagValue(doc, "CodigoRetorno"));
					responseObj.setAuthCode(getTagValue(doc, "CodigoAutorizacao"));
					responseObj.setMessage(getTagValue(doc, "Mensagem"));
				} else {
					responseObj.setSuccess(false);
					NodeList nodeList = doc.getElementsByTagName("Erro");
					if (nodeList.item(0) != null) {
						for (int i = 0; i < nodeList.getLength(); i++){
							String code = nodeList.item(i).getAttributes().getNamedItem("Codigo").getNodeValue();
							String message = nodeList.item(i).getChildNodes().item(0).getNodeValue();
							responseObj.addError(code, message);
						}
					}
				}
			} else {
				responseObj.setSuccess(false);
				responseObj.addError("xml", "Missing 'status' element in XML repsonse");
				return false;
			}

		} catch (ParserConfigurationException e) {
			throw new XmlException("Error parsing XML response", e);
		} catch (SAXException e) {
			throw new XmlException("Error parsing XML response", e);
		} catch (IOException e) {
			throw new XmlException("Error parsing XML response", e);
		} catch (DOMException e) {
			throw new XmlException("Error parsing XML response", e);
		} catch (XPathExpressionException e) {
			throw new XmlException("Error parsing XML response", e);
		}

		return true;
	}

	private String getXpathValue(XPath xpath, Document doc, String exprStr) throws XPathExpressionException {
		XPathExpression expr = xpath.compile(exprStr);

		NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
	    if (nodes.item(0) != null)
	    	return nodes.item(0).getNodeValue();
	    
	    return null;
	}

	private String getTagValue(Document doc, String tag) {
		NodeList nodeList = doc.getElementsByTagName(tag);
		Node item = nodeList.item(0);
		if (item != null) {
			return item.getChildNodes().item(0).getNodeValue();
		}
		
		return null;
	}

	private void logResponse(Document doc) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer );
 
			transformer.transform(source, result);
			log.debug(writer.toString());
		} catch (Exception e) {
			log.error("Failed to print response XML", e);
		}
	}

	public MoIPResponse getParsedResponse() {
		return this.responseObj;
	}
}