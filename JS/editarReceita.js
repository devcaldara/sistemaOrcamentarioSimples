const form = document.querySelector('#cadastroReceita');
let receita = sessionStorage.getItem('receita');
receita = JSON.parse(receita);
let usuario = sessionStorage.getItem('usuario');
usuario = JSON.parse(usuario);

form.addEventListener('submit', (evento) => {
  evento.preventDefault();
  if(receita.idReceita != undefined){
      editarReceita();
  } else {
      cadastrarReceita();
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
  if(receita != null && receita != undefined){
    document.querySelector('#descricao').value = receita.descricao,
    document.querySelector('#dataReceita').value = formatarData(receita.data),
    document.querySelector('#valor').value = receita.valor
  }   
}
preencherEdicao();

async function editarReceita(){
  let options = {
      method: "PUT",
      headers: {"Content-type": "application/json"},
      body: JSON.stringify({
          idReceita: receita.idReceita,
          descricao: document.querySelector('#descricao').value,
          data: document.querySelector('#dataReceita').value,
          valor: document.querySelector('#valor').value 
      })
  };
  const resultado = await fetch('http://localhost:8080/senhor-financas/rest/receita/atualizar', options);
  if(resultado.ok == true){
      alert("Atualização realizada com sucesso.");
      sessionStorage.removeItem('receita');
      receita = {};
      window.location.href = "../html/receitas.html";
      buscarReceitas();
  } else {
      alert("Houve um problema na atualização da receita.");
  }
  form.reset();
}

async function cadastrarReceita () {
let options = {
    method: "POST",
    headers: {"Content-type": "application/json"},
    body: JSON.stringify({
        idReceita: 0,
        idUsuario: usuario.idUsuario,
        descricao: document.querySelector('#descricao').value,
        valor: document.querySelector('#valor').value,
        data: document.querySelector('#dataReceita').value
    })
};
const resultado = await fetch ('http://localhost:8080/senhor-financas/rest/receita/cadastrar', options);
receita = await resultado.json();
if(receita.id != 0) {
    alert("Receita cadastrada com sucesso.");
    receita = {};
    window.location.href = "../html/receitas.html";
    buscarReceitas();
}else {
    alert("Erro ao cadastrar uma receita.");
}
form.reset();
}