package com.addynamo.moip;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.Test;

public class MoIPXMLBuilderTest {
	
	@Test
	public void testBuild() throws XmlException{
		MoIPXmlBuilder builder = new MoIPXmlBuilder();
		PaymentDetails details = new PaymentDetails();
		details.setAddressComplement("addressComplement");
		details.setBrand("brand");
		details.setCellPhone("123456789");
		details.setCity("city");
		details.setCountry("country");
		details.setCreditCardNumber("4073020000000002");
		details.setEmail("joe@black.com");
		details.setExpirationDate("12/13");
		details.setFixedPhone("(00) 0000 000");
		details.setInstallmentsQuantity("1");
		details.setNeighborhood("San Jose");
		details.setOrderIdentifier("orderIdentifier");
		details.setOwnerBirthDate("20/08/1980");
		details.setOwnerIdNumber("987654321");
		details.setOwnerIdType("CPF");
		details.setOwnerName("Joe Black");
		details.setCVV("123");
		details.setState("state");
		details.setStreetAddress("streetAddress");
		details.setStreetNumberAddress("2");
		details.setValue("999");
		details.setZipCode("5555");
		
		//builder.setFormat(true);
		String message = builder.getDirectPaymentMessage(details);
		//System.out.println(message);
		
		assertThat(message, containsString("<Valores><Valor moeda=\"BRL\">999</Valor>"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Instituicao>brand</Instituicao>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<CartaoCredito>.*<Numero>4073020000000002</Numero>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<CartaoCredito>.*<Expiracao>12/13</Expiracao>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<CartaoCredito>.*<CodigoSeguranca>123</CodigoSeguranca>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<CartaoCredito>.*<Telefone>123456789</Telefone>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Portador>.*<Nome>Joe Black</Nome>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Portador>.*<Identidade Tipo=\"CPF\">987654321</Identidade>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Portador>.*<Telefone>123456789</Telefone>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Portador>.*<DataNascimento>20/08/1980</DataNascimento>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Parcelamento>.*<Parcelas>1</Parcelas>.*"));
		assertTrue(message.matches(".*<PagamentoDireto>.*<Parcelamento>.*<Recebimento>AVista</Recebimento>.*"));
		assertTrue(message.matches(".*<Pagador>.*<Nome>Joe Black</Nome>.*"));
		assertTrue(message.matches(".*<Pagador>.*<Email>joe@black.com</Email>.*"));
		assertTrue(message.matches(".*<Pagador>.*<TelefoneCelular>123456789</TelefoneCelular>.*"));
		assertTrue(message.matches(".*<Pagador>.*<Identidade>987654321</Identidade>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Logradouro>streetAddress</Logradouro>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Numero>2</Numero>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Complemento>addressComplement</Complemento>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Bairro>San Jose</Bairro>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Cidade>city</Cidade>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Estado>state</Estado>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<Pais>country</Pais>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<CEP>5555</CEP>.*"));
		assertTrue(message.matches(".*<Pagador>.*<EnderecoCobranca>.*<TelefoneFixo>\\(00\\) 0000 000</TelefoneFixo>.*"));
		assertTrue(message.matches(".*<InstrucaoUnica>.*<IdProprio>orderIdentifier</IdProprio>.*"));
	}

}
