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
                statusDiv.className = "status-reserva";
                statusDiv.textContent = `Status: ${reserva.status}`;

                const deleteButton = document.createElement("button");
                deleteButton.className = "delete-button";
                deleteButton.textContent = "Excluir";
                deleteButton.addEventListener("click", () => deletarReserva(reserva.id));

                itemDiv.appendChild(textoDiv);
                itemDiv.appendChild(dataDiv);
                itemDiv.appendChild(statusDiv);
                itemDiv.appendChild(deleteButton);

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

async function deletarReserva(id) {
    if (!confirm("Tem certeza de que deseja excluir esta reserva?")) {
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/reservas/${id}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error("Erro ao excluir a reserva.");
        }

        alert("Reserva excluída com sucesso!");
        carregarReservas();
    } catch (error) {
        console.error("Erro ao excluir reserva:", error);
        alert("Não foi possível excluir a reserva. Tente novamente mais tarde.");
    }
}

document.addEventListener("DOMContentLoaded", () => {
    carregarReservas();
});
