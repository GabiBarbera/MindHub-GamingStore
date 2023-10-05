const { createApp } = Vue


createApp({
  data() {
    return {
      showColors: false,
      showBrand: false,
      showCategory: false,
      showPrice: false,
      filterShow: false,
      colors: [],
      prices: [],
      brands: [],
      categories: [],
      products: [],
      productsFiltered: [],
      productCategory: [],
      productBrand: [],
      productPrice: [],
      productColor: [],
      productColorReduced: [],
      productColorFinal: [],
      productsFilteredByNav: [],
      ranges: [{ label: 'Under $100', min: 0, max: 99.99 }, { label: '$100 - $200', min: 100, max: 199.99 }, { label: '$200 - $300', min: 200, max: 299.99 }, { label: 'Over $300', min: 300, max: 1000000 }],
      priceMin: null,
      priceMax: null,
      parameter: null,
      params: null,
      carrito: [],
      cart: 0,
      localStorage: [],
      localStorageQty: 0,
      isOverlayVisible: false,
      buttonTexts: ['Log In', 'Register'],
      currentIndex: 0,
      isLoggedIn: false,
      client: [],
    }
  },
  created() {
    this.loadData3()
    this.loadData()
    this.localStorage = JSON.parse(localStorage.getItem("carritoProductos")) || [];
    this.carrito = this.localStorage;
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    if (isLoggedIn === "true") {
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  },
  methods: {
    loadData3() {
      axios.get('/api/clients/current')
        .then(response => {
          this.client = response.data
          console.log(this.client);
        })
        .catch(error => console.error('Error:', error));
    },
    loadData() {
      axios({
        method: "get",
        url: '/api/components',
      })
        .then((response) => {
          console.log(response);
          this.products = response.data;
          this.parameter = location.search;
          this.params = new URLSearchParams(this.parameter);
          this.categoryChosen = this.params.get("parameter");
          this.productsFiltered = response.data;
          this.SelectedProductsByNav = this.products.filter((product) => product.category == this.categoryChosen)
          this.productCategory = [...new Set(this.products.map((product) => product.category))];
          this.productBrand = [...new Set(this.products.map((product) => product.brand))];
          this.productColor = [...new Set(this.products.map((product) => product.colors))];
          this.productColorReduced = this.productColor.reduce((r, e) => (r.push(...e), r), []);
          this.productColorFinal = [...new Set(this.productColorReduced)].sort();
          this.productPrice = [...new Set(this.products.map((product) => product.price))];
          this.addRange(this.products);
        })
        .catch(error => console.log(error));
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
    colorDropdown() {
      this.showColors = !this.showColors;
    },
    priceDropdown() {
      this.showPrice = !this.showPrice;
    },
    categoryDropdown() {
      this.showCategory = !this.showCategory;
    },
    brandDropdown() {
      this.showBrand = !this.showBrand;
    },
    showFilter() {
      this.filterShow = !this.filterShow;
    },
    clearFilters() {
      this.colors = [],
        this.prices = [],
        this.brands = [],
        this.categories = []
    },
    addRange(products) {
      for (product of products) {
        if (product.price < 100) {
          product.priceRange = "Under $100"
        } else if (product.price >= 100 && product.price < 200) {
          product.priceRange = "$100 - $200"
        } else if (product.price >= 200 && product.price < 300) {
          product.priceRange = "$200 - $300"
        } else if (product.price >= 300) {
          product.priceRange = "Over $300"
        }
      }
    },
    addCart(producto) {
      this.cart++;
      this.carrito.push(producto);
      localStorage.setItem("carritoProductos", JSON.stringify(this.carrito));
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
  },
  computed: {
    priceRange() {
      this.priceRange = this.prices.map(range => range.min)
    },
    filter() {
      this.priceMin = Math.min(...this.prices.map(range => range.min));
      this.priceMax = Math.max(...this.prices.map(range => range.max));
      if (!this.categoryChosen) {
        this.productsFiltered = this.products.filter(
          (product) =>
            (this.brands.includes(product.brand) || this.brands.length == 0) &&
            (this.categories.includes(product.category) || this.categories.length == 0) &&
            (product.colors.some(color => this.colors.includes(color)) || this.colors.length == 0) &&
            (this.prices.includes(product.priceRange) || this.prices.length == 0)
        )
      } else if (this.categoryChosen) {
        this.productsFiltered = this.SelectedProductsByNav.filter(
          (product) =>
            (this.brands.includes(product.brand) || this.brands.length == 0) &&
            (this.categories.includes(product.category) || this.categories.length == 0) &&
            (product.colors.some(color => this.colors.includes(color)) || this.colors.length == 0) &&
            (this.prices.includes(product.priceRange) || this.prices.length == 0)
        )
      }
    },
    buttonText() {
      return this.buttonTexts[this.currentIndex];
    },
  },
}).mount('#app')
