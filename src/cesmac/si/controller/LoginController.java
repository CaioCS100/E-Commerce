package cesmac.si.controller;

import javax.faces.bean.ManagedBean;

import cesmac.si.dao.LoginDAO;
import cesmac.si.model.Pessoa;

@ManagedBean
public class LoginController {
	
	Pessoa model = new Pessoa();
	LoginDAO dao = new LoginDAO();
	
	public String fazerLogin()
	{
		if(this.dao.autenticarLogin(model))
		{
			return "CadastroFuncionario?faces-redirect=true";
		}
		
		return "Template?faces-redirect=true";
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
