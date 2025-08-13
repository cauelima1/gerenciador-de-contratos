//// Verifica se o token existe ao carregar a página
//function verificarAutenticacao() {
//  const token = localStorage.getItem("token");
//  if (!token) {
//    alert("Você precisa estar logado.");
//    window.location.href = "/login.html";
//  }
//}
//
//// Requisição protegida usando o token
//async function buscarDadosProtegidos() {
//  const token = localStorage.getItem("token");
//
//  const response = await fetch("http://localhost:8080/index", {
//    method: "GET",
//    headers: {
//      "Authorization": `Bearer ${token}`
//    }
//  });
//
//  if (response.status === 401) {
//    alert("Sessão expirada. Faça login novamente.");
//    window.location.href = "/login.html";
//    return;
//  }
//
//  const dados = await response.json();
//  console.log(dados); // ou atualiza a interface com os dados
//}
//
//// Logout
//function logout() {
//  localStorage.removeItem("token");
//  window.location.href = "/login.html";
//}
//
//// Chamar a verificação ao carregar a página
//verificarAutenticacao();
//function getToken() {
//  return localStorage.getItem('token') || null;
//}
//
//function isTokenExpired(token) {
//  const [, payloadB64] = token.split('.');
//  const { exp } = JSON.parse(atob(payloadB64));
//  return exp * 1000 < Date.now();
//}
//
//function isAuthenticated() {
//  const token = getToken();
//  if (!token) return false;
//  return !isTokenExpired(token);
//}