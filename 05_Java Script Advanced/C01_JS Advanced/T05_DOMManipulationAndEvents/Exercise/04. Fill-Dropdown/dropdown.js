function addItem() {
    const textInputElement = document.getElementById('newItemText');
    const valueInputElement = document.getElementById('newItemValue');

    const text = textInputElement.value;
    const value = valueInputElement.value;

    const optionElement = document.createElement('option');
    optionElement.textContent = text;
    optionElement.value = value;

    const menu = document.getElementById('menu');
    menu.appendChild(optionElement);
    
    textInputElement.value = '';
    valueInputElement.value = '';
}