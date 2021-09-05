function solve(arr, N){
    for (let i = 0; i < N; i++) {
        const element = arr.pop();
        arr.unshift(element);      
    }
    const result = arr.join(' ');
    console.log(result);
}

solve(['Banana', 
'Orange', 
'Coconut', 
'Apple'], 
15
);

