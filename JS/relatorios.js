async function gerarRelatorio(ano){
  let options = {
      method: "GET",
      headers: {"Content-type": "application/json"}
  };
  const relatorio = await fetch('http://localhost:8080/senhor-financas/rest/relatorio', options);
  const relatorioJson = await relatorio.json();
  
}