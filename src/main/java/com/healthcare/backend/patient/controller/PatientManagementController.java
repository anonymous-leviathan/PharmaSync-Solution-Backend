package com.healthcare.backend.patient.controller;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.response.APIResponse;
import com.healthcare.backend.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientManagementController {

    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<APIResponse> createPatient(@RequestBody Patient patient) {
        log.info("Creating patient: {}", patient);
        ResponseEntity<APIResponse> patient1 = patientService.createPatient(patient);
        log.info("Patient created successfully: {}", patient1);
        return patient1;
    }

    public void updatePatient() {
    }

    @DeleteMapping()
    public ResponseEntity<APIResponse> deletePatient(Long patientId) {
        return patientService.deletePatient(patientId);
    }

    public void retrievePatient() {
    }

    @GetMapping()
    public ResponseEntity<APIResponse> retrievePatientDetails() {
        log.info("Retrieving patient details");
        return patientService.retrievePatientDetails();
    }
}
