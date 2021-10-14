const generateRecepiesButton = document.getElementById('generate-recepies');
const appendTo = document.getElementById('list-the-recepies');
generateRecepiesButton.addEventListener('click', generateRecepies);
const baseUrl = 'http://localhost:3030/data';

function generateRecepies(){
    fetch(`${baseUrl}/recipes`)
    .then(response => response.json())
    .then(result => {
        result.forEach(recipe => {
            const newLiItem = document.createElement('li');
            const newLinkItem = document.createElement('a');
            const generateButton = document.createElement('button');
            generateButton.textContent = 'Step By Step Guide';
            //generate step-by-step guide
            generateButton.addEventListener('click', () => {
                const newDivItem = document.createElement('div');
                newDivItem.textContent = recipe.steps;
                newLinkItem.parentNode.appendChild(newDivItem);
            });
            newLiItem.appendChild(generateButton);
            newLinkItem.href = '';
            //generate ingridients
            newLinkItem.addEventListener('click', (e) => {
                e.preventDefault();
                const newDivItem = document.createElement('div');
                newDivItem.textContent = recipe.ingredients;
                newLinkItem.parentNode.appendChild(newDivItem);
            });
            newLinkItem.textContent = recipe.name;
            newLiItem.appendChild(newLinkItem);
            newLiItem.appendChild(newLinkItem);
            appendTo.appendChild(newLiItem);
        });
    });
}