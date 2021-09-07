function solve() {
   document.querySelector('#btnSend').addEventListener('click', onClick);

   function onClick () { 
      let input = JSON.parse(document.querySelector('textarea').value);
      let data = [];

      for (const restourant of input) {
         let tokens = restourant.split(' - ');
         const restourantName = tokens[0];
         let workers = tokens[1].split(', ');

         let restourantData = {
            restourantName: restourantName,
            workers: [],
            avgSalary: () => {
               let sum = 0;
               restourantData.workers.forEach(w => sum += w.workerSalary);
               return sum /= workers.length;
            },
            bestPayedWorker: () => {
               restourantData.workers.sort((w1, w2) => w2.workerSalary - w1.workerSalary);
               return restourantData.workers[0];
            },
            toString: () => {
               return `Name: ${bestRestourant.restourantName} Average Salary: ${bestRestourant.avgSalary().toFixed(2)} Best Salary: ${bestRestourant.bestPayedWorker().workerSalary.toFixed(2)}`;
            }
         }; 

         for (const worker of workers) {
            let tokens = worker.split(' ');
            const workerName = tokens[0];
            const workerSalary = Number(tokens[1]);
            let personal = {
                  workerName: workerName,
                  workerSalary: workerSalary,
                  toString: () => {
                     return `Name: ${personal.workerName} With Salary: ${personal.workerSalary}`;
                  }
               };
               
         const restourant = data.find(r => r.restourantName == restourantName);
         if (restourant !== undefined){
            restourantData = Object.assign({}, restourant);
         } 
            restourantData.workers.push(personal);
            } 
         data.push(restourantData);
      }

      data.sort((r1, r2) => r2.avgSalary() - r1.avgSalary());
      const bestRestourant = data[0];
      const workers = bestRestourant.workers.sort((w1, w2) => w2.workerSalary - w1.workerSalary).join(' ');

      const bestRestourantElement = document.querySelector('#bestRestaurant p');
      const restourantWorkersElement = document.querySelector('#workers p');

      bestRestourantElement.textContent = bestRestourant.toString();
      restourantWorkersElement.textContent = workers;
   }
}
