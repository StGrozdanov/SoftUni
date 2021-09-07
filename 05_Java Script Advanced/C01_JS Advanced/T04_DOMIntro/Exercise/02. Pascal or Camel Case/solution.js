function solve() {
  const textElement = document.getElementById('text');
  const conventionElement = document.getElementById('naming-convention');

  const text = textElement.value;
  const convention = conventionElement.value;

  let input = text.split(' ');
  let data = input.map(e => e.toLowerCase());

    if (convention === 'Camel Case'){
      data = data.map((e, index) => index > 0 ? e[0].toUpperCase() + e.substring(1) : e);
    } else if (convention === 'Pascal Case') {
      data = data.map(e => e[0].toUpperCase() + e.substring(1));
    } else {
      data = [];
      data.push('Error!');
  }

  const result = data.join('');
  const resultElement = document.getElementById('result');
  resultElement.textContent = result;
}