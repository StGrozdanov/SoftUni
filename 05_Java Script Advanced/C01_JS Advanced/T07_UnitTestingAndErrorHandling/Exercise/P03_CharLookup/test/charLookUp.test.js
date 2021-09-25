const assert = require('chai').assert;
const lookupChar = require('../charLookUp');

describe('Test char look up functionality', () => {
    it('Should not accept anything different than String for first param', ()=> {
        const firstParam1 = 1;
        const firstParam2 = [];
        const firstParam3 = 1.6;
        const firstParam4 = {};
        const secondParam = 5;

        assert.isUndefined(lookupChar(firstParam1, secondParam));
        assert.isUndefined(lookupChar(firstParam2, secondParam));
        assert.isUndefined(lookupChar(firstParam3, secondParam));
        assert.isUndefined(lookupChar(firstParam4, secondParam));
    });

    it('Should not accept anything different than Integer for second param', ()=> {
        const secondParam1 = 'hallo';
        const secondParam2 = [];
        const secondParam3 = () => '';
        const secondParam4 = {};
        const secondParam5 = 5.5;
        const firstParam = 'random text';

        assert.isUndefined(lookupChar(firstParam, secondParam1));
        assert.isUndefined(lookupChar(firstParam, secondParam2));
        assert.isUndefined(lookupChar(firstParam, secondParam3));
        assert.isUndefined(lookupChar(firstParam, secondParam4));
        assert.isUndefined(lookupChar(firstParam, secondParam5));
    });

    it('Should not accept index that is less than zero', ()=> {
        const text = 'random text';
        const index = -5;

        assert.equal("Incorrect index", lookupChar(text, index));
    });

    it('Should not accept index that is bigger than target text length', ()=> {
        const text = 'random text';
        const index = text.length + 1;

        assert.equal("Incorrect index", lookupChar(text, index));
    });

    it('Should not accept index that is equal to target text length', ()=> {
        const text = 'random text';
        const index = text.length;

        assert.equal("Incorrect index", lookupChar(text, index));
    });

    it('Should return the desired char', ()=> {
        const text = 'Gimme da MoneY';

        assert.equal("G", lookupChar(text, 0));
        assert.equal("Y", lookupChar(text, text.length - 1));
        assert.equal("i", lookupChar(text, 1));
    });

});