async function carregarQuadras() {
    const quadraSelect = document.getElementById("quadra");

    try {
        const response = await fetch("http://localhost:8080/quadras");
        if (!response.ok) {
            throw new Error("Erro ao carregar as quadras");
        }

        const quadras = await response.json();
        quadras.forEach(quadra => {
            const option = document.createElement("option");
            option.value = quadra.id;
            option.textContent = quadra.nome;
            quadraSelect.appendChild(option);
        });
    } catch (error) {
        console.error("Erro ao carregar quadras:", error);
        const message = document.getElementById("message");
        message.textContent = "Erro ao carregar quadras.";
    }
}

async function criarReserva(event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);

    const reserva = {
        quadraId: formData.get("quadra"),
        data: formData.get("data"),
        horarioInicio: formData.get("horarioInicio"),
        duracao: formData.get("duracao"),
        status: formData.get("status")
    };

    console.log("Reserva a ser enviada:", reserva);

    try {
        const response = await fetch("http://localhost:8080/reservas", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reserva)
        });

        if (!response.ok) {
            throw new Error("Erro ao criar a reserva");
        }

        const result = await response.json();
        const message = document.getElementById("message");
        message.textContent = `Reserva criada com sucesso. ID: ${result.id}`;
        form.reset();
    } catch (error) {
        console.error("Erro ao criar reserva:", error);
        const message = document.getElementById("message");
        message.textContent = "Erro ao criar reserva.";
    }
}

document.addEventListener("DOMContentLoaded", () => {
    carregarQuadras();

    const formReserva = document.getElementById("formReserva");
    formReserva.addEventListener("submit", criarReserva);
});
