export async function getWeather(input) {
    const code = await (await fetch(`http://localhost:3030/jsonstore/forecaster/locations`)).json();
    let target = code.find(re => re.name == input);
    return target.code;
}

export async function getCurrWeather(code) {
    return await (await fetch(`http://localhost:3030/jsonstore/forecaster/today/${code}`)).json();
}

export async function getExpectedWeather(code) {
    return await (await fetch(`http://localhost:3030/jsonstore/forecaster/upcoming/${code}`)).json();
}