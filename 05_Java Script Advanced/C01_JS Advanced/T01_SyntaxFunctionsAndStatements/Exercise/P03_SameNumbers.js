function testDigits(number){
    const numberAsString = number.toString();
    
    let digitsSum = 0;
    let allDigitsAreEqual = true;

    for (let i = 0; i < numberAsString.length; i++) {
        const digit = Number(numberAsString[i]);
        if (i !== numberAsString.length-1 && digit !== Number(numberAsString[i+1])){
            allDigitsAreEqual = false;
        }
        digitsSum += digit;
    }

    console.log(allDigitsAreEqual);
    console.log(digitsSum);
}

testDigits(2222222);
testDigits(1234);