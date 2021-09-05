function carFactory(clientRequirement){
    const engineTypes = [
        { type: 'Small Engine', power: 90, volume: 1800 },
        { type: 'Meidum Engine', power: 120, volume: 2400 },
        { type: 'Sport Engine', power: 200, volume: 3500 },
    ];

    const carriageTypes = [
        {type: 'hatchback', color: clientRequirement.color},
        {type: 'coupe', color: clientRequirement.color}
    ]

    let producedCar = {};

    producedCar.model = clientRequirement.model;
    producedCar.engine = engineTypes.find(engine => engine.power >= clientRequirement.power);
    producedCar.carriage = carriageTypes.find(carriage => carriage.type == clientRequirement.carriage);
    const wheels = clientRequirement.wheelsize % 2 === 0 ? clientRequirement.wheelsize - 1 : clientRequirement.wheelsize;
    producedCar.wheels = [wheels, wheels, wheels, wheels];

    return producedCar;
}

console.log(carFactory({ model: 'VW Golf II',
power: 90,
color: 'blue',
carriage: 'hatchback',
wheelsize: 14 }
));

console.log(carFactory({ model: 'Opel Vectra',
power: 110,
color: 'grey',
carriage: 'coupe',
wheelsize: 17 }
));