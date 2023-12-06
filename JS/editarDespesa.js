const form = document.querySelector('#cadastroDespesa');
let despesa = sessionStorage.getItem('despesa');
despesa = JSON.parse(despesa);
let usuario = sessionStorage.getItem('usuario');
usuario = JSON.parse(usuario);

form.addEventListener('submit', (evento) => {
  evento.preventDefault();
  if(despesa.iddespesa != undefined){
      editarDespesa();
  } else {
      cadastrarDespesa();
  }
})

function formatarData(data){
  let dataFormatada = new Date(data),
  dia  = dataFormatada.getDate().toString().padStart(2,'0'),
  mes  = (dataFormatada.getMonth()+1).toString().padStart(2,'0'),
  ano  = dataFormatada.getFullYear();
  return ano+"-"+mes+"-"+dia;
}

function preencherEdicao() {  
  if(despesa != null && despesa != undefined){
    document.querySelector('#descricao').value = despesa.descricao,
    document.querySelector('#dataVencimento').value = formatarData(despesa.dataVencimento),
    document.querySelector('#dataPagamento').value = formatarData(despesa.dataPagamento)
    document.querySelector('#valor').value = despesa.valor
  }   
}
preencherEdicao();

async function editarDespesa(){
  let options = {
      method: "PUT",
      headers: {"Content-type": "application/json"},
      body: JSON.stringify({
          idDespesa: despesa.idDespesa,
          descricao: document.querySelector('#descricao').value,
          dataVencimento: document.querySelector('#dataVencimento').value,
          dataPagamento: document.querySelector('#dataPagamento').value,
          valor: document.querySelector('#valor').value 
      })
  };
  const resultado = await fetch('http://localhost:8080/senhor-financas/rest/despesa/atualizar', options);
  if(resultado.ok == true){
      alert("Atualização realizada com sucesso.");
      sessionStorage.removeItem('despesa');
      despesa = {};
      window.location.href = "../html/despesas.html";
      buscarDespesas();
  } else {
      alert("Houve um problema na atualização da despesa.");
  }
  form.reset();
}

async function cadastrarDespesa () {
let options = {
    method: "POST",
    headers: {"Content-type": "application/json"},
    body: JSON.stringify({
        idDespesa: 0,
        idUsuario: usuario.idUsuario,
        descricao: document.querySelector('#descricao').value,
        valor: document.querySelector('#valor').value,
        dataVencimento: document.querySelector('#dataVencimento').value,
        dataPagamento: document.querySelector('#dataPagamento').value
    })
};
const resultado = await fetch ('http://localhost:8080/senhor-financas/rest/despesa/cadastrar', options);
receita = await resultado.json();
if(receita.id != 0) {
    alert("Despesa cadastrada com sucesso.");
    receita = {};
    window.location.href = "../html/despesas.html";
    buscarReceitas();
}else {
    alert("Erro ao cadastrar uma despesa.");
}
form.reset();
}
