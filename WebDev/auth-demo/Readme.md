# Auth-demo
[demo video](https://user-images.githubusercontent.com/79207707/178149710-8463c9f1-4942-41be-95bc-d6e9dafbeaa4.webm)

- auth-demo is a basic website developed to demonstrate how to implement authentication system using plain and simple HTML, CSS Javascript along with Express framework for backend.
- The project uses MongoDB for the database, and Mogoose ODM.
- WARNING: This code implements no security features (like input sanitization and password encryption) because I trust my users blindly, but it is highly recommended that you do not :)

## Installation
To test the project on your machine simply clone the project or download it as a compressed zip. Then,
- make a .env config file which shall store all the variables needed to use Nodemailer GMail Oauth and MongoDB.
- After this simply run this script:
```
npm run start
```
- It will run the index.js file located in the root of this project directory. This will start uo your local server.
- Visit the url logged in the console to check out the website.
