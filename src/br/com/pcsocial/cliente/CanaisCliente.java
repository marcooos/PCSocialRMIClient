package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Canais;
import br.com.pcsocial.servidor.servico.CanaisServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class CanaisCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Canais> montarTabela(String text, long valor) throws RemoteException {
		try {
			CanaisServ cn = (CanaisServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CanaisService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Canais.class);

			// We use the resolver cs parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Canais> tableModel = new ObjectTableModel<Canais>(
					resolver,
					"id,descricao");

			// Here we use the list to be the data of the table.
			tableModel.setData(cn.getListarCanais(text,valor));
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

	public boolean adicionarCanais(Canais cn) {
		try {
			CanaisServ cs = (CanaisServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CanaisService");
			cs.adicionarCanais(cn);
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

	public boolean alterarCanais(Canais cn) {
		try {
			CanaisServ cs = (CanaisServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CanaisService");
			cs.alterarCanais(cn);
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

	public Canais buscarCanaisId(Long id) {
		try {
			Canais cn = new Canais();
			CanaisServ cs = (CanaisServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CanaisService");
			cn = cs.getCanais(id);
			return cn;
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

	public boolean excluirCanais(Long id) {
		try {
			CanaisServ es;
			es = (CanaisServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CanaisService");
			es.excluirCanais(id);
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