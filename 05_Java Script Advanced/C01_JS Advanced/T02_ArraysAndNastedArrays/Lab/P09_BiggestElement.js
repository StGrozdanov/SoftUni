function findBiggestElement(matrix){
    const allElements = matrix.toString().split(',').map(Number);
    return Math.max.apply(null, allElements);
}

const biggest1 = findBiggestElement([[20, 50, 10],[8, 33, 145]]);
const biggest2 = findBiggestElement([[3, 5, 7, 12],
    [-1, 4, 33, 2],
    [8, 3, 0, 4]]
   )

console.log(biggest2);