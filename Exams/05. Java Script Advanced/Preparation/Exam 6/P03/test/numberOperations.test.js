let numberOperations = require('../numberOperations');
let { assert, equal, expect } = require('chai');

describe("Tests number operations object", function () {
    describe("Tests pow number function", function () {
        it("Should pow the provided number", function () {
            let num1 = 1;
            let num2 = 2;
            let num3 = 2.5;
            let num4 = -2.5;
            let num5 = -2;
            assert.equal(numberOperations.powNumber(num1), 1);
            assert.equal(numberOperations.powNumber(num2), 4);
            assert.equal(numberOperations.powNumber(num3), 6.25);
            assert.equal(numberOperations.powNumber(num4), 6.25);
            assert.equal(numberOperations.powNumber(num5), 4);
        });
    });
    describe("Tests number checker function", function () {
        it("Should throw if it is provided something different than number", function () {
            expect(() => numberOperations.numberChecker('Not a num, baby')).to.throw('The input is not a number!');
            expect(() => numberOperations.numberChecker({ 25: 25 })).to.throw('The input is not a number!');
        });
        it("Should return msg if it is provided number lower than 100", function () {
            assert.equal(numberOperations.numberChecker('99'), 'The number is lower than 100!');
            assert.equal(numberOperations.numberChecker('-10'), 'The number is lower than 100!');
            assert.equal(numberOperations.numberChecker('50.4'), 'The number is lower than 100!');
            assert.equal(numberOperations.numberChecker('1'), 'The number is lower than 100!');
        });
        it("Should return msg if it is provided number equal or greater than 100", function () {
            assert.equal(numberOperations.numberChecker('100'), 'The number is greater or equal to 100!');
            assert.equal(numberOperations.numberChecker('101'), 'The number is greater or equal to 100!');
            assert.equal(numberOperations.numberChecker('200'), 'The number is greater or equal to 100!');
            assert.equal(numberOperations.numberChecker('111.2'), 'The number is greater or equal to 100!');
        });
    });
    describe("Tests sum arrays function", function () {
        it("Should sum the numbers in the two arrays", function () {
            let example1 = [22];
            let example2 = [22, 55];
            let example3 = {first: [22, 22], second: [20, 20]};
            let example4 = {first: [22, 22, 22], second: [20, 20]};
            let example5 = {first: [0.5, 0.5, 0.5], second: [20.5, 20.5]};
            let example6 = {first: [-22, 22], second: [20, 20]};
            let example7 = {first: [-22, -22], second: [-20, -20]};
        
            assert.deepEqual(numberOperations.sumArrays(example1, example1), [44]);
            assert.deepEqual(numberOperations.sumArrays(example2, example2), [44, 110]);
            assert.deepEqual(numberOperations.sumArrays(example3.first, example3.second), [42, 42]);
            assert.deepEqual(numberOperations.sumArrays(example4.first, example4.second), [42, 42, 22]);
            assert.deepEqual(numberOperations.sumArrays(example5.first, example5.second), [21, 21, 0.5]);
            assert.deepEqual(numberOperations.sumArrays(example6.first, example6.second), [-2, 42]);
            assert.deepEqual(numberOperations.sumArrays(example7.first, example7.second), [-42, -42]);
        });
    });
});