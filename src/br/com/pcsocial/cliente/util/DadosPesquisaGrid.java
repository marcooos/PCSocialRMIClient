package br.com.pcsocial.cliente.util;

public class DadosPesquisaGrid {
	
	public String textoPesquisa(String text) {
		try {
			Long.parseLong(text);
			if ((text.length() == 11) || (text.length() == 14) ){
				return text;
			} else {
				return "";
			}
		} catch (Exception e ) {
			return text;
		}
	}
	
	public long valorPesquisa(String text){
		try {
			if ((text.length() == 11) || (text.length() == 14) ){
				return 0;
			} else {
				return Long.parseLong(text);
			}
		} catch (Exception e ) {
			return 0;
		}
	}

}
