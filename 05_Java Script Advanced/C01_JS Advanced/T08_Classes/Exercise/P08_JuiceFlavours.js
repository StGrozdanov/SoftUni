function solve(arr){
    let juiceBottles = new Map();

    arr.forEach(juice => {
        const tokens = juice.split(' => ');
        const juiceType = tokens[0];
        const juiceQuantity = Number(tokens[1]);
        
        if (!juiceBottles.has(juiceType)){
            juiceBottles.set(juiceType, juiceQuantity);
        } else {
            const newQuantity = juiceBottles.get(juiceType) + juiceQuantity;
            juiceBottles.set(juiceType, newQuantity);
        }
    });

    for (const juice of juiceBottles.keys()) {
        let juiceQuantity = juiceBottles.get(juice);
        if (juiceQuantity >= 1000){
            juiceQuantity /= 1000;
            juiceQuantity = Math.floor(juiceQuantity);
            console.log(juice + ' => ' + juiceQuantity);
        }
    }
}

solve(['Kiwi => 234',
'Pear => 2345',
'Watermelon => 3456',
'Kiwi => 4567',
'Pear => 5678',
'Watermelon => 6789']
);
