function solve(input){
    let elements = JSON.parse(input);
    let html = [];
    html.push(
     "<table>",
     "<tr>"
    )

    Object.keys(elements[0]).forEach(e => html.push(`<th>${e}</th>`));

    html.push("</tr>", "<tr>");

    for(let object of elements){
        for(let value of Object.values(object)){
            html.push(`<td>${value}</td>`)
        }
    }
    html.push("</tr>", "</table>");
    
    let result = html.join("/n");
    console.log(result);
}



solve('[{"Name":"Pesho","Score":4," Grade":8},{"Name":"Gosho","Score":5," Grade":8},{"Name":"Angel","Score":5.50," Grade":10}]');