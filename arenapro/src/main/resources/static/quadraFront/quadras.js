const apiQuadrasUrl = 'http://localhost:8080/quadras';

const listaQuadras = document.getElementById('listaQuadras');

const loadingQuadrasDiv = document.getElementById('loadingQuadras');

function carregarQuadras() {
    loadingQuadrasDiv.style.display = 'block';
    listaQuadras.style.display = 'none';

    fetch(apiQuadrasUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar as quadras.');
            }
            return response.json();
        })
        .then(data => {
            loadingQuadrasDiv.style.display = 'none';
            listaQuadras.style.display = 'block';

            if (Array.isArray(data) && data.length > 0) {
                data.forEach(quadra => {
                    const quadraDiv = document.createElement('div');
                    quadraDiv.classList.add('quadra-item');

                    const nomeQuadra = document.createElement('h3');
                    nomeQuadra.classList.add('nome-quadra');
                    nomeQuadra.textContent = quadra.nome;

                    const localizacaoQuadra = document.createElement('p');
                    localizacaoQuadra.classList.add('localizacao-quadra');
                    localizacaoQuadra.textContent = `Localização: ${quadra.localizacao}`;

                    const tipoQuadra = document.createElement('p');
                    tipoQuadra.classList.add('tipo-quadra');
                    tipoQuadra.textContent = `Tipo: ${quadra.tipo}`;

                    const capacidadeQuadra = document.createElement('p');
                    capacidadeQuadra.classList.add('capacidade-quadra');
                    capacidadeQuadra.textContent = `Capacidade: ${quadra.capacidade} pessoas`;

                    const fotoQuadra = document.createElement('div');
                    fotoQuadra.classList.add('foto-quadra');

                    if (quadra.fotoUrl && quadra.fotoUrl.trim() !== "") {
                        const imgQuadra = document.createElement('img');
                        imgQuadra.src = quadra.fotoUrl;
                        imgQuadra.alt = `Foto da quadra ${quadra.nome}`;
                        imgQuadra.style.width = '100%';
                        imgQuadra.style.borderRadius = '10px';
                        fotoQuadra.appendChild(imgQuadra);
                    } else {
                        const semFotoMsg = document.createElement('p');
                        semFotoMsg.textContent = 'Sem foto disponível';
                        semFotoMsg.style.color = '#999';
                        semFotoMsg.style.fontStyle = 'italic';
                        fotoQuadra.appendChild(semFotoMsg);
                    }

                    quadraDiv.appendChild(nomeQuadra);
                    quadraDiv.appendChild(localizacaoQuadra);
                    quadraDiv.appendChild(tipoQuadra);
                    quadraDiv.appendChild(capacidadeQuadra);
                    quadraDiv.appendChild(fotoQuadra);

                    listaQuadras.appendChild(quadraDiv);
                });
            } else {
                listaQuadras.textContent = 'Nenhuma quadra encontrada no momento.';
            }
        })
        .catch(error => {
            loadingQuadrasDiv.style.display = 'none';
            listaQuadras.style.display = 'block';

            console.error('Erro:', error);
            listaQuadras.textContent = 'Ocorreu um erro ao carregar as quadras.';
        });
}

document.addEventListener('DOMContentLoaded', carregarQuadras);