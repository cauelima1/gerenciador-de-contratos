function editarContrato(contract) {
  fetch(`/edit/${contract}`)
    .then(response => response.text())
    .then(html => {
      document.getElementById("modalContent").innerHTML = html;
      document.getElementById("modalEdicao").style.display = "block";
    })
    .catch(error => {
      alert("Erro ao carregar o formulário de edição.");
      console.error(error);
    });
}

function fecharModal() {
  document.getElementById("modalEdicao").style.display = "none";
}


function deletarContrato(contract) {
    if (confirm(`Deseja realmente deletar o contrato ${contract}?`)) {
        fetch(`/deletar/${contract}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert("Contrato deletado com sucesso!");
                location.reload(); // Atualiza a página
            } else {
                alert("Erro ao deletar contrato.");
            }
        });
    }
}

function generatePdf(contract) {
    fetch('/gerar-pdf-existente', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ contratoId: contratoId })
    })
    .then(response => {
        if (!response.ok) throw new Error("Erro ao gerar PDF");
        return response.blob();
    })
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = "contrato-" + contratoId + ".pdf";
        a.click();
        window.URL.revokeObjectURL(url);
    })
    .catch(err => console.error(err));
}
