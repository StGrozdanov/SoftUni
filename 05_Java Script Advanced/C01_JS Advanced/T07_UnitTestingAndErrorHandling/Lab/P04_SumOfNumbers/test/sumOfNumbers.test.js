const assert = require('chai').assert;
let sum = require('../sumOfNumbers');

describe('Test the sum functionality', () => {
    it('Should sum the positive elements', ()=> {
        const input = [10, 20];
        const expectedResult = 30;

        let actualResult = sum(input);

        assert.strictEqual(expectedResult, actualResult);
    });

    it('Should sum the negative elements', ()=> {
        const input = [-10, -20];
        const expectedResult = -30;

        let actualResult = sum(input);

        assert.strictEqual(expectedResult, actualResult);
    });

    it('Should sum the mixed elements', ()=> {
        const input = [-10, 20];
        const expectedResult = 10;

        let actualResult = sum(input);

        assert.strictEqual(expectedResult, actualResult);
    });
    
});