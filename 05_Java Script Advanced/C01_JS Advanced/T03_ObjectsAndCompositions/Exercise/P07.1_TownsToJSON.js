function solve(arr){
    const data = [];
    for (let i = 1; i < arr.length; i++){
        const [emptySpace, townName, lat, lng] = arr[i].split(/\s*\|\s*/);

        const townInfo = { 
            Town: townName, 
            Latitude: Number(Number(lat).toFixed(2)), 
            Longitude: Number(Number(lng).toFixed(2)) 
        };
        data.push(townInfo);
    }
    return JSON.stringify(data);
}

console.log(solve(['| Town | Latitude | Longitude |',
'| Veliko Turnovo | 43.0757 | 25.6172 |',
'| Monatevideo | 34.50 | 56.11 |']
));