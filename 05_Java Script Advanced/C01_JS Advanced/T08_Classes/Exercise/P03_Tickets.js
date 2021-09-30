function factory(arr, sortCriteria)  {
    class Ticket {
        constructor(destination, price, status){
            this.destination = destination;
            this.price = price;
            this.status = status;
        }
    }

    let database = [];

    arr.forEach(input => {
        const tokens = input.split('|');
        const destination = tokens[0];
        const price = Number(tokens[1]);
        const status = tokens[2];

        const ticket = new Ticket(destination, price, status);
        database.push(ticket);
    })

    return database.sort((t1, t2) => {
        let result;
        
        typeof t1[sortCriteria] == 'number' ? result = t1[sortCriteria] - t2[sortCriteria] : 
        result = t1[sortCriteria].localeCompare(t2[sortCriteria]);
        
        return result;
    });
}

let resultArray = factory(['Philadelphia|94.20|available',
        'New York City|95.99|available',
        'New York City|95.99|sold',
        'Boston|126.20|departed'],
    'price');

    console.log(resultArray);

console.log(factory(['Philadelphia|94.20|available',
'New York City|95.99|available',
'New York City|95.99|sold',
'Boston|126.20|departed'],
'destination'));

console.log(factory(['Philadelphia|94.20|available',
 'New York City|95.99|available',
 'New York City|95.99|sold',
 'Boston|126.20|departed'],
'status'));

