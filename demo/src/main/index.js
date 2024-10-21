flatpickr("#DD", {
    minDate: "today",
    maxDate: "dd/mm/2025",
    allowInput: true,
    "disable": [
        function(date) {
            return (date.getDay() === 0 || date.getDay() === 6);

        }
    ],
    "locale": {
        "firstDayOfWeek": 0
    },
    inline: true
});




document.getElementById('diaEscolhido').addEventListener('click', function(){
    const valor = document.getElementById('DD').value;
    document.getElementById("resultado").innerHTML = valor;
    console.log(valor);
});




window.addEventListener('load', function() {
    const calendar = document.querySelector('.flatpickr-calendar');
    if (calendar){
        calendar.classList.add('large'); // Aplica a classe que aumenta o tamanho
    }
});