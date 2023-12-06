package model.bo;

import java.util.ArrayList;

import model.dao.ReceitaDAO;
import model.vo.ReceitaVO;

public class ReceitaBO {

	public ReceitaVO cadastrarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if(receitaVO.getValor() <= 0) {
			System.out.println("\nO valor da receita deve ser maior que zero.");
		} else {			
			receitaVO = receitaDAO.cadastrarReceitaDAO(receitaVO);
		}
		return receitaVO;
	}
	
	public ArrayList<ReceitaVO> listarTodasReceitasBO(int idUsuario){
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ArrayList<ReceitaVO> listaReceitas = receitaDAO.listarTodasReceitasDAO(idUsuario);		
		if(listaReceitas.isEmpty()) {
			System.out.println("Não há receitas cadastradas.");
		} 
		return listaReceitas;
	}
	
	public ReceitaVO buscarReceitaBO(int idreceita) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		return receitaDAO.buscarReceitaDAO(idreceita);
	}
	
	public Boolean atualizarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if(receitaVO.getValor() <= 0) {
			System.out.println("\nO valor da receita deve ser maior que zero.");
		}
		return receitaDAO.atualizarReceitaDAO(receitaVO);
	}
	
	public Boolean excluirReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		return receitaDAO.excluirReceitaDAO(receitaVO);
	}
}