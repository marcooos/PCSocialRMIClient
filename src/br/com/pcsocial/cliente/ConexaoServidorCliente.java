package br.com.pcsocial.cliente;

public class ConexaoServidorCliente {
	
	public static String servidor;
	
	public ConexaoServidorCliente() {
		servidor = "localhost:1099";
	}
	
	public static void setEnderecoServidor(String srv) {
		servidor = srv;
	}
	
	public static String getEnderecoServidor(){
		return servidor;
	}

}
