function solve(arr){
    arr.sort((a, b) => a > b ? 1 : a < b ? -1 : 0);

    const middleArrIndex = Math.ceil(arr.length / 2);
    let biggest = arr.slice(middleArrIndex).reverse();
    let smallest = arr.slice(0, middleArrIndex);

    let result = [];
    for (let i = 0; i < middleArrIndex; i++) {
        result.push(smallest[i], biggest[i]);
    }
    return result;
}

console.log(solve([1, 65, 3, 52, 48, 63, 31, -3, 18, 56]));
// console.log(solve([1, 3, 65]));