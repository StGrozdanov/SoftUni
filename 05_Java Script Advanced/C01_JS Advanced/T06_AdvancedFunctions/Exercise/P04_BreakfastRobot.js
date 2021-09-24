function solution(){
    let ingridients = {protein: 0, carbohydrate: 0, fat: 0, flavour: 0, order: ['protein', 'carbohydrate', 'fat', 'flavour']};
    let recepies = {
        apple: {carbohydrate: 1, flavour: 2, order: ['carbohydrate', 'flavour']},
        lemonade: {carbohydrate: 10, flavour: 20, order: ['carbohydrate', 'flavour']},
        burger: {carbohydrate: 5, fat: 7, flavour: 3, order: ['carbohydrate', 'fat', 'flavour']},
        eggs: {protein: 5, fat: 1, flavour: 1, order: ['protein', 'fat', 'flavour']},
        turkey: {protein: 10, carbohydrate: 10, fat: 10, flavour: 10, order: ['protein', 'carbohydrate', 'fat', 'flavour']}
    };  

    function restock(microelement, quantity){
        ingridients[microelement] += quantity;
        return 'Success';
    }

    function prepare(recipe, quantity){
        for (const element of recepies[recipe].order) {
            let requiredStock = recepies[recipe][element] * quantity;
            let availableStock = ingridients[element];

            if (requiredStock > availableStock){
                return `Error: not enough ${element} in stock`;
            } else {
                availableStock -= requiredStock;
                ingridients[element] = availableStock;
            }
        }
        return 'Success';
    }

    function report(){
        let result = '';
        for (const element of ingridients.order){
            result += `${element}=${ingridients[element]} `;
        }
        return result.trim();
    }

    function cmdHandler(arguments){
        const tokens = arguments.split(' ');
        const cmd = tokens[0];
        const quantity = Number(tokens[2]);

        switch(cmd){
            case 'restock': 
            return restock(tokens[1], quantity);
            case 'prepare': 
            return prepare(tokens[1], quantity);
            case 'report': 
            return report();
        }
    }
    return cmdHandler; 
}

let manager = solution (); 
console.log(manager('restock flavour 50')); 
console.log(manager('prepare turkey 1')); 
console.log(manager('prepare lemonade 4 ')); 
console.log(manager('restock carbohydrate 10')); 
console.log(manager('restock flavour 10')); 
console.log(manager('prepare apple 1')); 
console.log(manager('restock fat 10')); 
console.log(manager('prepare burger 1')); 
console.log(manager('report')); 
