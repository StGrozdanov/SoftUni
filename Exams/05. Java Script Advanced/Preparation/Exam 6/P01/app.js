function solve() {
   const author = document.getElementById('creator');
   const title = document.getElementById('title');
   const category = document.getElementById('category');
   const content = document.getElementById('content');
   const createButton = document.querySelector('section:nth-child(1) > form > button');
   const posts = document.querySelector('body > div > div > main > section');
   const archive = document.querySelector('body > div > div > aside > section.archive-section > ol');

   createButton.addEventListener('click', (e) => {
      e.preventDefault();

      const article = createElement('article');
      const h1 = createElement('h1', null, title.value);
      const categoryP = createElement('p', null, 'Category:');
      const strongCat = createElement('strong', null, category.value);
      const creatorP = createElement('p', null, 'Creator:');
      const strongCrea = createElement('strong', null, author.value);
      const contentP = createElement('p', null, content.value);
      const buttonDiv = createElement('div', 'buttons');
      const deleteButton = createElement('button', null, 'Delete');
      const archiveButton = createElement('button', null, 'Archive');

      deleteButton.classList.add('btn');
      deleteButton.classList.add('delete');
      archiveButton.classList.add('btn');
      archiveButton.classList.add('archive');

      deleteButton.addEventListener('click', () => { article.remove() });
      archiveButton.addEventListener('click', () => {
         const li = createElement('li', null, h1.textContent);
         archive.appendChild(li);
         sort();
         article.remove();
      });

      buttonDiv.appendChild(deleteButton);
      buttonDiv.appendChild(archiveButton);

      creatorP.appendChild(strongCrea);
      categoryP.appendChild(strongCat);

      article.appendChild(h1);
      article.appendChild(categoryP);
      article.appendChild(creatorP);
      article.appendChild(contentP);
      article.appendChild(buttonDiv);

      posts.appendChild(article);
   });

   function sort() {
      Array.from(archive.children)
         .sort((a, b) => a.textContent.localeCompare(b.textContent))
         .forEach(g => archive.appendChild(g));
   }

   function createElement(type, clazz, content) {
      const result = document.createElement(type);
      if (clazz) {
         result.classList.add(clazz);
      }
      if (content) {
         result.textContent = content;
      }
      return result;
   }
}