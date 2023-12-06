package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.vo.UsuarioVO;


public class UsuarioDAO {

	public boolean verificarCadastroUsuarioBaseDadosDAO(UsuarioVO usuarioVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT cpf FROM usuario WHERE cpf = '" + usuarioVO.getCpf() + "'";
		try {
			resultado = stmt.executeQuery(query); 
			if(resultado.next()) { 
				retorno = true;
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método verificarCadastroPessoaBaseDadosDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;		
	}
	
	public UsuarioVO cadastrarUsuarioDAO(UsuarioVO usuarioVO) {
		String query = "INSERT INTO usuario (nome, cpf, email, datanascimento, login, senha) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, usuarioVO.getNome());
			pstmt.setString(2, usuarioVO.getCpf());
			pstmt.setString(3, usuarioVO.getEmail());
			pstmt.setObject(4, usuarioVO.getDataNascimento());//???
			pstmt.setString(5, usuarioVO.getLogin());
			pstmt.setString(6, usuarioVO.getSenha());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys(); 
			if(resultado.next()) {
				usuarioVO.setidUsuario(resultado.getInt(1));
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método cadastrarPessoaDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return usuarioVO;
	}
	
	public UsuarioVO realizarLoginDAO(UsuarioVO usuarioVO) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);        
        ResultSet resultado = null;
        UsuarioVO usuario = new UsuarioVO();
        String query = "SELECT idusuario, nome, cpf, email, datanascimento, login, senha FROM usuario WHERE login = '" 
        		+ usuarioVO.getLogin() + "' AND senha = '" + usuarioVO.getSenha() + "'";
        try{
            resultado = stmt.executeQuery(query);
            if(resultado.next()){
                usuario = new UsuarioVO();
                usuario.setidUsuario(resultado.getInt(1));;
                usuario.setNome(resultado.getString(2));
                usuario.setCpf(resultado.getString(3));
                usuario.setEmail(resultado.getString(4));
                if(resultado.getString(5) != null) {
                    usuario.setDataNascimento(LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                usuario.setLogin(resultado.getString(6));
                usuario.setSenha(resultado.getString(7));
            }
        } catch (Exception erro){
            System.out.println("Erro ao executar a query do método realizarLoginDAO.");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }
        return usuario;
    }
}
