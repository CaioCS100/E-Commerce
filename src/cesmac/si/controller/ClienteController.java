package cesmac.si.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import cesmac.si.model.Pessoa;

@ManagedBean
@ViewScoped
public class ClienteController {
	private Pessoa cliente = new Pessoa();
	private Pessoa clienteSelecionado = new Pessoa();
	private List<Pessoa> listaclientes = new ArrayList<>();
	
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public Pessoa getClienteSelecionado() {
		return clienteSelecionado;
	}
	public void setClienteSelecionado(Pessoa clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	public List<Pessoa> getListaclientes() {
		return listaclientes;
	}
	public void setListaclientes(List<Pessoa> listaclientes) {
		this.listaclientes = listaclientes;
	}	 
	
}
