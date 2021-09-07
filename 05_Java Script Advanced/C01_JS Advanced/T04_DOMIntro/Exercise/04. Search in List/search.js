function search() {
   const cityElements = document.querySelectorAll('#towns li');
   const inputElement = document.getElementById('searchText');

   let input = inputElement.value;
   let cities = [];

   for (const city of cityElements) {
      cities.push(city.textContent);
   }

   let filteredCities = cities.filter(c => c.includes(input));

   if (filteredCities.length > 0){
      for (const target of filteredCities) {
         const index = cities.indexOf(target);

         cityElements.item(index).style.textDecorationLine = 'underline';
         cityElements.item(index).style.fontWeight = 'bold';
      }
      const resultElement = document.getElementById('result');
      resultElement.textContent = filteredCities.length + ' matches found';
   }
}

