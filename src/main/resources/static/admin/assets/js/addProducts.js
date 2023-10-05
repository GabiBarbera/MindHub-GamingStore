const { createApp } = Vue
const options = {
    data() {
        return {
            components: null,
            category: "",
            brand: "",
            name: "",
            description: "",
            snapshot: null,
            colors: null,
            photos: null,
            price: null,
            stock: null,

        }
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {
            axios.get('/api/components')
                .then(response => {
                    this.components = response.data
                    console.log(this.components);
                }).catch(error => console.log(error))
        },
        alert() {
            let mensaje;
            let object = {
                "category": this.category,
                "brand": this.brand,
                "name": this.name,
                "description": this.description,
                "snapshot": this.snapshot,
                "colors": this.colors,
                "photos": this.photos,
                "price": this.price,
                "stock": this.stock
            }

            let opcion = confirm("Do you want to create a new product?");
            console.log("Hola");
            if (opcion == true) {
                axios.post('/api/components/create', object)
                    .then(response => {
                    })
                    .catch(error => {
                        console.log(error.response);
                        window.alert(error.response.data)
                    })
            } else {
                mensaje = "Cancel";
            }

        },
        logout() {
            axios.post("/api/logout")
                .then(response => console.log('signed out!!'))
            return (window.location.href = "../../index.html")
        }
    }
}
const app = createApp(options)
app.mount('#wrapper')
