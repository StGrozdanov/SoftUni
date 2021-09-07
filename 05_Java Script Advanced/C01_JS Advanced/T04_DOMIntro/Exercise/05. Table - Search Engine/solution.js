function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   function onClick() {
   let studentInfoElement = document.querySelectorAll('tbody tr');
   const inputElement = document.getElementById('searchField');

   let input = inputElement.value;
   let studentInfo = [];

   for (const student of studentInfoElement) {
      student.classList.remove('select');
      studentInfo.push(student.textContent);
   }

   let filteredStudents = studentInfo.filter(c => c.includes(input) && input != '');
   
   if (filteredStudents.length > 0){
      for (const target of filteredStudents) {
         const index = studentInfo.indexOf(target);
         studentInfoElement.item(index).className = 'select';
      }
   }
   inputElement.value = '';
   }
}