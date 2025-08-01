function obterDataFormatada() {
  const hoje = new Date();
  const dia = hoje.getDate().toString().padStart(2, '0');
  const mes = (hoje.getMonth() + 1).toString().padStart(2, '0');
  const ano = hoje.getFullYear();
  return `${dia}/${mes}/${ano}`;
}

document.addEventListener("DOMContentLoaded", () => {
  const campoData = document.getElementById("data-formatada");
  if (campoData) {
    campoData.textContent = obterDataFormatada();
  }
});
