package cesmac.si.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import cesmac.si.dao.FuncionarioDAO;
import cesmac.si.model.Funcionario;

@ManagedBean(name = "controleFuncionario")
@SessionScoped
public class FuncionarioController {
	
	private Funcionario model;
	private FuncionarioDAO dao;
	private final UtilitariosController controleUtilitario = new UtilitariosController();
	private List<Funcionario> listaFuncionario;
	private PrimeFaces context = PrimeFaces.current(); // item necessario para poder usar js no backend
    private boolean uploadImagem = true; 
    private boolean modificarCampos = false; //Se for true, o usuário vai poder alterar os campos se for false ele só vai poder visualiza-los

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
			this.model = new Funcionario();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Erro em Cadastrar o Funcionário"));
		}
		
	}

	public void carregarDadosParaEditarFuncionario(Funcionario funcionario) {
		this.model = funcionario;
		this.context.executeScript("$('#modal').modal('show')");
	}
	
	public void carregarDadosParaVisualizarFuncionario(Funcionario funcionario)
	{
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("funcionario", funcionario);
			this.model = funcionario;
			this.uploadImagem = false;
			this.modificarCampos = true;
			this.context.executeScript("$('#visualizar').modal('show')");
		
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
	
	public StreamedContent getImageFromDB() {
 
		if (this.model.getImagem() == null) {
			System.out.println("entrou no null \n blah");
			return new DefaultStreamedContent();
		} else {
			System.out.println("entrou no else ahsudhasuid");
			InputStream inputStream = new ByteArrayInputStream(this.model.getImagem());
			StreamedContent streamedContent = new DefaultStreamedContent(inputStream, "image/jpeg");
			
			return streamedContent;
 
//			return new DefaultStreamedContent(new ByteArrayInputStream(this.model.getImagem()),"image/png");
 
		}
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
	
	public void carregarImagem(FileUploadEvent event) {
		
		try {
			InputStream input = event.getFile().getInputstream();
			
			boolean isPng = event.getFile().getFileName().endsWith("png");
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
	        
        } catch (IOException e) {
			e.printStackTrace();
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
	
}
