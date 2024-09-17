package com.healthcare.backend.patient.controller;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.patient.service.PatientService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("patient-management/")
public class PatientManagementController {


    @NonNull
    private final PatientService patientService;

    @PostMapping("create-patient")
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {

        patientService.createPatient(patient);
        return null;
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
