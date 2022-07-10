document.getElementById('signup-form').addEventListener('submit', (e) => {
    e.preventDefault();
    // POST the form data
    let data = {};
    data.name = document.getElementById('name').value;
    data.email = document.getElementById('email').value;
    data.username = document.getElementById('username').value;
    data.password = document.getElementById('password').value;
    axios.post('/api/register', data).then(response => {
        if (response.status === 200 && response.data.success) {
            console.log(response.data.message);
            location.href = "/";
        }
        else if (!response.data.success) {
            // display an error toast
            const toast = document.createElement("div");
            toast.classList.add("error-toast");
            toast.innerHTML = `<h6 class='toast-header'>Error</h6>
            <p class='toast-body'>${response.data.message}</p>
            <hr class='toast-loader'>`;
            document.getElementsByTagName("body")[0].appendChild(toast);
            setTimeout(() => {
                toast.style.transform = 'translateX(calc(100% + 10px))'; // slide it out
                setTimeout(() => {toast.remove();}, 500);
            }, 10000);

            if (response.data.error)
                console.error(response.data.error);
        }
    }).catch(err => {
        console.error(err);
    });
});
