class ChristmasDinner {
    constructor(budget) {
        this.budget = budget;
        this.dishes = [];
        this.products = [];
        this.guests = {};
    }
    set budget(number) {
        if (typeof number != 'number' || number === NaN) {
            return;
        }
        if (number < 0) {
            throw new Error('The budget cannot be a negative number');
        }
        this._budget = number;
    }
    get budget() {
        return this._budget;
    }
    shopping(list) {
        let product = list[0];
        let price = list[1];

        if (price > this.budget) {
            throw new Error('Not enough money to buy this product');
        }
        this.products.push(product);
        this.budget -= price;
        return `You have successfully bought ${product}!`;
    }
    recipes(recipe) {
        let requiredProducts = recipe.productsList;
        if (requiredProducts.some(p => !this.products.includes(p))) {
            throw new Error('We do not have this product');
        }

        this.dishes.push(recipe);
        return `${recipe.recipeName} has been successfully cooked!`
    }
    inviteGuests(name, dish) {
        if (!this.dishes.some(d => d.recipeName == dish)) {
            throw new Error('We do not have this dish');
        }
        if (Object.keys(this.guests).includes(name)) {
            throw new Error('This guest has already been invited');
        }

        this.guests[name] = dish;
        return `You have successfully invited ${name}!`;
    }
    showAttendance() {
        let attendance = [];
        Object.entries(this.guests).forEach(guest => {
            let name = guest[0];
            let dish = guest[1];
            let recipe = this.dishes.find(meal => meal.recipeName == dish);
            attendance.push(`${name} will eat ${dish}, which consists of ${Object.values(recipe)[1].join(', ')}`);
        });
        return attendance.join('\n');
    }
}

let dinner = new ChristmasDinner(300);

dinner.shopping(['Salt', 1]);
dinner.shopping(['Beans', 3]);
dinner.shopping(['Cabbage', 4]);
dinner.shopping(['Rice', 2]);
dinner.shopping(['Savory', 1]);
dinner.shopping(['Peppers', 1]);
dinner.shopping(['Fruits', 40]);
dinner.shopping(['Honey', 248]);

console.log(dinner.budget);

console.log(dinner.recipes({
    recipeName: 'Oshav',
    productsList: ['Fruits', 'Honey']
}));
console.log(dinner.recipes({
    recipeName: 'Folded cabbage leaves filled with rice',
    productsList: ['Cabbage', 'Rice', 'Salt', 'Savory']
}));
console.log(dinner.recipes({
    recipeName: 'Peppers filled with beans',
    productsList: ['Beans', 'Peppers', 'Salt']
}));

console.log(dinner.inviteGuests('Ivan', 'Oshav'));
console.log(dinner.inviteGuests('Petar', 'Folded cabbage leaves filled with rice'));
console.log(dinner.inviteGuests('Georgi', 'Peppers filled with beans'));

console.log(dinner.showAttendance());