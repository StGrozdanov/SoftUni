const HolidayPackage = require('../HolidayPackage');
const {assert, expect} = require('chai');

describe("Holiday class tests", function() {
    describe("Test constructor and properties", function() {
        it("should be instantained with two string params", function() {
            let package = new HolidayPackage('ff', 'ffff');
            assert.equal(package.destination, 'ff');
            assert.equal(package.season, 'ffff');
        });
    });
    describe("Test showVacationers method", function() {
        it("should return that no vacationairs are included if there are none added", function() {
            let package = new HolidayPackage('ff', 'ffff');
            let answer = package.showVacationers();
            assert.equal(answer, 'No vacationers are added yet');
        });
        it("should return vacationairs that are added", function() {
            let package = new HolidayPackage('ff', 'ffff');
            package.addVacationer('Stoyan G');
            package.addVacationer('Andreana G');
            let answer = package.showVacationers();
            assert.equal(answer, 'Vacationers:\nStoyan G\nAndreana G');
        });
    });

    describe("Test addVacationair method", function() {
        it("throws if empty string is added", function() {
            let package = new HolidayPackage('ff', 'ffff');
            expect(() => package.addVacationer(' ')).to.throw('Vacationer name must be a non-empty string');
        });
        it("throws if different param than string is added", function() {
            let package = new HolidayPackage('ff', 'ffff');
            expect(() => package.addVacationer(1)).to.throw('Vacationer name must be a non-empty string');
            expect(() => package.addVacationer(['text'])).to.throw('Vacationer name must be a non-empty string');
            expect(() => package.addVacationer({1: 2, 2: '3'})).to.throw('Vacationer name must be a non-empty string');
        });
        it("throws if less or more than two names are added as params", function() {
            let package = new HolidayPackage('ff', 'ffff');
            expect(() => package.addVacationer('Stoyan')).to.throw('Name must consist of first name and last name');
            expect(() => package.addVacationer('Stoyan Andreana Stoyan')).to.throw('Name must consist of first name and last name');
        });
        it("should accept vacationair with correct params and include it", function() {
            let package = new HolidayPackage('ff', 'ffff');
            package.addVacationer('Stoyan G');
            package.addVacationer('Andreana G');
            let answer = package.showVacationers();
            assert.equal(answer, 'Vacationers:\nStoyan G\nAndreana G');
        });
    });

    describe("Test insurance property", function() {
        it("default value of insurance should be false upon initialization", function() {
            let package = new HolidayPackage('ff', 'ffff');
            assert.isFalse(package.insuranceIncluded);
        });
        it("default value should be able to change to true", function() {
            let package = new HolidayPackage('ff', 'ffff');
            package.insuranceIncluded = true;
            assert.isTrue(package.insuranceIncluded);
        });
        it("should throw is different value than boolean is set", function() {
            let package = new HolidayPackage('ff', 'ffff');
            expect(() => package.insuranceIncluded = 2).to.throw('Insurance status must be a boolean');
            expect(() => package.insuranceIncluded = 'true').to.throw('Insurance status must be a boolean');
            expect(() => package.insuranceIncluded = []).to.throw('Insurance status must be a boolean');
            expect(() => package.insuranceIncluded = {}).to.throw('Insurance status must be a boolean');
            expect(() => package.insuranceIncluded = 2.2).to.throw('Insurance status must be a boolean');
            expect(() => package.insuranceIncluded = ['true']).to.throw('Insurance status must be a boolean');
        });
    });

    describe("Test generateHolidayPackage method", function() {
        it("throws if vacationairs are less than one", function() {
            let package = new HolidayPackage('ff', 'ffff');
            expect(() => package.generateHolidayPackage()).to.throw('There must be at least 1 vacationer added');
        });
        it("should generate 400 BGN per person as default price", function() {
            let package = new HolidayPackage('Dubai', 'Random Season');
            package.addVacationer('Stoyan G');
            package.addVacationer('Andreana G');
            assert.equal(package.generateHolidayPackage(), "Holiday Package Generated\n" +
            "Destination: " + 'Dubai' + "\n" +
            'Vacationers:\nStoyan G\nAndreana G' + "\n" +
            "Price: 800");
        });
        it("should increase total price by 200 BGN if the season is summer", function() {
            let package = new HolidayPackage('Dubai', 'Summer');
            package.addVacationer('Stoyan G');
            package.addVacationer('Andreana G');
            assert.equal(package.generateHolidayPackage(), "Holiday Package Generated\n" +
            "Destination: " + 'Dubai' + "\n" +
            'Vacationers:\nStoyan G\nAndreana G' + "\n" +
            "Price: 1000");
        });
        it("should increase total price by 200 BGN if the season is winter", function() {
            let package = new HolidayPackage('Dubai', 'Winter');
            package.addVacationer('Stoyan G');
            package.addVacationer('Andreana G');
            assert.equal(package.generateHolidayPackage(), "Holiday Package Generated\n" +
            "Destination: " + 'Dubai' + "\n" +
            'Vacationers:\nStoyan G\nAndreana G' + "\n" +
            "Price: 1000");
        });
        it("should increase the price by 100 BGN if insurance is included", function() {
            let package = new HolidayPackage('Dubai', 'Winter');
            package.addVacationer('Stoyan G');
            package.addVacationer('Andreana G');
            package.insuranceIncluded = true;
            assert.equal(package.generateHolidayPackage(), "Holiday Package Generated\n" +
            "Destination: " + 'Dubai' + "\n" +
            'Vacationers:\nStoyan G\nAndreana G' + "\n" +
            "Price: 1100");
        });
    });

});
