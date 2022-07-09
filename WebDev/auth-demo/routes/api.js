const express = require('express');
const router = express.Router();
const google = require('googleapis');
const nodemailer = require('nodemailer')
const User = require('../models/User')

/* The following API routes are handled:
/user/login - request body should be like {username: ..., password: ...}
/user/register - request body should have username, email and password
/user/forgotPassword - request should contain user email, the password will be emailed if user registered with that email-id
/verify:id - verifies the user's email, necessary for login */
const OAuth2Client = new google.Auth.OAuth2Client(process.env.Oauth_Id, process.env.Oauth_Secret, process.env.Oauth_Redirect_URI)
OAuth2Client.setCredentials({ refresh_token: process.env.Oauth_Refresh_Token })
const accessToken = OAuth2Client.getAccessToken()
let transporter = nodemailer.createTransport({
    service: "gmail",
    auth: {
        type: 'OAuth2',
        user: process.env.EMAILER,
        clientId: process.env.Oauth_Id,
        clientSecret: process.env.Oauth_Secret,
        refreshToken: process.env.Oauth_Refresh_Token,
        accessToken: accessToken
    },
});

router.post("/login", async (req, res) => {
    const user = req.body; // no security (sanitization, encryption, etc) implemented!
    try {
        User.findOne({ 'username': user.username }, (err, doc) => {
            if (err)
                throw new Error(err);
            else if (doc == null)
                res.status(200).json({ success: false, message: 'No such user exists !' });
            else if (!doc.verified)
                res.status(200).json({ success: false, message: 'Please verify your email first !' });
            else if (doc.password === user.password)
                res.status(200).json({ success: true, message: 'User logged in !' });
            else
                res.status(200).json({ success: false, message: 'Invalid Credentials !' });
        });
    } catch (e) {
        console.error(e);
        res.status(500).json({ success: false, message: 'Some error occured !' });
    }
});
router.post("/register", async (req, res) => {
    const user = new User(req.body); // no security (sanitization, encryption, etc) implemented!
    try {
        user.save(async (err, doc) => {
            if (err) throw new Error(err);
            // else
            await transporter.sendMail({
                from: "Auth Demo", // sender address
                to: doc.email,
                subject: "Auth Demo - Confirm registration", // Subject line
                text: `Confirm your registeration at http://localhost:3000/api/verify:${doc._id}`,
                html:
                    `<html>
                    <body>
                        <h3 
                            style="
                                padding-top: 30px;
                                padding-bottom: 10px;
                                font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;"
                        >
                            Hi ${doc.username},
                            <br/>
                            To login to your account first complete your verification !
                        </h3>
                        <a 
                            href="http://localhost:3000/api/verify:${doc._id}" 
                            style="
                                background-color: rgb(0, 0, 0);
                                color: rgb(255, 255, 255);
                                padding: 15px 20px;
                                font-size: 0.875rem;
                                border-radius: 3px; 
                                color: white;
                                font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
                                font-weight: 500;
                                line-height: 1.75;
                                letter-spacing: 0.02857em;
                                text-transform: uppercase;
                                text-decoration: none;
                                margin-top: 50px;
                                margin-bottom: 50px"
                        >
                            Confirm your registration
                        </a>
                        <br><br><br>
                        <p 
                            style="
                                padding-top: 30px;
                                padding-bottom: 10px;
                                font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;"
                        >
                            Hi ${doc.username},
                            <br/>
                            To login to your account first complete your verification !
                        </p>
                        <small>This email is automatic, please do not answer it.</small>
                    </body>
                </html>`,
            });

            res.status(200).json({ success: true, message: 'User registered !' });
        });
    } catch (e) {
        console.error(e);
        res.status(500).json({ success: false, message: 'Some error occured !' });
    }
});
router.post("/forgotPassword", async (req, res) => {
    const user = new User(req.body); // no security (sanitization, encryption, etc) implemented!
    try {
        const doc = await User.findOne({ 'email': user.email }).lean();
        if (!doc.verified)
            res.status(200).json({ success: false, message: 'Please verify your email first !' });
        else {
            await transporter.sendMail({
                from: "Auth Demo", // sender address
                to: doc.email,
                subject: "Auth Demo - Revealing password", // Subject line
                text: `Your password is...`,
                html: `<html>
            <body>
                <h3 
                    style="
                        padding-top: 30px;
                        padding-bottom: 10px;
                        font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;"
                >
                    Hi ${doc.username},
                    <br/>
                    Your password is:
                </h3>
                <h5 style="font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;">
                    ${doc.password}
                </h5>
            </body>
            </html>`
            });

            res.status(200).json({ success: true, message: 'Email with password sent !' });
        }
    } catch (e) {
        console.error(e);
        res.status(500).json({ success: false, message: 'Some error occured !' });
    }
});
router.get("/verify:id", async (req, res) => {
    const usr_id = req.params.id;
    try {
        const doc = await User.findByIdAndUpdate(usr_id, { verified: true }).lean();
        if (doc == null)
            res.status(200).json({ success: false, message: 'User not found !' });
        else
            res.status(200).json({ success: true, message: 'User verified !' });
    } catch (e) {
        console.error(e);
        res.status(500).json({ success: false, message: 'Some error occured !' });
    }
});

module.exports = router;
