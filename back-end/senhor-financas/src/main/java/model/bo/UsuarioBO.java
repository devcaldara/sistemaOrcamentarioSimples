package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarCadastroUsuarioBaseDadosDAO(usuarioVO)) {
			System.out.println("\nUsuario já cadastrada na base de Dados.");
		} else {			
			usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		return usuarioVO;
	}
	
	public UsuarioVO realizarLoginBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();		
		return usuarioDAO.realizarLoginDAO(usuarioVO);
	}
}
