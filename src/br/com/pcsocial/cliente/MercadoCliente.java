package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Mercado;
import br.com.pcsocial.servidor.servico.MercadoServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class MercadoCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Mercado> montarTabela(String text, long valor) throws RemoteException {
		try {
			MercadoServ mc = (MercadoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/MercadoService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Mercado.class);

			// We use the resolver ms parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Mercado> tableModel = new ObjectTableModel<Mercado>(
					resolver,
					"id,descricao");

			// Here we use the list to be the data of the table.
			tableModel.setData(mc.getListarMercados(text,valor));
			return tableModel;
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

	public boolean adicionarMercado(Mercado mc) {
		try {
			MercadoServ ms = (MercadoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/MercadoService");
			ms.adicionarMercado(mc);
			return true;
		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}

	public boolean alterarMercado(Mercado mc) {
		try {
			MercadoServ ms = (MercadoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/MercadoService");
			ms.alterarMercado(mc);
			return true;
		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}

	public Mercado buscarMercadoId(Long id) {
		try {
			Mercado mc = new Mercado();
			MercadoServ ms = (MercadoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/MercadoService");
			mc = ms.getMercado(id);
			return mc;
		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return null;
	}

	public boolean excluirMercado(Long id) {
		try {
			MercadoServ es;
			es = (MercadoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/MercadoService");
			es.excluirMercado(id);
			return true;
		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}
}