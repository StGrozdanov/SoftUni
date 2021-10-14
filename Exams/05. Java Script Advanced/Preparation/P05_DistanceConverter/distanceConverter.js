function attachEventsListeners() {
    const inputFieldElement = document.querySelector('#inputDistance');
    const convertButtonElement = document.querySelector('#convert');
    const inputUnitsElement = document.querySelector('#inputUnits');
    const outputElement = document.querySelector('#outputDistance');
    const outputUnitsElement = document.querySelector('#outputUnits');

    const conversionRatesFromMeter = {
        km: 1000,
        m: 1,
        cm: 0.01,
        mm: 0.001,
        mi: 1609.34,
        yrd: 0.9144,
        ft: 0.3048,
        in: 0.0254,
    }

    convertButtonElement.addEventListener('click', () => {
        let convertFrom = inputUnitsElement.value;
        let convertTo = outputUnitsElement.value;

        let valueInMeters = inputFieldElement.value * conversionRatesFromMeter[convertFrom];
        let convertedValue = valueInMeters / conversionRatesFromMeter[convertTo];

        outputElement.value = convertedValue;
    });
}