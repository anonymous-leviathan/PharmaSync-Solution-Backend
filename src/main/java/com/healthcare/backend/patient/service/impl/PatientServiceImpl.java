package com.healthcare.backend.patient.service.impl;

import com.healthcare.backend.constant.SqlQuery;
import com.healthcare.backend.domain.entity.Appointment;
import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.enums.AppointmentStatus;
import com.healthcare.backend.domain.enums.Gender;
import com.healthcare.backend.domain.enums.PatientStatus;
import com.healthcare.backend.domain.response.APIResponse;
import com.healthcare.backend.patient.service.PatientService;
import com.healthcare.backend.util.ResponseUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    @NonNull
    private final JdbcTemplate writeJdbcTemplate;

    @NonNull
    private final JdbcTemplate readJdbcTemplate;

    @NonNull
    private final ResponseUtil responseUtil;

    public PatientServiceImpl(@NonNull JdbcTemplate writeJdbcTemplate, @NonNull JdbcTemplate readJdbcTemplate, @NonNull ResponseUtil responseUtil) {
        this.writeJdbcTemplate = writeJdbcTemplate;
        this.readJdbcTemplate = readJdbcTemplate;
        this.responseUtil = responseUtil;
    }

    @Override
    public ResponseEntity<APIResponse> createPatient(Patient patient) {
        log.info("logging from PatientRepoImpl createPatient API : {}", patient);

        // Ensure patientStatus is not null
        if (patient.getPatientStatus() == null) {
            patient.setPatientStatus(PatientStatus.ACTIVE);
        }

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        // Insert patient details into the database
        int update = writeJdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.InsertQuery.INSERT_PATIENT, new String[]{"id"});
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            preparedStatement.setString(4, patient.getGender().name());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getCity());
            preparedStatement.setString(7, patient.getCountry());
            preparedStatement.setString(8, patient.getPhoneNumber());
            preparedStatement.setString(9, patient.getEmail());
            preparedStatement.setString(10, patient.getEmergencyContactName());
            preparedStatement.setString(11, patient.getEmergencyContactPhone());
            preparedStatement.setString(12, patient.getBloodType());
            preparedStatement.setString(13, patient.getMedicalHistory());
            preparedStatement.setString(14, patient.getCurrentMedication());
            preparedStatement.setString(15, patient.getAllergies());
            preparedStatement.setString(16, patient.getInsuranceProvider());
            preparedStatement.setString(17, patient.getInsurancePolicyNumber());
            preparedStatement.setString(18, patient.getPatientStatus().name());
            preparedStatement.setTimestamp(19, new Timestamp(System.currentTimeMillis()));
            return preparedStatement;
        }, keyHolder);

        int patientId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        patient.setId((long) patientId);

        if (update == 1) {
            // Insert appointments for the patient
            for (Appointment appointment : patient.getAppointments()) {
                writeJdbcTemplate.update(connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.InsertQuery.CREATE_AND_APPOINTMENT, new String[]{"id"});
                    preparedStatement.setLong(1, patient.getId());
                    preparedStatement.setTimestamp(2, Timestamp.valueOf(appointment.getAppointmentDateTime()));
                    preparedStatement.setString(3, appointment.getReason());
                    preparedStatement.setString(4, appointment.getStatus().name());
                    preparedStatement.setString(5, appointment.getNotes());
                    preparedStatement.setBoolean(6, appointment.getIs_active());
                    preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                    preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                    return preparedStatement;
                }, keyHolder);
                int appointmentId = Objects.requireNonNull(keyHolder.getKey()).intValue();
                appointment.setId((long) appointmentId);
            }
            log.info("Patient created successfully! {}", patient);
            return responseUtil.wrapSuccess(patient, HttpStatus.OK);
        } else {
            log.error("Patient creation failed!");
            return responseUtil.wrapError(patient, "Patient creation failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<APIResponse> updatePatient() {
return null;
    }

    @Override
    public ResponseEntity<APIResponse> deletePatient(Long patientId) {
        try {
            writeJdbcTemplate.execute((ConnectionCallback<Void>) conn -> {
                try {
                    conn.setAutoCommit(false);

                    // Delete appointments associated with the patient
                    try (PreparedStatement psAppointments = conn.prepareStatement(SqlQuery.DeleteQuery.DELETE_APPOINTMENT)) {
                        psAppointments.setLong(1, patientId);
                        psAppointments.executeUpdate();
                    }

                    // Delete the patient record
                    try (PreparedStatement psPatient = conn.prepareStatement(SqlQuery.DeleteQuery.DELETE_PATIENT)) {
                        psPatient.setLong(1, patientId);
                        psPatient.executeUpdate();
                    }

                    conn.commit();
                } catch (Exception e) {
                    conn.rollback();
                    log.warn("Failed to delete patient and their appointments", e);
                    throw new RuntimeException("Failed to delete patient and their appointments", e);
                } finally {
                    conn.setAutoCommit(true);
                }
                return null;
            });

            log.info("Patient and their appointments deleted successfully!");
            return responseUtil.wrapSuccess("Patient and their appointments deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while deleting patient and their appointments!", e);
            return responseUtil.wrapError(null, "Error occurred while deleting patient and their appointments!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public void retrievePatient() {

    }

    @Override
    public ResponseEntity<APIResponse> retrievePatientDetails() {
        List<Patient> patients = readJdbcTemplate.query(SqlQuery.SelectQuery.SELECT_ALL_PATIENTS, (rs, rowNum) -> {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id"));
            patient.setFirstName(rs.getString("first_name"));
            patient.setLastName(rs.getString("last_name"));
            patient.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            patient.setGender(Gender.valueOf(rs.getString("gender")));
            patient.setAddress(rs.getString("address"));
            patient.setCity(rs.getString("city"));
            patient.setCountry(rs.getString("country"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setEmail(rs.getString("email"));
            patient.setEmergencyContactName(rs.getString("emergency_contact_name"));
            patient.setEmergencyContactPhone(rs.getString("emergency_contact_phone"));
            patient.setBloodType(rs.getString("blood_type"));
            patient.setMedicalHistory(rs.getString("medical_history"));
            patient.setCurrentMedication(rs.getString("current_medication"));
            patient.setAllergies(rs.getString("allergies"));
            patient.setInsuranceProvider(rs.getString("insurance_provider"));
            patient.setInsurancePolicyNumber(rs.getString("insurance_policy_number"));
            patient.setPatientStatus(PatientStatus.valueOf(rs.getString("patient_status")));
            patient.setCreated_at(rs.getTimestamp("created_at"));
            patient.setUpdated_at(rs.getTimestamp("updated_at"));
            return patient;
        });

        for (Patient patient : patients) {
            List<Object> args = Collections.singletonList(patient.getId());
            List<Appointment> appointments = readJdbcTemplate.query(SqlQuery.SelectQuery.SELECT_APPOINTMENTS_BY_PATIENT_ID, args.toArray(), (rs, rowNum) -> {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getLong("id"));
                appointment.setAppointmentDateTime(rs.getTimestamp("appointment_date_time").toLocalDateTime());
                appointment.setReason(rs.getString("reason"));
                appointment.setStatus(AppointmentStatus.valueOf(rs.getString("status")));
                appointment.setNotes(rs.getString("notes"));
                appointment.setIs_active(rs.getBoolean("is_active"));
                appointment.setCreated_at(rs.getTimestamp("created_at"));
                appointment.setUpdated_at(rs.getTimestamp("updated_at"));
                return appointment;
            });
            patient.setAppointments(appointments);
        }

        if (patients.isEmpty()) {
            log.error("No patients found!");
            return responseUtil.wrapError(null, "No patients found!", HttpStatus.NOT_FOUND);
        }
        log.info("Patients retrieved successfully! {} ", patients);
        return responseUtil.wrapSuccess(patients, HttpStatus.OK);
    }


}
