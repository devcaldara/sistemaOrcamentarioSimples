package model.vo;

public class RelatorioVO {

	private double somaReceitas;
	private double somaDespesas;
	
	public RelatorioVO(double somaReceitas, double somaDespesas) {
		super();
		this.somaReceitas = somaReceitas;
		this.somaDespesas = somaDespesas;
	}

	public RelatorioVO() {
		super();
	}

	public double getSomaReceitas() {
		return somaReceitas;
	}

	public void setSomaReceitas(double somaReceitas) {
		this.somaReceitas = somaReceitas;
	}

	public double getSomaDespesas() {
		return somaDespesas;
	}

	public void setSomaDespesas(double somaDespesas) {
		this.somaDespesas = somaDespesas;
	}		
}