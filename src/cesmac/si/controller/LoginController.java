package cesmac.si.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import cesmac.si.dao.LoginDAO;
import cesmac.si.model.Pessoa;
import cesmac.si.util.SessionUtil;

@ManagedBean
public class LoginController {
	
	Pessoa model = new Pessoa();
	LoginDAO dao = new LoginDAO();
	Pessoa usuarioLogado = new Pessoa();

	public void abrirModal() {
		PrimeFaces context = PrimeFaces.current();
		context.executeScript("$('#mdLogin').modal('show')");
	}
	
	public String fazerLogin() {
		usuarioLogado = this.dao.autenticarLogin(model);
		String retorno = "";
		if (usuarioLogado != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("expired", "N");
			if (usuarioLogado.getRestrito()) {
				SessionUtil.setSessionAttribute("usuario", usuarioLogado);
				retorno = "interno?faces-redirect=true";
			} else if (!usuarioLogado.getRestrito()) {
				SessionUtil.setSessionAttribute("usuario", usuarioLogado);
				retorno = "cliente?faces-redirect=true";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Login ou Senha erradas!"));
			model.setSenha("");
			
		}
		return retorno;
	}
	
    public String logout() {
        SessionUtil.getSession().invalidate();
        return "index?faces-redirect=true";
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

	public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Pessoa usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
