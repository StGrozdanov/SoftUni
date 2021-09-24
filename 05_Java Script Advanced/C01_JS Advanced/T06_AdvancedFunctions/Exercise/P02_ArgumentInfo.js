function solve(...arguments){
    let data = {};
    
    arguments.forEach(a => {
        let elementType = typeof a;
        if (!data.hasOwnProperty(elementType)){
            data[elementType] = 0;
        }
        data[elementType]++;
        console.log(elementType + ': ' + a);
    });
    
    Object.keys(data).sort((a, b) => a-b).forEach(key => console.log(`${key} = ${data[key]}`));
}

solve('cat', 42, function () { console.log('Hello world!'); });
solve(`-`.repeat(40));
solve( { name: 'bob'}, { name: 'pesho'}, 3.333, 9.999, 'dog');