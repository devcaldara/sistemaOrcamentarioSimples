const btnCadastrar = document.querySelector('#cadastrar');
let despesa = {};
let usuario = sessionStorage.getItem('usuario');
usuario = JSON.parse(usuario);

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
  const listaDespesas = await fetch('http://localhost:8080/senhor-financas/rest/despesa/listar/' + usuario.idUsuario, options);
  const listaDespesasJson = await listaDespesas.json();
  if(listaDespesasJson.length != 0){
      preencherTabela(listaDespesasJson);
  } else {
      alert("Houve um problema na busca das despesas.");
  }
}

async function cadastrarDespesa () {
window.location.href =  "../html/cadastroDespesas.html";
}

async function editarDespesa(dados){
    sessionStorage.setItem("despesa", JSON.stringify(dados));
    window.location.href = "../html/cadastroDespesas.html"
}

function preencherTabela(dados){
    let tbody = document.getElementById('tbody');
    
    tbody.innerText = '';
    let acumulador = 0;
    for(let i = 0; i < dados.length; i++){
        let tr = tbody.insertRow();
        let td_id = tr.insertCell();
        let td_descricao = tr.insertCell();
        let td_data = tr.insertCell();
        let td_valor = tr.insertCell();
        let td_acoes = tr.insertCell();
        acumulador += dados[i].valor;

        td_id.innerText = dados[i].id;
        td_descricao.innerText = dados[i].descricao;
        td_data.innerText = formatarData(dados[i].data);
        td_valor.innerText = dados[i].valor;

        let editar = document.createElement('button');
        editar.textContent = 'Editar';
        editar.style.fontFamily = "Kalam, cursive";
        editar.style.padding = "3px 30px";
        editar.style.marginRight = "4px";
        editar.style.border = "0.5px solid #112D4E";
        editar.setAttribute('onclick', 'editarReceita('+JSON.stringify(dados[i])+')');
        td_acoes.appendChild(editar);

        let excluir = document.createElement('button');
        excluir.textContent = 'Excluir';
        editar.style.fontFamily = "Kalam, cursive";
        editar.style.padding = "3px 30px";
        editar.style.border = "0.5px solid #112D4E";
        excluir.setAttribute('onclick', 'excluirReceita('+JSON.stringify(dados[i])+')');
        td_acoes.appendChild(excluir);  
    }
    document.querySelector('#total').innerText = acumulador.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
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
        buscarDespesas();
    } else {
        alert("Houve um problema na exclusão da despesa.");
    }
}

function limparTabela(){
    let tbody = document.getElementById('tbody');
    tbody.innerHTML = '';
}
