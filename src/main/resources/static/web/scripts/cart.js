const { createApp } = Vue;

createApp({
    data() {
        return {
            isOverlayVisible: false,
            buttonTexts: ['Log In', 'Register'],
            currentIndex: 0,
            isLoggedIn: false,
            client: [],
            products: [],
            productQty: [],
            localStorage: [],
            localStorageQty: 0,
            carrito: [],
            subtotalPrice: 0,
            total: 0,
            iva: 1.21
        };
    },
    created() {
        this.loadData();
        this.loadData2();
        setInterval(this.changeButtonText, 2000);
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        if (isLoggedIn === "true") {
            this.isLoggedIn = true;
        } else {
            this.isLoggedIn = false;
        }
        this.localStorage = JSON.parse(localStorage.getItem("carritoProductos")) ?? [];
        this.localStorageQty = this.localStorage.length;
        this.carrito = this.localStorage;
        this.createProperty();
    },
    methods: {
        loadData() {
            axios.get("/api/components")
                .then((response) => {
                    this.products = response.data;
                })
                .catch((error) => console.log(error));
        },
        loadData2() {
            axios.get('/api/client/authenticate')
                .then(response => {
                    console.log(response);
                    if (response.status == 200) {
                        this.isLoggedIn = true;
                    }
                })
                .catch(error => console.error('Error:', error));
        },
        createProperty() {
            this.localStorage.map((product) => {
                if (product.qty <= 0 || product.qty == null) {
                    product.qty = 1
                }
            })
        },
        emptyCart() {
            localStorage.clear("carritoProductos");
            this.localStorage = [];
            this.localStorageQty = 0;
            this.carrito = [];
        },
        removeOneItem(index) {
            const item = this.localStorage[index];
            if (item.qty > 1) {
                item.qty--;
                localStorage.setItem("carritoProductos", JSON.stringify(this.localStorage));
            } else if (item.qty == 1) {
                this.localStorage.splice(index, 1);
                localStorage.setItem("carritoProductos", JSON.stringify(this.localStorage));
            }
        },
        plusItem(product) {
            const indexProduct = this.localStorage.findIndex((item) => item.id === product.id)
            if (indexProduct !== -1) {
                this.localStorage[indexProduct].qty++;
            } else {
                this.localStorage.push(product)
            }
            localStorage.setItem("carritoProductos", JSON.stringify(this.localStorage));
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
                    axios.post("/api/logout")
                        .then(response => {
                            this.isLoggedIn = false;
                            localStorage.removeItem('isLoggedIn');
                            Swal.fire({
                                icon: 'succes',
                                title: response.data,
                                text: 'You have successfully logged out',
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
    },
    computed: {
        subTotalCombined() {
            this.subtotalPrice = this.carrito.reduce((total, articulo) => total + (articulo.price * articulo.qty), 0);
        },
        totalClaculated() {
            this.total = this.subtotalPrice * this.iva
        },
        buttonText() {
            return this.buttonTexts[this.currentIndex];
        },
        // changeStorage() {
        //     window.addEventListener('storage', (event) => {
        //         if (event.key === 'carritoProductos') {
        //             this.carrito = JSON.parse(localStorage.getItem('carrito') ?? []);
        //         }
        //     })
        // },
    },
}).mount("#app");