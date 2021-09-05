function solve(arr){
    const result = [];

    for (let element of arr){
        const tokens = element.split(' : ');
        const product = tokens[0];
        const price = Number(tokens[1]);
        
        const group = product[0];
        const target = result.find(e => e.group == group);
        if (target){
            target.products.push({name: product, price: price});
        } else {
            const sortedProduct = { group: group, products: [{ name: product, price: price }] };
            result.push(sortedProduct);
        }
    }
    //inner sort
    result.forEach(e => e.products.sort((a, b) => a.name.localeCompare(b.name)));
    //outer sort and print
    result
        .sort((a, b) => a.group.localeCompare(b.group))
        .forEach(e => {
        console.log(e.group);
        e.products.forEach(p => console.log('  ' + p.name + ': ' + p.price));
            });
}

solve(['Appricot : 20.4',
'Fridge : 1500',
'TV : 1499',
'Deodorant : 10',
'Boiler : 300',
'Apple : 1.25',
'Anti-Bug Spray : 15',
'T-Shirt : 10']
);