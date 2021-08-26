function calculateSum(N, M){
    let number1 = Number(N);
    let number2 = Number(M);
    let sum = 0;

    for (let i = number1; i <= number2; i++) {
        sum += i;
    }

    return sum;
}

let result1 = calculateSum('1', '5');
let result2 = calculateSum('-8', '20');

console.log(result1);
console.log(result2);