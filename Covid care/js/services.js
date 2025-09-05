// Services Management
class ServicesManager {
    constructor() {
        this.services = window.servicesData || [];
        this.selectedService = null;
        this.init();
    }

    init() {
        this.renderServices();
        this.setupBookingForm();
    }

    renderServices() {
        const servicesGrid = document.getElementById('servicesGrid');
        if (!servicesGrid) return;

        servicesGrid.innerHTML = this.services.map(service => `
            <div class="service-card fade-in">
                <div class="service-image">
                    <img src="${service.image}" alt="${service.name}" loading="lazy">
                </div>
                <div class="service-info">
                    <h3 class="service-title">${service.name}</h3>
                    <p class="service-description">${service.description}</p>
                    <div class="service-details">
                        <div class="service-duration">
                            <i class="fas fa-clock"></i>
                            <span>${service.duration}</span>
                        </div>
                        <div class="service-price">$${service.price.toFixed(2)}</div>
                    </div>
                    <button class="book-service-btn" 
                            onclick="servicesManager.openBookingModal('${service.id}')"
                            ${!service.available ? 'disabled' : ''}>
                        <i class="fas fa-calendar"></i>
                        ${service.available ? 'Book Now' : 'Unavailable'}
                    </button>
                </div>
            </div>
        `).join('');
    }

    openBookingModal(serviceId) {
        this.selectedService = this.services.find(s => s.id === serviceId);
        if (!this.selectedService) return;

        const modal = document.getElementById('bookingModal');
        const modalHeader = modal.querySelector('.modal-header h3');
        
        modalHeader.textContent = `Book ${this.selectedService.name}`;
        modal.classList.add('active');
        document.body.style.overflow = 'hidden';

        // Set minimum date to today
        const dateInput = document.getElementById('bookingDate');
        if (dateInput) {
            const today = new Date().toISOString().split('T')[0];
            dateInput.min = today;
        }
    }

    closeBookingModal() {
        const modal = document.getElementById('bookingModal');
        modal.classList.remove('active');
        document.body.style.overflow = '';
        this.selectedService = null;
        this.resetBookingForm();
    }

    setupBookingForm() {
        const form = document.getElementById('bookingForm');
        if (!form) return;

        form.addEventListener('submit', (e) => {
            e.preventDefault();
            this.handleBookingSubmit(e);
        });
    }

    async handleBookingSubmit(e) {
        const formData = new FormData(e.target);
        const bookingData = {
            serviceId: this.selectedService.id,
            serviceName: this.selectedService.name,
            servicePrice: this.selectedService.price,
            customerName: document.getElementById('customerName').value,
            customerPhone: document.getElementById('customerPhone').value,
            customerEmail: document.getElementById('customerEmail').value,
            bookingDate: document.getElementById('bookingDate').value,
            bookingTime: document.getElementById('bookingTime').value,
            notes: document.getElementById('bookingNotes').value,
            timestamp: new Date().toISOString()
        };

        try {
            // Show loading state
            const submitBtn = e.target.querySelector('button[type="submit"]');
            const originalText = submitBtn.innerHTML;
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Booking...';
            submitBtn.disabled = true;

            // Simulate API call
            await this.submitBooking(bookingData);

            // Show success message
            this.showSuccessMessage('Booking submitted successfully! We will contact you shortly.');
            
            // Close modal and reset form
            this.closeBookingModal();

        } catch (error) {
            console.error('Booking error:', error);
            this.showErrorMessage('Failed to submit booking. Please try again.');
        }
    }

    async submitBooking(bookingData) {
        // Simulate API call delay
        await new Promise(resolve => setTimeout(resolve, 2000));
        
        // Store booking in localStorage for demo purposes
        const bookings = JSON.parse(localStorage.getItem('bookings')) || [];
        bookings.push({
            ...bookingData,
            id: Date.now().toString()
        });
        localStorage.setItem('bookings', JSON.stringify(bookings));
        
        console.log('Booking submitted:', bookingData);
    }

    resetBookingForm() {
        const form = document.getElementById('bookingForm');
        if (form) {
            form.reset();
        }
    }

    showSuccessMessage(message) {
        this.showNotification(message, 'success');
    }

    showErrorMessage(message) {
        this.showNotification(message, 'error');
    }

    showNotification(message, type = 'success') {
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.innerHTML = `
            <div class="notification-content">
                <i class="fas fa-${type === 'success' ? 'check-circle' : 'exclamation-circle'}"></i>
                <span>${message}</span>
            </div>
        `;
        
        const bgColor = type === 'success' ? '#10b981' : '#ef4444';
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
            max-width: 300px;
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
        }, 4000);
    }
}

// Initialize services manager
const servicesManager = new ServicesManager();

// Global functions
function openBookingModal(serviceId) {
    servicesManager.openBookingModal(serviceId);
}

function closeBookingModal() {
    servicesManager.closeBookingModal();
}