package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.bo.RelatorioBO;
import model.vo.RelatorioVO;

@Path("/relatorio")
public class RelatorioRest {

	@GET
	@Path("/{idusuario}/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public RelatorioVO gerarRelatorioController(@PathParam("idusuario") int idusuario, @PathParam("ano") int ano) {
		RelatorioBO relatorio = new RelatorioBO();
		return relatorio.gerarRelatorioBO(idusuario, ano);
	}
}