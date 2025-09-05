// Cart Management
class CartManager {
    constructor() {
        this.items = JSON.parse(localStorage.getItem('cartItems')) || [];
        this.isOpen = false;
        this.init();
    }

    init() {
        this.updateCartCount();
        this.renderCart();
    }

    addItem(product) {
        const existingItem = this.items.find(item => item.id === product.id);
        
        if (existingItem) {
            existingItem.quantity += 1;
        } else {
            this.items.push({
                ...product,
                quantity: 1
            });
        }
        
        this.saveToStorage();
        this.updateCartCount();
        this.renderCart();
        this.showNotification(`${product.name} added to cart!`);
    }

    updateQuantity(id, quantity) {
        if (quantity <= 0) {
            this.removeItem(id);
            return;
        }
        
        const item = this.items.find(item => item.id === id);
        if (item) {
            item.quantity = quantity;
            this.saveToStorage();
            this.updateCartCount();
            this.renderCart();
        }
    }

    removeItem(id) {
        this.items = this.items.filter(item => item.id !== id);
        this.saveToStorage();
        this.updateCartCount();
        this.renderCart();
    }

    clearCart() {
        this.items = [];
        this.saveToStorage();
        this.updateCartCount();
        this.renderCart();
    }

    getTotalItems() {
        return this.items.reduce((sum, item) => sum + item.quantity, 0);
    }

    getTotalPrice() {
        return this.items.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    }

    getShippingFee() {
        return this.getTotalPrice() > 50 ? 0 : 9.99;
    }

    getTax() {
        return this.getTotalPrice() * 0.08;
    }

    getFinalTotal() {
        return this.getTotalPrice() + this.getShippingFee() + this.getTax();
    }

    saveToStorage() {
        localStorage.setItem('cartItems', JSON.stringify(this.items));
    }

    updateCartCount() {
        const cartCount = document.getElementById('cartCount');
        if (cartCount) {
            cartCount.textContent = this.getTotalItems();
        }
    }

    renderCart() {
        const cartContent = document.getElementById('cartContent');
        const cartFooter = document.getElementById('cartFooter');
        
        if (!cartContent || !cartFooter) return;

        if (this.items.length === 0) {
            cartContent.innerHTML = `
                <div class="cart-empty">
                    <i class="fas fa-shopping-bag"></i>
                    <p>Your cart is empty</p>
                    <button class="btn btn-primary" onclick="cart.toggle()">
                        Continue Shopping
                    </button>
                </div>
            `;
            cartFooter.innerHTML = '';
            return;
        }

        // Render cart items
        cartContent.innerHTML = this.items.map(item => `
            <div class="cart-item">
                <div class="cart-item-image">
                    <img src="${item.image}" alt="${item.name}">
                </div>
                <div class="cart-item-info">
                    <div class="cart-item-title">${item.name}</div>
                    <div class="cart-item-price">$${item.price.toFixed(2)}</div>
                    <div class="quantity-controls">
                        <button class="quantity-btn" onclick="cart.updateQuantity('${item.id}', ${item.quantity - 1})">
                            <i class="fas fa-minus"></i>
                        </button>
                        <span class="quantity">${item.quantity}</span>
                        <button class="quantity-btn" onclick="cart.updateQuantity('${item.id}', ${item.quantity + 1})">
                            <i class="fas fa-plus"></i>
                        </button>
                    </div>
                </div>
                <button class="remove-item" onclick="cart.removeItem('${item.id}')">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        `).join('');

        // Render cart summary
        const subtotal = this.getTotalPrice();
        const shipping = this.getShippingFee();
        const tax = this.getTax();
        const total = this.getFinalTotal();

        cartFooter.innerHTML = `
            <div class="cart-summary">
                <div class="summary-row">
                    <span>Subtotal</span>
                    <span>$${subtotal.toFixed(2)}</span>
                </div>
                <div class="summary-row">
                    <span>Shipping</span>
                    <span>${shipping === 0 ? 'Free' : '$' + shipping.toFixed(2)}</span>
                </div>
                <div class="summary-row">
                    <span>Tax</span>
                    <span>$${tax.toFixed(2)}</span>
                </div>
                <div class="summary-row total">
                    <span>Total</span>
                    <span>$${total.toFixed(2)}</span>
                </div>
            </div>
            <button class="checkout-btn" onclick="openCheckoutModal()">
                <i class="fas fa-credit-card"></i>
                Checkout - $${total.toFixed(2)}
            </button>
        `;
    }

    toggle() {
        this.isOpen = !this.isOpen;
        const sidebar = document.getElementById('cartSidebar');
        const overlay = document.getElementById('cartOverlay');
        
        if (this.isOpen) {
            sidebar.classList.add('active');
            overlay.classList.add('active');
            document.body.style.overflow = 'hidden';
        } else {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
            document.body.style.overflow = '';
        }
    }

    showNotification(message) {
        // Create notification element
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.innerHTML = `
            <div class="notification-content">
                <i class="fas fa-check-circle"></i>
                <span>${message}</span>
            </div>
        `;
        
        // Add styles
        notification.style.cssText = `
            position: fixed;
            top: 90px;
            right: 20px;
            background: #10b981;
            color: white;
            padding: 1rem 1.5rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            z-index: 10000;
            transform: translateX(100%);
            transition: transform 0.3s ease;
        `;
        
        document.body.appendChild(notification);
        
        // Animate in
        setTimeout(() => {
            notification.style.transform = 'translateX(0)';
        }, 100);
        
        // Remove after 3 seconds
        setTimeout(() => {
            notification.style.transform = 'translateX(100%)';
            setTimeout(() => {
                document.body.removeChild(notification);
            }, 300);
        }, 3000);
    }
}

// Initialize cart
const cart = new CartManager();

// Global functions for cart
function toggleCart() {
    cart.toggle();
}

function addToCart(productId) {
    const product = window.productsData.find(p => p.id === productId);
    if (product && product.inStock) {
        cart.addItem(product);
    }
}