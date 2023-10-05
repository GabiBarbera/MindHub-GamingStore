const { createApp } = Vue

const url = createApp({
    data() {
        return {
            firstName: "",
            lastName: "",
            registerOn: true,
            email: "",
            password: "",
            error_msg: "",
        }
    },
    created() {

    },
    methods: {
        toggleForm() {
            this.registerOn = !this.registerOn;
        },
        login() {
            axios.post('/api/login', "email=" + this.email + "&password=" + this.password)
                .then(response => {
                    console.error('Error:', response);
                    localStorage.setItem('isLoggedIn', true);
                    if (this.email == "admin@admin.com") {
                        return window.location.href = "/admin/blank.html"
                    } else {
                        return window.location.href = "../../index.html"
                    }
                }).catch(error => {
                    console.log(error);
                    Swal.fire({
                        icon: 'error',
                        text: "Incorrect email or password",
                        confirmButtonText: 'OK',
                        customClass: {
                            popup: 'custom-alert',
                        }
                    });
                })
        },
        register() {
            axios
                .post(
                    "/api/clients/register", `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`)
                .then((response) => {
                    this.login()
                })
                .catch(error => {
                    console.error('Error:', error);
                    Swal.fire({
                        icon: 'error',
                        title: error.response.data,
                        confirmButtonText: 'OK',
                        customClass: {
                            popup: 'custom-alert',
                        }
                    });
                });
        }
    },
}
).mount('#app')
