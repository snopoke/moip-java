package com.addynamo.moip;

public interface Config {
	// Connection remote server
	public static enum RemoteServer {
		NONE, TEST, PRODUCTION
	};

	// Connectioin URLs
	public static final String TEST_SERVER = "https://desenvolvedor.moip.com.br/sandbox/ws/alpha/EnviarInstrucao/Unica";
	public static final String PRODUCTION_SERVER = "https://www.moip.com.br/ws/alpha/EnviarInstrucao/Unica";
}