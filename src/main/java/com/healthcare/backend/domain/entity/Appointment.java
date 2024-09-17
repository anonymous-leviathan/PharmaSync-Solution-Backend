package com.healthcare.backend.domain.entity;

import com.healthcare.backend.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Appointment date and time cannot be null")
    @Future(message = "Appointment date must be in the future")
    @Column(name = "appointment_date_time", nullable = false)
    private LocalDateTime appointmentDateTime;

    @NotBlank(message = "Reason for appointment is required")
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @NotNull(message = "Patient is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "doctor_id")
//    private Doctor doctor;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    public AppointmentStatus appointmentStatus;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @Column(name = "updated_at", insertable = false)
    private Date updated_at;
}
