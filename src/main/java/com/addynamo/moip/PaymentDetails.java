package com.addynamo.moip;


public class PaymentDetails {
	public static enum OwnerIdType {
		CPF, RG
	};

	private String value;
	private String brand;
	private String creditCardNumber;
	private String expirationDate;
	private String cvv;
	private String ownerName;
	private OwnerIdType ownerIdType = OwnerIdType.CPF; // Determines if this is
														// a CPF or RG
	// number.
	private String ownerIdNumber;
	private String ownerBirthDate;
	private String installmentsQuantity;
	private String email;
	private String cellPhone;
	private String streetAddress;
	private String streetNumberAddress;
	private String addressComplement;
	private String neighborhood;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String fixedPhone;

	private String orderIdentifier;

	public PaymentDetails() {
	}

	public String getBrand() {
		return brand;
	}

	/**
	 * Definition of the payment institution to be used.
	 * <ul>
	 * <li>AmericanExpress
	 * <li>Diners
	 * <li>Hipercard
	 * <li>Mastercard
	 * <li>Visa
	 * 
	 * @see Instituicao
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * Number of credit card
	 * 
	 * @see CartaoCredito:Numero
	 * 
	 * @param creditCardNumber e.g. 4073020000000002
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Expiration information of the credit card, containing only the month and
	 * year with two digits each.
	 * 
	 * @see CartaoCredito:Expiracao
	 * @param expirationDate
	 *            format = MM/yy
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCVV() {
		return cvv;
	}

	/**
	 * CVV entered by the customer.
	 * 
	 * @see CartaoCredito:CodigoSeguranca
	 * @param secureCode
	 */
	public void setCVV(String cvv) {
		this.cvv = cvv;
	}

	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * Name of card credit holder, identical to the name displayed on the card.
	 * 
	 * @see Portador:Nome
	 * @see Pagador:Nome
	 * @param ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerIdNumber() {
		return ownerIdNumber;
	}

	/**
	 * Identity CPF of credit card holder
	 * 
	 * e.g. 222.222.222-22
	 * 
	 * @see Portador:Identidade
	 * @see Pagador:Identidade
	 * 
	 * @param ownerIdNumber
	 *            format = 222.222.222-22
	 */
	public void setOwnerIdNumber(String ownerIdNumber) {
		this.ownerIdNumber = ownerIdNumber;
	}

	public String getOwnerBirthDate() {
		return ownerBirthDate;
	}

	/**
	 * Date of birth of the cardholder’s credit.
	 * 
	 * @see Tipo:DataNascimento
	 * @param ownerBirthDate
	 *            fromat = dd/MM/yyyy
	 */
	public void setOwnerBirthDate(String ownerBirthDate) {
		this.ownerBirthDate = ownerBirthDate;
	}

	public String getInstallmentsQuantity() {
		return installmentsQuantity;
	}

	/**
	 * Number of installments in which payment will be made.
	 * 
	 * @see Parcelamento:Parcelas
	 * @param installmentsQuantity
	 */
	public void setInstallmentsQuantity(String installmentsQuantity) {
		this.installmentsQuantity = installmentsQuantity;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * @see Pagador:Email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @see CartaoCredito:Telefone
	 * @see Tipo:Telefone
	 * @see Pagador:TelefoneCelular
	 * @param cellPhone
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @see EnderecoCobranca:Logradouro
	 * @param streetAddress
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	/**
	 * Additional information from the billing address of the user.
	 * 
	 * @see EnderecoCobranca:Complemento
	 * @param addressComplement
	 */
	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	/**
	 * Neighborhood or district of the billing address of the user.
	 * 
	 * @see EnderecoCobranca:Bairro
	 * @param neighborhood
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	/**
	 * @see EnderecoCobranca:Cidade
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	/**
	 * Acronym of the state of the billing address of the user.
	 * 
	 * @see EnderecoCobranca:Estado
	 * @param state
	 *            e.g. UF
	 */
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	/**
	 * @see EnderecoCobranca:Pais
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	/**
	 * CEP’s billing address of the user.
	 * 
	 * @see EnderecoCobranca:Cep
	 * @param zipCode
	 *            format =00000-000
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Phone of billing address of the user. (00)0000-0000
	 */
	public String getFixedPhone() {
		return fixedPhone;
	}

	/**
	 * Phone of billing address of the user.
	 * 
	 * @see EnderecoCobranca:TelefoneFixo
	 * @param fixedPhone
	 *            format = (00)0000-0000
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	/**
	 * Initial value to be paid by the buyer.
	 * 
	 * @see Valores:Valor
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public String getStreetNumberAddress() {
		return streetNumberAddress;
	}

	/**
	 * Number of the billing address of the user.
	 * 
	 * @see EnderecoCobranca:Numero
	 * @param streetNumberAddress
	 */
	public void setStreetNumberAddress(String streetNumberAddress) {
		this.streetNumberAddress = streetNumberAddress;
	}

	public OwnerIdType getOwnerIdType() {
		return ownerIdType;
	}

	/**
	 * @see Portador:Identidade@Tipo
	 * @param ownerIdType
	 *            CPF or RG. Defaults to CPF
	 */
	public void setOwnerIdType(String ownerIdType) {
		if (ownerIdType.equals("CPF"))
			this.ownerIdType = OwnerIdType.CPF;
		else
			this.ownerIdType = OwnerIdType.RG;
	}

	/**
	 * @see Portador:Identidade@Tipo
	 * @param ownerIdType
	 *            defaults to CPF
	 */
	public void setOwnerIdType(OwnerIdType ownerIdType) {
		this.ownerIdType = ownerIdType;
	}

	public String getOrderIdentifier() {
		return orderIdentifier;
	}

	/**
	 * Your unique identifier of the order, the key will be returned on the NASP
	 * status changes.
	 * 
	 * @see InstrucaoUnica:IdProprio
	 * @param orderIdentifier
	 */
	public void setOrderIdentifier(String orderIdentifier) {
		this.orderIdentifier = orderIdentifier;
	}
}