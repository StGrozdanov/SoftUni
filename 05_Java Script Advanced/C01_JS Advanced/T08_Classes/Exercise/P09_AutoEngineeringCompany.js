function solve(arr){
    let carData = new Map();

    arr.forEach(e => {
        const tokens = e.split(' | ');
        const carBrand = tokens[0];
        const carModel = tokens[1];
        const producedCars = Number(tokens[2]);
        
        if (!carData.has(carBrand)){
            carData.set(carBrand, new Map());
        }
        if (!carData.get(carBrand).has(carModel)){
            carData.get(carBrand).set(carModel, producedCars);
        } else {
            const newValue = carData.get(carBrand).get(carModel) + producedCars;
            carData.get(carBrand).set(carModel, newValue);
        }
    });

    for (const brand of carData.keys()) {
        console.log(brand);
        carData.get(brand).forEach((quantity, model) => console.log(`###${model} -> ${quantity}`));
    }
}

solve(['Audi | Q7 | 1000',
'Audi | Q6 | 100',
'BMW | X5 | 1000',
'BMW | X6 | 100',
'Citroen | C4 | 123',
'Volga | GAZ-24 | 1000000',
'Lada | Niva | 1000000',
'Lada | Jigula | 1000000',
'Citroen | C4 | 22',
'Citroen | C5 | 10',
'Citroen | C5 | 12']
);