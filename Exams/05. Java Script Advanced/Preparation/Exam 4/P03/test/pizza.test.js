let pizzUni = require('../pizza');
let { assert, equal, expect } = require('chai');

describe("Test pizzaUni object", function () {
    describe("test make an order function", function () {
        it("should throw if pizza order is not present as object parameter with property orderedPizza", function () {
            let order = 3;
            let order1 = [3];
            let order2 = 'pizza';
            let order3 = { pizza: 'i want pizza!!!' };
            let order4 = ['gimme pizzaaaaa!!'];

            expect(() => pizzUni.makeAnOrder(order)).to.throw('You must order at least 1 Pizza to finish the order.');
            expect(() => pizzUni.makeAnOrder(order1)).to.throw('You must order at least 1 Pizza to finish the order.');
            expect(() => pizzUni.makeAnOrder(order2)).to.throw('You must order at least 1 Pizza to finish the order.');
            expect(() => pizzUni.makeAnOrder(order3)).to.throw('You must order at least 1 Pizza to finish the order.');
            expect(() => pizzUni.makeAnOrder(order4)).to.throw('You must order at least 1 Pizza to finish the order.');
        });
        it("should return information about the order of pizza", function () {
            let order = { orderedPizza: 'Peperoni, baby!' };
            assert.equal(pizzUni.makeAnOrder(order), 'You just ordered Peperoni, baby!');
        });
        it("should return information about the order of pizza if additionaly is ordered a drink for it as well", function () {
            let order = { orderedPizza: 'Peperoni, baby!', orderedDrink: 'Coke' };
            assert.equal(pizzUni.makeAnOrder(order), 'You just ordered Peperoni, baby! and Coke.');
        });
    });
    describe("test get remaining work function", function () {
        it("should return information about the status of the pizza if it is preparing", function () {
            let status = [{ pizzaName: 'Peperoni, baby', status: 'preparing' }];
            let status2 = [{ pizzaName: 'Peperoni, baby', status: 'preparing' }, { pizzaName: 'Burger Pizza', status: 'preparing' }];
            assert.equal(pizzUni.getRemainingWork(status), 'The following pizzas are still preparing: Peperoni, baby.');
            assert.equal(pizzUni.getRemainingWork(status2), 'The following pizzas are still preparing: Peperoni, baby, Burger Pizza.');
        });
        it("should return information about the ready pizza's", function () {
            let status = [{ pizzaName: 'Peperoni, baby', status: 'ready' }];
            let status2 = [{ pizzaName: 'Peperoni, baby', status: 'ready' }, { pizzaName: 'Burger Pizza', status: 'ready' }];
            assert.equal(pizzUni.getRemainingWork(status), 'All orders are complete!');
            assert.equal(pizzUni.getRemainingWork(status2), 'All orders are complete!');
        });
        it("should return information about mixed status of the orders", function () {
            let status2 = [{ pizzaName: 'Peperoni, baby', status: 'ready' }, { pizzaName: 'Burger Pizza', status: 'preparing' }];
            assert.equal(pizzUni.getRemainingWork(status2), 'The following pizzas are still preparing: Burger Pizza.');
        });
    });
    describe("test get order type function", function () {
        it("should return information about the prize of the pizza if it is delivery", function () {
            let prize = 20;
            let transportation = 'Delivery';
            assert.equal(pizzUni.orderType(prize, transportation), 20);
        });
        it("should return information about the prize of the pizza if it is carry out with 10% discount", function () {
            let prize = 20;
            let transportation = 'Carry Out';
            assert.equal(pizzUni.orderType(prize, transportation), 18);
        });
    });
});
