function solve(arr) {
    let oddIndexArr = [];
    for (let i = 0; i < arr.length; i++) {
        if (i % 2 !== 0){
            oddIndexArr.push(arr[i]);
        }
    }
    return oddIndexArr.map(e => e * 2).reverse();
}

const arr2 = solve([3, 0, 10, 4, 7, 3]);

console.log(arr1);
console.log(arr2);