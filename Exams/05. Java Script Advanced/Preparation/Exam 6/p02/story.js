class Story {
    #comments;
    #likes;
    #dislikes;
    constructor(title, creator) {
        this.title = title;
        this.creator = creator;
        this.#comments = [];
        this.#likes = [];
        this.#dislikes = [];
        this.commentId = 0;
    }
    get likes() {
        if (this.#likes.length === 0) {
            return `${this.title} has 0 likes`;
        } else if (this.#likes.length === 1) {
            return `${this.#likes[0]} likes this story!`;
        }
        return `${this.#likes[0]} and ${this.#likes.length - 1} others like this story!`;
    }
    like(username) {
        if (this.#likes.some(p => p === username)) {
            throw new Error("You can't like the same story twice!");
        } else if (username === this.creator) {
            throw new Error("You can't like your own story!");
        }
        if (this.#dislikes.some(p => p === username)) {
            let target = this.#dislikes.find(p => p === username);
            let targetIndex = this.#dislikes.indexOf(target);
            this.#dislikes.splice(targetIndex, 1);
        }
        this.#likes.push(username);
        return `${username} liked ${this.title}!`;
    }
    dislike(username) {
        if (!this.#likes.some(p => p === username)) {
            throw new Error("You can't dislike this story!");
        } else {
            let target = this.#likes.find(p => p === username);
            let targetIndex = this.#likes.indexOf(target);
            this.#likes.splice(targetIndex, 1);
        }
        this.#dislikes.push(username);
        return `${username} disliked ${this.title}`;
    }
    comment(username, content, id) {
        if (id === undefined || !this.#comments.some(c => c.Id === id)) {
            this.commentId++;
            let comment = {
                Id: this.commentId,
                Username: username,
                Content: content,
                Replies: [],
            }
            this.#comments.push(comment);
            return `${username} commented on ${this.title}`;
        } else if (this.#comments.some(c => c.Id === id)) {
            let target = this.#comments.find(comment => comment.Id === id);
            let reply = {
                Id: target.Id + '.' + (target.Replies.length + 1),
                Username: username,
                Content: content,
            }
            target.Replies.push(reply);
            return `You replied successfully`;
        }
    }
    toString(sortingType) {
        let base = `Title: ${this.title}\nCreator: ${this.creator}\nLikes: ${this.#likes.length}\nComments:`;
        if (this.#comments.length === 0) {
            return base;
        }
        let sort = {
            asc: function (comments) {
                comments.sort((a, b) => a.Id - b.Id);
                comments.forEach(c => c.Replies.sort((a, b) => a.Id.localeCompare(b.Id)));
            },
            desc: function (comments) {
                comments.sort((a, b) => b.Id - a.Id);
                comments.forEach(c => c.Replies.sort((a, b) => b.Id.localeCompare(a.Id)));
            },
            username: function (comments) {
                comments.sort((a, b) => a.Username.localeCompare(b.Username));
                comments.forEach(c => c.Replies.sort((a, b) => a.Username.localeCompare(b.Username)));
            },
        }
        sort[sortingType](this.#comments);

        let result = [];

        result.push(base);

        this.#comments.forEach(c => {
            let commented = `-- ${c.Id}. ${c.Username}: ${c.Content}`;
            result.push(commented);
            if (c.Replies.length > 0) {
                c.Replies.forEach(r => {
                    let reply = `--- ${r.Id}. ${r.Username}: ${r.Content}`;
                    result.push(reply);
                });
            }
        });
        return result.join('\n');
    }
}

let art = new Story("My Story", "Anny");
art.like("John");
console.log(art.likes);
art.dislike("John");
console.log(art.likes);
art.comment("Sammy", "Some Content");
console.log(art.comment("Ammy", "New Content"));
art.comment("Zane", "Reply", 1);
art.comment("Jessy", "Nice :)");
console.log(art.comment("SAmmy", "Reply@", 1));
console.log()
console.log(art.toString('username'));
console.log()
art.like("Zane");
console.log(art.toString('desc'));