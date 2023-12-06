const form = document.querySelector('#cadastroUsuario');
const btnCadastrar = document.querySelector('#cadastrar');
let usuario = {};

async function cadastrarUsuario(){
  let options = {
      method: "POST",
      headers: {"Content-type": "application/json"},
      body: JSON.stringify({
        idUsuario: 0,
        nome: document.querySelector('#nome').value,
        cpf: document.querySelector('#cpf').value,
        email: document.querySelector('#email').value,
        dataNascimento: document.querySelector('#nascimento').value,
        login: document.querySelector('#login').value,
        senha: document.querySelector('#senha').value
      })
  };
  const resultado = await fetch('http://localhost:8080/senhor-financas/rest/usuario/cadastrar', options);
  usuario = await resultado.json();
  if(usuario.idUsuario != 0) {
      alert("Cadastro realizado com sucesso.");
      usuario = {};
      window.location.href = "../html/index.html";
  } else {
      alert("Houve um problema no cadastro da pessoa.");
    }  
    form.reset();
}

btnCadastrar.addEventListener("click", (evento) => {
  evento.preventDefault();
  cadastrarUsuario();
});