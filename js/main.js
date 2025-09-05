// Main Application Logic
class App {
    constructor() {
        this.currentSection = 'products';
        this.init();
    }

    init() {
        this.setupEventListeners();
        this.setupScrollEffects();
        this.setupCheckoutModal();
        this.showSection('products');
    }

    setupEventListeners() {
        // Mobile menu toggle
        window.toggleMobileMenu = () => {
            const mobileMenu = document.getElementById('mobileMenu');
            mobileMenu.classList.toggle('active');
        };

        // Section navigation
        window.showSection = (section) => {
            this.showSection(section);
        };

        // Smooth scrolling
        window.scrollToSection = (sectionId) => {
            const element = document.getElementById(sectionId);
            if (element) {
                element.scrollIntoView({ behavior: 'smooth' });
            }
        };

        // Close modals on escape key
        document.addEventListener('keydown', (e) => {
            if (e.key === 'Escape') {
                this.closeAllModals();
            }
        });

        // Close mobile menu when clicking outside
        document.addEventListener('click', (e) => {
            const mobileMenu = document.getElementById('mobileMenu');
            const menuBtn = document.querySelector('.mobile-menu-btn');
            
            if (mobileMenu.classList.contains('active') && 
                !mobileMenu.contains(e.target) && 
                !menuBtn.contains(e.target)) {
                mobileMenu.classList.remove('active');
            }
        });

        // Header scroll effect
        window.addEventListener('scroll', () => {
            const header = document.querySelector('.header');
            if (window.scrollY > 100) {
                header.style.background = 'rgba(255, 255, 255, 0.98)';
                header.style.backdropFilter = 'blur(20px)';
            } else {
                header.style.background = 'rgba(255, 255, 255, 0.95)';
                header.style.backdropFilter = 'blur(10px)';
            }
        });
    }

    setupScrollEffects() {
        // Intersection Observer for animations
        const observerOptions = {
            threshold: 0.1,
            rootMargin: '0px 0px -50px 0px'
        };

        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('fade-in');
                }
            });
        }, observerOptions);

        // Observe elements that should animate
        const animateElements = document.querySelectorAll('.product-card, .service-card, .feature');
        animateElements.forEach(el => observer.observe(el));
    }

    showSection(section) {
        // Update current section
        this.currentSection = section;

        // Update toggle buttons
        const toggleBtns = document.querySelectorAll('.toggle-btn');
        toggleBtns.forEach(btn => {
            btn.classList.remove('active');
            if (btn.textContent.toLowerCase().includes(section)) {
                btn.classList.add('active');
            }
        });

        // Show/hide sections
        const sections = document.querySelectorAll('.products-section, .services-section');
        sections.forEach(sec => {
            sec.classList.remove('active');
        });

        const targetSection = document.getElementById(section);
        if (targetSection) {
            targetSection.classList.add('active');
        }
    }

    setupCheckoutModal() {
        const checkoutForm = document.getElementById('checkoutForm');
        if (checkoutForm) {
            checkoutForm.addEventListener('submit', (e) => {
                e.preventDefault();
                this.handleCheckout(e);
            });
        }

        // Global functions for checkout
        window.openCheckoutModal = () => {
            this.openCheckoutModal();
        };

        window.closeCheckoutModal = () => {
            this.closeCheckoutModal();
        };
    }

    openCheckoutModal() {
        if (cart.items.length === 0) {
            cart.showNotification('Your cart is empty!');
            return;
        }

        const modal = document.getElementById('checkoutModal');
        const orderSummary = document.getElementById('orderSummary');
        
        // Populate order summary
        orderSummary.innerHTML = `
            <h4>Order Summary</h4>
            <div class="order-items">
                ${cart.items.map(item => `
                    <div class="summary-row">
                        <span>${item.name} x${item.quantity}</span>
                        <span>$${(item.price * item.quantity).toFixed(2)}</span>
                    </div>
                `).join('')}
            </div>
            <div class="summary-row">
                <span>Subtotal</span>
                <span>$${cart.getTotalPrice().toFixed(2)}</span>
            </div>
            <div class="summary-row">
                <span>Shipping</span>
                <span>${cart.getShippingFee() === 0 ? 'Free' : '$' + cart.getShippingFee().toFixed(2)}</span>
            </div>
            <div class="summary-row">
                <span>Tax</span>
                <span>$${cart.getTax().toFixed(2)}</span>
            </div>
            <div class="summary-row total">
                <span>Total</span>
                <span>$${cart.getFinalTotal().toFixed(2)}</span>
            </div>
        `;

        modal.classList.add('active');
        document.body.style.overflow = 'hidden';
    }

    closeCheckoutModal() {
        const modal = document.getElementById('checkoutModal');
        modal.classList.remove('active');
        document.body.style.overflow = '';
        
        // Reset form
        const form = document.getElementById('checkoutForm');
        if (form) {
            form.reset();
        }
    }

    async handleCheckout(e) {
        const submitBtn = e.target.querySelector('button[type="submit"]');
        const originalText = submitBtn.innerHTML;
        
        try {
            // Show loading state
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Processing...';
            submitBtn.disabled = true;

            // Collect form data
            const orderData = {
                items: cart.items,
                customer: {
                    name: document.getElementById('checkoutName').value,
                    email: document.getElementById('checkoutEmail').value,
                    phone: document.getElementById('checkoutPhone').value,
                    address: document.getElementById('checkoutAddress').value,
                    city: document.getElementById('checkoutCity').value,
                    zipCode: document.getElementById('checkoutZip').value
                },
                paymentMethod: document.querySelector('input[name="paymentMethod"]:checked').value,
                totals: {
                    subtotal: cart.getTotalPrice(),
                    shipping: cart.getShippingFee(),
                    tax: cart.getTax(),
                    total: cart.getFinalTotal()
                },
                timestamp: new Date().toISOString(),
                orderId: 'ORD-' + Date.now()
            };

            // Simulate API call
            await this.submitOrder(orderData);

            // Show success message
            cart.showNotification('Order placed successfully! You will receive a confirmation email shortly.');
            
            // Clear cart and close modal
            cart.clearCart();
            this.closeCheckoutModal();

        } catch (error) {
            console.error('Checkout error:', error);
            cart.showNotification('Failed to process order. Please try again.');
        } finally {
            // Restore button state
            submitBtn.innerHTML = originalText;
            submitBtn.disabled = false;
        }
    }

    async submitOrder(orderData) {
        // Simulate API call delay
        await new Promise(resolve => setTimeout(resolve, 3000));
        
        // Store order in localStorage for demo purposes
        const orders = JSON.parse(localStorage.getItem('orders')) || [];
        orders.push(orderData);
        localStorage.setItem('orders', JSON.stringify(orders));
        
        console.log('Order submitted:', orderData);
    }

    closeAllModals() {
        const modals = document.querySelectorAll('.modal');
        modals.forEach(modal => {
            modal.classList.remove('active');
        });
        
        // Close cart
        if (cart.isOpen) {
            cart.toggle();
        }
        
        // Close mobile menu
        const mobileMenu = document.getElementById('mobileMenu');
        mobileMenu.classList.remove('active');
        
        document.body.style.overflow = '';
    }

    showLoading() {
        const spinner = document.getElementById('loadingSpinner');
        if (spinner) {
            spinner.style.display = 'flex';
        }
    }

    hideLoading() {
        const spinner = document.getElementById('loadingSpinner');
        if (spinner) {
            spinner.style.display = 'none';
        }
    }
}

// Initialize app when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    const app = new App();
    
    // Hide loading spinner
    setTimeout(() => {
        app.hideLoading();
    }, 1000);
});

// Service Worker Registration (for PWA capabilities)
if ('serviceWorker' in navigator) {
    window.addEventListener('load', () => {
        navigator.serviceWorker.register('/sw.js')
            .then(registration => {
                console.log('SW registered: ', registration);
            })
            .catch(registrationError => {
                console.log('SW registration failed: ', registrationError);
            });
    });
}