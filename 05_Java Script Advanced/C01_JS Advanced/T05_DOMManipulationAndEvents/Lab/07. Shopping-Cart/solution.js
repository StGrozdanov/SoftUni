function solve() {
   const productTitleElements = document.querySelectorAll('.product .product-title');
   const productPriceElements = document.querySelectorAll('.product .product-line-price');
   const addButtonElements = document.querySelectorAll('.product .add-product');
   const textFieldElement = document.querySelector('textarea');
   const checkoutButtonElement = document.querySelector('.checkout');

   let totalPrice = 0;
   let productList = new Set();

   for (let i = 0; i < addButtonElements.length; i++) {
      const addButton = addButtonElements[i];
      const productName = productTitleElements[i].textContent;
      const productPrice = Number(productPriceElements[i].textContent);

      addButton.addEventListener('click', addProductHandler);

      function addProductHandler(){
         const result = `Added ${productName} for ${productPrice.toFixed(2)} to the cart.\n`;
         
         productList.add(productName);
         totalPrice += productPrice;
         textFieldElement.textContent += result;
      }
   }

   checkoutButtonElement.addEventListener('click', checkOutHandler);

   function checkOutHandler(){
      const products = Array.from(productList).join(', ');
      const result = `You bought ${products} for ${totalPrice.toFixed(2)}.`;
      textFieldElement.textContent += result;

      for (const addButton of addButtonElements) {
         addButton.disabled = true;
      }
      
      checkoutButtonElement.disabled = true;
   }
}