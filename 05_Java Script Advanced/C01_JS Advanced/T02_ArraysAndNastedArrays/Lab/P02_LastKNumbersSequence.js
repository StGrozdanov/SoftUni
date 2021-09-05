function solve(sequenceLength, previousNElements){
    let arr = [];

    if (sequenceLength <= 0){
        return arr; 
    } else if (sequenceLength <= 2){
        for (let i = 1; i <= sequenceLength; i++) {
            arr.push(i);
        }
        return arr;
    } else {
        arr = [1, 1];

        while (arr.length < sequenceLength){
        let indexToGoTo = arr.length - 1;

        let sum = 0;
        let counter = 0;
        while (indexToGoTo >= 0 && counter < previousNElements) {
            sum += arr[indexToGoTo];
            indexToGoTo--;
            counter++;
        }
        arr.push(sum);
    }
        return arr;
    }
}
    const result2 = solve(8, 2);
    console.log(result2);