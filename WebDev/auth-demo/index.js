require('dotenv').config()
const path = require('path')
const mongoose = require('mongoose')
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

app.use('/api', api)

const PORT = process.env.PORT || 3000
app.listen(PORT, () => {
    console.log(`Server listening to http://localhost:${PORT}/`)
})