function solve(arr){
    const result = {};

    for (let i = 0; i < arr.length-1; i+=2) {
        const food = arr[i];
        const calorie = arr[i + 1];
        result[food] = Number(calorie);
    }
    console.log(result);
}

solve(['Potato', '93', 'Skyr', '63', 'Cucumber', '18', 'Milk', '42']);