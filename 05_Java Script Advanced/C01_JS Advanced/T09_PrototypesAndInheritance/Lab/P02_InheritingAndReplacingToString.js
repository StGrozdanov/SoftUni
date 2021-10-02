function toStringExtension() {
    class Person{
        constructor(name, email){
            this.name = name;
            this.email = email;
        }
        toString(){
            return `${this.constructor.name} (name: ${this.name}, email: ${this.email})`;
        }
    }

    class Teacher extends Person{
        constructor(name, email, subject){
            super(name, email);
            this.subject = subject;
        }
        toString(){
            return super.toString().substring(0, super.toString().length - 1) + `, subject: ${this.subject})`;
        }
    }

    class Student extends Person{
        constructor(name, email, course){
            super(name, email);
            this.course = course;
        }
        toString(){
            return super.toString().substring(0, super.toString().length - 1) + `, course: ${this.course})`;
        }
    }

    return {
        Person,
        Teacher,
        Student
    }
}

let classes = toStringExtension();
let Person = classes.Person;
let Teacher = classes.Teacher;
let Student = classes.Student;

let p = new Person("Pesho",'Pesho@pesh.com');
let t = new Teacher("Ivan",'ivan@ivan.com',"Graphics");
let s = new Student('Stoyan', 'stgrrrr@abv.bg', 'Engineering');

console.log(p.toString());
console.log(t.toString());
console.log(s.toString());