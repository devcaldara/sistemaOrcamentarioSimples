const btnEntrar = document.querySelector('#entrar');

async function realizarLogin() {
  let options = {
    method: "POST",
    headers: {"Content-type": "application/json"},
    body: JSON.stringify({
      login: document.querySelector("#login-input").value,
      senha: document.querySelector("#senha").value
    })
  };
  const resposta = await fetch('http://localhost:8080/senhor-financas/rest/usuario/login', options);
  const usuarioLogado = await resposta.json();
  if(usuarioLogado.idUsuario !=0) {
    sessionStorage.setItem("usuario", JSON.stringify(usuarioLogado)); // session só guarda string
    window.location.href = "../html/telaInicial.html"
  } else {
    alert("Login ou senha incorretos.")
    document.querySelector("#senha").value = "";
  }
}

btnEntrar.addEventListener("click", (evento) => {
  evento.preventDefault();
  realizarLogin();
});