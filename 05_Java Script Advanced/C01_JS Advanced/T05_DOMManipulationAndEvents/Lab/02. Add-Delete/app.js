function addItem() {
    const newItemElement = document.querySelector('#items');
    const textInputElement = document.querySelector('#newItemText');

    let newContent = document.createElement('li');
    newContent.textContent = textInputElement.value;
    
    let deleteLink = document.createElement('a');
    deleteLink.href = '#';
    deleteLink.textContent = '[Delete]';
    deleteLink.addEventListener('click', (e) => {
        e.target.parentNode.remove();
    });

    newContent.appendChild(deleteLink);
    newItemElement.appendChild(newContent);

    textInputElement.value = '';
}

