function solve(arr){
    let newArrSize = 0;

    if (arr.length % 2 === 0){
        newArrSize = (arr.length / 2) + 1;
    } else {
        newArrSize = Math.ceil(arr.length / 2);
    }
    arr.sort((a, b) => a - b);    
    const secondHalfArr = arr.slice(newArrSize - 1, arr.length);

    return secondHalfArr;
}