function findLowestPrice(arr){
    const data = [];

    for (let info of arr){
        const tokens = info.split(' | ');
        const townName = tokens[0];
        const productName = tokens[1];
        const productPrice = Number(tokens[2]);

        let product = { name: productName, town: townName, price: productPrice };

        let changePrice = data.find(p => p.name == productName && p.town == townName);
        let target = data.find(p => p.name == productName);

        if (changePrice == undefined && target == undefined){
            data.push(product);
        } else if (changePrice != undefined){
            changePrice.price = productPrice;
        } else if (changePrice == undefined && target != undefined){
            if (target.price > productPrice){
            target.town = townName;
            target.price = productPrice;
            }
        }
    }
    data.forEach(product => console.log(`${product.name} -> ${product.price} (${product.town})`))
}