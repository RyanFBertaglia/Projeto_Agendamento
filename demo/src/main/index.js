flatpickr("#DD", {
    minDate: "today",
    maxDate: "dd/mm/2025",
    "disable": [
        function(date) {
            return (date.getDay() === 0 || date.getDay() === 6);

        }
    ],
    "locale": {
        "firstDayOfWeek": 0
    },
});

/*.flatpickr-months {
    padding-top: 30px;
  }

  .flatpickr-innerContainer {
    padding: 100 30px 30px;
  }*/

document.getElementById('diaEscolhido').addEventListener('click', function() {
    const valor = document.getElementById('DD').value;
    console.log(valor);
});