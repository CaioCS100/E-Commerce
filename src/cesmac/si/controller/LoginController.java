package cesmac.si.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import cesmac.si.dao.LoginDAO;
import cesmac.si.model.Pessoa;

@ManagedBean
public class LoginController {
	
	Pessoa model = new Pessoa();
	LoginDAO dao = new LoginDAO();
	
	public void abrirModal()
	{
		PrimeFaces context = PrimeFaces.current();
		context.executeScript("$('#login').modal('show')");
	}
	
	public String fazerLogin()
	{
		if(this.dao.autenticarLogin(model))
		{
			return "cadastrarFuncionario?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Login ou Senha erradas!"));
		model.setSenha("");
		return "";
	}
	

	public Pessoa getModel() {
		return model;
	}

	public void setModel(Pessoa model) {
		this.model = model;
	}

	public LoginDAO getDao() {
		return dao;
	}

	public void setDao(LoginDAO dao) {
		this.dao = dao;
	}

}
