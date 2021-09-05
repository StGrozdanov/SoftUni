function solve(arr) {
    arr.sort((e1, e2) => {
        result = e1.length - e2.length;
        if (result === 0){
            result = e1.toLowerCase().localeCompare(e2.toLowerCase());
        }
        return result;
    })
    .forEach(e => console.log(e));
}

solve(['test', 
'Deny', 
'omen', 
'Default']
);