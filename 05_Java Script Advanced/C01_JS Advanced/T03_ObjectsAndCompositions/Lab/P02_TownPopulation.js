function solve(arr){
    let registry = [];

    for (let tokens of arr){
        const args = tokens.split(' <-> ');
        let town = args[0];
        let population = Number(args[1]);

        let element = registry.filter(e => e.location == town);

        if (element.length === 0){
            let data = {
                location: town,
                people: population
            };
            registry.push(data);
        } else {
            element.forEach(e => e.people += population);
        }
    }
    for (const data of registry) {
        console.log(data.location + ' : ' + data.people);   
    }
}

solve(['Sofia <-> 1200000',
'Montana <-> 20000',
'Montana <-> 20000',
'New York <-> 10000000',
'Washington <-> 2345000',
'Las Vegas <-> 1000000']
);

