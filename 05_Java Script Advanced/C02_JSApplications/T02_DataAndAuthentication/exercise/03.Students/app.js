const firstName = document.getElementById('first-name');
const lastName = document.getElementById('last-name');
const facNumber = document.getElementById('fac-number');
const grade = document.getElementById('grade');
const studentList = document.getElementById('student-list');
const submitButton = document.getElementById('submit');
submitButton.addEventListener('click', clickHandler);

function clickHandler(e) {
    e.preventDefault();
    if (firstName.value.trim() != '' && lastName.value.trim() != '' && facNumber.value.trim() != '' && grade.value.trim() != '') {
        const newStudent = {
            firstName: firstName.value,
            lastName: lastName.value,
            facultyNumber: facNumber.value,
            grade: grade.value
        };
        postData(newStudent);
        clearFields();
    }
    getData();
}

async function getData() {
    Array.from(studentList.children).forEach(c => c.remove());

    const response = await fetch('http://localhost:3030/jsonstore/collections/students');
    const data = await response.json();
    Object.values(data).forEach(e => appendStudents(e.firstName, e.lastName, e.facultyNumber, e.grade));
}

function appendStudents(firstName, lastName, facNumber, grade) {
    const resultTr = document.createElement('tr');
    const nameTd = document.createElement('td');
    nameTd.textContent = firstName;
    const lastNameTd = document.createElement('td');
    lastNameTd.textContent = lastName;
    const facNumberTd = document.createElement('td');
    facNumberTd.textContent = facNumber;
    const gradeTd = document.createElement('td');
    grade = Number(grade).toFixed(2);
    gradeTd.textContent = grade;

    resultTr.appendChild(nameTd);
    resultTr.appendChild(lastNameTd);
    resultTr.appendChild(facNumberTd);
    resultTr.appendChild(gradeTd);

    studentList.appendChild(resultTr);
}

async function postData(message) {
    await fetch('http://localhost:3030/jsonstore/collections/students', {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(message),
    });
}

function clearFields() {
    firstName.value = '';
    lastName.value = '';
    facNumber.value = '';
    grade.value = '';
}