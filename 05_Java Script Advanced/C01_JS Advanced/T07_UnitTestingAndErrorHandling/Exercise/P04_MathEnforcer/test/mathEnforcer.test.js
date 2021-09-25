const assert = require('chai').assert;
const mathEnforcer = require('../mathEnforcer');

describe('Test mathEnforcer.js functionality', () => {
    describe('Test addFive method', () => {
        it('Should return undefined if input is different than number', () => {
            const input1 = '';
            const input2 = ['one', 'two', 'three'];
            const input3 = {one: 'two'};
            const input4 = 'Hallo';

            const result1 = mathEnforcer.addFive(input1);
            const result2 = mathEnforcer.addFive(input2);
            const result3 = mathEnforcer.addFive(input3);
            const result4 = mathEnforcer.addFive(input4);

            assert.isUndefined(result1);
            assert.isUndefined(result2);
            assert.isUndefined(result3);
            assert.isUndefined(result4);
        });
        it('Should add five to input integer number', () => {
            const result = mathEnforcer.addFive(5);
            const result2 = mathEnforcer.addFive(-2);
            assert.equal(10, result);
            assert.equal(3, result2);
        });

        it('Should add five to input double number', () => {
            const result = mathEnforcer.addFive(5.2);
            const result2 = mathEnforcer.addFive(-2.5)
            assert.equal(10.2, result);
            assert.equal(2.5, result2);
        });
    });
    describe('Test subtractTen method', () => {
        it('Should return undefined if input is different than number', () => {
            const input1 = '';
            const input2 = ['one', 'two', 'three'];
            const input3 = {one: 'two'};
            const input4 = 'Hallo';

            const result1 = mathEnforcer.subtractTen(input1);
            const result2 = mathEnforcer.subtractTen(input2);
            const result3 = mathEnforcer.subtractTen(input3);
            const result4 = mathEnforcer.subtractTen(input4);

            assert.isUndefined(result1);
            assert.isUndefined(result2);
            assert.isUndefined(result3);
            assert.isUndefined(result4);
        });
        it('Should subtract ten from input integer number', () => {
            const result = mathEnforcer.subtractTen(5);
            const result2 = mathEnforcer.subtractTen(-5);
            assert.equal(-5, result);
            assert.equal(-15, result2);
        });

        it('Should subtract ten from input double number', () => {
            const result = mathEnforcer.subtractTen(20.2);
            const result2 = mathEnforcer.subtractTen(-9.9)
            assert.equal(10.2, result);
            assert.equal(-19.9, result2);
        });
    });
    describe('Test sum method', () => {
        it('Should return undefined if first param is different than number', () => {
            const input1 = '';
            const input2 = ['one', 'two', 'three'];
            const input3 = {one: 'two'};
            const input4 = 'Hallo';

            const result1 = mathEnforcer.sum(input1, 5);
            const result2 = mathEnforcer.sum(input2, 5);
            const result3 = mathEnforcer.sum(input3, 5);
            const result4 = mathEnforcer.sum(input4, 5);

            assert.isUndefined(result1);
            assert.isUndefined(result2);
            assert.isUndefined(result3);
            assert.isUndefined(result4);
        });
        it('Should return undefined if second param is different than number', () => {
            const input1 = '';
            const input2 = ['one', 'two', 'three'];
            const input3 = {one: 'two'};
            const input4 = 'Hallo';

            const result1 = mathEnforcer.sum(5, input1);
            const result2 = mathEnforcer.sum(5, input2);
            const result3 = mathEnforcer.sum(5, input3);
            const result4 = mathEnforcer.sum(5, input4);

            assert.isUndefined(result1);
            assert.isUndefined(result2);
            assert.isUndefined(result3);
            assert.isUndefined(result4);
        });
        it('Should sum integer numbers', () => {
            const result = mathEnforcer.sum(5, 5);
            const result2 = mathEnforcer.sum(10, -5);
            assert.equal(10, result);
            assert.equal(5, result2);
        });

        it('Should sum double numbers', () => {
            const result = mathEnforcer.sum(5.2, 5.5);
            const result2 = mathEnforcer.sum(5.5, -5.5);
            assert.equal(10.7, result);
            assert.equal(0, result2);
        });

        it('Should sum mixed numbers', () => {
            const result = mathEnforcer.sum(5, 5.5);
            const result2 = mathEnforcer.sum(-5, -5.5);
            assert.equal(10.5, result);
            assert.equal(-10.5, result2);
        });
    });
});