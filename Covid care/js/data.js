// Product Data
const products = [
    {
        id: '1',
        name: 'N95 Face Masks (Pack of 10)',
        price: 29.99,
        image: 'https://images.pexels.com/photos/3952241/pexels-photo-3952241.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'masks',
        description: 'Medical-grade N95 masks with 95% filtration efficiency',
        inStock: true,
        rating: 4.8,
        reviews: 324
    },
    {
        id: '2',
        name: 'Hand Sanitizer 500ml',
        price: 12.99,
        image: 'https://images.pexels.com/photos/4039921/pexels-photo-4039921.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'sanitizers',
        description: '70% alcohol-based hand sanitizer, WHO recommended formula',
        inStock: true,
        rating: 4.6,
        reviews: 156
    },
    {
        id: '3',
        name: 'Vitamin C Tablets',
        price: 18.99,
        image: 'https://images.pexels.com/photos/3683107/pexels-photo-3683107.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'medicines',
        description: 'Immune system support with 1000mg Vitamin C',
        inStock: true,
        rating: 4.7,
        reviews: 89
    },
    {
        id: '4',
        name: 'Surgical Face Masks (Pack of 50)',
        price: 19.99,
        image: 'https://images.pexels.com/photos/4386370/pexels-photo-4386370.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'masks',
        description: 'Disposable 3-layer surgical masks with ear loops',
        inStock: true,
        rating: 4.5,
        reviews: 267
    },
    {
        id: '5',
        name: 'Antibacterial Wipes (Pack of 6)',
        price: 24.99,
        image: 'https://images.pexels.com/photos/4033148/pexels-photo-4033148.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'sanitizers',
        description: 'Disinfecting wipes that kill 99.9% of germs',
        inStock: true,
        rating: 4.4,
        reviews: 198
    },
    {
        id: '6',
        name: 'Thermometer (Digital)',
        price: 35.99,
        image: 'https://images.pexels.com/photos/4386467/pexels-photo-4386467.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'medicines',
        description: 'Non-contact infrared thermometer with LCD display',
        inStock: true,
        rating: 4.9,
        reviews: 445
    },
    {
        id: '7',
        name: 'KN95 Masks (Pack of 20)',
        price: 39.99,
        image: 'https://images.pexels.com/photos/3952242/pexels-photo-3952242.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'masks',
        description: 'Premium KN95 masks with 5-layer filtration',
        inStock: true,
        rating: 4.7,
        reviews: 312
    },
    {
        id: '8',
        name: 'Zinc Supplements',
        price: 16.99,
        image: 'https://images.pexels.com/photos/3683111/pexels-photo-3683111.jpeg?auto=compress&cs=tinysrgb&w=300',
        category: 'medicines',
        description: 'Immune support with 15mg zinc per tablet',
        inStock: true,
        rating: 4.3,
        reviews: 67
    }
];

// Services Data
const services = [
    {
        id: '1',
        name: 'Online Doctor Consultation',
        price: 49.99,
        duration: '30 minutes',
        description: 'Connect with certified doctors via video call for general health consultations',
        image: 'https://images.pexels.com/photos/5327921/pexels-photo-5327921.jpeg?auto=compress&cs=tinysrgb&w=300',
        available: true
    },
    {
        id: '2',
        name: 'Home Medicine Delivery',
        price: 5.99,
        duration: 'Same day',
        description: 'Get your prescribed medicines delivered safely to your doorstep',
        image: 'https://images.pexels.com/photos/4386467/pexels-photo-4386467.jpeg?auto=compress&cs=tinysrgb&w=300',
        available: true
    },
    {
        id: '3',
        name: 'Home COVID Testing',
        price: 89.99,
        duration: '45 minutes',
        description: 'Professional COVID-19 testing service at your home with quick results',
        image: 'https://images.pexels.com/photos/3985163/pexels-photo-3985163.jpeg?auto=compress&cs=tinysrgb&w=300',
        available: true
    },
    {
        id: '4',
        name: 'Telemedicine Specialist',
        price: 79.99,
        duration: '45 minutes',
        description: 'Consult with medical specialists for specific health concerns',
        image: 'https://images.pexels.com/photos/5327656/pexels-photo-5327656.jpeg?auto=compress&cs=tinysrgb&w=300',
        available: true
    },
    {
        id: '5',
        name: 'Mental Health Support',
        price: 59.99,
        duration: '50 minutes',
        description: 'Professional counseling and mental health support sessions',
        image: 'https://images.pexels.com/photos/7176325/pexels-photo-7176325.jpeg?auto=compress&cs=tinysrgb&w=300',
        available: true
    },
    {
        id: '6',
        name: 'Emergency Medicine Delivery',
        price: 12.99,
        duration: '2 hours',
        description: 'Urgent delivery of essential medicines within 2 hours',
        image: 'https://images.pexels.com/photos/4386370/pexels-photo-4386370.jpeg?auto=compress&cs=tinysrgb&w=300',
        available: true
    }
];

// Categories
const categories = [
    { id: 'all', name: 'All Products' },
    { id: 'masks', name: 'Face Masks' },
    { id: 'sanitizers', name: 'Sanitizers' },
    { id: 'medicines', name: 'Medicines' }
];

// Export data for use in other files
window.productsData = products;
window.servicesData = services;
window.categoriesData = categories;