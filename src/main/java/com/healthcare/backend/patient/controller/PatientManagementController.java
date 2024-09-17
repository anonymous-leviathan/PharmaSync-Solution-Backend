package com.healthcare.backend.patient.controller;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.response.APIResponse;
import com.healthcare.backend.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public void deletePatient() {
    }

    public void retrievePatient() {
    }

    public void retrievePatientDetails() {
    }
}
