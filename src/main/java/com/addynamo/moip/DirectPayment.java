package com.addynamo.moip;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import com.addynamo.moip.Config.RemoteServer;

public class DirectPayment {

	private MoIPResponse response;

	private RemoteServer serverType;

	private String token;
	private String key;

	public DirectPayment() {
		this.response = new MoIPResponse();
		this.serverType = RemoteServer.NONE;
	}
	
	public DirectPayment(String token, String key, RemoteServer serverType) {
		this.response = new MoIPResponse();
		this.token = token;
		this.key = key;
		this.serverType = serverType;
	}

	public boolean pay(PaymentDetails details) throws PaymentException {
		MoIPXmlBuilder builder = new MoIPXmlBuilder();
		MoIPXmlParser parser = new MoIPXmlParser();

		try {
			String msg = builder.getDirectPaymentMessage(details);
			DefaultHttpClient client = new DefaultHttpClient();

			String url;
			if (this.serverType == RemoteServer.TEST) {
				url = Config.TEST_SERVER;
			} else {
				url = Config.PRODUCTION_SERVER;
			}
			String host = new URL(Config.TEST_SERVER).getHost();
			HttpPost post = new HttpPost(url);

			BasicHttpContext localcontext = setupAuth(client, host); 
			
			StringEntity entity = new StringEntity(msg.trim(), "UTF-8");
			entity.setContentType("application/x-www-formurlencoded");
			post.setEntity(entity);

			HttpResponse response = client.execute(post, localcontext);

			InputStream in = response.getEntity().getContent();
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				// TODO: handle server errors better
				this.response.setSuccess(false);
				this.response.setMessage(response.getStatusLine().getReasonPhrase());
				this.response.addError("http", response.getStatusLine().getReasonPhrase());
			} else {
				if (parser.parseDirectPaymentResponse(in)) {
					this.response = parser.getParsedResponse();
				} else {
					this.response.setSuccess(false);
					this.response.addError("xml", "Error parsing response");
				}
			}

			return this.response.success;
		} catch (ClientProtocolException e) {
			throw new PaymentException("Error processing payment", e);
		} catch (IOException e) {
			throw new PaymentException("Error processing payment", e);
		} catch (IllegalArgumentException e) {
			throw new PaymentException("Error processing payment", e);
		} catch (XmlException e) {
			throw new PaymentException("Error processing payment", e);
		}
	}

	private BasicHttpContext setupAuth(DefaultHttpClient client, String host) {
		client.getCredentialsProvider().setCredentials(
				new AuthScope(host, AuthScope.ANY_PORT), 
			    new UsernamePasswordCredentials(token, key));
		
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(new HttpHost(host, -1, "https") , basicAuth);
		
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		return localcontext;
	}

	/**
	 * Authentication token
	 * 
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Authentication token
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Authentication key
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Authentication key
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public MoIPResponse getResponse() {
		return response;
	}

	public void setResponse(MoIPResponse response) {
		this.response = response;
	}

	public RemoteServer getServerType() {
		return serverType;
	}

	public void setServerType(RemoteServer serverType) {
		this.serverType = serverType;
	}
}