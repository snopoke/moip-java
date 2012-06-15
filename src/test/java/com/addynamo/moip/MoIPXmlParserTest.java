package com.addynamo.moip;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class MoIPXmlParserTest {

	@Test
	public void testParsing_successResponse() throws XmlException, UnsupportedEncodingException {
		String xml = "<ns1:EnviarInstrucaoUnicaResponse xmlns:ns1=\"https://desenvolvedor.moip.com.br/sandbox/\">"
				+ "<Resposta>" 
				+ "<ID>200807272314444710000000000022</ID>" 
				+ "<Status>Sucesso</Status>"
				+ "<Token>T2N0L0X8E0S71217U2H3W1T4F4S4G4K731D010V0S0V0S080M010E0Q082X2</Token>"
				+ "<RespostaPagamentoDireto>" 
				+ "<TotalPago>213.25</TotalPago>" 
				+ "<TaxaMoIP>15.19</TaxaMoIP>"
				+ "<Status>EmAnalise</Status>" 
				+ "<CodigoMoIP>0000.0006.9922</CodigoMoIP>"
				+ "<Mensagem>Transação com Sucesso</Mensagem>" 
				+ "<CodigoAutorizacao>396822</CodigoAutorizacao>"
				+ "<CodigoRetorno>0</CodigoRetorno>" 
				+ "</RespostaPagamentoDireto>" 
				+ "</Resposta>"
				+ "</ns1:EnviarInstrucaoUnicaResponse>";
		MoIPXmlParser parser = new MoIPXmlParser();
		boolean success = parser.parseDirectPaymentResponse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		assertTrue(success);
		MoIPResponse response = parser.getParsedResponse();
		assertThat(response.id, is("200807272314444710000000000022"));
		assertThat(response.token, is("T2N0L0X8E0S71217U2H3W1T4F4S4G4K731D010V0S0V0S080M010E0Q082X2"));
		assertThat(response.amount, is("213.25"));
		assertThat(response.moIPTax, is("15.19"));
		assertThat(response.success, is(true));
		assertThat(response.transactionStatus, is("EmAnalise"));
		assertThat(response.moIPCode, is("0000.0006.9922"));
		assertThat(response.authCode, is("396822"));
		assertThat(response.returnCode, is("0"));
		
	}
	@Test
	public void testParsing_failureResponse() throws XmlException {
		String xml = "<ns1:EnviarInstrucaoUnicaResponse xmlns:ns1=\"http://www.moip.com.br/ws/alpha/\">"
				+ "<Resposta>"
				+ "<ID>200807272314444710000000000022</ID>"
				+ "<Status>Falha</Status>"
				+ "<Erro Codigo=\"123\">Message1</Erro>"
				+ "<Erro Codigo=\"456\">Message2</Erro>"
				+ "</Resposta>"
				+ "</ns1:EnviarInstrucaoUnicaResponse>";
		MoIPXmlParser parser = new MoIPXmlParser();
		boolean success = parser.parseDirectPaymentResponse(new ByteArrayInputStream(xml.getBytes()));
		assertTrue(success);
		MoIPResponse response = parser.getParsedResponse();
		assertThat(response.id, is("200807272314444710000000000022"));
		assertThat(response.success, is(false));
		assertThat(response.errors.size(), is(2));
		assertTrue(response.errors.containsKey("123"));
		assertThat(response.errors.get("123"), is("Message1"));
		assertTrue(response.errors.containsKey("456"));
		assertThat(response.errors.get("456"), is("Message2"));
		
	}
}
