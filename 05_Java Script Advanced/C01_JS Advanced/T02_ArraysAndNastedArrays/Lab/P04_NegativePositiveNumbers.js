function solve(arr){
    let negativeNumArr = arr.filter(e => e < 0).sort((a, b) => b-a);
    let positiveNumArr = arr.filter(e => e >= 0);
    let result = negativeNumArr.concat(positiveNumArr);

    console.log(result);
}

solve([7, -2, 8, 9]);
solve([3, -2, 0, -1]);