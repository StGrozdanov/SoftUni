function findSmallestTwoNumbers(arr){
    const smallestNum = arr.reduce((a, x) => a < x ? a : x);

    if (arr.length > 1) {
        const removedIndex = arr.indexOf(smallestNum);
        arr.splice(removedIndex, 1);
        const secondSmallestNum = arr.reduce((a, x) => a < x ? a : x);

        console.log(smallestNum + ' ' + secondSmallestNum);
    } else {
        console.log(smallestNum);
    }
}

findSmallestTwoNumbers([30, 15, 50, 5]);
findSmallestTwoNumbers([3, 0, 10, 4, 7, 3]);