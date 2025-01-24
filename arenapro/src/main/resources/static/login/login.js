document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    try {
        const response = await fetch('http://localhost:8080/usuarios/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, senha }),
        });

        if (response.ok) {
            const token = await response.text();
            console.log("Token recebido:", token);
            localStorage.setItem('token', token);
            console.log("Token armazenado no Local Storage:", localStorage.getItem("token"));
            alert('Login bem-sucedido!');
            window.location.href = '../home/home.html';
        } else {
            const errorData = await response.text();
            console.error("Erro no login:", errorData);
            alert("Login falhou. Verifique suas credenciais.");
        }
    } catch (error) {
        console.error('Erro ao conectar com o servidor:', error);
        alert('Erro ao conectar com o servidor. Tente novamente mais tarde.');
    }
});


document.getElementById('btnRegistro').addEventListener('click', function () {
    window.location.href = '../registro/registro.html';
});
