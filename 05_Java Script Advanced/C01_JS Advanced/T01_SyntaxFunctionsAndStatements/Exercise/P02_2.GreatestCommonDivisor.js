function findTheGreatestCommonDivisor(firstNum, secondNum){
    let biggestNum = Math.max(firstNum, secondNum);
    let smallestNum = Math.min(firstNum, secondNum);

    let greatestCommonDivisor = 1;

    for(let i = 2; i <= smallestNum; i++){
        if (biggestNum % i === 0 && smallestNum % i === 0){
            greatestCommonDivisor = i;
            break;
        }
    }

    console.log(greatestCommonDivisor);
}

findTheGreatestCommonDivisor(15, 5);
findTheGreatestCommonDivisor(2154, 458);