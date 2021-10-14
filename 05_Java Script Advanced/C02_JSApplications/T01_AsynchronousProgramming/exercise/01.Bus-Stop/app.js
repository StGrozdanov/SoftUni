function getInfo() {
    const inputField = document.getElementById('stopId');
    const button = document.getElementById('submit');
    const stopNameField = document.getElementById('stopName');
    const appendTo = document.getElementById('buses');

    button.addEventListener('click', clickHandler);
    const defaultUrl = 'http://localhost:3030/jsonstore/bus/businfo';

    function clickHandler(e) {
        e.preventDefault();
        if (inputField.value != '') {
            fetch(`${defaultUrl}/${inputField.value}`)
                .then(response => response.json())
                .then(result => {
                    stopNameField.textContent = result.name;
                    Object.entries(result.buses)
                        .forEach(bus => {
                            const newLiItem = document.createElement('li');
                            newLiItem.textContent = `Bus ${bus[0]} arrives in ${bus[1]} minutes`;
                            appendTo.appendChild(newLiItem);
                        });
                })
                .catch(function (error) {
                    stopNameField.textContent = 'Error'
                });
            stopNameField.textContent = '';
            inputField.value = '';
            appendTo.textContent = '';
        }
    }
}