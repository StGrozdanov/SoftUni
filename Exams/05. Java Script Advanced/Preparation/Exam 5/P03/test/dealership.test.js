let dealership = require('../dealership');
let { assert, equal, expect } = require('chai');

describe("Tests dealership object", function () {
    describe("Test buy new car function", function () {
        it("should return the actual price if the old car is not among the included for price discount", function () {
            assert.equal(dealership.newCarCost('BMW E36', 20000), 20000);
        });
        it("should return the actual price with discount if the old car is among the included for price discount", function () {
            let discountForOldCar = {
                'Audi A4 B8': 15000,
                'Audi A6 4K': 20000,
                'Audi A8 D5': 25000,
                'Audi TT 8J': 14000,
            }
            assert.equal(dealership.newCarCost('Audi A4 B8', 40000), 25000);
            assert.equal(dealership.newCarCost('Audi A6 4K', 40000), 20000);
            assert.equal(dealership.newCarCost('Audi A8 D5', 40000), 15000);
            assert.equal(dealership.newCarCost('Audi TT 8J', 40000), 26000);
        });
    });
    describe("Test car equipment function", function () {
        it("should return the selected by index extras", function () {
            let possibleEquipment = ['heated seats', 'sliding roof', 'sport rims', 'navigation', 'electric seats', 'leather seats']
            let selected = [0, 1, 3, 5];
            let expected = ['heated seats', 'sliding roof', 'navigation', 'leather seats']
            assert.deepEqual(dealership.carEquipment(possibleEquipment, selected), expected);
        });
    });
    describe("Test euro category function", function () {
        it("should return the price with discount if the car euro category is equal to 4", function () {
            let price = dealership.newCarCost('Audi A4 B8', 30000);
            let total = price - (price * 0.05);
            assert.equal(dealership.euroCategory(4), `We have added 5% discount to the final price: ${total}.`);
        });
        it("should return the price with discount if the car euro category is above 4", function () {
            let price2 = dealership.newCarCost('Audi A4 B8', 30000);
            let total2 = price2 - (price2 * 0.05);
            assert.equal(dealership.euroCategory(5), `We have added 5% discount to the final price: ${total2}.`);
        });
        it("should return the price without discount if the car euro category is bellow 4", function () {
            let price = dealership.newCarCost('Audi A4 B8', 30000);
            assert.equal(dealership.euroCategory(3), 'Your euro category is low, so there is no discount from the final price!');
        });
    });
});
