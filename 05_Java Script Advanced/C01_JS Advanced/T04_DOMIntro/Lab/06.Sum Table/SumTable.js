function sumTable() {
    const numberElements = document.querySelectorAll('tr:not(:last-child)  td:nth-child(2)');
    let sum = 0;
    for (const element of numberElements) {
        const number = Number(element.textContent);
        sum += number;
    }

    const sumField = document.getElementById('sum');
    sumField.textContent = sum;
}