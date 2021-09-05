function solve(arr, N){
    let newArray = [];
    for (let i = 0; i < arr.length; i+=N) {
        const element = arr[i];
        newArray.push(element);
    }
    return newArray;
}

const arr = solve(['1', 
'2',
'3', 
'4', 
'5'], 
6
);

console.log(arr);