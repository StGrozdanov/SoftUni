function solve(town, population, treasury){
    let cityRecord = {};

    cityRecord.name = town;
    cityRecord.population = population;
    cityRecord.treasury = treasury;

    return cityRecord;
}

const record = solve('Santo Domingo',
12000,
23500
);

console.log(record);