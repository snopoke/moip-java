package com.addynamo.moip;

import java.util.UUID;

import org.junit.Ignore;

import com.addynamo.moip.Config.RemoteServer;

@Ignore
public class DirectPaymentTest {

	private static final String KEY = "JTSZQ8OTCPHIL36ME5MDTTSZ6ZM0CSAW0EHPEIDS";
	private static final String TOKEN = "UU6HMLRVAARXSYO36IRLRTDIEHENUACG";

	public static void main(String[] args) throws PaymentException {
		PaymentDetails details = new PaymentDetails();
		
		details.setOrderIdentifier(UUID.randomUUID().toString());

		details.setValue("999");
		details.setInstallmentsQuantity("1");
		details.setCreditCardNumber("345678901234564");
		details.setBrand("AmericanExpress");
		details.setExpirationDate("12/13");
		details.setCVV("123");
		
		details.setOwnerName("Joe Black");
		details.setOwnerIdNumber("111.111.111-11");
		details.setOwnerBirthDate("20/08/1980");
		details.setCellPhone("(61)9999-9999");
		details.setEmail("joe@black.com");
		
		details.setStreetAddress("Praça dos Três Poderes");
		details.setStreetNumberAddress("2");
		details.setNeighborhood("Zona Cívico-Administrativa");
		details.setAddressComplement("additional address info");
		details.setCity("Brasília");
		details.setState("DF");
		details.setCountry("BRA");
		details.setZipCode("70100-000");
		details.setFixedPhone("(11)1111-1111");

		DirectPayment payment = new DirectPayment(TOKEN, KEY, RemoteServer.TEST);
		payment.pay(details);
		System.out.println(payment.getResponse());
	}
}
