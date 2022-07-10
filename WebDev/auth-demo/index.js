require('dotenv').config()
const fs = require('fs')
const path = require('path')
const mongoose = require('mongoose')
const User = require('./models/User')
const api = require('./routes/api')
const express = require('express')
const app = express()

console.log('CONNECTING TO DB...');
mongoose.connect(process.env.DB_URI, { useNewUrlParser: true, useUnifiedTopology: true })
console.log('CONNECTED TO DB!!!');

app.use(express.static(path.join(__dirname, '/public')))
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.get('/', (req, res) => {
    res.status(200).sendFile(path.join(__dirname, '/public/index.html'))
})
app.get('/signup', (req, res) => {
    res.status(200).sendFile(path.join(__dirname, '/public/signup.html'))
})
app.get('/forgotPassword', (req, res) => {
    res.status(200).sendFile(path.join(__dirname, '/public/forgotPassword.html'))
})
app.get('/home/:id', (req, res) => {
    const usr_id = req.params.id; // no secuirty (sanitization) implemented !
    User.findById(usr_id, { _id: 0, password: 0 }, (err, doc) => {
        if (err) {
            console.error(err);
            if (err.name === 'CastError') {
                // id is corrupt or contains invalid characters (ascii encoded)
                res.status(400).json({ success: false, message: "Invalid/Corrupted data !" });
            }
            else res.status(500).json({ success: false, message: "Some error occured !" });
        }
        else {
            // render home.html
            fs.readFile('./public/home.html', 'utf-8', (err, data) => {
                if (err)
                    res.status(500).json({ success: false, message: 'Some error occured !' });
                else {
                    data = data.replace('${USERSNAME}', doc.name)
                    .replace('${EMAIL}', doc.email)
                    .replace('${USERNAME}', doc.username);
                    res.setHeader('Content-Type', 'text/html');
                    res.status(200).send(data);
                }
            });
        }
    });
});

app.use('/api', api)

const PORT = process.env.PORT || 3000
app.listen(PORT, () => {
    console.log(`Server listening to http://localhost:${PORT}/`)
})