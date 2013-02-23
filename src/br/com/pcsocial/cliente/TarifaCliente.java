package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Tarifa;
import br.com.pcsocial.servidor.servico.TarifaServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class TarifaCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Tarifa> montarTabela(String text, long valor) throws RemoteException {
		try {
			TarifaServ tr = (TarifaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TarifaService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Tarifa.class);

			// We use the resolver ms parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Tarifa> tableModel = new ObjectTableModel<Tarifa>(
					resolver, "id,descricao,codPms,tarifaVariavel,vendaOnline");

			// Here we use the list to be the data of the table.
			tableModel.setData(tr.getListarTarifa(text,valor));
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

	public boolean adicionarTarifa(Tarifa tr) {
		try {
			TarifaServ ts = (TarifaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TarifaService");
			ts.adicionarTarifa(tr);
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

	public boolean alterarTarifa(Tarifa tr) {
		try {
			TarifaServ ts = (TarifaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TarifaService");
			ts.alterarTarifa(tr);
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

	public Tarifa buscarTarifaId(Long id) {
		try {
			Tarifa tr = new Tarifa();
			TarifaServ ts = (TarifaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TarifaService");
			tr = ts.getTarifa(id);
			return tr;
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

	public boolean excluirTarifa(Long id) {
		try {
			TarifaServ ts;
			ts = (TarifaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TarifaService");
			ts.excluirTarifa(id);
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