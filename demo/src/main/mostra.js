const toggleButton = document.getElementById("diaEscolhido");
const container = document.getElementById("espaçoContainer");
const conteudoExtra = document.getElementById("espaçoHorarios");
var dia = "";
let horario = "";

toggleButton.addEventListener("click", () => {
    if (conteudoExtra.style.display === "none" || !conteudoExtra.style.display) {
        conteudoExtra.style.display = "block";
        container.classList.add("show-grid");
    } else {
        conteudoExtra.style.display = "none";
        container.classList.remove("show-grid");
    }
     dia = document.getElementById('DD').value;
});


const NaoDisponiveis = ["12:00", "13:00", "14:00"];
const cells = document.querySelectorAll("td");
cells.forEach(cell => {
    if (NaoDisponiveis.includes(cell.dataset.horario)) {
        cell.classList.add("naoPode");
        cell.dataset.usavel = "false";
    } else {
        cell.dataset.usavel = "true";
    }

    cell.addEventListener("click", () => {
        cells.forEach(c => c.classList.remove("selecionado"));
        if (cell.dataset.usavel === "true") {
            cell.classList.add("selecionado");
            horario = cell.dataset.horario;
        }
    });
});

const botao = document.getElementById("envia");
botao.addEventListener("click", () => {
    console.log(dia);
    console.log(horario);
    novoHtmlConfirmação =
    `<h1>Dia: ${dia}</h1>
    <h1>Horário: ${horario}</h1>
    <button class="botaozinho" onclick="confirmar()">Confirmar</button>
    <button class="botaozinho" onclick="cancelar()">Cancelar</button>`;
    var confirmacao = document.getElementById("elementoConfirmação");
    var embaçaTela = document.getElementById("elementoBlur").style.filter = "blur(4px)";
    confirmacao.style.display = "block";
    confirmacao.innerHTML = novoHtmlConfirmação;
});

function confirmar() {
    let textoAgradecimento = `<h1>Obrigado</h1>
    <button class="botaozinho" onclick="cancelar()">Fechar</button>`;
    var confirmacao = document.getElementById("elementoConfirmação");
    confirmacao.innerHTML = textoAgradecimento;
}

function cancelar() {
    var embaçaTela = document.getElementById("elementoBlur");
    embaçaTela.style.filter = "";
    var confirmacao = document.getElementById("elementoConfirmação");
    confirmacao.style.display = "none";
}