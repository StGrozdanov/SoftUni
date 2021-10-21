let { Repository } = require("../solution");
let { assert, expect } = require('chai');

describe("Test class repository", function () {
    describe("test add entity", function () {
        it("should throw if entity does not have the required fields", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repository = new Repository(properties);
            let entity = {
                nam: "Pesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity2 = {
                name: "Pesho",
                age: 22,
                birthda: new Date(1998, 0, 7)
            };
            let entity3 = {
                name: "Pesho",
                ag: 22,
                birthday: new Date(1998, 0, 7)
            };
            expect(() => repository.add(entity)).to.throw('Property name is missing from the entity!');
            expect(() => repository.add(entity2)).to.throw('Property birthday is missing from the entity!');
            expect(() => repository.add(entity3)).to.throw('Property age is missing from the entity!');
        });
        it("should throw if entity does not have the required type of params", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repository = new Repository(properties);
            let entity = {
                name: 1,
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity2 = {
                name: "Pesho",
                age: '22',
                birthday: new Date(1998, 0, 7)
            };
            let entity3 = {
                name: "Pesho",
                age: 22,
                birthday: 12 / 12 / 2012
            };
            expect(() => repository.add(entity)).to.throw('Property name is not of correct type!');
            expect(() => repository.add(entity2)).to.throw('Property age is not of correct type!');
            expect(() => repository.add(entity3)).to.throw('Property birthday is not of correct type!');
        });
        it("should set new id for each new element and return it", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repository = new Repository(properties);
            let entity = {
                name: 'Gosho',
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity2 = {
                name: "Pesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity3 = {
                name: "Mesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            assert.equal(repository.add(entity), 0);
            assert.equal(repository.add(entity2), 1);
            assert.equal(repository.add(entity3), 2);
            assert.equal(repository.count, 3);
        });
        it("should store the new entity in the data and return it by id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repository = new Repository(properties);
            let entity = {
                name: 'Gosho',
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity2 = {
                name: "Pesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity3 = {
                name: "Mesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            repository.add(entity);
            repository.add(entity2);
            repository.add(entity3);

            assert.equal(repository.getId(0), entity);
            assert.equal(repository.getId(1), entity2);
            assert.equal(repository.getId(2), entity3);
        });
        it("should throw if there is not entity with this id", function () {
            let properties = {
                name: "string",
                age: "number",
                birthday: "object"
            };
            let repository = new Repository(properties);
            let entity = {
                name: 'Gosho',
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity2 = {
                name: "Pesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            let entity3 = {
                name: "Mesho",
                age: 22,
                birthday: new Date(1998, 0, 7)
            };
            repository.add(entity);
            repository.add(entity2);
            repository.add(entity3);

            expect(() => repository.getId(3)).to.throw('Entity with id: 3 does not exist!');
            expect(() => repository.getId(-1)).to.throw('Entity with id: -1 does not exist!');
        });

        describe("test update entity", function () {
            it("should throw if entity does not have the required fields", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity0 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity0);
                let entity = {
                    nam: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthda: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Pesho",
                    ag: 22,
                    birthday: new Date(1998, 0, 7)
                };
                expect(() => repository.update(0, entity)).to.throw('Property name is missing from the entity!');
                expect(() => repository.update(0, entity2)).to.throw('Property birthday is missing from the entity!');
                expect(() => repository.update(0, entity3)).to.throw('Property age is missing from the entity!');
            });
            it("should throw if entity does not have the required type of params", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity0 = {
                    name: 'ss',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity0);
                let entity = {
                    name: 1,
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: '22',
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Pesho",
                    age: 22,
                    birthday: 12 / 12 / 2012
                };
                expect(() => repository.update(0, entity)).to.throw('Property name is not of correct type!');
                expect(() => repository.update(0, entity2)).to.throw('Property age is not of correct type!');
                expect(() => repository.update(0, entity3)).to.throw('Property birthday is not of correct type!');
            });
            it("should set new id for each new element and return it", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity = {
                    name: 'Gosho',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Mesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                assert.equal(repository.add(entity), 0);
                assert.equal(repository.add(entity2), 1);
                assert.equal(repository.add(entity3), 2);
                assert.equal(repository.count, 3);
                repository.update(0, entity2);
                assert.equal(repository.count, 3);
            });
            it("should store the new entity in the data and return it by id", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity = {
                    name: 'Gosho',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Mesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity);
                repository.add(entity2);
                repository.add(entity3);
    
                assert.equal(repository.getId(0), entity);
                assert.equal(repository.getId(1), entity2);
                assert.equal(repository.getId(2), entity3);
            });
            it("should throw if id is non existent", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity = {
                    name: 'Gosho',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Mesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity);
                repository.add(entity2);
                repository.add(entity3);

                expect(() => repository.update(3, entity2)).to.throw('Entity with id: 3 does not exist!');
                expect(() => repository.update(-1, entity2)).to.throw('Entity with id: -1 does not exist!');
            });
            it("should store the new entity in the data and return it by id", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity = {
                    name: 'Gosho',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Mesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity);
                repository.add(entity2);
                repository.add(entity3);
                repository.update(2, entity2);

                assert.equal(repository.getId(0), entity);
                assert.equal(repository.getId(1), entity2);
                assert.equal(repository.getId(2), entity2);
                assert.equal(repository.count, 3);
            });
        });

        describe("test delete entity", function () {
            it("should throw if id is non existent", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity = {
                    name: 'Gosho',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Mesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity);
                repository.add(entity2);
                repository.add(entity3);

                expect(() => repository.del(3)).to.throw('Entity with id: 3 does not exist!');
                expect(() => repository.del(-1)).to.throw('Entity with id: -1 does not exist!');
            });
            it("should delete the target entity", function () {
                let properties = {
                    name: "string",
                    age: "number",
                    birthday: "object"
                };
                let repository = new Repository(properties);
                let entity = {
                    name: 'Gosho',
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity2 = {
                    name: "Pesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                let entity3 = {
                    name: "Mesho",
                    age: 22,
                    birthday: new Date(1998, 0, 7)
                };
                repository.add(entity);
                repository.add(entity2);
                repository.add(entity3);
                repository.del(0);

                expect(() => repository.getId(0)).to.throw('Entity with id: 0 does not exist!');
                assert.equal(repository.getId(1), entity2);
                assert.equal(repository.getId(2), entity3);
                assert.equal(repository.count, 2);
            });
        });
    });
});