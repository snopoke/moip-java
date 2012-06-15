/**
 * Copyright (c) 2011, MENKI MOBILE SOLUTIONS - http://www.menkimobile.com.br
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation and/or 
 *   other materials provided with the distribution.
 * * Neither the name of the MENKI MOBILE SOLUTIONS nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software without 
 *   specific prior written permission.
 *   
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
 *  SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 *  TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 *  ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 *  SUCH DAMAGE. 
 *  
 *  @version 0.0.1
 */
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