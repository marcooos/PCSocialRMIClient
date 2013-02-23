package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.com.pcsocial.servidor.modelo.Restricoes;
import br.com.pcsocial.servidor.servico.RestricoesServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class RestricoesCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Restricoes> montarTabela(String text, long valor) throws RemoteException {
		try {
			RestricoesServ rs = (RestricoesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/RestricoesService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Restricoes.class);

			// We use the resolver rss parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Restricoes> tableModel = new ObjectTableModel<Restricoes>(
					resolver,
					"id,descricao");

			// Here we use the list to be the data of the table.
			tableModel.setData(rs.getListarRestricoes(text,valor));
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

	public boolean adicionarRestricoes(Restricoes rs) {
		try {
			RestricoesServ rss = (RestricoesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/RestricoesService");
			rss.adicionarRestricoes(rs);
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

	public boolean alterarRestricoes(Restricoes rs) {
		try {
			RestricoesServ rss = (RestricoesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/RestricoesService");
			rss.alterarRestricoes(rs);
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

	public Restricoes buscarRestricoesId(Long id) {
		try {
			Restricoes rs = new Restricoes();
			RestricoesServ rss = (RestricoesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/RestricoesService");
			rs = rss.getRestricoes(id);
			return rs;
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
	
	@SuppressWarnings("unchecked")
	public List<Restricoes> listaDeRestricoes() {
		try {
			@SuppressWarnings("rawtypes")
			List listRestricoes = new ArrayList<Restricoes>();
			RestricoesServ rss = (RestricoesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/RestricoesService");
			listRestricoes = rss.getListarRestricoes();
			return listRestricoes;
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


	public boolean excluirRestricoes(Long id) {
		try {
			RestricoesServ es;
			es = (RestricoesServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/RestricoesService");
			es.excluirRestricoes(id);
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