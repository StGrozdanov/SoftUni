function checkSpeed(speed, area){
    let allowedSpeed;
    switch(area){
        case 'motorway': 
        allowedSpeed = 130;
        break;
        case 'interstate': 
        allowedSpeed = 90;
        break;
        case 'city': 
        allowedSpeed = 50;
        break;
        case 'residential':
        allowedSpeed = 20;    
        break;
    }
    if (allowedSpeed - speed >= 0){
        console.log(`Driving ${speed} km/h in a ${allowedSpeed} zone`)
    } else {
        const speeding = speed - allowedSpeed;
        console.log(`The speed is ${speeding} km/h faster than the allowed speed of ${allowedSpeed} - ${speedPenalty(speeding)}`);
    }

    function speedPenalty(speeding){
        if (speeding <= 20){
            return 'speeding'
        } else if (speeding <= 40){
            return 'excessive speeding'
        } else {
            return 'reckless driving'
        }
    }
}

checkSpeed(40, 'city');
checkSpeed(21, 'residential');
checkSpeed(120, 'interstate');
checkSpeed(200, 'motorway');