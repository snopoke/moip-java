package com.addynamo.moip;

import java.util.HashMap;
import java.util.Map;

public class MoIPResponse {

	public boolean success;
	public String id;
	public String token;
	public String amount;
	public String moIPTax;
	public String transactionStatus;
	public String moIPCode;
	public String authCode;
	public String returnCode;
	public String message;
	/*
	 * messages will be set with error messages in case responseStatus is
	 * "Falha"
	 */
	public Map<String, String> errors;

	public MoIPResponse() {
		this.errors = new HashMap<String, String>();
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMoIPTax() {
		return moIPTax;
	}

	public void setMoIPTax(String moIPTax) {
		this.moIPTax = moIPTax;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMoIPCode() {
		return moIPCode;
	}

	public void setMoIPCode(String moIPCode) {
		this.moIPCode = moIPCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Map<String, String> getErrors(){
		return errors;
	}
	
	public void addError(String code, String message) {
		this.errors.put(code, message);
	}

	@Override
	public String toString() {
		return "MoIPResponse [id=" + id + ", success=" + success + ", token=" + token + ", amount="
				+ amount + ", moIPTax=" + moIPTax + ", transactionStatus=" + transactionStatus + ", moIPCode="
				+ moIPCode + ", authCode=" + authCode + ", returnCode=" + returnCode + ", errors=" + errors
				+ "]";
	}

}