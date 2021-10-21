class Restaurant {
    constructor(budget) {
        this.budgetMoney = budget;
        this.menu = [];
        this.stockProducts = [];
        this.history = [];
    }
    loadProducts(products) {
        products.forEach(p => {
            let [productName, productQuantity, productTotalPrice] = p.split(' ');
            productTotalPrice = Number(productTotalPrice);
            productQuantity = Number(productQuantity);
            if (this.budgetMoney >= productTotalPrice) {
                if (this.stockProducts.some(p => p.productName === productName)) {
                    let target = this.stockProducts.find(p => p.productName === productName);
                    let oldQuantity = target.quantity;
                    let newQuantity = productQuantity + oldQuantity;
                    target.quantity = newQuantity;
                } else {
                    let newProduct = {
                        name: productName,
                        quantity: productQuantity
                    }
                    this.stockProducts.push(newProduct);
                }
                this.budgetMoney -= productTotalPrice;
                this.history.push(`Successfully loaded ${productQuantity} ${productName}`);
            } else {
                this.history.push(`There was not enough money to load ${productQuantity} ${productName}`);
            }
        });
        return this.history.join('\n');
    }
    addToMenu(meal, neededProducts, price) {
        if (this.menu.some(m => m.name === meal)) {
            return `The ${meal} is already in the our menu, try something different.`;
        }
        let newMeal = {
            products: neededProducts,
            price: Number(price),
            name: meal,
        }
        this.menu.push(newMeal);
        if (this.menu.length === 1) {
            return `Great idea! Now with the ${meal} we have 1 meal in the menu, other ideas?`;
        }
        return `Great idea! Now with the ${meal} we have ${this.menu.length} meals in the menu, other ideas?`;
    }
    showTheMenu() {
        if (this.menu.length === 0) {
            return `Our menu is not ready yet, please come later...`;
        }
        let result = [];
        this.menu.forEach(meal => {
            result.push(`${meal.name} - $ ${meal.price}`);
        });
        return result.join('\n');
    }
    makeTheOrder(meal) {
        if (!this.menu.some(m => m.name === meal)) {
            return `There is not ${meal} yet in our menu, do you want to order something else?`;
        }
        let targetProduct = this.menu.find(m => m.name === meal);
        let result = '';
        targetProduct.products.forEach(product => {
            let [name, quantity] = product.split(' ');
            quantity = Number(quantity);
            if (!this.stockProducts.some(p => p.name === name)) {
                result = `For the time being, we cannot complete your order (${meal}), we are very sorry...`;
                return;
            }
            this.stockProducts.forEach(stock => {
                if (stock.name === name) {
                    if (stock.quantity - quantity < 0) {
                        result = `For the time being, we cannot complete your order (${meal}), we are very sorry...`;
                        return;
                    }
                }
            });
        });
        if (result === '') {
            targetProduct.products.forEach(product => {
                let [name, quantity] = product.split(' ');
                quantity = Number(quantity);
                this.stockProducts.forEach(stock => {
                    if (stock.name === name) {
                        stock.quantity -= quantity;
                        if (stock.quantity === 0) {
                            this.stockProducts.remove(stock);
                        }
                    }
                });
            });
            this.budgetMoney += targetProduct.price;
            return `Your order (${meal}) will be completed in the next 30 minutes and will cost you ${targetProduct.price}.`
        }
        return result;
    }
}

let test = new Restaurant(1000);
test.loadProducts(['Yogurt 30 3', 'Honey 50 4', 'Strawberries 20 10', 'Banana 5 1']);
test.addToMenu('frozenYogurt', ['Yogurt 1', 'Honey 1', 'Banana 1', 'Strawberries 10'], 9.99);
console.log(test.makeTheOrder('Kilikanzer'));
