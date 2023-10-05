const { createApp } = Vue

const url = createApp({
    data() {
        return {
            isOverlayVisible: false,
            buttonTexts: ['Log In', 'Register'],
            currentIndex: 0,
            isLoggedIn: false,
            client: [],
        }
    },
    created() {
        this.loadData()
        this.loadData2()
        setInterval(this.changeButtonText, 2000);
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        if (isLoggedIn === "true") {
            this.isLoggedIn = true;
        } else {
            this.isLoggedIn = false;
        }
    },
    computed: {
        buttonText() {
            return this.buttonTexts[this.currentIndex];
        },
    },
    methods: {
        loadData() {
            axios.get('/api/clients/current')
                .then(response => {
                    this.client = response.data
                    console.log(this.client);
                })
                .catch(error => console.error('Error:', error));
        },
        loadData2() {
            axios.get('/api/client/authenticate')
                .then(response => {
                    if (response.status == 200) {
                        this.isLoggedIn = true;
                    }
                })
                .catch(error => console.error('Error:', error));
        },
        showOverlay() {
            this.isOverlayVisible = true;
        },
        hideOverlay() {
            this.isOverlayVisible = false;
        },
        changeButtonText() {
            this.currentIndex = (this.currentIndex + 1) % this.buttonTexts.length;
        },
        logout() {
            Swal.fire({
                title: 'Do you want to log out of your account?',
                inputAttributes: {
                    autocapitalize: 'off',
                },
                showCancelButton: true,
                confirmButtonText: 'Sure',
                showLoaderOnConfirm: true,
                preConfirm: (login) => {
                    axios.post(`/api/logout`)
                        .then(response => {
                            this.isLoggedIn = false;
                            localStorage.removeItem('isLoggedIn');
                            Swal.fire({
                                icon: 'succes',
                                title: response.data,
                                text: `You have successfully logged out.`,
                                customClass: {
                                    popup: 'custom-alert',
                                }
                            });
                            setTimeout(() => {
                                window.location.href = "../../index.html"
                            }, 2000);
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
                        })
                },
                allowOutsideClick: () => !Swal.isLoading(),
            })
        }
    }
}).mount('#app')
