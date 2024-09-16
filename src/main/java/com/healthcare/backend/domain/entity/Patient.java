package com.healthcare.backend.domain.entity;


import com.healthcare.backend.domain.enums.Gender;
import com.healthcare.backend.domain.enums.PatientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotBlank(message = "Address is required")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "City is required")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Country is required")
    @Column(name = "country", nullable = false)
    private String country;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "emergency_contact_name", nullable = false)
    private String emergencyContactName;

    @NotBlank(message = "Emergency contact phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Emergency contact phone number must be 10 digits")
    @Column(name = "emergency_contact_phone", nullable = false)
    private String emergencyContactPhone;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "medical_history", columnDefinition = "TEXT")
    private String medicalHistory;

    @Column(name = "current_medication", columnDefinition = "TEXT")
    private String currentMedication;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "insurance_provider")
    private String insuranceProvider;

    @Column(name = "insurance_policy_number")
    private String insurancePolicyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PatientStatus status;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @Enumerated(EnumType.STRING)
    public PatientStatus patientStatus;
}
