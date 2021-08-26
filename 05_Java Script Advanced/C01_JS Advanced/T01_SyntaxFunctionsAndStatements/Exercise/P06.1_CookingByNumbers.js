function solve(num, ...operations){
    let number = Number(num);

    const functions = {
        'chop': (x) => x / 2,
        'dice': (x) => Math.sqrt(x),
        'spice': (x) => x + 1,
        'bake': (x) => x * 3, 
        'fillet': (x) => x * 0.8
    };

    for (let cmd of operations) {
        const result = functions[cmd](number);
        console.log(result);
        number = result;
    }
}

solve('32', 'chop', 'chop', 'chop', 'chop', 'chop');
solve('9', 'dice', 'spice', 'chop', 'bake', 'fillet');