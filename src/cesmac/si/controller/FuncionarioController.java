package cesmac.si.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import cesmac.si.dao.FuncionarioDAO;
import cesmac.si.model.Funcionario;

@ManagedBean(name = "controleFuncionario")
@ViewScoped
public class FuncionarioController {

	private UploadedFile foto;
	private Funcionario model;
	private FuncionarioDAO dao;
	private final UtilitariosController controleUtilitario = new UtilitariosController();
	private List<Funcionario> listaFuncionario;
	private PrimeFaces context = PrimeFaces.current(); // item necessario para poder usar js no backend

	@PostConstruct
	public void init() {
		this.model = new Funcionario();
		this.dao = new FuncionarioDAO();
		this.listaFuncionario = this.dao.listarTodosFuncionarios();
	}
	
	public void salvarFuncionario() {
		String[] telefoneDDD = this.controleUtilitario.retirarMascaraTelefone(this.model.getTelefone());
		this.model.setCpf(this.controleUtilitario.retirarMascara(this.model.getCpf()));
		this.model.setCep(this.controleUtilitario.retirarMascara(this.model.getCep()));
		this.model.setDdd(telefoneDDD[0]);
		this.model.setTelefone(telefoneDDD[1]);
		if (dao.cadastrarFuncionario(model)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Funcionário Cadastrado com sucesso"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em Cadastrar o Funcionário"));
		}
		
	}

	public void carregarDadosParaEditarFuncionario(Funcionario funcionario) {
		this.model = funcionario;
		this.context.executeScript("$('#modal').modal('show')");
	}
	
	public void editarFuncionario()
	{
		String[] telefoneDDD = this.controleUtilitario.retirarMascaraTelefone(this.model.getTelefone());
		this.model.setCpf(this.controleUtilitario.retirarMascara(this.model.getCpf()));
		this.model.setCep(this.controleUtilitario.retirarMascara(this.model.getCep()));
		this.model.setDdd(telefoneDDD[0]);
		this.model.setTelefone(telefoneDDD[1]);
		if (dao.editarFuncionario(model)) 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Funcionário Editado com sucesso"));
		} 
		else 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em Editar o Funcionário"));
		}
		this.context.executeScript("$('#modal').modal('hide')");
	}
	
	public void deletarFuncionario(Funcionario funcionario)
	{
		if (dao.deletarFuncionario(funcionario.getId())) 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Funcionário deletado com sucesso"));
			listaFuncionario.remove(funcionario);
		} 
		else 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em deletar o Funcionário"));
		}
		
	}

	public void teste(FileUploadEvent event) {
		System.out.println("entrou aqui");
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public List<Funcionario> getListaFuncionario() {
		return this.listaFuncionario;
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
