package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import br.com.pcsocial.servidor.modelo.Pessoa;
import br.com.pcsocial.servidor.servico.PessoaServ;

public class PessoaCliente {

	public boolean validarLogin(String email, String senha) {
		try {
			PessoaServ p = (PessoaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/PessoaService");
			if (!(p.validarLogin(email, senha))) {
				return true;
			} else
				return false;
		} catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException: " + e.toString());
			return false;
		} catch (RemoteException e) {
			System.out.println();
			System.out.println("RemoteException: " + e.toString());
			JOptionPane.showMessageDialog(null,
					"N‹o foi poss’vel conectar com o servidor", null, 0, new ImageIcon(
							getClass().getResource("/gui/icones/acoes/cancelar.png")));
			return false;
		} catch (NotBoundException e) {
			System.out.println();
			System.out.println("NotBoundException: " + e.toString());
			JOptionPane.showMessageDialog(null,
					"N‹o foi poss’vel conectar com o servidor", null, 0, new ImageIcon(
							getClass().getResource("/gui/icones/acoes/cancelar.png")));
			return false;
		} catch (Exception e) {
			System.out.println();
			System.out.println("Exception: " + e.toString());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public ObjectTableModel<Pessoa> montarTabela(String text, long valor) throws RemoteException {
		try {
			PessoaServ p = (PessoaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/PessoaService");

			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Pessoa.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Pessoa> tableModel = new ObjectTableModel<Pessoa>(
					resolver, "id,email,nomeRazaoSocial,sobreNomeFantasia,cpfCnpj");

			// Here we use the list to be the data of the table.
			tableModel.setData(p.getListarPessoas(text,valor));
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

	public boolean adicionarPessoa(Pessoa p) {
		try {
			PessoaServ ps = (PessoaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/PessoaService");
			ps.adicionarPessoa(p);
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
	
	public boolean alterarPessoa(Pessoa p) {
		try {
			PessoaServ ps = (PessoaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/PessoaService");
			ps.alterarPessoa(p);
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
	
	public Pessoa buscarPessoaId(Long id) {
		try {
			Pessoa p = new Pessoa();
			PessoaServ ps = (PessoaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/PessoaService");
			p = (Pessoa) ps.getListarPessoas(id).get(0);
			return p;
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
	
	public boolean excluirPessoa(Long id){
		try {
			PessoaServ ps;
			ps = (PessoaServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/PessoaService");
			ps.excluirPessoa(id);
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
