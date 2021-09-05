function solve(arr){
    let result = [];
    let biggestElement = -1000000;
    for(let element of arr) {
        if (element >= biggestElement){
            result.push(element);
            biggestElement = element;
        }
    }
    return result;
}

