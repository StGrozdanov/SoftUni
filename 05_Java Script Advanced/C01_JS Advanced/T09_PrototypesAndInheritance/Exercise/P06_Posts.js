function solve(){
    class Post {
        constructor(title, content){
            this.title = title;
            this.content = content;
        }
        toString(){
            return `Post: ${this.title}\nContent: ${this.content}`
        }
    }

    class SocialMediaPost extends Post {
        constructor(title, content, likes, dislikes){
            super(title, content);
            this.likes = likes;
            this.dislikes = dislikes;
            this._comments = [];
        }
        addComment(comment){
            this._comments.push(comment);
        }
        toString(){
            let commentData;
            if (this._comments.length > 0){
                commentData = '\nComments:\n' + this._comments.map(c => c = '*' + c).join('\n').trim();
            }
            return super.toString() + `\nRating: ${this.likes - this.dislikes}${commentData != undefined ? commentData : ' '.trim()}`;
        }
    }

    class BlogPost extends Post {
        constructor(title, content, views){
            super(title, content);
            this.views = views;
        }
        view() {
            this.views += 1;
            return this;
        }
        toString(){
            return super.toString() + `\nViews: ${this.views}`;
        }
    }
    return {Post, SocialMediaPost, BlogPost}
}

const classes = solve();
let post = new classes.Post("Post", "Content");

console.log(post.toString());

// Post: Post
// Content: Content

let scm = new classes.SocialMediaPost("TestTitle", "TestContent", 25, 30);

scm.addComment("Good post");
scm.addComment("Very good post");
scm.addComment("Wow!");

console.log(scm.toString());

// Post: TestTitle
// Content: TestContent
// Rating: -5
// Comments:
//  * Good post
//  * Very good post
//  * Wow!

let blogPost = new classes.BlogPost('Blog Post', 'Content', 5);
console.log(blogPost.toString());
blogPost.view().view().view();
console.log(blogPost.toString());