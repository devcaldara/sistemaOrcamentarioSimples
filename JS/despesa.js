const form = document.querySelector('#cadastrodespesa');
const btnCadastrar = document.querySelector('#cadastrar');
let despesa = {};

function formatarData(data){
    let dataFormatada = new Date(data),
    dia  = dataFormatada.getDate().toString().padStart(2,'0'),
    mes  = (dataFormatada.getMonth()+1).toString().padStart(2,'0'),
    ano  = dataFormatada.getFullYear();
    return dia+"/"+mes+"/"+ano;
}

async function buscarDespesas(){
  let options = {
      method: "GET",
      headers: {"Content-type": "application/json"}
  };
  const listaDespesas = await fetch('http://localhost:8080/senhor-financas/rest/despesa/listar', options);
  const listaDespesasJson = await listaDespesas.json();
  if(listaDespesasJson.length != 0){
      preencherTabela(listaDespesasJson);
  } else {
      alert("Houve um problema na busca das despesas.");
  }
}

form.addEventListener('submit', (evento) => {
    evento.preventDefault();
    if(pessoa.id != undefined){
        atualizardespesa();
    } else {
        cadastrardespesa();
    }
})

async function cadastrarDespesa () {
  let options = {
      method: "POST",
      headers: {"Content-type": "application/json"},
      body: JSON.stringify({
          idDespesa: 0,
          descricao: document.querySelector('#descricao').value,
          valor: document.querySelector('#valor').value,
          dataVencimento: document.querySelector('#dataVencimento').value,
          dataPagamento: document.querySelector('#dataPagamento').value
      })
  };
  const resultado = await fetch ('http://localhost:8080/senhor-financas/rest/despesa/cadastrar', options);
  despesa = await resultado.json();
  if(despesa.idDespesa != 0) {
      alert("Despesa cadastrada com sucesso.");
      despesa = {};
      window.location.href = "./html/despesas.html";
      buscarDespesas();
  }else {
      alert("Erro ao cadastrar a despesa.");
  }
  form.reset();
}

async function atualizarDespesa(){
    let options = {
        method: "PUT",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({
          idDespesa: despesa.idDespesa,
          descricao: document.querySelector('#descricao').value,
          valor: document.querySelector('#valor').value,
          dataVencimento: document.querySelector('#dataVencimento').value,
          dataPagamento: document.querySelector('#dataPagamento').value
        })
    };
    const resultado = await fetch('http://localhost:8080/senhor-financas/rest/despesa/atualizar', options);
    if(resultado.ok == true){
        alert("Atualização realizada com sucesso.");
        despesa = {};
        window.location.href = "./html/despesas.html";
        buscarDespesas();
    } else {
        alert("Houve um problema na atualização da despesa.");
    }
    form.reset();
}

function preencherTabela(dados){
    let tbody = document.getElementById('tbody');
    
    tbody.innerText = '';
    for(let i = 0; i < dados.length; i++){
        let tr = tbody.insertRow();
        let td_id = tr.insertCell();
        let td_descricao = tr.insertCell();
        let td_dataVencimento = tr.insertCell();
        let td_dataPagamento = tr.insertCell();
        let td_valor = tr.insertCell();
        let td_acoes = tr.insertCell();

        td_id.innerText = dados[i].idDespesa;
        td_descricao.innerText = dados[i].descricao;
        td_dataVencimento.innerText = formatarData(dados[i].dataVencimento);
        td_dataPagamento.innerText = formatarData(dados[i].dataPagamento);
        td_valor.innerText = dados[i].valor;

        let editar = document.createElement('button');
        editar.textContent = 'Editar';
        editar.style.fontFamily = "Kalam, cursive";
        editar.style.padding = "3px 30px";
        editar.style.margin = "10px 0";
        editar.style.border = "0.5px solid #112D4E";
        editar.setAttribute('onclick', 'editardespesa('+JSON.stringify(dados[i])+')');
        td_acoes.appendChild(editar);

        let excluir = document.createElement('button');
        excluir.textContent = 'Excluir';
        editar.style.fontFamily = "Kalam, cursive";
        editar.style.padding = "3px 30px";
        editar.style.margin = "10px 0";
        editar.style.border = "0.5px solid #112D4E";
        excluir.setAttribute('onclick', 'excluirdespesa('+JSON.stringify(dados[i])+')');
        td_acoes.appendChild(excluir);  
    }
}

async function excluirdespesa(dados){
    let options = {
        method: "DELETE",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({
            iddespesa: dados.iddespesa,
            idUsuario: dados.idUsuario,
            descricao: dados.descricao,
            data: dados.data,
            valor: dados.valor 
        })
    };
    const resultado = await fetch('http://localhost:8080/senhor-financas/rest/despesa/excluir', options);
    if(resultado.ok == true){
        alert("Exclusão realizada com sucesso.");
        despesa = {};
        buscarDespesas
      ();
    } else {
        alert("Houve um problema na exclusão da despesa.");
    }
}