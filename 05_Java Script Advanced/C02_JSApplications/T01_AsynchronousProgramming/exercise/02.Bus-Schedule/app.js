function solve() {
    const infoBox = document.querySelector('.info');
    const departButton = document.getElementById('depart');
    const arriveButton = document.getElementById('arrive');

    const stopData = 'http://localhost:3030/jsonstore/bus/schedule';
    let currentStop = {
        name: '',
        next: ''
    };

    fetch(`${stopData}`)
        .then(response => response.json())
        .then(result => {
            currentStop.name = result.depot.name;
            currentStop.next = result.depot.next;
        })
        .catch(function (err) {
            infoBox.textContent = 'Error';
            departButton.disabled = true;
            arriveButton.disabled = true;
        });

    function depart() {
        infoBox.textContent = `Next stop ${currentStop.name}`;
        departButton.disabled = true;
        arriveButton.disabled = false;
    }

    function arrive() {
        fetch(`${stopData}/${currentStop.next}`)
            .then(response => response.json())
            .then(result => {
                infoBox.textContent = `Arriving at ${currentStop.name}`;
                currentStop.name = result.name;
                currentStop.next = result.next;
            })
            .catch(function (err) {
                infoBox.textContent = 'Error';
                departButton.disabled = true;
                arriveButton.disabled = true;
            });
        departButton.disabled = false;
        arriveButton.disabled = true;
    }
    return {
        depart,
        arrive
    };
}

let result = solve();