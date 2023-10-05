const { createApp } = Vue


createApp({
    data() {
        return {
            color: "black",
            isOverlayVisible: false,
            buttonTexts: ['Log In', 'Register'],
            currentIndex: 0,
            carrito: [],
            cart: 0,
            localStorage: [],
            localStorageQty: 0,
            client: [],
        }
    },
    created() {
        this.loadData();
        this.loadData2();
        setInterval(this.changeButtonText, 2000);
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
            axios.get('/api/clients/current')
                .then(response => {
                    this.client = response.data
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
        addCart(producto) {
            this.cart++;
            this.carrito.push(producto);
            localStorage.setItem("carritoProductos", JSON.stringify(this.carrito));
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

const carousel = document.querySelector(".carousel"),
    firstImg = carousel.querySelectorAll("img")[0],
    arrowIcons = document.querySelectorAll(".wrapper i");

let isDragStart = false, isDragging = false, prevPageX, prevScrollLeft, positionDiff;

const showHideIcons = () => {
    // showing and hiding prev/next icon according to carousel scroll left value
    let scrollWidth = carousel.scrollWidth - carousel.clientWidth; // getting max scrollable width
    arrowIcons[0].style.display = carousel.scrollLeft == 0 ? "none" : "block";
    arrowIcons[1].style.display = carousel.scrollLeft == scrollWidth ? "none" : "block";
}
arrowIcons.forEach(icon => {
    icon.addEventListener("click", () => {
        let firstImgWidth = firstImg.clientWidth + 14; // getting first img width & adding 14 margin value
        // if clicked icon is left, reduce width value from the carousel scroll left else add to it
        carousel.scrollLeft += icon.id == "left" ? -firstImgWidth : firstImgWidth;
        setTimeout(() => showHideIcons(), 60); // calling showHideIcons after 60ms
    });
});

const autoSlide = () => {
    // if there is no image left to scroll then return from here
    if (carousel.scrollLeft - (carousel.scrollWidth - carousel.clientWidth) > -1 || carousel.scrollLeft <= 0) return;

    positionDiff = Math.abs(positionDiff); // making positionDiff value to positive
    let firstImgWidth = firstImg.clientWidth + 14;
    // getting difference value that needs to add or reduce from carousel left to take middle img center
    let valDifference = firstImgWidth - positionDiff;

    if (carousel.scrollLeft > prevScrollLeft) { // if user is scrolling to the right
        return carousel.scrollLeft += positionDiff > firstImgWidth / 3 ? valDifference : -positionDiff;
    }
    // if user is scrolling to the left
    carousel.scrollLeft -= positionDiff > firstImgWidth / 3 ? valDifference : -positionDiff;
}

const dragStart = (e) => {
    // updatating global variables value on mouse down event
    isDragStart = true;
    prevPageX = e.pageX || e.touches[0].pageX;
    prevScrollLeft = carousel.scrollLeft;
}

const dragging = (e) => {
    // scrolling images/carousel to left according to mouse pointer
    if (!isDragStart) return;
    e.preventDefault();
    isDragging = true;
    carousel.classList.add("dragging");
    positionDiff = (e.pageX || e.touches[0].pageX) - prevPageX;
    carousel.scrollLeft = prevScrollLeft - positionDiff;
    showHideIcons();
}

const dragStop = () => {
    isDragStart = false;
    carousel.classList.remove("dragging");

    if (!isDragging) return;
    isDragging = false;
    autoSlide();
}

carousel.addEventListener("mousedown", dragStart);
carousel.addEventListener("touchstart", dragStart);

document.addEventListener("mousemove", dragging);
carousel.addEventListener("touchmove", dragging);

document.addEventListener("mouseup", dragStop);
carousel.addEventListener("touchend", dragStop);

// galeria de imagenes 

