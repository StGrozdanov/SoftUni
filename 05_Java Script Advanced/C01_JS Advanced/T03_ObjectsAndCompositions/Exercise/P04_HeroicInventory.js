function solve(arr){
    const heroData = [];

    for (let input of arr){
        const tokens = input.split(' / ');
        const heroName = tokens[0];
        const heroLevel = Number(tokens[1]);
        const heroItems = tokens.length > 2 ? tokens[2].split(', ') : [];

        let hero = { name: heroName, level: heroLevel, items: heroItems };

        heroData.push(hero);
    }
    console.log(JSON.stringify(heroData));
}

solve(['Isacc / 25 / Apple, GravityGun',
'Derek / 12 / BarrelVest, DestructionSword',
'Hes / 1']
);

solve(['Jake / 1000 / Gauss, HolidayGrenade']);