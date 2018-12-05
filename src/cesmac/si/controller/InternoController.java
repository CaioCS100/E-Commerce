package cesmac.si.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleInterno")
@ViewScoped
public class InternoController {
	
	public String redirecionarParaPagPrincipal()
	{
		return "cadastrarFuncionario?faces-redirect=true";
	}

}
