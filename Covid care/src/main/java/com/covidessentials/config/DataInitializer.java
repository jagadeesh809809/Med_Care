package com.covidessentials.config;

import com.covidessentials.model.User;
import com.covidessentials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@covidessentials.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("System");
            admin.setLastName("Administrator");
            admin.setPhoneNumber("+1-555-0001");
            admin.setAddress("123 Admin Street");
            admin.setCity("Admin City");
            admin.setZipCode("12345");
            admin.setDateOfBirth(LocalDate.of(1980, 1, 1));
            admin.setGender("prefer-not-to-say");
            admin.setEmergencyContact("+1-555-0000");
            admin.setRoles(Set.of(User.Role.ADMIN, User.Role.USER));
            userRepository.save(admin);
        }

        // Create sample doctor user
        if (!userRepository.existsByUsername("dr.smith")) {
            User doctor = new User();
            doctor.setUsername("dr.smith");
            doctor.setEmail("dr.smith@covidessentials.com");
            doctor.setPassword(passwordEncoder.encode("doctor123"));
            doctor.setFirstName("Dr. Sarah");
            doctor.setLastName("Smith");
            doctor.setPhoneNumber("+1-555-0002");
            doctor.setAddress("456 Medical Center");
            doctor.setCity("Healthcare City");
            doctor.setZipCode("54321");
            doctor.setDateOfBirth(LocalDate.of(1975, 5, 15));
            doctor.setGender("female");
            doctor.setEmergencyContact("+1-555-0003");
            doctor.setRoles(Set.of(User.Role.DOCTOR, User.Role.USER));
            userRepository.save(doctor);
        }

        // Create sample pharmacist user
        if (!userRepository.existsByUsername("pharmacist.john")) {
            User pharmacist = new User();
            pharmacist.setUsername("pharmacist.john");
            pharmacist.setEmail("john@pharmacy.com");
            pharmacist.setPassword(passwordEncoder.encode("pharma123"));
            pharmacist.setFirstName("John");
            pharmacist.setLastName("Pharmacist");
            pharmacist.setPhoneNumber("+1-555-0003");
            pharmacist.setAddress("789 Pharmacy Lane");
            pharmacist.setCity("Medicine Town");
            pharmacist.setZipCode("67890");
            pharmacist.setDateOfBirth(LocalDate.of(1985, 8, 20));
            pharmacist.setGender("male");
            pharmacist.setEmergencyContact("+1-555-0004");
            pharmacist.setRoles(Set.of(User.Role.PHARMACIST, User.Role.USER));
            userRepository.save(pharmacist);
        }

        // Create sample client users with detailed profiles
        if (!userRepository.existsByUsername("john.doe")) {
            User client1 = new User();
            client1.setUsername("john.doe");
            client1.setEmail("john.doe@email.com");
            client1.setPassword(passwordEncoder.encode("client123"));
            client1.setFirstName("John");
            client1.setLastName("Doe");
            client1.setPhoneNumber("+1-555-1001");
            client1.setAddress("123 Main Street");
            client1.setCity("Springfield");
            client1.setZipCode("12345");
            client1.setDateOfBirth(LocalDate.of(1990, 3, 15));
            client1.setGender("male");
            client1.setEmergencyContact("+1-555-1002");
            client1.setRoles(Set.of(User.Role.USER));
            userRepository.save(client1);
        }

        if (!userRepository.existsByUsername("jane.smith")) {
            User client2 = new User();
            client2.setUsername("jane.smith");
            client2.setEmail("jane.smith@email.com");
            client2.setPassword(passwordEncoder.encode("client123"));
            client2.setFirstName("Jane");
            client2.setLastName("Smith");
            client2.setPhoneNumber("+1-555-1002");
            client2.setAddress("456 Oak Avenue");
            client2.setCity("Riverside");
            client2.setZipCode("54321");
            client2.setDateOfBirth(LocalDate.of(1988, 7, 22));
            client2.setGender("female");
            client2.setEmergencyContact("+1-555-1003");
            client2.setRoles(Set.of(User.Role.USER));
            userRepository.save(client2);
        }

        if (!userRepository.existsByUsername("mike.johnson")) {
            User client3 = new User();
            client3.setUsername("mike.johnson");
            client3.setEmail("mike.johnson@email.com");
            client3.setPassword(passwordEncoder.encode("client123"));
            client3.setFirstName("Mike");
            client3.setLastName("Johnson");
            client3.setPhoneNumber("+1-555-1003");
            client3.setAddress("789 Pine Road");
            client3.setCity("Lakewood");
            client3.setZipCode("67890");
            client3.setDateOfBirth(LocalDate.of(1992, 11, 8));
            client3.setGender("male");
            client3.setEmergencyContact("+1-555-1004");
            client3.setRoles(Set.of(User.Role.USER));
            userRepository.save(client3);
        }

        if (!userRepository.existsByUsername("sarah.wilson")) {
            User client4 = new User();
            client4.setUsername("sarah.wilson");
            client4.setEmail("sarah.wilson@email.com");
            client4.setPassword(passwordEncoder.encode("client123"));
            client4.setFirstName("Sarah");
            client4.setLastName("Wilson");
            client4.setPhoneNumber("+1-555-1004");
            client4.setAddress("321 Elm Street");
            client4.setCity("Greenville");
            client4.setZipCode("13579");
            client4.setDateOfBirth(LocalDate.of(1987, 4, 12));
            client4.setGender("female");
            client4.setEmergencyContact("+1-555-1005");
            client4.setRoles(Set.of(User.Role.USER));
            userRepository.save(client4);
        }

        if (!userRepository.existsByUsername("david.brown")) {
            User client5 = new User();
            client5.setUsername("david.brown");
            client5.setEmail("david.brown@email.com");
            client5.setPassword(passwordEncoder.encode("client123"));
            client5.setFirstName("David");
            client5.setLastName("Brown");
            client5.setPhoneNumber("+1-555-1005");
            client5.setAddress("654 Maple Drive");
            client5.setCity("Hillside");
            client5.setZipCode("24680");
            client5.setDateOfBirth(LocalDate.of(1995, 9, 30));
            client5.setGender("male");
            client5.setEmergencyContact("+1-555-1006");
            client5.setRoles(Set.of(User.Role.USER));
            userRepository.save(client5);
        }

        if (!userRepository.existsByUsername("emily.davis")) {
            User client6 = new User();
            client6.setUsername("emily.davis");
            client6.setEmail("emily.davis@email.com");
            client6.setPassword(passwordEncoder.encode("client123"));
            client6.setFirstName("Emily");
            client6.setLastName("Davis");
            client6.setPhoneNumber("+1-555-1006");
            client6.setAddress("987 Cedar Lane");
            client6.setCity("Brookfield");
            client6.setZipCode("97531");
            client6.setDateOfBirth(LocalDate.of(1993, 12, 5));
            client6.setGender("female");
            client6.setEmergencyContact("+1-555-1007");
            client6.setRoles(Set.of(User.Role.USER));
            userRepository.save(client6);
        }

        System.out.println("Sample users with detailed profiles created successfully!");
        System.out.println("Admin Login: admin / admin123");
        System.out.println("Doctor Login: dr.smith / doctor123");
        System.out.println("Pharmacist Login: pharmacist.john / pharma123");
        System.out.println("Client Login: john.doe / client123");
        System.out.println("All users have comprehensive profile data including:");
        System.out.println("- Personal information (name, email, phone)");
        System.out.println("- Address details");
        System.out.println("- Date of birth and gender");
        System.out.println("- Emergency contact information");
        System.out.println("- Role-based access permissions");
    }
}