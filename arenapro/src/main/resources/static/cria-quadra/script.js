async function criarQuadra(event) {
    event.preventDefault();

    const form = event.target;
    const formData = new FormData(form);

    const quadra = {
        nome: formData.get("nome"),
        localizacao: formData.get("localizacao"),
        tipo: formData.get("tipo"),
        capacidade: parseInt(formData.get("capacidade"), 10),
        fotoUrl: formData.get("fotoUrl"),
        status: true
    };

    console.log("Quadra a ser enviada:", quadra);

    try {
        const token = localStorage.getItem('token'); // Recupera o token do localStorage
        console.log("Token recuperado do localStorage:", token);
        if (!token) {
            throw new Error("Token não encontrado. Faça login novamente.");
        }

        const response = await fetch("http://localhost:8080/quadras", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(quadra)
        });

        if (!response.ok) {
            if (response.status === 403) {
                throw new Error("Acesso negado. Você não tem permissão para criar quadras.");
            } else {
                throw new Error("Erro ao criar a quadra.");
            }
        }

        const result = await response.json();
        const message = document.getElementById("message");
        message.textContent = `Quadra criada com sucesso. ID: ${result.id}`;
        message.style.color = "green";
        form.reset();
    } catch (error) {
        console.error("Erro ao criar quadra:", error);
        const message = document.getElementById("message");
        message.textContent = error.message;
        message.style.color = "red";
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const formQuadra = document.getElementById("formReserva");
    formQuadra.addEventListener("submit", criarQuadra);
});
