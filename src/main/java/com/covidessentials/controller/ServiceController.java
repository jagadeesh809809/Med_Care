package com.covidessentials.controller;

import com.covidessentials.model.Service;
import com.covidessentials.model.ServiceBooking;
import com.covidessentials.service.ServiceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    @Autowired
    private ServiceManagementService serviceManagementService;

    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceManagementService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable String id) {
        Service service = serviceManagementService.getServiceById(id);
        if (service != null) {
            return ResponseEntity.ok(service);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        Service createdService = serviceManagementService.createService(service);
        return ResponseEntity.ok(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable String id, @RequestBody Service service) {
        Service updatedService = serviceManagementService.updateService(id, service);
        if (updatedService != null) {
            return ResponseEntity.ok(updatedService);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        boolean deleted = serviceManagementService.deleteService(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/bookings")
    public ResponseEntity<ServiceBooking> createBooking(@RequestBody ServiceBooking booking) {
        ServiceBooking createdBooking = serviceManagementService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<ServiceBooking>> getAllBookings() {
        List<ServiceBooking> bookings = serviceManagementService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<ServiceBooking> getBookingById(@PathVariable String id) {
        ServiceBooking booking = serviceManagementService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/bookings/{id}/status")
    public ResponseEntity<ServiceBooking> updateBookingStatus(@PathVariable String id, @RequestParam String status) {
        ServiceBooking booking = serviceManagementService.updateBookingStatus(id, status);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }
}