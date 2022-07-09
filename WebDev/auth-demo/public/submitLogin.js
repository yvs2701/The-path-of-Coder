document.getElementById('login-form').addEventListener('submit', (e) => {
    e.preventDefault();
    // POST the form data
    let data = {}
    data.username = document.getElementById('username').value;
    data.password = document.getElementById('password').value;
    axios.post('/api/login', data).then(response => {
        if (response.status === 200 && response.data.success) {
            console.log(response.data.message)
            location.href = "./signup.html";
        }
        else if (!response.data.success) {
            console.log(response.data.message);
            if (response.data.error) 
                console.error(response.data.error);
        }
    }).catch(err => {
        console.error(err);
    });
});

// page exit animation
// document.querySelector('form').style.opacity = 0;
