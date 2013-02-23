package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Temporada;
import br.com.pcsocial.servidor.servico.TemporadaServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class TemporadaCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Temporada> montarTabela(String text, long valor) throws RemoteException {
		try {
			TemporadaServ mc = (TemporadaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TemporadaService");

			// Here we create the resolver for annotated clmsses
			AnnotationResolver resolver = new AnnotationResolver(Temporada.class);

			// We use the resolver ms parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Temporada> tableModel = new ObjectTableModel<Temporada>(
					resolver,
					"id,descricao,dataInicial,dataFinal,vendaAtiva");

			// Here we use the list to be the data of the table.
			tableModel.setData(mc.getListarTemporadas(text,valor));
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

	public boolean adicionarTemporada(Temporada tm) {
		try {
			TemporadaServ ts = (TemporadaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TemporadaService");
			ts.adicionarTemporada(tm);
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

	public boolean alterarTemporada(Temporada tm) {
		try {
			TemporadaServ ts = (TemporadaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TemporadaService");
			ts.alterarTemporada(tm);
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

	public Temporada buscarTemporadaId(Long id) {
		try {
			Temporada tm = new Temporada();
			TemporadaServ ts = (TemporadaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TemporadaService");
			tm = ts.getTemporada(id);
			return tm;
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

	public boolean excluirTemporada(Long id) {
		try {
			TemporadaServ ts;
			ts = (TemporadaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/TemporadaService");
			ts.excluirTemporada(id);
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