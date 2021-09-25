const assert = require('chai').assert;
const createCalculator = require('../addSubtract');

describe('Test calculator functionality', () => {
    it('Should return calculator with add functionality', () => {
        const calculator = createCalculator();
        assert.isTrue(calculator.hasOwnProperty('add'));
    });
    
    it('Should return calculator with subtract functionality', () => {
        const calculator = createCalculator();
        assert.isTrue(calculator.hasOwnProperty('subtract'));
    });

    it('Should return calculator with get functionality', () => {
        const calculator = createCalculator();
        assert.isTrue(calculator.hasOwnProperty('get'));
    });

    it('Should not allow modification from the outside of the operation value', () => {
        const calculator = createCalculator();
        assert.isFalse(calculator.hasOwnProperty('value'));
    });

    it('Should parse operand of the add function as number', () => {
        const calculator = createCalculator();

        calculator.add(2);
        calculator.add('5');

        assert.equal(calculator.get(), 7);
    });

    it('Should parse operand of the subtract function as number', () => {
        const calculator = createCalculator();

        calculator.add('5');
        calculator.subtract(2);
        calculator.subtract('1');

        assert.equal(calculator.get(), 2);
    });
});