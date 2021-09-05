function solve(arr, element1, element2){
    const index1 = arr.indexOf(element1);
    const index2 = arr.indexOf(element2);

    return arr.slice(index1, index2 + 1);
}