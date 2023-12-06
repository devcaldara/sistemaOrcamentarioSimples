package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.ReceitaVO;

public class ReceitaDAO {

	public ReceitaVO cadastrarReceitaDAO(ReceitaVO receitaVO) {
		String query = "INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, receitaVO.getIdUsuario());
			pstmt.setString(2, receitaVO.getDescricao());
			pstmt.setDouble(3, receitaVO.getValor());
			pstmt.setObject(4, receitaVO.getData());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys(); 
			if(resultado.next()) {
				receitaVO.setIdReceita(resultado.getInt(1));
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método cadastrarReceitaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return receitaVO;
	}
	
	public ArrayList<ReceitaVO> listarTodasReceitasDAO(int usuario){
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ReceitaVO> listaReceitas = new ArrayList<ReceitaVO>();
		String query = "SELECT idreceita, idusuario, descricao, valor, datareceita FROM receita WHERE idusuario = " + usuario;
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ReceitaVO receita = new ReceitaVO();
				receita.setIdReceita(resultado.getInt(1));
				receita.setIdUsuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setData(LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				listaReceitas.add(receita);
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método listarTodasReceitasDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaReceitas;
	}
	
	public ReceitaVO buscarReceitaDAO(int idReceita) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ReceitaVO receita = new ReceitaVO();
		String query = "SELECT idreceita, idusuario, descricao, valor, datareceita FROM receita WHERE idreceita = " + idReceita;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				receita.setIdReceita(resultado.getInt(1));
				receita.setIdUsuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setData(LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));				
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método buscarReceitaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return receita;
	}
	
	public Boolean atualizarReceitaDAO(ReceitaVO receitaVO) {
		boolean retorno = false;
		String query = "UPDATE receita SET descricao = '" + receitaVO.getDescricao() + "', valor = " + receitaVO.getValor() +
				", datareceita = '" + receitaVO.getData() + "' WHERE idreceita = " + receitaVO.getIdReceita();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, query);
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método atualizarReceitaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	public Boolean excluirReceitaDAO(ReceitaVO receitaVO) {
		boolean retorno = false;
		String query = "DELETE FROM receita WHERE idreceita = " + receitaVO.getIdReceita();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método excluirReceitaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
}