package br.com.pcsocial.cliente.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesLoader {

	private Properties props;
	private String nomeDoProperties = "parametros.properties";
	private InputStream in;
	private OutputStream outPropFile;

	protected PropertiesLoader(){
		props = new Properties();
		try {
			in = new FileInputStream(nomeDoProperties);
		} catch (FileNotFoundException e1) {
			try {
				outPropFile = new FileOutputStream(nomeDoProperties);
				props.setProperty("servidor", "localhost:1099");
				props.store(outPropFile, "Arquivo de propriedades");
				outPropFile.close();
				in = new FileInputStream(nomeDoProperties);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
			//this.getClass().getResourceAsStream(nomeDoProperties);
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String getValor(String chave) {
		return (String) props.getProperty(chave);
	}

	protected void setValor(String chave, String valor) {
		props.setProperty(chave, valor);
		saveProperties(props, "parametros.properties");
	}

	protected void saveProperties(Properties p, String fileName) {
		try {
			outPropFile = new FileOutputStream(fileName);
			p.store(outPropFile, "Arquivo de propriedades");
			outPropFile.close();

		} catch (IOException ioe) {
			System.out.println("I/O Exception.");
			ioe.printStackTrace();
			System.exit(0);

		}

	}

}