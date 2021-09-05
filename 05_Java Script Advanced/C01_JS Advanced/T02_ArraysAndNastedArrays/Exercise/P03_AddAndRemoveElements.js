function solve(arr){ 
    let nextNum = 0;
    let newArr = [];
    for (let i = 0; i < arr.length; i++) {
        nextNum += 1;
        if (arr[i] === 'add'){
            newArr.push(nextNum);
        } else if (arr[i] === 'remove'){
            newArr.pop();
        }       
    }
    if (newArr.length === 0) {
        console.log('Empty');
    }
    newArr.forEach(e => console.log(e));
}

solve(['remove', 
'remove', 
'remove']
);