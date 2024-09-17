package com.healthcare.backend.patient.repository.impl;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.response.APIResponse;
import com.healthcare.backend.patient.repository.PatientRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepoImpl implements PatientRepo {

    private final JdbcTemplate writeJdbcTemplate;
    private final JdbcTemplate readJdbcTemplate;

    public PatientRepoImpl(JdbcTemplate writeJdbcTemplate, JdbcTemplate readJdbcTemplate) {
        this.writeJdbcTemplate = writeJdbcTemplate;
        this.readJdbcTemplate = readJdbcTemplate;
    }

    @Override
    public ResponseEntity<APIResponse> createPatient(Patient patient) {
        return null;
    }

    @Override
    public ResponseEntity<APIResponse> updatePatient(Patient patient) {
        return null;
    }

    @Override
    public ResponseEntity<APIResponse> deletePatient(Long patientId) {
        return null;
    }

    @Override
    public ResponseEntity<APIResponse> retrievePatientDetails(Long patientId) {
        return null;
    }

    @Override
    public ResponseEntity<APIResponse> retrieveAllPatient() {
        return null;
    }
}
