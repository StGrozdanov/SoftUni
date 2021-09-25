const assert = require('chai').assert;
const isSymmetric = require('../checkForSymmetry');

describe('Test check for Symmetry functionality', () => {
    it('Should not recieve argument different than array as input', () => {
        const text = 'Hallo';
        const num = 2;
        const boolean = true;
        const obj = {Hallo: 'I dunno what hallo is ?!?!'};

        let result1 = isSymmetric(text);
        let result2 = isSymmetric(num);
        let result3 = isSymmetric(boolean);
        let result4 = isSymmetric(obj);

        assert.isFalse(result1);
        assert.isFalse(result2);
        assert.isFalse(result3);
        assert.isFalse(result4);
    });

    it('Should return true if number array is symetric', () => {
        const input = [1, 2, 1];

        let result = isSymmetric(input);

        assert.isTrue(result);
    });

    it('Should return false if number array is non symetric', () => {
        const input = [1, 2, 1, 5];

        let result = isSymmetric(input);

        assert.isFalse(result);
    });

    it('Should return true if alphabetic array is symetric', () => {
        const input = ['a', 'b', 'a'];

        let result = isSymmetric(input);

        assert.isTrue(result);
    });

    it('Should return false if alphabetic array is non symetric', () => {
        const input = ['a', 'b', 'c', 'd'];

        let result = isSymmetric(input);

        assert.isFalse(result);
    });

    it('Should return false if mixed array is passed', () => {
        const input = [1, '1'];

        let result = isSymmetric(input);

        assert.isFalse(result);
    });

});