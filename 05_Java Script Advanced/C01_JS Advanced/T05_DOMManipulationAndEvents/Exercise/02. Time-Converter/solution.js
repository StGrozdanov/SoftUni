function attachEventsListeners() {

    const attributeRatios = {
        days: 1,
        hours: 24,
        minutes: 1440,
        seconds: 86400
    }

    const daysInputElement = document.getElementById('days');
    const hoursInputElement = document.getElementById('hours');
    const minutesInputElement = document.getElementById('minutes');
    const secondsInputElement = document.getElementById('seconds');

    const daysButtonElement = document.getElementById('daysBtn');
    const hoursButtonElement = document.getElementById('hoursBtn');
    const minutesButtonElement = document.getElementById('minutesBtn');
    const secondsButtonElement = document.getElementById('secondsBtn');  

    daysButtonElement.addEventListener('click', (e) => {
        convertAttributes(daysInputElement.value, 'days');
    });
    hoursButtonElement.addEventListener('click', (e) => {
        convertAttributes(hoursInputElement.value, 'hours');
    });
    minutesButtonElement.addEventListener('click', (e) => {
        convertAttributes(minutesInputElement.value, 'minutes');
    });
    secondsButtonElement.addEventListener('click', (e) => {
        convertAttributes(secondsInputElement.value, 'seconds');
    });


    function convertAttributes(value, attribute){
        const inDays = value / attributeRatios[attribute];

        const attributes = {
            days: inDays,
            hours: inDays * attributeRatios.hours,
            minutes: inDays * attributeRatios.minutes,
            seconds: inDays * attributeRatios.seconds,
    }
        daysInputElement.value = attributes.days;
        hoursInputElement.value = attributes.hours;
        minutesInputElement.value = attributes.minutes;
        secondsInputElement.value = attributes.seconds;
    }
}