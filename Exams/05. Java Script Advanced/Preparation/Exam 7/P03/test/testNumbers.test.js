const testNumbers = require('../testNumbers');
const { assert, equal, expect } = require('chai');

describe("Tests testNumbers object", function () {
    describe("Test sumNumbers function", function () {
        it("Should accept TWO numbers as params otherways returns undefined", function () {
            assert.equal(testNumbers.sumNumbers('2', 2), undefined);
            assert.equal(testNumbers.sumNumbers(2, '2'), undefined);
            assert.equal(testNumbers.sumNumbers('2', '2'), undefined);
            assert.equal(testNumbers.sumNumbers(2, 'num'), undefined);
            assert.equal(testNumbers.sumNumbers([2], [2]), undefined);
            assert.equal(testNumbers.sumNumbers({ 2: 2 }, { 2: 2 }), undefined);
        });
        it("Should return result rounded to second digit!", function () {
            assert.equal(testNumbers.sumNumbers(2.2, 2), 4.20);
            assert.equal(testNumbers.sumNumbers(2.5, 2.5), 5.00);
            assert.equal(testNumbers.sumNumbers(2.55, 2.555), 5.11);
        });
        it("Should work with negative nums", function () {
            assert.equal(testNumbers.sumNumbers(-2, 2), 0.00);
            assert.equal(testNumbers.sumNumbers(-2, -2), -4.00);
        });
    });
    describe("Test numberChecker function", function () {
        it("Should accept NUMBER as param otherways throws", function () {
            expect(() => testNumbers.numberChecker('number')).to.throw('The input is not a number!');
            expect(() => testNumbers.numberChecker([2, 3, 4])).to.throw('The input is not a number!');
            expect(() => testNumbers.numberChecker({ 2.2: 2.2 })).to.throw('The input is not a number!');
        });
        it("Should return if num is even", function () {
            assert.equal(testNumbers.numberChecker(2), 'The number is even!');
            assert.equal(testNumbers.numberChecker(-22), 'The number is even!');
        });
        it("Should return if number is odd", function () {
            assert.equal(testNumbers.numberChecker(3), 'The number is odd!');
            assert.equal(testNumbers.numberChecker(-33), 'The number is odd!');
        });
    });
    describe("Test averageSumArray function", function () {
        it("Should return the avg sum of a given arr", function () {
            let case1 = [1, 1, 1, 1];
            let case2 = [2];
            let case3 = [0, 1];
            let case4 = [-2, 1, -3, 4];
            let case5 = [-2, -1, -3, -4];
            assert.equal(testNumbers.averageSumArray(case1), 1);
            assert.equal(testNumbers.averageSumArray(case2), 2);
            assert.equal(testNumbers.averageSumArray(case3), 0.5);
            assert.equal(testNumbers.averageSumArray(case4), 0);
            assert.equal(testNumbers.averageSumArray(case5), -2.5);
        });
    });
});

