package com.covidessentials.service;

import com.covidessentials.model.Service;
import com.covidessentials.model.ServiceBooking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class ServiceManagementService {
    
    private Map<String, Service> services = new HashMap<>();
    private Map<String, ServiceBooking> bookings = new HashMap<>();
    
    public ServiceManagementService() {
        initializeServices();
        initializeSampleBookings();
    }
    
    private void initializeServices() {
        // Initialize with sample data
        services.put("1", new Service("1", "Online Doctor Consultation", 49.99, "30 minutes",
            "Connect with certified doctors via video call for general health consultations",
            "https://images.pexels.com/photos/5327921/pexels-photo-5327921.jpeg?auto=compress&cs=tinysrgb&w=300",
            true, "consultation"));
            
        services.put("2", new Service("2", "Home Medicine Delivery", 5.99, "Same day",
            "Get your prescribed medicines delivered safely to your doorstep",
            "https://images.pexels.com/photos/4386467/pexels-photo-4386467.jpeg?auto=compress&cs=tinysrgb&w=300",
            true, "delivery"));
            
        services.put("3", new Service("3", "Home COVID Testing", 89.99, "45 minutes",
            "Professional COVID-19 testing service at your home with quick results",
            "https://images.pexels.com/photos/3985163/pexels-photo-3985163.jpeg?auto=compress&cs=tinysrgb&w=300",
            true, "testing"));
            
        services.put("4", new Service("4", "Telemedicine Specialist", 79.99, "45 minutes",
            "Consult with medical specialists for specific health concerns",
            "https://images.pexels.com/photos/5327656/pexels-photo-5327656.jpeg?auto=compress&cs=tinysrgb&w=300",
            true, "consultation"));
            
        services.put("5", new Service("5", "Mental Health Support", 59.99, "50 minutes",
            "Professional counseling and mental health support sessions",
            "https://images.pexels.com/photos/7176325/pexels-photo-7176325.jpeg?auto=compress&cs=tinysrgb&w=300",
            true, "mental-health"));
            
        services.put("6", new Service("6", "Emergency Medicine Delivery", 12.99, "2 hours",
            "Urgent delivery of essential medicines within 2 hours",
            "https://images.pexels.com/photos/4386370/pexels-photo-4386370.jpeg?auto=compress&cs=tinysrgb&w=300",
            true, "delivery"));
    }
    
    private void initializeSampleBookings() {
        // Sample Booking 1
        ServiceBooking booking1 = new ServiceBooking();
        booking1.setId("BK-001");
        booking1.setServiceId("1");
        booking1.setServiceName("Online Doctor Consultation");
        booking1.setServicePrice(49.99);
        booking1.setCustomerName("John Doe");
        booking1.setCustomerPhone("+1-555-1001");
        booking1.setCustomerEmail("john.doe@email.com");
        booking1.setBookingDate(LocalDate.now().plusDays(1));
        booking1.setBookingTime(LocalTime.of(10, 30));
        booking1.setNotes("General health checkup and consultation about recent symptoms");
        booking1.setStatus("CONFIRMED");
        bookings.put(booking1.getId(), booking1);

        // Sample Booking 2
        ServiceBooking booking2 = new ServiceBooking();
        booking2.setId("BK-002");
        booking2.setServiceId("3");
        booking2.setServiceName("Home COVID Testing");
        booking2.setServicePrice(89.99);
        booking2.setCustomerName("Jane Smith");
        booking2.setCustomerPhone("+1-555-1002");
        booking2.setCustomerEmail("jane.smith@email.com");
        booking2.setBookingDate(LocalDate.now());
        booking2.setBookingTime(LocalTime.of(14, 0));
        booking2.setNotes("Need COVID test for travel requirements");
        booking2.setStatus("PENDING");
        bookings.put(booking2.getId(), booking2);

        // Sample Booking 3
        ServiceBooking booking3 = new ServiceBooking();
        booking3.setId("BK-003");
        booking3.setServiceId("5");
        booking3.setServiceName("Mental Health Support");
        booking3.setServicePrice(59.99);
        booking3.setCustomerName("Mike Johnson");
        booking3.setCustomerPhone("+1-555-1003");
        booking3.setCustomerEmail("mike.johnson@email.com");
        booking3.setBookingDate(LocalDate.now().plusDays(3));
        booking3.setBookingTime(LocalTime.of(16, 0));
        booking3.setNotes("Stress management and anxiety counseling session");
        booking3.setStatus("CONFIRMED");
        bookings.put(booking3.getId(), booking3);

        // Sample Booking 4
        ServiceBooking booking4 = new ServiceBooking();
        booking4.setId("BK-004");
        booking4.setServiceId("4");
        booking4.setServiceName("Telemedicine Specialist");
        booking4.setServicePrice(79.99);
        booking4.setCustomerName("Sarah Wilson");
        booking4.setCustomerPhone("+1-555-1004");
        booking4.setCustomerEmail("sarah.wilson@email.com");
        booking4.setBookingDate(LocalDate.now().minusDays(1));
        booking4.setBookingTime(LocalTime.of(11, 0));
        booking4.setNotes("Cardiology consultation for heart palpitations");
        booking4.setStatus("COMPLETED");
        bookings.put(booking4.getId(), booking4);

        // Sample Booking 5
        ServiceBooking booking5 = new ServiceBooking();
        booking5.setId("BK-005");
        booking5.setServiceId("2");
        booking5.setServiceName("Home Medicine Delivery");
        booking5.setServicePrice(5.99);
        booking5.setCustomerName("David Brown");
        booking5.setCustomerPhone("+1-555-1005");
        booking5.setCustomerEmail("david.brown@email.com");
        booking5.setBookingDate(LocalDate.now());
        booking5.setBookingTime(LocalTime.of(18, 30));
        booking5.setNotes("Prescription delivery for diabetes medication");
        booking5.setStatus("CONFIRMED");
        bookings.put(booking5.getId(), booking5);

        // Sample Booking 6
        ServiceBooking booking6 = new ServiceBooking();
        booking6.setId("BK-006");
        booking6.setServiceId("6");
        booking6.setServiceName("Emergency Medicine Delivery");
        booking6.setServicePrice(12.99);
        booking6.setCustomerName("Emily Davis");
        booking6.setCustomerPhone("+1-555-1006");
        booking6.setCustomerEmail("emily.davis@email.com");
        booking6.setBookingDate(LocalDate.now().minusDays(2));
        booking6.setBookingTime(LocalTime.of(20, 15));
        booking6.setNotes("Urgent delivery of fever medication for child");
        booking6.setStatus("COMPLETED");
        bookings.put(booking6.getId(), booking6);
    }
    
    public List<Service> getAllServices() {
        return new ArrayList<>(services.values());
    }
    
    public Service getServiceById(String id) {
        return services.get(id);
    }
    
    public Service createService(Service service) {
        String id = UUID.randomUUID().toString();
        service.setId(id);
        services.put(id, service);
        return service;
    }
    
    public Service updateService(String id, Service updatedService) {
        if (services.containsKey(id)) {
            updatedService.setId(id);
            services.put(id, updatedService);
            return updatedService;
        }
        return null;
    }
    
    public boolean deleteService(String id) {
        return services.remove(id) != null;
    }
    
    public ServiceBooking createBooking(ServiceBooking booking) {
        String id = "BK-" + System.currentTimeMillis();
        booking.setId(id);
        bookings.put(id, booking);
        return booking;
    }
    
    public List<ServiceBooking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
    
    public ServiceBooking getBookingById(String id) {
        return bookings.get(id);
    }
    
    public ServiceBooking updateBookingStatus(String id, String status) {
        ServiceBooking booking = bookings.get(id);
        if (booking != null) {
            booking.setStatus(status);
            return booking;
        }
        return null;
    }
}