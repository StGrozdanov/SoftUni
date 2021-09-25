function solve(arr, startIndex, endIndex){
    try {
        if (startIndex < 0){
            startIndex = 0;
        }
        if (endIndex > arr.length - 1){
            endIndex = arr.length - 1;
        }
        let sum = 0;
        for (let i = startIndex; i <= endIndex; i++) {
            const number = Number(arr[i]);
            sum += number;
        }
        return sum;
    } catch(ex) {
        if (typeof startIndex != Number || typeof endIndex != Number){
            return 'NaN';
        }
    }
}

console.log(solve([10, 20, 30, 40, 50, 60], 3, 300));

console.log(solve([1.1, 2.2, 3.3, 4.4, 5.5], -3, 1));

console.log(solve([10, 'twenty', 30, 40], 0, 2));

console.log(solve([], 1, 2));

console.log(solve('text', 0, 2));