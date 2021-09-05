function solve(arr){
    return arr
            .filter((e, i) => i % 2 ==! 0)
            .map(e => e * 2)
            .reverse()
            .join(' ');
}

const arr2 = solve([3, 0, 10, 4, 7, 3]);
console.log(arr2);