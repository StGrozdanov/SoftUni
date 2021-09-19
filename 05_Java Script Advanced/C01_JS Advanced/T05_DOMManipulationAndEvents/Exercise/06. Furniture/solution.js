function solve() {
  const textInputField = document.querySelector('#exercise > textarea:nth-child(2)');
  const generateButton = document.querySelector('#exercise > button:nth-child(3)');

  generateButton.addEventListener('click', (e) => {
    const input = JSON.parse(textInputField.value);

    for (const order of input) {
      const imgSrc = order.img;
      const name = order.name;
      const price = Number(order.price);
      const decorationFactor = Number(order.decFactor);

      const trElement = document.createElement('tr');
      const tdImgElement = document.createElement('td');
      const imgElement = document.createElement('img');
      imgElement.src = imgSrc;
      
      const tdNameElement = document.createElement('td');
      const pNameElement = document.createElement('p');
      pNameElement.textContent = name;
      
      const tdPriceElement = document.createElement('td');
      const pPriceElement = document.createElement('p');
      pPriceElement.textContent = price;

      const tdDecorationElement = document.createElement('td');
      const pDecorationElement = document.createElement('p');
      pDecorationElement.textContent = decorationFactor;

      const tdCheckBoxElement = document.createElement('td');
      const checkBox = document.createElement('input');
      checkBox.type = 'checkbox';

      tdImgElement.appendChild(imgElement);
      tdNameElement.appendChild(pNameElement);
      tdPriceElement.appendChild(pPriceElement);
      tdDecorationElement.appendChild(pDecorationElement);
      tdCheckBoxElement.appendChild(checkBox);

      trElement.appendChild(tdImgElement);
      trElement.appendChild(tdNameElement);
      trElement.appendChild(tdPriceElement);
      trElement.appendChild(tdDecorationElement);
      trElement.appendChild(tdCheckBoxElement);

      const table = document.querySelector('tbody');
      table.appendChild(trElement);
    }
  });

  const buyButton = document.querySelector('#exercise > button:nth-child(6)');
  let totalPrice = 0;
  let comfort = [];
  let productList = new Set();

  buyButton.addEventListener('click', (e) => {
    const checkBoxes = document.querySelectorAll('input[type="checkbox"]');
    let boxes = Array.from(checkBoxes);
    let checkedBoxes = boxes.filter(b => b.checked);
    let fields = checkedBoxes.map(box => box.parentNode.parentNode);
    for (const field of fields) {
      const furnitureName = field.childNodes[1].textContent;
      const price = Number(field.childNodes[2].textContent);
      const decFactor = Number(field.childNodes[3].textContent);

      productList.add(furnitureName);
      totalPrice += price;
      comfort.push(decFactor);
    }
    const avgDecorationFactor = comfort.reduce((acc, curr, index, arr) => {
      return acc + (curr / arr.length);
    }, 0);

    let products = Array.from(productList).join(', ');

    const result = `Bought furniture: ${products}\nTotal price: ${totalPrice.toFixed(2)}\nAverage decoration factor: ${avgDecorationFactor}`;
    const outputElement = document.querySelector('#exercise > textarea:nth-child(5)');

    outputElement.value = result;
  });
}