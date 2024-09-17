package com.healthcare.backend.patient.service;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PatientService {

    ResponseEntity<APIResponse> createPatient(Patient patient);

    void updatePatient();

    void deletePatient();

    void retrievePatient();

    void retrievePatientDetails();
}
