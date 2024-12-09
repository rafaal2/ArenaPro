document.getElementById('registroForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const cpf = document.getElementById('cpf').value;
    const senha = document.getElementById('senha').value;
    const telefone = document.getElementById('telefone').value;

    try {
        const response = await fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nome, email, cpf, senha, telefone, reservas: [] }),
        });

        if (response.ok) {
            alert('Usuário registrado com sucesso!');
            window.location.href = './login.html';
        } else {
            const errorData = await response.json();
            alert(errorData.message || 'Falha ao registrar usuário.');
        }
    } catch (error) {
        console.error('Erro ao registrar:', error);
        alert('Erro ao conectar com o servidor. Tente novamente mais tarde.');
    }
});
