function loadRepos() {
   const buttonElement = document.querySelector('button');
   const resultElement = document.getElementById('res');
   const targetUrl = 'https://api.github.com/users/testnakov';

   buttonElement.addEventListener('click', () => {
      fetch(`${targetUrl}/repos`)
      .then(response => response.json())
      .then(repository => {
         let result = JSON.stringify(repository);
         resultElement.textContent = result;
      })
      .catch(error => console.log(error));
   });
}