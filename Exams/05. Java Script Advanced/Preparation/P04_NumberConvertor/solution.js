function solve() {
    const selectMenuElement = document.getElementById('selectMenuTo');
    const inputNumberElement = document.getElementById('input');

    const hexadecimal = document.createElement('option');
    const binary = document.createElement('option');
    hexadecimal.textContent = 'Hexadecimal';
    hexadecimal.value = 'hexadecimal';
    binary.textContent = 'Binary';
    binary.value = 'binary';
    selectMenuElement.appendChild(hexadecimal);
    selectMenuElement.appendChild(binary);

    let convert;

    const buttonElement = document.querySelector('button');
    const resultElement = document.querySelector('#result');

    buttonElement.addEventListener('click', () => {
        if (selectMenuElement.value == 'binary') {
            convert = dec2bin(inputNumberElement.value);
            resultElement.value = convert;
        } else if (selectMenuElement.value == 'hexadecimal') {
            convert = toHex(inputNumberElement.value);
            resultElement.value = convert;
        }
    });

    function dec2bin(dec) {
        return (dec >>> 0).toString(2);
    }

    function toHex(d) {
        return ("0" + (Number(d).toString(16))).slice(-2).toUpperCase();
    }
}