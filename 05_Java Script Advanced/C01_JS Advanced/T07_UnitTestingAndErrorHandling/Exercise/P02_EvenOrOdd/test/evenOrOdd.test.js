const assert = require('chai').assert;
const isOddOrEven = require('../evenOrOdd');

describe('Test odd / Even function', () => {
    it('Should accept only string elements as param', () => {
        const input1 = 1;
        const input2 = 5.5;
        const input3 = ['Hallo', 'No Hallo', 'Defenetley not Hallo'];
        const input4 = {some: 'element', other: 'some'};
        
        assert.isUndefined(isOddOrEven(input1));
        assert.isUndefined(isOddOrEven(input2));
        assert.isUndefined(isOddOrEven(input3));
        assert.isUndefined(isOddOrEven(input4));
    });
    it('Should return even if input length is even', () => {
        const input = 'abcd';
        assert.equal('even', isOddOrEven(input));
    });
    it('Should return odd if input length is odd', () => {
        const input = 'abc';
        assert.equal('odd', isOddOrEven(input));
    });
});
