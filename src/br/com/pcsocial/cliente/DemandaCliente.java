package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Demanda;
import br.com.pcsocial.servidor.servico.DemandaServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class DemandaCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Demanda> montarTabela(String text, long valor) throws RemoteException {
		try {
			DemandaServ mc = (DemandaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/DemandaService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Demanda.class);

			// We use the resolver ms parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Demanda> tableModel = new ObjectTableModel<Demanda>(
					resolver,
					"id,descricao,codPms");

			// Here we use the list to be the data of the table.
			tableModel.setData(mc.getListarDemandas(text,valor));
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

	public boolean adicionarDemanda(Demanda dm) {
		try {
			DemandaServ ds = (DemandaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/DemandaService");
			ds.adicionarDemanda(dm);
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

	public boolean alterarDemanda(Demanda dm) {
		try {
			DemandaServ ms = (DemandaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/DemandaService");
			ms.alterarDemanda(dm);
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

	public Demanda buscarDemandaId(Long id) {
		try {
			Demanda dm = new Demanda();
			DemandaServ ds = (DemandaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/DemandaService");
			dm = ds.getDemanda(id);
			return dm;
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

	public boolean excluirDemanda(Long id) {
		try {
			DemandaServ ds;
			ds = (DemandaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/DemandaService");
			ds.excluirDemanda(id);
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