function loadRepos() {
	const inputFieldElement = document.getElementById('username');
	const buttonElement = document.querySelector('button');
	const appendTo = document.getElementById('repos');
	const baseUrl = 'https://api.github.com/users'

	buttonElement.addEventListener('click', () => {
		appendTo.textContent = '';
		fetch(`${baseUrl}/${inputFieldElement.value}/repos`)
			.then(response => response.json())
			.then(result => {
				result.forEach(repo => {
					const liElement = document.createElement('li');
					const hrefElement = document.createElement('a');
					hrefElement.href = `${repo.html_url}`;
					hrefElement.textContent = `${repo.full_name}`;
					liElement.appendChild(hrefElement);
					appendTo.appendChild(liElement);
				});
			})
			.catch(e => console.log(e));
	});
}