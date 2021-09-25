const assert = require('chai').assert;
const rgbToHexColor = require('../rgbToHex');

describe('Test rgbToHexColor functionality', () => {
    it('Should return undefined if params are different from Numbers!', () => {
        const red = 'I am very red!';
        const green = 'I am very green!';
        const blue = 'I am very, very blue!';

        const resultRed = rgbToHexColor(red, 1, 2);
        const resultGreen = rgbToHexColor(1, green, 2);
        const resultBlue = rgbToHexColor(2, 1, blue);

        assert.equal(resultRed, undefined);
        assert.equal(resultGreen, undefined);
        assert.equal(resultBlue, undefined);
    });

    it('Should return undefined if params are less than zero!', () => {
        const resultRed = rgbToHexColor(-1, 1, 2);
        const resultGreen = rgbToHexColor(1, -5, 2);
        const resultBlue = rgbToHexColor(2, 1, -200);

        assert.equal(resultRed, undefined);
        assert.equal(resultGreen, undefined);
        assert.equal(resultBlue, undefined);
    });

    it('Should return undefined if params are bigger than 255!', () => {
        const resultRed = rgbToHexColor(256, 255, 242);
        const resultGreen = rgbToHexColor(1, 285, 2);
        const resultBlue = rgbToHexColor(2, 1, 10000);

        assert.equal(resultRed, undefined);
        assert.equal(resultGreen, undefined);
        assert.equal(resultBlue, undefined);
    });

    it('Should return correct hexidemical format of the desired color', () => {
        const resultRed = rgbToHexColor(0, 255, 242);

        assert.strictEqual(resultRed, '#00FFF2');
    });
});