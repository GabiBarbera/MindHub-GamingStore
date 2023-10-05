const { createApp } = Vue

const url = createApp({
    data() {
        return {
            registerOn: true,
            cp: null,
            cardHolder: null,
            cardNumber: null,
            expiry: null,
            cvv: null,
            errors: [],
        }
    },
    created() {
    },
    methods: {
        toggleForm() {
            this.registerOn = !this.registerOn;
        },
        checkForm: function (e) {
            this.errors = [];

            if (this.cp == null) {
                this.errors.push("CP is required");
            }
            if (this.cardHolder == null) {
                this.errors.push('Card holder is required');
            }
            if (!this.cardNumber == null) {
                this.errors.push('Card number is required');
            }
            if (this.cvv == null) {
                this.errors.push('Enter a cvv');
            }
            else {
                Swal.fire({
                    icon: 'succes',
                    text: "Your purchase was successful!",
                    confirmButtonText: 'OK',
                    customClass: {
                        popup: 'custom-alert',
                    }
                });
            }
        }
    },

}).mount('#app')
