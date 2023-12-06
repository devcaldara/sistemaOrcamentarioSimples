package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.RelatorioVO;

public class RelatorioDAO {

	public double somarReceitas(int idusuario, int ano) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		double somaReceitas = 0;
		String query = "SELECT sum(valor) FROM receita WHERE idusuario = " + idusuario + " AND YEAR(datareceita) = " + ano;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				somaReceitas = resultado.getDouble(1);				
			}			
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método somarReceitas.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return somaReceitas;
	}
	
	public double somarDespesas(int idusuario, int ano) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		double somaDespesas = 0;
		String query = "SELECT sum(valor) FROM despesa WHERE idusuario = " + idusuario + " AND YEAR(datavencimento) = " + ano;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				somaDespesas = resultado.getDouble(1);				
			}			
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método somarDespesas.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return somaDespesas;
	}
	
	public RelatorioVO gerarRelatorioDAO(int idusuario, int ano) {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		RelatorioVO relatorio = new RelatorioVO();
		relatorio.setSomaReceitas(relatorioDAO.somarReceitas(idusuario, ano));
		relatorio.setSomaDespesas(relatorioDAO.somarDespesas(idusuario, ano));
		return relatorio;
	}
}