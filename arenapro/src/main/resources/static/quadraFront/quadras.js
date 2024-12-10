// URL da API
const apiQuadrasUrl = 'http://localhost:8080/quadras';

// Seleciona o elemento onde as quadras serão exibidas
const listaQuadras = document.getElementById('listaQuadras');

// Seleciona o elemento do indicador de carregamento
const loadingQuadrasDiv = document.getElementById('loadingQuadras');

// Função para buscar e exibir as quadras
function carregarQuadras() {
    // Mostrar o indicador de carregamento
    loadingQuadrasDiv.style.display = 'block';
    // Esconder a lista de quadras enquanto carrega
    listaQuadras.style.display = 'none';

    fetch(apiQuadrasUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar as quadras.');
            }
            return response.json();
        })
        .then(data => {
            // Esconder o indicador de carregamento
            loadingQuadrasDiv.style.display = 'none';
            // Mostrar a lista de quadras
            listaQuadras.style.display = 'block';

            if (Array.isArray(data) && data.length > 0) {
                data.forEach(quadra => {
                    // Cria o elemento div para cada quadra
                    const quadraDiv = document.createElement('div');
                    quadraDiv.classList.add('quadra-item');

                    // Cria o elemento para o nome da quadra
                    const nomeQuadra = document.createElement('h3');
                    nomeQuadra.classList.add('nome-quadra');
                    nomeQuadra.textContent = quadra.nome;

                    // Cria o elemento para a localização
                    const localizacaoQuadra = document.createElement('p');
                    localizacaoQuadra.classList.add('localizacao-quadra');
                    localizacaoQuadra.textContent = `Localização: ${quadra.localizacao}`;

                    // Cria o elemento para o tipo
                    const tipoQuadra = document.createElement('p');
                    tipoQuadra.classList.add('tipo-quadra');
                    tipoQuadra.textContent = `Tipo: ${quadra.tipo}`;

                    // Cria o elemento para a capacidade
                    const capacidadeQuadra = document.createElement('p');
                    capacidadeQuadra.classList.add('capacidade-quadra');
                    capacidadeQuadra.textContent = `Capacidade: ${quadra.capacidade} pessoas`;

                    // Adiciona os elementos ao div da quadra
                    quadraDiv.appendChild(nomeQuadra);
                    quadraDiv.appendChild(localizacaoQuadra);
                    quadraDiv.appendChild(tipoQuadra);
                    quadraDiv.appendChild(capacidadeQuadra);

                    // Adiciona o div da quadra à lista
                    listaQuadras.appendChild(quadraDiv);
                });
            } else {
                listaQuadras.textContent = 'Nenhuma quadra encontrada no momento.';
            }
        })
        .catch(error => {
            // Esconder o indicador de carregamento
            loadingQuadrasDiv.style.display = 'none';
            // Mostrar a lista de quadras
            listaQuadras.style.display = 'block';

            console.error('Erro:', error);
            listaQuadras.textContent = 'Ocorreu um erro ao carregar as quadras.';
        });
}

// Chama a função para carregar as quadras ao carregar a página
document.addEventListener('DOMContentLoaded', carregarQuadras);
