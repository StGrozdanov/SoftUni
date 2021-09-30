const { assert, expect } = require('chai');
const PaymentPackage = require('../PeymentPackage');

describe('Test class peyment package', () => {

    describe('Test constructor validation', () =>{
        it('Should accept name as string as first param', () => {
            const name1 = 'Dqdo ti';
            const name2 = '';
            const name3 = 2;
            const name4 = ['Gushter', 'Som'];
    
            expect(() => new PaymentPackage(name2, 2)).to.throw('Name must be a non-empty string');
            expect(() => new PaymentPackage(name3, 2)).to.throw('Name must be a non-empty string');
            expect(() => new PaymentPackage(name4, 2)).to.throw('Name must be a non-empty string');
            expect(() => new PaymentPackage(name1, 2)).to.not.throw('Name must be a non-empty string');
        });

        it('Should accept value as number as first param', () => {
            const value1 = 1;
            const value2 = 2.5;
            const value3 = 'Hallo';
            const value4 = ['Gushter', 'Som'];
            const value5 = -5;
            const value6 = 0;
    
            expect(() => new PaymentPackage('Random', value3)).to.throw('Value must be a non-negative number');
            expect(() => new PaymentPackage('Random', value4)).to.throw('Value must be a non-negative number');
            expect(() => new PaymentPackage('Random', value5)).to.throw('Value must be a non-negative number');
            expect(() => new PaymentPackage('Random', value2)).not.to.throw('Value must be a non-negative number');
            expect(() => new PaymentPackage('Random', value1)).not.to.throw('Value must be a non-negative number');
            expect(() => new PaymentPackage('Random', value6)).not.to.throw('Value must be a non-negative number');
        });

        it('Should set VAT as number', () => {
            const value1 = 1;
            const value2 = 2.5;
            const value3 = 'Hallo';
            const value4 = ['Gushter', 'Som'];
            const value5 = -5;
    
            expect(() => new PaymentPackage('Random', 1).VAT = value3).to.throw('VAT must be a non-negative number');
            expect(() => new PaymentPackage('Random', 1).VAT = value4).to.throw('VAT must be a non-negative number');
            expect(() => new PaymentPackage('Random', 1).VAT = value5).to.throw('VAT must be a non-negative number');
            expect(() => new PaymentPackage('Random', 1).VAT = value1).not.to.throw('VAT must be a non-negative number');
            expect(() => new PaymentPackage('Random', 1).VAT = value2).not.to.throw('VAT must be a non-negative number');
        });

        it('Should set status as boolean', () => {
            const value1 = 1;
            const value2 = 'Hallo';
    
            expect(() => new PaymentPackage('Random', 1).active = value1).to.throw('Active status must be a boolean');
            expect(() => new PaymentPackage('Random', 1).active = value2).to.throw('Active status must be a boolean');
            expect(() => new PaymentPackage('Random', 1).active = true).not.to.throw('Active status must be a boolean');
            expect(() => new PaymentPackage('Random', 1).active = false).not.to.throw('Active status must be a boolean');
        });
    });

    describe('Test default values', () =>{
        it('Should return correct name', () => {
            const name = 'Dqdo ti';
            const value = 2.2;

            const package = new PaymentPackage(name, value);

            assert.equal('Dqdo ti', package.name);
        });

        it('Should return correct value', () => {
            const name = 'Dqdo ti';
            const value = 2.2;

            const package = new PaymentPackage(name, value);

            assert.equal(2.2, package.value);
        });

        it('Should return correct VAT', () => {
            const name = 'Dqdo ti';
            const value = 2.2;

            const package = new PaymentPackage(name, value);

            assert.equal(20, package.VAT);
        });

        it('Should return correct state', () => {
            const name = 'Dqdo ti';
            const value = 2.2;

            const package = new PaymentPackage(name, value);

            assert.isTrue(package.active);
        });

    });

    describe('Test toString method', () =>{
        it('Should add (inactive) to output if the status is inactive', () => {
            const name = 'Dqdo ti';
            const value = 2.2;

            const package = new PaymentPackage(name, value);
            package.active = false;

            assert.isTrue(package.toString().includes('(inactive)'));
        });

        it('Should not add (inactive) to output if the status is active', () => {
            const name = 'Dqdo ti';
            const value = 2.2;

            const package = new PaymentPackage(name, value);

            assert.isFalse(package.toString().includes('(inactive)'));
        });

        it ('Should return correct output', () => {
            let flagClass = new PaymentPackage('abc', 123);
            let expected = [
                `Package: abc`,
                `- Value (excl. VAT): 123`,
                `- Value (VAT 20%): 147.6`
            ]
            expect(flagClass.toString()).to.equal(expected.join('\n'));
        });

    });

});