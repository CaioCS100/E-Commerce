package cesmac.si.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import cesmac.si.dao.ClienteDAO;
import cesmac.si.model.Funcionario;
import cesmac.si.model.Pessoa;

@ManagedBean
@ViewScoped
public class ClienteController {
	private Pessoa cliente = new Pessoa();
	private List<Pessoa> listaclientes = new ArrayList<>();
	private Pessoa clienteSelecionado = new Pessoa();
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public List<Pessoa> getListaclientes() {
		return listaclientes;
	}
	public void setListaclientes(List<Pessoa> listaclientes) {
		this.listaclientes = listaclientes;
	}	 
	
	public Pessoa getClienteSelecionado() {
		return clienteSelecionado;
	}
	public void setClienteSelecionado(Pessoa clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	public void cadastrarCliente() {
		
		if(new ClienteDAO().cadastrarUsuario(cliente)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cliente Cadastrado com sucesso"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em Cadastrar o cliente"));
		}
	}
	
	public void listarClientes() {
		listaclientes = new ClienteDAO().listarUsuarios();
	}
	
	public void carregarDadosParaEditarCliente(Pessoa cliente) {
		this.clienteSelecionado = cliente;
		PrimeFaces.current().executeScript("$('#modal').modal('show')");
	}
	
	public void editarCliente() {
		
		if(new ClienteDAO().editarUsuario(this.clienteSelecionado)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cliente editado com sucesso"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em editar o cliente"));
		}
	}
	
	public void removerCliente(Integer idClienteSelecionado) {
		
		if(new ClienteDAO().excluirUsuario(idClienteSelecionado)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cliente excluído com sucesso"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em excluir o cliente"));
		}
	}
}
