package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import br.com.pcsocial.servidor.modelo.Hospedagem;
import br.com.pcsocial.servidor.servico.HospedagemServ;

public class HospedagemCliente {

	@SuppressWarnings("unchecked")
	public List<Hospedagem> getListarHospedagems(Date dataInicial,
			Date dataFinal) {
		try {
			HospedagemServ mc = (HospedagemServ) Naming.lookup("rmi://"
					+ ConexaoServidorCliente.getEnderecoServidor()
					+ "/HospedagemService");

			return mc.getListarHospedagems(dataInicial, dataFinal);
		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
		} catch (RemoteException e) {
			System.out.println();
			System.out.println("RemoteException: " + e.toString());
		} catch (NotBoundException e) {
			System.out.println();
			System.out.println("NotBoundException: " + e.toString());
		} catch (Exception e) {
			System.out.println();
			System.out.println("Exception: " + e.toString());
		}
		return null;
	}
}