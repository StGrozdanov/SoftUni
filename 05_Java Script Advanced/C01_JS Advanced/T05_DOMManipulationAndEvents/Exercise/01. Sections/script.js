function create(words) {
   words.forEach(w => {
      const resultElement = document.getElementById('content');
      const divElement = document.createElement('div');
      const paragraph = document.createElement('p');
      const textContent = w;

      paragraph.textContent = textContent;
      paragraph.style.display = 'none';
      divElement.appendChild(paragraph);

      divElement.addEventListener('click', clickHandler);

      resultElement.appendChild(divElement);

      function clickHandler(){
         paragraph.style.display = 'inline-block';
      }
   })
}