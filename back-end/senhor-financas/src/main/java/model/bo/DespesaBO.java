package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.DespesaDAO;
import model.vo.DespesaVO;

public class DespesaBO {

	public DespesaVO cadastrarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if(despesaVO.getValor() <= 0) {
			System.out.println("\nO valor da despesa deve ser maior que zero.");
		} else {
			if(despesaVO.getDataPagamento() != null && despesaVO.getDataPagamento().isAfter(LocalDate.now())) {
				System.out.println("Data de pagamento não pode ser maior que a atual.");
			} else {
				despesaVO = despesaDAO.cadastrarDespesaDAO(despesaVO);
			}
		}
		return despesaVO;
	}
	
	public ArrayList<DespesaVO> listarTodasDespesasBO(int idUsuario){
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> listaDespesas = despesaDAO.listarTodasDespesasDAO(idUsuario);		
		if(listaDespesas.isEmpty()) {
			System.out.println("Não há despesas cadastradas.");
		} 
		return listaDespesas;
	}
	
	public DespesaVO buscarDespesaBO(int iddespesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		return despesaDAO.buscarDespesaDAO(iddespesa);
	}
	
	public Boolean atualizarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO receitaDAO = new DespesaDAO();
		if(despesaVO.getValor() <= 0) {
			System.out.println("\nO valor da despesa deve ser maior que zero.");
		}
		return receitaDAO.atualizarDespesaDAO(despesaVO);
	}
	
	public Boolean excluirDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		return despesaDAO.excluirDespesaDAO(despesaVO);
	}
}
