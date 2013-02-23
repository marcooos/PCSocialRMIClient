package br.com.pcsocial.cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.com.pcsocial.servidor.modelo.Cidade;
import br.com.pcsocial.servidor.servico.CidadeServ;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

public class CidadeCliente {
	
	@SuppressWarnings("unchecked")
	public ObjectTableModel<Cidade> montarTabela(String text, long valor) throws RemoteException {
		try {
			CidadeServ ep = (CidadeServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CidadeService");
			
			// Here we create the resolver for annotated classes
			AnnotationResolver resolver = new AnnotationResolver(Cidade.class);

			// We use the resolver as parameter to the ObjectTableModel
			// and the String represent the cols.
			ObjectTableModel<Cidade> tableModel = new ObjectTableModel<Cidade>(
					resolver,
					"id,descricao,estado.descricao:Estado,estado.pais.descricao:Pais");
			
			// Here we use the list to be the data of the table.
			tableModel.setData(ep.getListarCidades(text,valor));
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

	public Cidade buscarCidadeId(Long id) {
		try {
			Cidade cd = new Cidade();
			CidadeServ cs = (CidadeServ) Naming
					.lookup("rmi://"+ConexaoServidorCliente.getEnderecoServidor()+"/CidadeService");
			cd = cs.getCidade(id);
			return cd;
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
}
