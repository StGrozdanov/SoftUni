function solve(arr){
    let sum = 0;

    if (arr.length > 1){
        const firstElement = Number(arr.shift());
        const secondElement = Number(arr.pop());
        sum = firstElement + secondElement;
    } else if (arr.length === 1) {
        sum = arr.pop();
    }

    return sum;
}

console.log(solve(['20', '30', '40']));
console.log(solve(['5', '10']));