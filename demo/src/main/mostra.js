const toggleButton = document.getElementById("diaEscolhido");
const container = document.getElementById("espaçoContainer");
const conteudoExtra = document.getElementById("espaçoHorarios");

toggleButton.addEventListener("click", () => {
    if (conteudoExtra.style.display === "none" || !conteudoExtra.style.display) {
        conteudoExtra.style.display = "block";
        container.classList.add("show-grid");
    } else {
        conteudoExtra.style.display = "none";
        container.classList.remove("show-grid");
    }
});

const cells = document.querySelectorAll("td");
let horario = "";

// Lista de horários não disponíveis
const NaoDisponiveis = ["12:00", "13:00", "14:00"];

// Marcar células como não disponíveis
cells.forEach(cell => {
    // Verifica se a célula está na lista de não disponíveis
    if (NaoDisponiveis.includes(cell.dataset.horario)) {
        cell.classList.add("naoPode");
        cell.dataset.usavel = "false";
    } else {
        cell.dataset.usavel = "true";
    }

    // Adiciona evento de clique
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
    console.log(horario);
});
