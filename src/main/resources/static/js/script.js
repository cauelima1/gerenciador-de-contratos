 window.addEventListener("load", function () {
        const btn = document.querySelector("#generate-pdf");
        const content = document.getElementById("content");

        const clienteEl = document.getElementById("infoCliente");
        const nomeCliente = clienteEl?.dataset.nome || "cliente";
        const numeroContrato = clienteEl?.dataset.contrato || "0000";
          const hoje = new Date();
          const dia = hoje.getDate().toString().padStart(2, '0');
          const mes = (hoje.getMonth() + 1).toString().padStart(2, '0');
          const ano = hoje.getFullYear();

        const nomeArquivo = `${ano}/${numeroContrato}_${nomeCliente}_OrcamentoDroneair.pdf`;



        if (!btn) {
            console.warn("⚠️ Botão com id 'generate-pdf' não foi encontrado.");
            return;
        }

        if (!content) {
            console.error("🚫 Elemento com id 'content' não está presente no DOM. Verifique se ele existe no HTML renderizado.");
            return;
        }

        btn.addEventListener("click", function (e) {
            e.preventDefault();

            setTimeout(() => {
                html2pdf().set({
                    margin: 10,
                    filename: nomeArquivo,
                    image: { type: 'jpeg', quality: 0.98 },
                    html2canvas: { scale: 2 },
                    jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
                }).from(content.innerHTML).save();
            }, 300);
        });
    });


