function solve(sequenceLength, previousNElements){
    let arr = [1];
    while (arr.length < sequenceLength) {
        let indexToStartFrom = 0;

        if (previousNElements <= arr.length - 1) {
            indexToStartFrom = arr.length - previousNElements;
        }
        const slicedArr = arr.slice(indexToStartFrom, arr.length);
        const sum = slicedArr.reduce((a, x) => a + x, 0);
        arr.push(sum);
    }
    return arr;
}
    const result = solve(6, 3);
    const result2 = solve(8, 2);
    console.log(result);
    console.log(result2);