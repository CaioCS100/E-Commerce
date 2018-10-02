package cesmac.si.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import cesmac.si.dao.FuncionarioDAO;
import cesmac.si.model.Funcionario;

@ManagedBean(name="controleFuncionario")
public class FuncionarioController {
	
	private UploadedFile foto;
	private Funcionario model = new Funcionario();
	private FuncionarioDAO dao = new FuncionarioDAO();
	private final UtilitariosController controleUtilitario = new UtilitariosController();
	
	public void salvarFuncionario()
	{
		String[] telefoneDDD = this.controleUtilitario.retirarMascaraTelefone(this.model.getTelefone());
		this.model.setCpf(this.controleUtilitario.retirarMascara(this.model.getCpf()));
		this.model.setCep(this.controleUtilitario.retirarMascara(this.model.getCep()));
		this.model.setDdd(telefoneDDD[0]);
		this.model.setTelefone(telefoneDDD[1]);
		dao.cadastrarFuncionario(model);
	}
	
	
	
	
	
	public void teste(FileUploadEvent event)
	{
		System.out.println("entrou aqui");
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	
	public Funcionario getModel() {
		return model;
	}
	
	public void setModel(Funcionario model) {
		this.model = model;
	}
	

	public FuncionarioDAO getDao() {
		return dao;
	}

	public void setDao(FuncionarioDAO dao) {
		this.dao = dao;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}
}
