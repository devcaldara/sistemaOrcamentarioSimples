package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.DespesaVO;

public class DespesaDAO {

	public DespesaVO cadastrarDespesaDAO(DespesaVO despesaVO) {
		String query = "INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, despesaVO.getIdUsuario());
			pstmt.setString(2, despesaVO.getDescricao());
			pstmt.setDouble(3, despesaVO.getValor());
			pstmt.setObject(4, despesaVO.getDataVencimento());
			if(despesaVO.getDataPagamento() != null) {
				pstmt.setObject(5, despesaVO.getDataPagamento());				
			} else {
				pstmt.setObject(5, null);
			}
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys(); 
			if(resultado.next()) {
				despesaVO.setIdDespesa(resultado.getInt(1));
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método cadastrarDespesaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return despesaVO;
	}
	
	public ArrayList<DespesaVO> listarTodasDespesasDAO(int usuario){
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<DespesaVO> listaDespesas = new ArrayList<DespesaVO>();
		String query = "SELECT iddespesa, idusuario, descricao, valor, datavencimento, datapagamento FROM despesa WHERE idusuario = " + usuario;
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				DespesaVO despesa = new DespesaVO();
				despesa.setIdDespesa(resultado.getInt(1));
				despesa.setIdUsuario(resultado.getInt(2));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
				despesa.setDataVencimento(LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				if(resultado.getObject(6) != null) {
					despesa.setDataPagamento(LocalDate.parse(resultado.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}				
				listaDespesas.add(despesa);
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método listarTodasDespesasDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaDespesas;
	}
	
	public DespesaVO buscarDespesaDAO(int idDespesa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		DespesaVO despesa = new DespesaVO();
		String query = "SELECT iddespesa, idusuario, descricao, valor, datavencimento, datapagamento FROM despesa WHERE iddespesa = " + idDespesa;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {				
				despesa.setIdDespesa(resultado.getInt(1));
				despesa.setIdUsuario(resultado.getInt(2));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
				despesa.setDataVencimento(LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				if(resultado.getObject(6) != null) {
					despesa.setDataPagamento(LocalDate.parse(resultado.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}								
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método buscarDespesaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return despesa;
	}
	
	public Boolean atualizarDespesaDAO(DespesaVO despesaVO) {
		boolean retorno = false;
		String query = "UPDATE despesa SET descricao = '" + despesaVO.getDescricao() + "', valor = " + despesaVO.getValor() +
				", datavencimento = '" + despesaVO.getDataVencimento() + "', datapagamento = '" + despesaVO.getDataPagamento() + 
				"' WHERE iddespesa = " + despesaVO.getIdDespesa();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, query);
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método atualizarDespesaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	public Boolean excluirDespesaDAO(DespesaVO despesaVO) {
		boolean retorno = false;
		String query = "DELETE FROM despesa WHERE iddespesa = " + despesaVO.getIdDespesa();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método excluirDespesaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
}
