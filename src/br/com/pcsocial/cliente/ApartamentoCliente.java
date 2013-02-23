package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Apartamento;
import br.com.pcsocial.servidor.servico.ApartamentoServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class ApartamentoCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Apartamento> montarTabela(String text, long valor) throws RemoteException {
		try {
			ApartamentoServ ap = (ApartamentoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ApartamentoService");

			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Apartamento.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Apartamento> tableModel = new ObjectTableModel<Apartamento>(
					resolver,
					"id,descricao,descricaoAbreviada,codTipoPms,quantidadePool,quantidadeOutPool");

			// Here we use the list to be the data of the table.
			tableModel.setData(ap.getListarApartamentos(text,valor));
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

	public boolean adicionarTipoApartamento(Apartamento ap) {
		try {
			ApartamentoServ as = (ApartamentoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ApartamentoService");
			as.adicionarApartamento(ap);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean alterarApartamento(Apartamento ap) {
		try {
			ApartamentoServ as = (ApartamentoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ApartamentoService");
			as.alterarApartamento(ap);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Apartamento buscarApartamentoId(Long id) {
		try {
			Apartamento ap = new Apartamento();
			ApartamentoServ as = (ApartamentoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ApartamentoService");
			ap = as.getApartamento(id);
			return ap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return null;
	}

	public boolean excluirApartamento(Long id) {
		try {
			ApartamentoServ es;
			es = (ApartamentoServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/ApartamentoService");
			es.excluirApartamento(id);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
