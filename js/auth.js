// Authentication Management
class AuthManager {
    constructor() {
        this.token = localStorage.getItem('token');
        this.user = JSON.parse(localStorage.getItem('user') || '{}');
        this.init();
    }

    init() {
        this.updateAuthUI();
        this.setupAuthListeners();
        this.initializeSampleUsers();
    }

    initializeSampleUsers() {
        // Initialize sample users if not exists
        if (!localStorage.getItem('sampleUsersInitialized')) {
            const sampleUsers = [
                {
                    id: 1,
                    username: 'admin',
                    email: 'admin@covidessentials.com',
                    password: 'admin123',
                    firstName: 'System',
                    lastName: 'Administrator',
                    phoneNumber: '+1-555-0001',
                    address: '123 Admin Street',
                    city: 'Admin City',
                    zipCode: '12345',
                    roles: ['ROLE_ADMIN', 'ROLE_USER'],
                    dateOfBirth: '1980-01-01',
                    gender: 'prefer-not-to-say',
                    emergencyContact: '+1-555-0000'
                },
                {
                    id: 2,
                    username: 'dr.smith',
                    email: 'dr.smith@covidessentials.com',
                    password: 'doctor123',
                    firstName: 'Dr. Sarah',
                    lastName: 'Smith',
                    phoneNumber: '+1-555-0002',
                    address: '456 Medical Center',
                    city: 'Healthcare City',
                    zipCode: '54321',
                    roles: ['ROLE_DOCTOR', 'ROLE_USER'],
                    dateOfBirth: '1975-05-15',
                    gender: 'female',
                    emergencyContact: '+1-555-0003'
                },
                {
                    id: 3,
                    username: 'pharmacist.john',
                    email: 'john@pharmacy.com',
                    password: 'pharma123',
                    firstName: 'John',
                    lastName: 'Pharmacist',
                    phoneNumber: '+1-555-0003',
                    address: '789 Pharmacy Lane',
                    city: 'Medicine Town',
                    zipCode: '67890',
                    roles: ['ROLE_PHARMACIST', 'ROLE_USER'],
                    dateOfBirth: '1985-08-20',
                    gender: 'male',
                    emergencyContact: '+1-555-0004'
                },
                {
                    id: 4,
                    username: 'john.doe',
                    email: 'john.doe@email.com',
                    password: 'client123',
                    firstName: 'John',
                    lastName: 'Doe',
                    phoneNumber: '+1-555-1001',
                    address: '123 Main Street',
                    city: 'Springfield',
                    zipCode: '12345',
                    roles: ['ROLE_USER'],
                    dateOfBirth: '1990-03-15',
                    gender: 'male',
                    emergencyContact: '+1-555-1002'
                },
                {
                    id: 5,
                    username: 'jane.smith',
                    email: 'jane.smith@email.com',
                    password: 'client123',
                    firstName: 'Jane',
                    lastName: 'Smith',
                    phoneNumber: '+1-555-1002',
                    address: '456 Oak Avenue',
                    city: 'Riverside',
                    zipCode: '54321',
                    roles: ['ROLE_USER'],
                    dateOfBirth: '1988-07-22',
                    gender: 'female',
                    emergencyContact: '+1-555-1003'
                }
            ];
            
            localStorage.setItem('sampleUsers', JSON.stringify(sampleUsers));
            localStorage.setItem('sampleUsersInitialized', 'true');
        }
    }

    updateAuthUI() {
        const authButtons = document.querySelector('.auth-buttons');
        const userMenu = document.querySelector('.user-menu');
        
        if (this.isAuthenticated()) {
            // User is logged in
            if (authButtons) {
                authButtons.innerHTML = `
                    <div class="user-menu">
                        <button class="user-btn" onclick="auth.toggleUserMenu()">
                            <i class="fas fa-user-circle"></i>
                            <span>${this.user.firstName || this.user.username}</span>
                            <i class="fas fa-chevron-down"></i>
                        </button>
                        <div class="user-dropdown" id="userDropdown">
                            <a href="profile.html">
                                <i class="fas fa-user"></i> My Profile
                            </a>
                            <a href="#" onclick="auth.showOrders()">
                                <i class="fas fa-shopping-bag"></i> My Orders
                            </a>
                            <a href="#" onclick="auth.showBookings()">
                                <i class="fas fa-calendar"></i> My Bookings
                            </a>
                            ${this.isAdmin() ? '<a href="admin.html"><i class="fas fa-cog"></i> Admin Panel</a>' : ''}
                            <div class="dropdown-divider"></div>
                            <a href="#" onclick="auth.logout()">
                                <i class="fas fa-sign-out-alt"></i> Logout
                            </a>
                        </div>
                    </div>
                `;
            }
        } else {
            // User is not logged in
            if (authButtons) {
                authButtons.innerHTML = `
                    <a href="login.html" class="btn btn-secondary">
                        <i class="fas fa-sign-in-alt"></i> Login
                    </a>
                    <a href="signup.html" class="btn btn-primary">
                        <i class="fas fa-user-plus"></i> Sign Up
                    </a>
                `;
            }
        }
    }

    setupAuthListeners() {
        // Close user dropdown when clicking outside
        document.addEventListener('click', (e) => {
            const userMenu = document.querySelector('.user-menu');
            const dropdown = document.getElementById('userDropdown');
            
            if (userMenu && dropdown && !userMenu.contains(e.target)) {
                dropdown.classList.remove('active');
            }
        });
    }

    isAuthenticated() {
        return this.token && this.user.id;
    }

    isAdmin() {
        return this.user.roles && this.user.roles.includes('ROLE_ADMIN');
    }

    toggleUserMenu() {
        const dropdown = document.getElementById('userDropdown');
        if (dropdown) {
            dropdown.classList.toggle('active');
        }
    }

    // Simulate login API call
    async simulateLogin(usernameOrEmail, password) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                const users = JSON.parse(localStorage.getItem('sampleUsers') || '[]');
                const user = users.find(u => 
                    (u.username === usernameOrEmail || u.email === usernameOrEmail) && 
                    u.password === password
                );
                
                if (user) {
                    const token = 'sample-jwt-token-' + Date.now();
                    const userResponse = {
                        token: token,
                        id: user.id,
                        username: user.username,
                        email: user.email,
                        firstName: user.firstName,
                        lastName: user.lastName,
                        roles: user.roles
                    };
                    resolve(userResponse);
                } else {
                    reject(new Error('Invalid credentials'));
                }
            }, 1000); // Simulate network delay
        });
    }

    // Simulate signup API call
    async simulateSignup(userData) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                const users = JSON.parse(localStorage.getItem('sampleUsers') || '[]');
                
                // Check if username or email already exists
                const existingUser = users.find(u => 
                    u.username === userData.username || u.email === userData.email
                );
                
                if (existingUser) {
                    reject(new Error('Username or email already exists'));
                    return;
                }
                
                // Create new user
                const newUser = {
                    id: users.length + 1,
                    ...userData,
                    roles: ['ROLE_USER']
                };
                
                users.push(newUser);
                localStorage.setItem('sampleUsers', JSON.stringify(users));
                
                resolve({ message: 'User registered successfully!' });
            }, 1000); // Simulate network delay
        });
    }

    async showProfile() {
        // Redirect to profile page
        window.location.href = 'profile.html';
    }

    async showOrders() {
        try {
            const orders = JSON.parse(localStorage.getItem('orders') || '[]')
                .filter(order => order.customer.email === this.user.email);
            
            const ordersHtml = orders.length > 0 ? orders.map(order => `
                <div class="order-item">
                    <div class="order-header">
                        <span class="order-id">Order #${order.orderId}</span>
                        <span class="order-status status-${order.paymentMethod}">${order.paymentMethod.toUpperCase()}</span>
                    </div>
                    <div class="order-details">
                        <p>Date: ${new Date(order.timestamp).toLocaleDateString()}</p>
                        <p>Total: $${order.totals.total.toFixed(2)}</p>
                        <p>Items: ${order.items.length} item(s)</p>
                    </div>
                </div>
            `).join('') : '<p>No orders found. <a href="index.html">Start shopping!</a></p>';
            
            const modal = this.createModal('My Orders', `
                <div class="orders-list">
                    ${ordersHtml}
                </div>
                <div style="margin-top: 1rem; text-align: center;">
                    <a href="profile.html" class="btn btn-primary">View Full Profile</a>
                </div>
            `);
            
            document.body.appendChild(modal);
        } catch (error) {
            console.error('Error fetching orders:', error);
            this.showNotification('Failed to load orders', 'error');
        }
    }

    async showBookings() {
        try {
            const bookings = JSON.parse(localStorage.getItem('bookings') || '[]')
                .filter(booking => booking.customerEmail === this.user.email);
            
            const bookingsHtml = bookings.length > 0 ? bookings.map(booking => `
                <div class="order-item">
                    <div class="order-header">
                        <span class="order-id">${booking.serviceName}</span>
                        <span class="order-status status-confirmed">CONFIRMED</span>
                    </div>
                    <div class="order-details">
                        <p>Date: ${booking.bookingDate}</p>
                        <p>Time: ${booking.bookingTime}</p>
                        <p>Price: $${booking.servicePrice.toFixed(2)}</p>
                    </div>
                </div>
            `).join('') : '<p>No bookings found. <a href="index.html#services">Book a service!</a></p>';
            
            const modal = this.createModal('My Bookings', `
                <div class="orders-list">
                    ${bookingsHtml}
                </div>
                <div style="margin-top: 1rem; text-align: center;">
                    <a href="profile.html" class="btn btn-primary">View Full Profile</a>
                </div>
            `);
            
            document.body.appendChild(modal);
        } catch (error) {
            console.error('Error fetching bookings:', error);
            this.showNotification('Failed to load bookings', 'error');
        }
    }

    createModal(title, content) {
        const modal = document.createElement('div');
        modal.className = 'modal active';
        modal.innerHTML = `
            <div class="modal-content">
                <div class="modal-header">
                    <h3>${title}</h3>
                    <button class="close-modal" onclick="auth.closeModal()">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
                <div class="modal-body">
                    ${content}
                </div>
            </div>
        `;
        return modal;
    }

    closeModal() {
        const modal = document.querySelector('.modal');
        if (modal) {
            document.body.removeChild(modal);
        }
    }

    async updateProfile() {
        const firstName = document.getElementById('profileFirstName').value;
        const lastName = document.getElementById('profileLastName').value;
        
        try {
            // Update user data locally (in a real app, this would be an API call)
            this.user.firstName = firstName;
            this.user.lastName = lastName;
            localStorage.setItem('user', JSON.stringify(this.user));
            
            this.updateAuthUI();
            this.closeModal();
            this.showNotification('Profile updated successfully', 'success');
        } catch (error) {
            console.error('Error updating profile:', error);
            this.showNotification('Failed to update profile', 'error');
        }
    }

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        localStorage.removeItem('userPreferences');
        this.token = null;
        this.user = {};
        this.updateAuthUI();
        this.showNotification('Logged out successfully', 'success');
        
        // Redirect to home page
        if (window.location.pathname.includes('admin.html') || window.location.pathname.includes('profile.html')) {
            window.location.href = 'index.html';
        }
    }

    showNotification(message, type = 'info') {
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.innerHTML = `
            <div class="notification-content">
                <i class="fas fa-${type === 'success' ? 'check-circle' : type === 'error' ? 'exclamation-circle' : 'info-circle'}"></i>
                <span>${message}</span>
            </div>
        `;
        
        const bgColor = type === 'success' ? '#10b981' : type === 'error' ? '#ef4444' : '#3b82f6';
        notification.style.cssText = `
            position: fixed;
            top: 90px;
            right: 20px;
            background: ${bgColor};
            color: white;
            padding: 1rem 1.5rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            z-index: 10000;
            transform: translateX(100%);
            transition: transform 0.3s ease;
        `;
        
        document.body.appendChild(notification);
        
        setTimeout(() => {
            notification.style.transform = 'translateX(0)';
        }, 100);
        
        setTimeout(() => {
            notification.style.transform = 'translateX(100%)';
            setTimeout(() => {
                if (document.body.contains(notification)) {
                    document.body.removeChild(notification);
                }
            }, 300);
        }, 3000);
    }

    // API helper method - now works locally
    async apiCall(url, options = {}) {
        // Simulate API calls locally
        if (url.includes('/api/auth/signin')) {
            const body = JSON.parse(options.body);
            return {
                ok: true,
                json: () => this.simulateLogin(body.usernameOrEmail, body.password)
            };
        } else if (url.includes('/api/auth/signup')) {
            const body = JSON.parse(options.body);
            return {
                ok: true,
                json: () => this.simulateSignup(body)
            };
        }
        
        // For other API calls, return empty data
        return {
            ok: true,
            json: () => Promise.resolve([])
        };
    }
}

// Initialize auth manager
const auth = new AuthManager();

// Global functions
window.auth = auth;