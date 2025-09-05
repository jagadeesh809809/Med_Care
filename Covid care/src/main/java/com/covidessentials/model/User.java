package com.covidessentials.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 200)
    private String address;

    @Size(max = 200)
    private String addressLine2;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String state;

    @Size(max = 10)
    private String zipCode;

    @Size(max = 2)
    private String country = "US";

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 20)
    private String gender;

    @Size(max = 15)
    private String emergencyContact;

    @Size(max = 500)
    private String profilePicture;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "email_verified")
    private Boolean emailVerified = false;

    @Column(name = "phone_verified")
    private Boolean phoneVerified = false;

    @Column(name = "two_factor_enabled")
    private Boolean twoFactorEnabled = false;

    @Size(max = 20)
    private String twoFactorMethod;

    @Size(max = 10)
    private String language = "en";

    @Size(max = 20)
    private String timezone = "UTC-5";

    @Column(name = "email_notifications")
    private Boolean emailNotifications = true;

    @Column(name = "sms_notifications")
    private Boolean smsNotifications = false;

    @Column(name = "marketing_emails")
    private Boolean marketingEmails = false;

    @Column(name = "profile_visibility")
    private Boolean profileVisibility = false;

    @Column(name = "data_sharing")
    private Boolean dataSharing = false;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    // Constructors
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User(String username, String email, String password, String firstName, String lastName) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles.add(Role.USER);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { 
        this.username = username;
        this.updatedAt = LocalDateTime.now();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { 
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { 
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { 
        this.firstName = firstName;
        this.updatedAt = LocalDateTime.now();
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { 
        this.lastName = lastName;
        this.updatedAt = LocalDateTime.now();
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber;
        this.updatedAt = LocalDateTime.now();
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { 
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { 
        this.addressLine2 = addressLine2;
        this.updatedAt = LocalDateTime.now();
    }

    public String getCity() { return city; }
    public void setCity(String city) { 
        this.city = city;
        this.updatedAt = LocalDateTime.now();
    }

    public String getState() { return state; }
    public void setState(String state) { 
        this.state = state;
        this.updatedAt = LocalDateTime.now();
    }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { 
        this.zipCode = zipCode;
        this.updatedAt = LocalDateTime.now();
    }

    public String getCountry() { return country; }
    public void setCountry(String country) { 
        this.country = country;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { 
        this.dateOfBirth = dateOfBirth;
        this.updatedAt = LocalDateTime.now();
    }

    public String getGender() { return gender; }
    public void setGender(String gender) { 
        this.gender = gender;
        this.updatedAt = LocalDateTime.now();
    }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { 
        this.emergencyContact = emergencyContact;
        this.updatedAt = LocalDateTime.now();
    }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { 
        this.profilePicture = profilePicture;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { 
        this.isActive = isActive;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getEmailVerified() { return emailVerified; }
    public void setEmailVerified(Boolean emailVerified) { 
        this.emailVerified = emailVerified;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getPhoneVerified() { return phoneVerified; }
    public void setPhoneVerified(Boolean phoneVerified) { 
        this.phoneVerified = phoneVerified;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getTwoFactorEnabled() { return twoFactorEnabled; }
    public void setTwoFactorEnabled(Boolean twoFactorEnabled) { 
        this.twoFactorEnabled = twoFactorEnabled;
        this.updatedAt = LocalDateTime.now();
    }

    public String getTwoFactorMethod() { return twoFactorMethod; }
    public void setTwoFactorMethod(String twoFactorMethod) { 
        this.twoFactorMethod = twoFactorMethod;
        this.updatedAt = LocalDateTime.now();
    }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { 
        this.language = language;
        this.updatedAt = LocalDateTime.now();
    }

    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { 
        this.timezone = timezone;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { 
        this.emailNotifications = emailNotifications;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getSmsNotifications() { return smsNotifications; }
    public void setSmsNotifications(Boolean smsNotifications) { 
        this.smsNotifications = smsNotifications;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getMarketingEmails() { return marketingEmails; }
    public void setMarketingEmails(Boolean marketingEmails) { 
        this.marketingEmails = marketingEmails;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getProfileVisibility() { return profileVisibility; }
    public void setProfileVisibility(Boolean profileVisibility) { 
        this.profileVisibility = profileVisibility;
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getDataSharing() { return dataSharing; }
    public void setDataSharing(Boolean dataSharing) { 
        this.dataSharing = dataSharing;
        this.updatedAt = LocalDateTime.now();
    }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { 
        this.roles = roles;
        this.updatedAt = LocalDateTime.now();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        if (dateOfBirth == null) return 0;
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public enum Role {
        USER, ADMIN, DOCTOR, PHARMACIST
    }
}