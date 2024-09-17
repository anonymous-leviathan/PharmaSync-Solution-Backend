package com.healthcare.backend.patient.repository;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PatientRepo {
    ResponseEntity<APIResponse> createPatient(Patient patient);

    ResponseEntity<APIResponse> updatePatient(Patient patient);

    ResponseEntity<APIResponse> deletePatient(Long patientId);

    ResponseEntity<APIResponse> retrievePatientDetails(Long patientId);

    ResponseEntity<APIResponse> retrieveAllPatient();
}
