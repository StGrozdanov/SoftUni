function loadCommits() {
    const usernameElement = document.getElementById('username');
    const repoElement = document.getElementById('repo');
    const appendTo = document.getElementById('commits');
    const buttonElement = document.querySelector('button');
    const targetUrl = 'https://api.github.com/repos';
    
    buttonElement.addEventListener('click', () => {
        fetch(`${targetUrl}/${usernameElement.value}/${repoElement.value}/commits`)
        .then(response => response.json())
        .then(target => {
                const newLiElement = document.createElement('li');
                newLiElement.textContent = `${target.author.name}: ${target.message}`;
                appendTo.appendChild(newLiElement);
            })
        .catch(error => {
            const newLiElement = document.createElement('li');
            newLiElement.textContent = `Error: ${error.message} (Not Found)`;
            appendTo.appendChild(newLiElement);
          });
    });

}