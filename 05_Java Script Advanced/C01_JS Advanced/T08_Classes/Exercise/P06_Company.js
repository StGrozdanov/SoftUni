class Company {
    constructor(){
        this.departments = {};
    }
    addEmployee(name, salary, position, department){
        this._textValidator(name, position, department);
        this._numValidator(salary);
        class Employee {
            constructor(name, salary, position, department){
                this.name = name;
                this.salary = Number(salary);
                this.position = position;
                this.department = department;
            }
        }
        if (!this.departments.hasOwnProperty(department)) {
            this.departments[department] = [];
        } 
        this.departments[department].push(new Employee(name, salary, position, department));
        return `New employee is hired. Name: ${name}. Position: ${position}`;
    }

    bestDepartment(){
        let department = Object.values(this.departments);
        let bestDepartSalary = 0;
        let bestDepart = '';
        for (const depart of department) {
            let totalSalary = 0;
            for (const employee of depart) {
                totalSalary += employee.salary;
            }
            const avgSalary = totalSalary / depart.length;
            if (avgSalary > bestDepartSalary){
                bestDepartSalary = avgSalary;
                bestDepart = depart[0].department;
            }
        }
        let employees = '';
        let targetDepart = this.departments[bestDepart];
        targetDepart
            .sort((e1, e2) => {
                let result = e2.salary - e1.salary;
                if (result === 0){
                  result = e1.name.localeCompare(e2.name);
                }
                return result;
            })
            .forEach(e => employees += `${e.name} ${e.salary} ${e.position}\n`);
        
        return `Best Department is: ${bestDepart}\nAverage salary: ${bestDepartSalary.toFixed(2)}\n${employees.trim()}`
    }
    _textValidator(...param){
        param.forEach(p => {
        if (p == null || p == undefined || p == ''){
            throw new Error('Invalid input!');
        }
    });
    }
    _numValidator(num){
        if (num <= 0){
            throw new Error('Invalid input!');
        }
    }
}

let c = new Company();
c.addEmployee("Stanimir", 2000, "engineer", "Construction");
c.addEmployee("Pesho", 1500, "electrical engineer", "Construction");
c.addEmployee("Slavi", 500, "dyer", "Construction");
c.addEmployee("Stan", 2000, "architect", "Construction");
c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");
c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");
c.addEmployee("Gosho", 1350, "HR", "Human resources");
console.log(c.bestDepartment());
