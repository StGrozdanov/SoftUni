const cinema = require('../cinema');
const { assert, expect } = require('chai');

describe("Tests cinema object", function () {
    describe("Test show movies", function () {
        it("Should return msg if no movies are selected into the array", function () {
            assert.equal(cinema.showMovies([]), 'There are currently no movies to show.');
        });
        it("Should accept array of movies, join them with comma and return them", function () {
            assert.equal(cinema.showMovies(['Lone rider']), 'Lone rider');
            assert.equal(cinema.showMovies(['Lone rider', 'Old Rider']), 'Lone rider, Old Rider');
            assert.equal(cinema.showMovies(['Lone rider', 'old Rider', 'Young wolf']), 'Lone rider, old Rider, Young wolf');
        });
    });

    describe("Test ticket price", function () {
        it("Should throw if movie is not included into the current movie projections", function () {
            expect(() => cinema.ticketPrice('Random Projection')).to.throw('Invalid projection type.');
            expect(() => cinema.ticketPrice(2)).to.throw('Invalid projection type.');
            expect(() => cinema.ticketPrice([2])).to.throw('Invalid projection type.');
        });
        it("Should accept valid projection and return the ticket price", function () {
            assert.equal(cinema.ticketPrice('Premiere'), 12.00);
            assert.equal(cinema.ticketPrice('Normal'), 7.50);
            assert.equal(cinema.ticketPrice('Discount'), 5.50);
        });
    });

    describe("Test swap seats in hall", function () {
        it("Should return unsuccessfull msg if seat numbers are equal or less than zero", function () {
            assert.equal(cinema.swapSeatsInHall(0, 2), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(2, 0), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(-1, 2), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(2, -1), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(0, 0), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(-1, -1), 'Unsuccessful change of seats in the hall.');
        });
        it("Should return unsuccessfull msg if seat numbers are bigger than 20", function () {
            assert.equal(cinema.swapSeatsInHall(21, 20), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(20, 21), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(21, 21), 'Unsuccessful change of seats in the hall.');
        });
        it("Should return unsuccessfull msg if seat numbers are equal", function () {
            assert.equal(cinema.swapSeatsInHall(20, 20), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(5, 5), 'Unsuccessful change of seats in the hall.');
        });
        it("Should return unsuccessfull msg if the given numbers are not intigers", function () {
            assert.equal(cinema.swapSeatsInHall(19.9, 20), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(20, 2.3), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall('20', 19), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall('20', '19'), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(19), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(['19'], 19), 'Unsuccessful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall({ 20: 20 }, 10), 'Unsuccessful change of seats in the hall.');
        });
        it("Should return successfull msg if the given numbers are not equal, are integers and are in the given range", function () {
            assert.equal(cinema.swapSeatsInHall(19, 18), 'Successful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(1, 2), 'Successful change of seats in the hall.');
            assert.equal(cinema.swapSeatsInHall(20, 19), 'Successful change of seats in the hall.');
        });
    });
});
