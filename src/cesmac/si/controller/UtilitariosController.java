package cesmac.si.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean(name= "utilitarios")
public class UtilitariosController {
	private List<SelectItem> listaUfs;
	private String nomeUF;
	
	public String retirarMascara(String valorComMascara)
	{
		return valorComMascara.replaceAll("\\D*","");
	}
	
	public String[] retirarMascaraTelefone(String telefoneComMascara)
	{
		String[] retorno = new String[2]; 
		String ddd; 
		String telefone;
		
		ddd = telefoneComMascara.substring(telefoneComMascara.indexOf("(")+1, telefoneComMascara.indexOf(")"));
		telefone = telefoneComMascara.substring(telefoneComMascara.indexOf(")") + 2).replaceAll("-", "");
		
		retorno[0] = ddd;
		retorno[1] = telefone;
		
		return retorno;
	}
	
	public String putMascara(String valorSemMascara, String tipoMascara)
	{
		String valorComMascara = "";
		int j = 0;
		
		for (int i = 0; i < tipoMascara.length(); i++) 
		{
			if(tipoMascara.charAt(i) == '#')
			{
				valorComMascara += valorSemMascara.charAt(j);
				j++;
			}
			else
			{
				valorComMascara += tipoMascara.charAt(i);
			}
		}
		
		
		return valorComMascara;
	}

	private List<String> getUFs()
	{
		List<String> uf = new ArrayList<>();
		uf.add("AC");
		uf.add("AL");
		uf.add("AM");
		uf.add("AP");
		uf.add("BA");
		uf.add("CE");
		uf.add("DF");
		uf.add("ES");
		uf.add("GO");
		uf.add("MA");
		uf.add("MG");
		uf.add("MS");
		uf.add("MT");
		uf.add("PA");
		uf.add("PB");
		uf.add("PE");
		uf.add("PI");
		uf.add("PR");
		uf.add("RJ");
		uf.add("RN");
		uf.add("RO");
		uf.add("RR");
		uf.add("RS");
		uf.add("SC");
		uf.add("SE");
		uf.add("SP");
		uf.add("TO");
		
		return uf;
	}

	public List<SelectItem> getListaUfs() {
		
		this.listaUfs = new ArrayList<>();
		
		List<String> ufs = getUFs();
		
		SelectItem item;
		
		for (String listaUFs : ufs) {
			
			item = new SelectItem(listaUFs);
			this.listaUfs.add(item);
		}
		
		return listaUfs;
	}

	public String getNomeUF() {
		return nomeUF;
	}

	public void setNomeUF(String nomeUF) {
		this.nomeUF = nomeUF;
	}
	
}
