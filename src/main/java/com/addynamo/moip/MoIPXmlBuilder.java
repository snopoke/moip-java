package com.addynamo.moip;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.addynamo.moip.PaymentDetails.OwnerIdType;

public class MoIPXmlBuilder 
{

	/**
	 * MoIP Payment TAGs
	 */
	private static final String TAG_ENVIARINSTRUCAO = "EnviarInstrucao";
	private static final String TAG_INSTRUCAOUNICA = "InstrucaoUnica";
	private static final String TAG_RAZAO = "Razao";
	private static final String TAG_VALORES = "Valores";
	private static final String TAG_VALOR = "Valor";
	private static final String TAG_IDPROPRIO = "IdProprio";
	private static final String TAG_PAGAMENTODIRETO = "PagamentoDireto";
	private static final String TAG_FORMA = "Forma";
	private static final String TAG_INSTITUICAO = "Instituicao";
	private static final String TAG_CARTAOCREDITO = "CartaoCredito";
	private static final String TAG_NUMERO = "Numero";
	private static final String TAG_EXPIRACAO = "Expiracao";
	private static final String TAG_CODIGOSEGURANCA = "CodigoSeguranca";
	private static final String TAG_PORTADOR = "Portador";
	private static final String TAG_NOME = "Nome";
	private static final String TAG_IDENTIDADE = "Identidade";
	private static final String TAG_TELEFONE = "Telefone";
	private static final String TAG_DATANASCIMENTO = "DataNascimento";
	private static final String TAG_PARCELAMENTO = "Parcelamento";
	private static final String TAG_PARCELAS = "Parcelas";
	private static final String TAG_RECEBIMENTO = "Recebimento";
	private static final String TAG_PAGADOR = "Pagador";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_TELEFONECELULAR = "TelefoneCelular";
	private static final String TAG_ENDERECOCOBRANCA = "EnderecoCobranca";
	private static final String TAG_LOGRADOURO = "Logradouro";
	private static final String TAG_COMPLEMENTO = "Complemento";
	private static final String TAG_BAIRRO = "Bairro";
	private static final String TAG_CIDADE = "Cidade";
	private static final String TAG_ESTADO = "Estado";
	private static final String TAG_PAIS = "Pais";
	private static final String TAG_CEP = "CEP";
	private static final String TAG_TELEFONEFIXO = "TelefoneFixo";

	/**
	 * MoIP Payment Attributes
	 */
	private static final String ATTR_MOEDA = "moeda";
	private static final String ATTR_TIPO = "Tipo";
	private boolean format;

	/**
	 * <p>Creating XML Message for Direct Payment</p>
	 *<pre>
	 *	  <EnviarInstrucao>
	 *	  <InstrucaoUnica>
	 *	     <Razao>Pagamento direto com cartão de crédito</Razao>
	 *	     <Valores>
	 *	        <Valor moeda="BRL">213.25</Valor>
	 *	     </Valores>
	 *	     <IdProprio>dir_card_2</IdProprio>
	 *	     <PagamentoDireto>
	 *	        <Forma>CartaoCredito</Forma>
	 *	        <Instituicao>AmericanExpress</Instituicao>
	 *	        <CartaoCredito>
	 *	         <Numero>345678901234564</Numero>
	 *	         <Expiracao>08/11</Expiracao>
	 *	         <CodigoSeguranca>1234</CodigoSeguranca>
	 *	         <Portador>
	 *	             <Nome>Nome do Portador</Nome>
	 *	             <Identidade Tipo="CPF">111.111.111-11</Identidade>
	 *	             <Telefone>(11)1111-1111</Telefone>
	 *	             <DataNascimento>30/11/1980</DataNascimento>
	 *	         </Portador>
	 *	        </CartaoCredito>
	 *	        <Parcelamento>
	 *	         <Parcelas>2</Parcelas>
	 *	         <Recebimento>AVista</Recebimento>
	 *	        </Parcelamento>
	 *	     </PagamentoDireto>
	 *	     <Pagador>
	 *	         <Nome>Luiz Inácio Lula da Silva</Nome>
	 *	         <LoginMoIP>lula</LoginMoIP>
	 *	         <Email>presidente@planalto.gov.br</Email>
	 *	         <TelefoneCelular>(61)9999-9999</TelefoneCelular>
	 *	         <Apelido>Lula</Apelido>
	 *	         <Identidade>111.111.111-11</Identidade>
	 *	         <EnderecoCobranca>
	 *	             <Logradouro>Praça dos Três Poderes</Logradouro>
	 *	             <Numero>0</Numero>
	 *	             <Complemento>Palácio do Planalto</Complemento>
	 *	             <Bairro>Zona Cívico-Administrativa</Bairro>
	 *	             <Cidade>Brasília</Cidade>
	 *	             <Estado>DF</Estado>
	 *	             <Pais>BRA</Pais>
	 *	             <CEP>70100-000</CEP>
	 *	             <TelefoneFixo>(61)3211-1221</TelefoneFixo>
	 *	         </EnderecoCobranca>
	 *	     </Pagador>
	 *	  </InstrucaoUnica>
	 *	  </EnviarInstrucao>
	 *</pre>
	 * @param details 
	 */
	public String getDirectPaymentMessage(PaymentDetails details) throws XmlException {
			try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			StringWriter writer = new StringWriter();
	
			//Set payment type string
			String ownerIdType = null;
			if (details.getOwnerIdType() == OwnerIdType.CPF) {
				ownerIdType = "CPF";
			} else {
				ownerIdType = "RG";
			}
	
	    	Document doc = docBuilder.newDocument();
		    	Element root = doc.createElement(TAG_ENVIARINSTRUCAO);
		    	Element instrucaonica = doc.createElement(TAG_INSTRUCAOUNICA);
		    		createElement(doc, instrucaonica, TAG_RAZAO, "Pagamento direto com cartao de credito");
			    	Element valores = doc.createElement(TAG_VALORES);
			    		Element valor = createElement(doc, valores, TAG_VALOR, details.getValue());
			    			valor.setAttribute(ATTR_MOEDA, "BRL");
			    	instrucaonica.appendChild(valores);
			    	Element pagamentodireto = doc.createElement(TAG_PAGAMENTODIRETO);
			    		createElement(doc, pagamentodireto, TAG_FORMA, "CartaoCredito");
			    		createElement(doc, pagamentodireto, TAG_INSTITUICAO, details.getBrand());
			    		Element cartaocredito = doc.createElement(TAG_CARTAOCREDITO);
			    			createElement(doc, cartaocredito, TAG_NUMERO, details.getCreditCardNumber());
			    			createElement(doc, cartaocredito, TAG_EXPIRACAO, details.getExpirationDate());
			    			createElement(doc, cartaocredito, TAG_CODIGOSEGURANCA, details.getCVV());
			    			createElement(doc, cartaocredito, TAG_TELEFONE, details.getCellPhone());
			    			Element portador = doc.createElement(TAG_PORTADOR);
			    				createElement(doc, portador, TAG_NOME, details.getOwnerName());
			    				Element identidade = createElement(doc, portador, TAG_IDENTIDADE, details.getOwnerIdNumber());
			    					identidade.setAttribute(ATTR_TIPO, ownerIdType);
			    				createElement(doc, portador, TAG_TELEFONE, details.getCellPhone());
			    				createElement(doc, portador, TAG_DATANASCIMENTO, details.getOwnerBirthDate());
			    			cartaocredito.appendChild(portador);
			    		pagamentodireto.appendChild(cartaocredito);
			    		Element parcelemento = doc.createElement(TAG_PARCELAMENTO);
			    			createElement(doc, parcelemento, TAG_PARCELAS, details.getInstallmentsQuantity());
			    			createElement(doc, parcelemento, TAG_RECEBIMENTO, "AVista");
			    		pagamentodireto.appendChild(parcelemento);
			    	instrucaonica.appendChild(pagamentodireto);
			    	Element pagador = doc.createElement(TAG_PAGADOR);
						createElement(doc, pagador, TAG_NOME, details.getOwnerName());
						createElement(doc, pagador, TAG_EMAIL, details.getEmail());
						createElement(doc, pagador, TAG_TELEFONECELULAR, details.getCellPhone());
						createElement(doc, pagador, TAG_IDENTIDADE, details.getOwnerIdNumber());
						Element enderecocobranca = doc.createElement(TAG_ENDERECOCOBRANCA);
							createElement(doc, enderecocobranca, TAG_LOGRADOURO, details.getStreetAddress());
							createElement(doc, enderecocobranca, TAG_NUMERO, details.getStreetNumberAddress());
							createElement(doc, enderecocobranca, TAG_COMPLEMENTO, details.getAddressComplement());
							createElement(doc, enderecocobranca, TAG_BAIRRO, details.getNeighborhood());
							createElement(doc, enderecocobranca, TAG_CIDADE, details.getCity());
							createElement(doc, enderecocobranca, TAG_ESTADO, details.getState());
							createElement(doc, enderecocobranca, TAG_PAIS, details.getCountry());
							createElement(doc, enderecocobranca, TAG_CEP, details.getZipCode());
							createElement(doc, enderecocobranca, TAG_TELEFONEFIXO, details.getFixedPhone());
						pagador.appendChild(enderecocobranca);
			    	instrucaonica.appendChild(pagador);
				    	createElement(doc, instrucaonica, TAG_IDPROPRIO, details.getOrderIdentifier());
		    	root.appendChild(instrucaonica);
	    	doc.appendChild(root);
	    	
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			if (format) {
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			}
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(writer);
	 
			transformer.transform(source, result);
			return writer.toString();
		} catch (ParserConfigurationException e) {
			throw new XmlException(e);
		} catch (TransformerConfigurationException e) {
			throw new XmlException(e);
		} catch (TransformerException e) {
			throw new XmlException(e);
		}
	}
	
	private Element createElement(Document doc, Element parent, String tag, String value){
		Element el = doc.createElement(tag);
		el.appendChild(doc.createTextNode(value));
		parent.appendChild(el);
		return el;
	}

	public void setFormat(boolean format) {
		this.format = format;
	}

}