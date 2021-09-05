function solve(arr){
    let data = [];
    for (let i = 1; i < arr.length; i++){
        const reg = /\|\s?(?<town>[A-Za-z]+\s?[A-Za-z]*)\s?\|\s?(?<latitude>\d+.?\d*)\s?\|\s?(?<longitude>\d+.?\d*)\s?\|/;
        const tokens = arr[i].match(reg);

        const town = tokens[1].trim();
        const latitude = Number(Number(tokens[2]).toFixed(2));
        const longitude = Number(Number(tokens[3]).toFixed(2));
        
        const townInfo = { Town: town, Latitude: latitude, Longitude: longitude };
        data.push(townInfo);
    }
    return JSON.stringify(data);
}
console.log(solve(['| Town | Latitude | Longitude |',
'| Veliko Turnovo | 43.0757 | 25.6172 |',
'| Monatevideo | 34.50 | 56.11 |']
));