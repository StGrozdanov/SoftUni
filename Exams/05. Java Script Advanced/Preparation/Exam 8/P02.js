class ArtGallery {
    constructor(creator) {
        this.creator = creator;
        this.possibleArticles = { "picture": 200, "photo": 50, "item": 250 };
        this.listOfArticles = [];
        this.guests = [];
    }
    addArticle(articleModel, articleName, quantity) {
        quantity = Number(quantity);
        articleModel = articleModel.toLowerCase();
        if (!this.possibleArticles.hasOwnProperty(articleModel)) {
            throw new Error('This article model is not included in this gallery!');
        }
        if (this.listOfArticles.some(a => a.articleName == articleName)) {
            let target = this.listOfArticles.find(a => a.articleName == articleName);
            target.quantity += quantity;
        } else {
            let price = this.possibleArticles[articleModel];
            const article = {
                articleModel: articleModel,
                articleName: articleName,
                quantity: quantity,
                price: price,
            }
            this.listOfArticles.push(article);
        }
        return `Successfully added article ${articleName} with a new quantity- ${quantity}.`;
    }
    inviteGuest(guestName, personality) {
        if (this.guests.some(g => g.guestName == guestName)) {
            throw new Error(`${guestName} has already been invited.`);
        }
        const possiblePoints = {
            'Vip': 500,
            'Middle': 250
        }
        let currPoints = 50;
        if (possiblePoints.hasOwnProperty(personality)) {
            currPoints = possiblePoints[personality];
        }
        let guest = {
            guestName: guestName,
            points: currPoints,
            purchaseArticle: 0
        }
        this.guests.push(guest);
        return `You have successfully invited ${guestName}!`;
    }
    buyArticle(articleModel, articleName, guestName) {
        if (!this.possibleArticles.hasOwnProperty(articleModel) || !this.listOfArticles.some(a => a.articleName == articleName)) {
            throw new Error('This article is not found.');
        }
        const target = this.listOfArticles.find(a => a.articleName == articleName);
        if (target.quantity === 0) {
            return `The ${articleName} is not available.`;
        }
        const guest = this.guests.find(g => g.guestName == guestName);
        if (!guest) {
            return `This guest is not invited.`;
        }
        if (guest.points < target.price) {
            return "You need to more points to purchase the article.";
        }
        guest.points -= target.price;
        target.quantity -= 1;
        guest.purchaseArticle += 1;
        return `${guestName} successfully purchased the article worth ${target.price} points.`;
    }
    showGalleryInfo(criteria) {
        if (criteria == 'article') {
            let crit = ["Articles information:"];
            this.listOfArticles.forEach(a => {
                let r = `${a.articleModel} - ${a.articleName} - ${a.quantity}`;
                crit.push(r);
            });
            return crit.join('\n');
        } else if (criteria == 'guest') {
            let crit = ["Guests information:"];
            this.guests.forEach(g => {
                let r = `${g.guestName} - ${g.purchaseArticle}`;
                crit.push(r);
            });
            return crit.join('\n');
        }
    }
}

const artGallery = new ArtGallery('Curtis Mayfield');
artGallery.addArticle('picture', 'Mona Liza', 3);
artGallery.addArticle('Item', 'Ancient vase', 2);
artGallery.addArticle('picture', 'Mona Liza', 1);
artGallery.inviteGuest('John', 'Vip');
artGallery.inviteGuest('Peter', 'Middle');
artGallery.buyArticle('picture', 'Mona Liza', 'John');
artGallery.buyArticle('item', 'Ancient vase', 'Peter');
console.log(artGallery.showGalleryInfo('article'));
console.log(artGallery.showGalleryInfo('guest'));