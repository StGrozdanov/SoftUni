function checkSpeed(speed, area){
    const limit = {motorway: 130, interstate: 90, city: 50, residential: 20};
    let overSpeedType = '';

    if (speed > limit[area] + 40){
            overSpeedType = 'reckless driving';
    } else if (speed > limit[area] + 20){
            overSpeedType = 'excessive speeding';
    } else if (speed > limit[area]){
            overSpeedType = 'speeding';
    } else {
            return console.log(`Driving ${speed} km/h in a ${limit[area]} zone`);
    }
    return console.log(`The speed is ${speed - limit[area]} km/h faster than the allowed speed of ${limit[area]} - ${overSpeedType}`);
}

checkSpeed(40, 'city');
checkSpeed(21, 'residential');
checkSpeed(120, 'interstate');
checkSpeed(200, 'motorway');