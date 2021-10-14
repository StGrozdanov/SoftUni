export async function getData(ownerId){
    const options = {
        method: 'get',
        headers: {}
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null){
        options.headers['X-Authorization'] = token;
    } 
    
    const response = await fetch(`http://localhost:3030/data/orders`, options);

    return await response.json();
}

export async function postData(data){
    const options = {
        method: 'post',
        headers: {},
        body: JSON.stringify(data)
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null){
        options.headers['X-Authorization'] = token;
    } 
    
    const response = await fetch(`http://localhost:3030/data/orders`, options);

    return await response.json();
}

export async function deleteData(){
    const options = {
        method: 'delete',
        headers: {}
    };
    const token = sessionStorage.getItem('authToken');
    if (token !== null){
        options.headers['X-Authorization'] = token;
    } 
    
    const response = await fetch(`http://localhost:3030/data/orders/768ced7a-fbd1-456f-8ff5-5218991f7f3c`, options);

    return await response.json();
}