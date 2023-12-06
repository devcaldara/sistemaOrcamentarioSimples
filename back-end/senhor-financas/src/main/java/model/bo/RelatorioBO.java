package model.bo;

import model.dao.RelatorioDAO;
import model.vo.RelatorioVO;

public class RelatorioBO {

	public RelatorioVO gerarRelatorioBO(int idusuario, int ano) {
		RelatorioDAO relatorio = new RelatorioDAO();
		return relatorio.gerarRelatorioDAO(idusuario, ano);
	}
}
