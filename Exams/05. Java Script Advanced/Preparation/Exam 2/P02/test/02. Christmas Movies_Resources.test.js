let ChristmasMovies = require('../02. Christmas Movies_Resources');
let { assert, expect } = require('chai');

describe("Test ChristmasMovies class", function () {
    describe("Test buy Movie method", function () {
        it("should throw if existing movie is added twice", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', 'Rocky');
            expect(() => movies.buyMovie('Rocky', 'Rocky')).to.throw('You already own Rocky in your collection!');
        });
        it("should return msg when movie is added successfully", function () {
            let movies = new ChristmasMovies();
            assert.equal(movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']), 'You just got Rocky to your collection in which Rocky, Brocky are taking part!');
        });
    });

    describe("Test discardMovie method", function () {
        it("should throw if triyng to remove unexistent movie", function () {
            let movies = new ChristmasMovies();
            expect(() => movies.discardMovie('Non existent')).to.throw('Non existent is not at your collection!');
        });
        it("should throw if triyng to remove movie that is not yet watched", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            expect(() => movies.discardMovie('Rocky')).to.throw('Rocky is not watched!');
        });
        it("should remove watched and existent movie", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            movies.watchMovie('Rocky');
            assert.equal(movies.discardMovie('Rocky'), 'You just threw away Rocky!')
        });
    });

    describe("Test watchmovie method", function () {
        it("should throw if triyng to watch unexistent movie", function () {
            let movies = new ChristmasMovies();
            expect(() => movies.watchMovie('Non existent')).to.throw('No such movie in your collection!');
        });
        it("count the times that given movie is watched for first time", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            movies.watchMovie('Rocky');
            assert.equal(movies.watched['Rocky'], 1);
        });
        it("count the times that given movie is watched for more than one time", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            assert.equal(movies.watched['Rocky'], 4);
        });
    });

    describe("Test favoriteMovie method", function () {
        it("should throw if triyng find out favorite but u have not watched any movies yet", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            expect(() => movies.favouriteMovie()).to.throw('You have not watched a movie yet this year!');
        });
        it("should find out your most watched movie", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.buyMovie('Brocky', ['Focky']);
            movies.watchMovie('Brocky');
            movies.watchMovie('Brocky');
            movies.watchMovie('Brocky');
            assert.equal(movies.favouriteMovie(), 'Your favourite movie is Rocky and you have watched it 4 times!');
        });
    });

    describe("Test mostStaredActor method", function () {
        it("should throw if triyng find out most stared actor but u have not watched any movies yet", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            expect(() => movies.mostStarredActor()).to.throw('You have not watched a movie yet this year!');
        });
        it("should find out your most starred actor", function () {
            let movies = new ChristmasMovies();
            movies.buyMovie('Rocky', ['Rocky', 'Rocky', 'Brocky']);
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.watchMovie('Rocky');
            movies.buyMovie('Brocky', ['Focky', 'Rocky']);
            movies.watchMovie('Brocky');
            movies.watchMovie('Brocky');
            movies.watchMovie('Brocky');
            assert.equal(movies.mostStarredActor(), 'The most starred actor is Rocky and starred in 2 movies!');
        });
    });

});
