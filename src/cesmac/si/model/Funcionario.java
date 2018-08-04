package cesmac.si.model;

public class Funcionario extends Pessoa {

	private String cargo;
	private Double salario;
	private int qtd_horas;
	
	public int getQtd_horas() {
		return qtd_horas;
	}
	public void setQtd_horas(int qtd_horas) {
		this.qtd_horas = qtd_horas;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
}
