package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Classes;
import br.com.pcsocial.servidor.servico.ClassesServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class ClassesCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Classes> montarTabela(String text, long valor) throws RemoteException {
		try {
			ClassesServ mc = (ClassesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ClassesService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Classes.class);

			// We use the resolver css parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Classes> tableModel = new ObjectTableModel<Classes>(
					resolver,
					"id,descricao");

			// Here we use the list to be the data of the table.
			tableModel.setData(mc.getListarClasses(text,valor));
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

	public boolean adicionarClasses(Classes mc) {
		try {
			ClassesServ css = (ClassesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ClassesService");
			css.adicionarClasses(mc);
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

	public boolean alterarClasses(Classes mc) {
		try {
			ClassesServ css = (ClassesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ClassesService");
			css.alterarClasses(mc);
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

	public Classes buscarClassesId(Long id) {
		try {
			Classes mc = new Classes();
			ClassesServ css = (ClassesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ClassesService");
			mc = css.getClasses(id);
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

	public boolean excluirClasses(Long id) {
		try {
			ClassesServ es;
			es = (ClassesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ClassesService");
			es.excluirClasses(id);
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