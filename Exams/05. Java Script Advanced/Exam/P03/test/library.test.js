const library = require('../library');
let { assert, expect } = require('chai');

describe("Tests library", function () {
    describe("Test calcPriceOfBook", function () {
        it("Should accept string as first param otherways should throw", function () {
            expect(() => library.calcPriceOfBook(2, 2011)).to.throw('Invalid input');
            expect(() => library.calcPriceOfBook([2], 2011)).to.throw('Invalid input');
            expect(() => library.calcPriceOfBook({ '2': 2 }, 2011)).to.throw('Invalid input');
        });
        it("Should accept integer as second param otherways should throw", function () {
            expect(() => library.calcPriceOfBook('random', 2.2)).to.throw('Invalid input');
            expect(() => library.calcPriceOfBook('random', -2.2011)).to.throw('Invalid input');
            expect(() => library.calcPriceOfBook('random', [2011])).to.throw('Invalid input');
        });
        it("Should return base price of 20.00 for a book that is not an old year", function () {
            assert.equal(library.calcPriceOfBook('random', 2011), `Price of random is 20.00`);
        });
        it("Should return base price of 20.00 -50% discount for a book that is older than or equal to 1980", function () {
            assert.equal(library.calcPriceOfBook('random', 1980), `Price of random is 10.00`);
            assert.equal(library.calcPriceOfBook('random', 1979), `Price of random is 10.00`);
            assert.equal(library.calcPriceOfBook('random', 1960), `Price of random is 10.00`);
        });
    });
    describe("Test findBook", function () {
        it("Should accept arr as first param with length > 0, otherways should throw", function () {
            expect(() => library.findBook([], 'random')).to.throw('No books currently available');
        });
        it("Should return msg if target book is not found", function () {
            assert.equal(library.findBook(['not random'], 'random'), `The book you are looking for is not here!`);
            assert.equal(library.findBook(['not random', 'absolutely not random'], 'random'), `The book you are looking for is not here!`);
        });
        it("Should return msg if target book is found", function () {
            assert.equal(library.findBook(['random'], 'random'), `We found the book you want.`);
            assert.equal(library.findBook(['not random', 'random'], 'random'), `We found the book you want.`);
        });
    });

    describe("Test arrange books", function () {
        it("Should accept integer as param", function () {
            expect(() => library.arrangeTheBooks(2.2)).to.throw('Invalid input');
            expect(() => library.arrangeTheBooks(-2.2011)).to.throw('Invalid input');
            expect(() => library.arrangeTheBooks([2011])).to.throw('Invalid input');
        });
        it("Should accept positive integer as param", function () {
            expect(() => library.arrangeTheBooks(-2)).to.throw('Invalid input');
            expect(() => library.arrangeTheBooks(-1)).to.throw('Invalid input');
        });
        it("Should have capacity for 40 books, so if the books are more than that should return msg", function () {
            assert.equal(library.arrangeTheBooks(41), "Insufficient space, more shelves need to be purchased.");
            assert.equal(library.arrangeTheBooks(51), "Insufficient space, more shelves need to be purchased.");
        });
        it("Should return success msg if the books are equal or less that the 40 capacity", function () {
            assert.equal(library.arrangeTheBooks(40), "Great job, the books are arranged.");
            assert.equal(library.arrangeTheBooks(0), "Great job, the books are arranged.");
            assert.equal(library.arrangeTheBooks(32), "Great job, the books are arranged.");
        });
    });

});
