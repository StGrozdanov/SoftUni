window.addEventListener('load', solve);

function solve() {
    const modelField = document.getElementById('model');
    const yearField = document.getElementById('year');
    const descriptionField = document.getElementById('description');
    const priceField = document.getElementById('price');
    const addButton = document.getElementById('add');
    const appendTo = document.getElementById('furniture-list');
    const total = document.querySelector('#information > tfoot > tr > td.total-price');

    addButton.addEventListener('click', (e) => {
        e.preventDefault();
        if (modelField.value.trim() !== '' && descriptionField.value.trim() !== ''
            && yearField.value.trim() !== '' && priceField.value.trim() !== '') {
            let year = Number(yearField.value);
            let price = Number(priceField.value);
            if (year !== NaN && price !== NaN && year >= 0 && price >= 0) {
                const infoRow = createElement('tr', 'info');
                const modelCol = createElement('td', null, modelField.value);
                const priceCol = createElement('td', null, price.toFixed(2));
                const buttonCol = createElement('td');
                const infoButton = createElement('button', 'moreBtn', 'More Info');
                const buyButton = createElement('button', 'buyBtn', 'Buy it');

                const hiddenTr = createElement('tr', 'hide');
                const yearCol = createElement('td', null, `Year: ${year}`);
                const descrCol = createElement('td', null, `Description: ${descriptionField.value}`);
                descrCol.colSpan = 3;

                infoButton.addEventListener('click', () => {
                    if (infoButton.textContent == 'More Info') {
                        infoButton.textContent = 'Less Info';
                        hiddenTr.style.display = 'contents';
                    } else {
                        infoButton.textContent = 'More Info';
                        hiddenTr.style.display = 'none';
                    }
                });

                buyButton.addEventListener('click', () => {
                    let newPrice = Number(priceCol.textContent) + Number(total.textContent);
                    total.textContent = newPrice.toFixed(2);
                    infoRow.remove();
                    hiddenTr.remove();
                });

                buttonCol.appendChild(infoButton);
                buttonCol.appendChild(buyButton);
                infoRow.appendChild(modelCol);
                infoRow.appendChild(priceCol);
                infoRow.appendChild(buttonCol);

                hiddenTr.appendChild(yearCol);
                hiddenTr.appendChild(descrCol);

                appendTo.appendChild(infoRow);
                appendTo.appendChild(hiddenTr);
            }
        }
    });
    function createElement(type, clazz, content) {
        const result = document.createElement(type);
        if (clazz) {
            result.classList.add(clazz);
        } if (content) {
            result.textContent = content;
        }
        return result;
    }
}
