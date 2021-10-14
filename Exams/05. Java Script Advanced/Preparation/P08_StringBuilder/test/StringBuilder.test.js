const { assert, expect } = require('chai');
const StringBuilder = require('../StringBuilder');

describe("test StringBuilder functionality", function () {

    describe('test constructor', () => {
        it('should return class', function () {
            let testClass = new StringBuilder('xo-xo-xo');
            assert.isTrue(testClass instanceof StringBuilder);
        });
        it('should accept only string as param', () => {
            let test = 'xo-xo-xo';
            let test2 = 2;
            let test3 = 2.3;
            let test4 = ['xoxoox'];
            let test5 = { name: 'party', hour: 22.45 };

            expect(() => new StringBuilder(test2)).to.throw('Argument must be a string');
            expect(() => new StringBuilder(test3)).to.throw('Argument must be a string');
            expect(() => new StringBuilder(test4)).to.throw('Argument must be a string');
            expect(() => new StringBuilder(test5)).to.throw('Argument must be a string');
            expect(() => new StringBuilder(test)).to.not.throw('Argument must be a string');
            expect(() => new StringBuilder(undefined)).to.not.throw('Argument must be a string');
        });
    });

    describe('test properties', () => {
        it('if undefined is passed should return empty array as inside property', function () {
            let testClass = new StringBuilder(undefined);
            let actual = testClass._stringArray;
            let expected = [];

            assert.isArray(actual);
            assert.deepEqual(actual, expected);
        });
        it('should return arr of elements for each word of string input', function () {
            let testClass = new StringBuilder('test');

            assert.deepEqual(testClass._stringArray, ['t', 'e', 's', 't']);
            assert.equal(testClass._stringArray.length, 4);
        });
    });

    describe('test methods', () => {
        describe('test append', () => {
            it('should accept only string as param', () => {
                let test = 'xo-xo-xo';
                let test2 = 2;
                let test3 = 2.3;
                let test4 = ['xoxoox'];
                let test5 = { name: 'party', hour: 22.45 };

                let testClass = new StringBuilder('test');
    
                expect(() => testClass.append(test2)).to.throw('Argument must be a string');
                expect(() => testClass.append(test3)).to.throw('Argument must be a string');
                expect(() => testClass.append(test4)).to.throw('Argument must be a string');
                expect(() => testClass.append(test5)).to.throw('Argument must be a string');
                expect(() => testClass.append(test)).to.not.throw('Argument must be a string');
            });
            it('should add arr of elements for each word of string input', function () {
                let testClass = new StringBuilder('test');
                testClass.append('this');
    
                assert.deepEqual(testClass._stringArray, ['t', 'e', 's', 't', 't', 'h', 'i', 's']);
                assert.equal(testClass._stringArray.length, 8);
            });
            it('should add element to the end of the string', function () {
                let testClass = new StringBuilder('test');
                testClass.append('!');
    
                assert.equal(testClass._stringArray[testClass._stringArray.length - 1], '!');
            });
        });

        describe('test prepend', () => {
            it('should accept only string as param', () => {
                let test = 'xo-xo-xo';
                let test2 = 2;
                let test3 = 2.3;
                let test4 = ['xoxoox'];
                let test5 = { name: 'party', hour: 22.45 };

                let testClass = new StringBuilder('test');
    
                expect(() => testClass.prepend(test2)).to.throw('Argument must be a string');
                expect(() => testClass.prepend(test3)).to.throw('Argument must be a string');
                expect(() => testClass.prepend(test4)).to.throw('Argument must be a string');
                expect(() => testClass.prepend(test5)).to.throw('Argument must be a string');
                expect(() => testClass.prepend(test)).to.not.throw('Argument must be a string');
            });
            it('should add arr of elements for each word of string input', function () {
                let testClass = new StringBuilder('test');
                testClass.prepend('this');
    
                assert.deepEqual(testClass._stringArray, ['t', 'h', 'i', 's', 't', 'e', 's', 't']);
                assert.equal(testClass._stringArray.length, 8);
            });
            it('should add element to the beginning of the string', function () {
                let testClass = new StringBuilder('test');
                testClass.prepend('!');
    
                assert.equal(testClass._stringArray[0], '!');
            });
        });
    });

    describe('test insertAt', () => {
        it('should accept only string as first param', () => {
            let test = 'xo-xo-xo';
            let test2 = 2;
            let test3 = 2.3;
            let test4 = ['xoxoox'];
            let test5 = { name: 'party', hour: 22.45 };

            let testClass = new StringBuilder('test');

            expect(() => testClass.insertAt(test2, 0)).to.throw('Argument must be a string');
            expect(() => testClass.insertAt(test3, 0)).to.throw('Argument must be a string');
            expect(() => testClass.insertAt(test4, 0)).to.throw('Argument must be a string');
            expect(() => testClass.insertAt(test5, 0)).to.throw('Argument must be a string');
            expect(() => testClass.insertAt(test, 0)).to.not.throw('Argument must be a string');
        });
        it('insert element at specific index', function () {
            let testClass = new StringBuilder('test');
            testClass.insertAt('this', 1);
            let testClass2 = new StringBuilder('test');
            testClass2.insertAt('!', 1);

            assert.deepEqual(testClass._stringArray, ['t', 't', 'h', 'i', 's', 'e', 's', 't']);
            assert.equal(testClass._stringArray.length, 8);
            assert.equal(testClass2._stringArray[1], '!');
        });
    });

    describe('test remove', () => {
        it('shoud remove one element from given index', function () {
            let testClass = new StringBuilder('test');
            testClass.remove(0, 1);

            assert.deepEqual(testClass._stringArray, ['e', 's', 't']);
            assert.equal(testClass._stringArray.length, 3);
        });
        it('should remove given count elements from given index', function () {
            let testClass = new StringBuilder('test');
            testClass.remove(0, 3);

            assert.deepEqual(testClass._stringArray, ['t']);
            assert.equal(testClass._stringArray.length, 1);
        });
        it('should not remove element from given index', function () {
            let testClass = new StringBuilder('test');
            testClass.remove(3, 0);

            assert.deepEqual(testClass._stringArray, ['t', 'e', 's', 't']);
        });
    });

    describe('test toString', () => {
        it('shoud return string', function () {
            let testClass = new StringBuilder('test');
            let output = testClass.toString();

            assert.isString(output);
        });
        it('should return the text as expected', function () {
            let testClass = new StringBuilder('test');
            testClass.append('!');

            assert.equal(testClass.toString(), 'test!');
        });
    });
});
