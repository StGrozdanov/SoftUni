function calculateWalkingTime(numberOfSteps, footPrintLength, speedKmPerHour){
    //every 500m rest with 1 minute break
    const totalMetersWalked = numberOfSteps * footPrintLength;
    const totalKilometersWalked = totalMetersWalked / 1000;
    const totalTimeRestingInSeconds = Math.floor(totalMetersWalked / 500) * 60; 

    const walkingTimeInSeconds = (totalKilometersWalked / speedKmPerHour) * 3600;
    
    let totalWalkingTimeInSeconds = walkingTimeInSeconds + totalTimeRestingInSeconds;

    let hours = Math.floor(totalWalkingTimeInSeconds / 3600);
    totalWalkingTimeInSeconds -= hours * 3600;
    let minutes = Math.floor(totalWalkingTimeInSeconds / 60);
    totalWalkingTimeInSeconds -= minutes * 60;
    let seconds = Number(totalWalkingTimeInSeconds.toFixed(0));

    hours = timeFormat(hours);
    minutes = timeFormat(minutes);
    seconds = timeFormat(seconds);
    
    console.log(`${hours}:${minutes}:${seconds}`);

    function timeFormat(param){
        const paramAsString = '' + param;

        if (param < 10){
           return 0 + paramAsString;
        }
        return paramAsString;
    }
}

calculateWalkingTime(4000, 0.60, 5);
calculateWalkingTime(2564, 0.70, 5.5);