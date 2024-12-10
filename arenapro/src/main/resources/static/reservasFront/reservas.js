async function carregarReservas() {
    const loadingDiv = document.getElementById("loading");
    const listaPerguntasDiv = document.getElementById("listaPerguntas");

    loadingDiv.style.display = "block";
    listaPerguntasDiv.innerHTML = "";

    try {
        const responseUsuario = await fetch("http://localhost:8080/usuarios/usuario-logado");
        if (!responseUsuario.ok) {
            throw new Error("Erro ao obter o ID do usuário logado");
        }

        const usuarioLogadoId = await responseUsuario.json();

        const responseReservas = await fetch(`http://localhost:8080/reservas?usuarioId=${usuarioLogadoId}`);
        if (!responseReservas.ok) {
            throw new Error("Erro ao carregar reservas");
        }

        const reservas = await responseReservas.json();

        if (reservas.length === 0) {
            listaPerguntasDiv.innerHTML = "<p>Nenhuma reserva encontrada.</p>";
        } else {
            reservas.forEach(reserva => {
                const itemDiv = document.createElement("div");
                itemDiv.className = "pergunta-item";

                const textoDiv = document.createElement("div");
                textoDiv.className = "texto-pergunta";
                textoDiv.textContent = `Quadra: ${reserva.quadraNome}`;

                const dataDiv = document.createElement("div");
                dataDiv.className = "data-criacao";
                dataDiv.textContent = `Data: ${reserva.data} - Horário: ${reserva.horarioInicio}`;

                const statusDiv = document.createElement("div");
                statusDiv.className = "data-criacao";
                statusDiv.textContent = `Status: ${reserva.status}`;

                itemDiv.appendChild(textoDiv);
                itemDiv.appendChild(dataDiv);
                itemDiv.appendChild(statusDiv);

                listaPerguntasDiv.appendChild(itemDiv);
            });
        }
    } catch (error) {
        console.error("Erro ao carregar reservas:", error);
        listaPerguntasDiv.innerHTML = "<p>Erro ao carregar reservas.</p>";
    } finally {
        loadingDiv.style.display = "none";
    }
}

document.addEventListener("DOMContentLoaded", () => {
    carregarReservas();
});
