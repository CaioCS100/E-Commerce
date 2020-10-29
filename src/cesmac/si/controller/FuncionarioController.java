package cesmac.si.controller;

import cesmac.si.dao.FuncionarioDAO;
import cesmac.si.model.Funcionario;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ManagedBean(name = "controleFuncionario")
@SessionScoped
public class FuncionarioController {
	
	private Funcionario model;
	private Funcionario modelParaEditarEVisualizar;
	private FuncionarioDAO dao;
	private final UtilitariosController controleUtilitario = new UtilitariosController();
	private List<Funcionario> listaFuncionario;
	private PrimeFaces context = PrimeFaces.current(); // item necessario para poder usar js no backend
    private boolean uploadImagem = true; 
    private boolean modificarCampos = false; //Se for true, o usu�rio s� vai poder visualiza-los se for false ele vai poder alterar os campos.

	public FuncionarioController() {
		this.model = new Funcionario();
		this.dao = new FuncionarioDAO();
	}
	
	public void carregarTabelaFuncionarios()
	{
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
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Funcion�rio Cadastrado com sucesso"));
			this.model = new Funcionario();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em Cadastrar o Funcion�rio"));
		}
		
	}
	
	public void carregarImagem(FileUploadEvent event) {
		
		try {
			if(event.getFile().getInputstream() != null)
			{
				InputStream input = event.getFile().getInputstream();
				this.model.setExtensaoImagem(event.getFile().getContentType());
				
				boolean isPng = this.model.getExtensaoImagem().endsWith("png");
				
				BufferedImage imageBuffer = ImageIO.read(input);
				int type = BufferedImage.TYPE_INT_RGB;
				if(isPng)
		        {
		            type = BufferedImage.BITMASK;
		        }
				BufferedImage redimensionarImagem = new BufferedImage(200, 200, type);
				Graphics2D graphics2D = redimensionarImagem.createGraphics();
		        graphics2D.setComposite(AlphaComposite.Src);
		        graphics2D.drawImage(imageBuffer, 0, 0, 200, 200, null);
		        ByteArrayOutputStream bytesImgem = new ByteArrayOutputStream();
		        if(isPng)
		        {
		        	ImageIO.write(redimensionarImagem,"png", bytesImgem);
		        }
		        else
		        {
		            ImageIO.write(redimensionarImagem,"jpg", bytesImgem);
		        }
		        bytesImgem.flush();
		        byte[] byteArray = bytesImgem.toByteArray();
		        bytesImgem.close();
		        
		        this.model.setImagem(byteArray);
			}			
	        
        } catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void carregarDadosParaEditarFuncionario(Funcionario funcionario) 
	{
		this.modelParaEditarEVisualizar = funcionario;
		this.context.executeScript("$('#modal').modal('show')");
		this.uploadImagem = true;
		this.modificarCampos = false;
	}
	
	public void carregarDadosParaVisualizarFuncionario(Funcionario funcionario)
	{
			this.modelParaEditarEVisualizar = funcionario;
			this.uploadImagem = false;
			this.modificarCampos = true;
			this.context.executeScript("$('#visualizar').modal('show')");
		
	}
	
	public void editarFuncionario()
	{
		String[] telefoneDDD = this.controleUtilitario.retirarMascaraTelefone(this.modelParaEditarEVisualizar.getTelefone());
		this.modelParaEditarEVisualizar.setCpf(this.controleUtilitario.retirarMascara(this.modelParaEditarEVisualizar.getCpf()));
		this.modelParaEditarEVisualizar.setCep(this.controleUtilitario.retirarMascara(this.modelParaEditarEVisualizar.getCep()));
		this.modelParaEditarEVisualizar.setDdd(telefoneDDD[0]);
		this.modelParaEditarEVisualizar.setTelefone(telefoneDDD[1]);
		// aqui verifica se o usuario mudou a foto na hora de editar um funcionario
		if(this.model.getImagem() != null)
		{
			this.modelParaEditarEVisualizar.setImagem(model.getImagem());
			this.modelParaEditarEVisualizar.setExtensaoImagem(model.getExtensaoImagem());
		}
		
		if (dao.editarFuncionario(modelParaEditarEVisualizar)) 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Funcion�rio Editado com sucesso"));
		} 
		else 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em Editar o Funcion�rio"));
		}
		this.context.executeScript("$('#modal').modal('hide')");
		carregarTabelaFuncionarios();
	}
	
	public StreamedContent getImageFromDB() {
		
		InputStream inputStream = null;
		StreamedContent streamedContent = null;
		
		if (this.modelParaEditarEVisualizar.getImagem() == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			inputStream = facesContext.getExternalContext().getResourceAsStream("/imgs/no-image.png");
			streamedContent = new DefaultStreamedContent(inputStream, "image/png");
		} else {
			inputStream = new ByteArrayInputStream(this.modelParaEditarEVisualizar.getImagem());
			streamedContent = new DefaultStreamedContent(inputStream, this.modelParaEditarEVisualizar.getExtensaoImagem());
		}
		
		return streamedContent;
	}
	
	public void deletarFuncionario(Funcionario funcionario)
	{
		if (dao.deletarFuncionario(funcionario.getId())) 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Funcion�rio deletado com sucesso"));
			listaFuncionario.remove(funcionario);
		} 
		else 
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em deletar o Funcion�rio"));
		}
		
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

	public boolean isModificarCampos() {
		return modificarCampos;
	}

	public void setModificarCampos(boolean modificarCampos) {
		this.modificarCampos = modificarCampos;
	}

	public boolean isUploadImagem() {
		return uploadImagem;
	}

	public void setUploadImagem(boolean uploadImagem) {
		this.uploadImagem = uploadImagem;
	}

	public Funcionario getModelParaEditarEVisualizar() {
		return modelParaEditarEVisualizar;
	}

	public void setModelParaEditarEVisualizar(Funcionario modelParaEditarEVisualizar) {
		this.modelParaEditarEVisualizar = modelParaEditarEVisualizar;
	}
	
}
