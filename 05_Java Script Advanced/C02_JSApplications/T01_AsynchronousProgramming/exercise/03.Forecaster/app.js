import * as data from './data.js';
import * as weather from './weatherGenerator.js';

const inputField = document.getElementById('location');
const button = document.getElementById('submit');
const currentWeatherField = document.getElementById('forecast');
const currentWeather = document.getElementById('current');
const uppcomingWeatherField = document.getElementById('upcoming');

const status = {
    Sunny: '☀',
    PartlySunny: '⛅',
    Overcast: '☁',
    Rain: '☂',
    degrees: '°'
}

button.addEventListener('click', clickHandler);

async function clickHandler() {
    currentWeatherField.style.display = 'block';
    uppcomingWeatherField.style.display = 'block';
    
    //handle errors
    let code;
    try {
        code = await data.getWeather(inputField.value);
    } catch (e) {
        inputField.value = 'Error';
        currentWeatherField.style.display = 'none';
        uppcomingWeatherField.style.display = 'none';
    }

    //generate database response
    const currDayWeather = await data.getCurrWeather(code);
    const upcomingWeather = await data.getExpectedWeather(code);

    //current day weather
    const majorDiv = weather.generateCurrentWeatherData(currDayWeather, status);
    currentWeather.replaceChild(majorDiv, currentWeather.childNodes[2]);

    //upcoming days weather
    const upcomingMajorDiv = document.createElement('div');
    upcomingMajorDiv.classList.add('forecast-info');

    const day1 = weather.generateUpcomingWeatherData(0, upcomingWeather, status);
    const day2 = weather.generateUpcomingWeatherData(1, upcomingWeather, status);
    const day3 = weather.generateUpcomingWeatherData(2, upcomingWeather, status);

    upcomingMajorDiv.appendChild(day1);
    upcomingMajorDiv.appendChild(day2);
    upcomingMajorDiv.appendChild(day3);

    uppcomingWeatherField.replaceChild(upcomingMajorDiv, uppcomingWeatherField.childNodes[2]);

    //clear input field
    inputField.value = '';
}