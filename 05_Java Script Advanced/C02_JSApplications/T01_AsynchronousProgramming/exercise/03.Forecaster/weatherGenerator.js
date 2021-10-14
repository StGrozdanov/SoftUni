export function generateUpcomingWeatherData(day, upcomingWeather, status) {
    const minorSpan = document.createElement('span');
    minorSpan.classList.add('upcoming');
    const locationSpan = document.createElement('span');
    locationSpan.classList.add('symbol');
    let weather = upcomingWeather.forecast[Number(day)].condition;
    weather == 'Partly sunny' ? weather = 'PartlySunny' : weather;
    locationSpan.textContent = status[weather];
    const tempSpan = document.createElement('span');
    tempSpan.classList.add('forecast-data');
    tempSpan.textContent = `${upcomingWeather.forecast[day].low}/${upcomingWeather.forecast[day].high}`;
    const conditionSpan = document.createElement('span');
    conditionSpan.classList.add('forecast-data');
    weather == 'PartlySunny' ? weather = 'Partly sunny' : weather;
    conditionSpan.textContent = weather;

    minorSpan.appendChild(locationSpan);
    minorSpan.appendChild(tempSpan);
    minorSpan.appendChild(conditionSpan);

    return minorSpan;
}

export function generateCurrentWeatherData(currDay, status) {
    const region = currDay.name;
    let regionWeather = currDay.forecast.condition;
    regionWeather == 'Partly sunny' ? regionWeather = 'PartlySunny' : regionWeather;
    const regionMinTemp = currDay.forecast.low + status.degrees;
    const regionMaxTemp = currDay.forecast.high + status.degrees;

    const majorDiv = document.createElement('div');
    majorDiv.classList.add('forecasts');

    const majorSpan = document.createElement('span');
    majorSpan.classList.add('condition');
    majorSpan.classList.add('symbol');
    majorSpan.textContent = status[regionWeather];

    const minorSpan = document.createElement('span');
    minorSpan.classList.add('condition');

    const locationSpan = document.createElement('span');
    locationSpan.classList.add('forecast-data');
    locationSpan.textContent = region;

    const tempSpan = document.createElement('span');
    tempSpan.classList.add('forecast-data');
    tempSpan.textContent = `${regionMinTemp}/${regionMaxTemp}`;

    const conditionSpan = document.createElement('span');
    conditionSpan.classList.add('forecast-data');
    regionWeather == 'PartlySunny' ? regionWeather = 'Partly sunny' : regionWeather;
    conditionSpan.textContent = regionWeather;

    minorSpan.appendChild(locationSpan);
    minorSpan.appendChild(tempSpan);
    minorSpan.appendChild(conditionSpan);

    majorDiv.appendChild(majorSpan);
    majorDiv.appendChild(minorSpan);

    return majorDiv;
}