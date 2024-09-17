package com.healthcare.backend.patient.service;

import com.healthcare.backend.domain.entity.Patient;

public interface PatientService {

    void createPatient(Patient patient);

    void updatePatient();

    void deletePatient();

    void retrievePatient();

    void retrievePatientDetails();
}
