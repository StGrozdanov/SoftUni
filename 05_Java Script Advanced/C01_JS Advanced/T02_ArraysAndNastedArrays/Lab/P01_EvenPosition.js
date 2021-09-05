function findEvenIndexes(arr){
    const evenIndexArr = arr
                            .filter(e => arr.indexOf(e) % 2 == 0)
                            .join(' ');
                            
    console.log(evenIndexArr);
}

findEvenIndexes(['20', '30', '40', '50', '60']);
findEvenIndexes(['5', '10']);