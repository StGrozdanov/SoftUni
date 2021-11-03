import { html, render } from './node_modules/lit-html/lit-html.js';
import { getData } from './requests.js'

const studentsContainer = document.getElementById('students-info');
const inputField = document.getElementById('searchField');
document.querySelector('#searchBtn').addEventListener('click', onClick);

solve();

async function solve() {
   const data = await getData();

   const template = (data) => html`
      <tr>
         <td>${data.firstName} ${data.lastName}</td>
         <td>${data.email}</td>
         <td>${data.course}</td>
      </tr>`;

   const studentList = Object.values(data).map(template);
   render(studentList, studentsContainer)
}

function onClick() {
   const students = document.getElementById('students-info').children;
   unselectAllStudents(students);
   
   if (inputField.value.trim() != '') {
      const searchCriteria = inputField.value;
      findAndSelectStudents(searchCriteria, students);
   }
}

function unselectAllStudents(items) {
   Array.from(items).forEach(i => i.classList.remove('select'));
}

function findAndSelectStudents(criteria, students) {
   Array.from(students)
      .filter(t => t.textContent.toLowerCase().includes(criteria.toLowerCase()))
      .forEach(t => t.classList.add('select'));
   inputField.value = '';
}