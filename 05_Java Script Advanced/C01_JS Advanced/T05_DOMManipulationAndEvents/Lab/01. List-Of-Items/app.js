function addItem() {
    const textFieldElement = document.getElementById('newItemText');
    const listItemsElement = document.getElementById('items');
    
    const textField = textFieldElement.value;
    let newContent = document.createElement('li');
    newContent.textContent = textField;
    
    listItemsElement.appendChild(newContent);

    textFieldElement.value = '';
}