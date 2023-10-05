const { createApp } = Vue


createApp({
  data() {
    return {
      color: "black",
      isOverlayVisible: false,
      buttonTexts: ['Log In', 'Register'],
      currentIndex: 0,
      colorChosen: "hello",
      products: [],
      product: [],
      parameter: null,
      params: null,
      id: null,
      mainPhoto: [],
      productCategory: [],
      productBrand: [],
      productPrice: [],
      productColor: [],
      carrito: [],
      cart: 0,
      localStorage: [],
      localStorageQty: 0,
    }
  },
  created() {
    setInterval(this.changeButtonText, 2000);
    this.loadData();
    this.loadData2();
    this.localStorage = JSON.parse(localStorage.getItem("carritoProductos")) || [];
    this.carrito = this.localStorage;
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
      axios({
        method: "get",
        url: '/api/components',
      })
        .then((response) => {
          console.log(response);
          this.products = response.data;
          this.parameter = location.search;
          this.params = new URLSearchParams(this.parameter);
          this.id = this.params.get("parameter");
          this.product = this.products.filter((product) => product.id == this.id);
          this.changePhoto();
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
    addCart(producto) {
      this.cart++;
      this.carrito.push(producto);
      localStorage.setItem("carritoProductos", JSON.stringify(this.carrito));
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
    moveLeft() {
      document.getElementById('slider').scrollLeft += 120;
    },
    moveRight() {
      document.getElementById('slider').scrollLeft -= 120;
    },
    changePhoto() {
      let thumbnails = document.getElementsByClassName('thumbnail');
      let activeImages = document.getElementsByClassName('active');
      for (let i = 0; i < thumbnails.length; i++) {
        thumbnails[i].addEventListener('mouseover', function () {
          if (activeImages.length > 0) {
            activeImages[0].classList.remove('active')
          }
          this.classList.add('active')
          document.getElementById('featured').src = this.src
        })
      }
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


