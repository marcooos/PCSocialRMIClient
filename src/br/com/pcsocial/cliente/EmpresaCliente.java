package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Empresa;
import br.com.pcsocial.servidor.servico.EmpresaServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class EmpresaCliente {

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Empresa> montarTabela(String text, long valor) throws RemoteException {
		try {
			EmpresaServ ep = (EmpresaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/EmpresaService");

			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Empresa.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Empresa> tableModel = new ObjectTableModel<Empresa>(
					resolver,
					"id,pessoaEmpresa.nomeRazaoSocial:Razao Social,pessoaEmpresa.sobreNomeFantasia:Nome Fantasia,pessoaEmpresa.cpfCnpj:CNPJ");

			// Here we use the list to be the data of the table.
			tableModel.setData(ep.getListarEmpresas(text,valor));
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

	public boolean adicionarEmpresa(Empresa em) {
		try {
			EmpresaServ es = (EmpresaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/EmpresaService");
			es.adicionarEmpresa(em);
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

	public boolean alterarEmpresa(Empresa em) {
		try {
			EmpresaServ es = (EmpresaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/EmpresaService");
			es.alterarEmpresa(em);
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

	public Empresa buscarEmpresaId(Long id) {
		try {
			Empresa em = new Empresa();
			EmpresaServ es = (EmpresaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/EmpresaService");
			em = (Empresa) es.getListarEmpresas(id).get(0);
			return em;
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

	public boolean excluirEmpresa(Long id) {
		try {
			EmpresaServ es;
			es = (EmpresaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/EmpresaService");
			es.excluirEmpresa(id);
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
