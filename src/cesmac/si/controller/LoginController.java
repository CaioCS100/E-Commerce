package cesmac.si.controller;

import javax.faces.bean.ManagedBean;

import cesmac.si.dao.LoginDAO;
import cesmac.si.model.Usuario;

@ManagedBean
public class LoginController {
	
	Usuario model = new Usuario();
	LoginDAO dao = new LoginDAO();
	
	public String fazerLogin()
	{
		if(this.dao.autenticarLogin(model))
		{
			return "CadastroFuncionario?faces-redirect=true";
		}
		
		return "Template?faces-redirect=true";
	}

	public Usuario getModel() {
		return model;
	}

	public void setModel(Usuario model) {
		this.model = model;
	}

	public LoginDAO getDao() {
		return dao;
	}

	public void setDao(LoginDAO dao) {
		this.dao = dao;
	}

}
