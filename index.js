$("#DD").flatpickr({
    minDate: "today",
    maxDate: "dd/mm/2025",
    //enableTime: true,
    "disable": [
        function(date) {
            // return true to disable
            return (date.getDay() === 0 || date.getDay() === 6);

        }
    ],
    "locale": {
        "firstDayOfWeek": 0 // start week on Monday
    },
   // minuteIncrement: "30",
    //time_24hr: "true",
});


$("#AA").flatpickr({
    
    enableTime: true,
    noCalendar: true,
    dateFormat: "H:i",
    minTime: "16:00",
    maxTime: "22:30",

});
