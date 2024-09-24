package com.healthcare.backend.patient.service;

import com.healthcare.backend.domain.entity.Patient;
import com.healthcare.backend.domain.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PatientService {

    /**
     * Creates a new patient in the database, along with their associated appointments if provided.
     *
     * <p>This method takes a {@link Patient} object, validates and ensures the patient status is set
     * (defaults to ACTIVE if not provided), and inserts the patient details into the database.
     * If the patient creation is successful, the patient's appointments (if any) are also inserted into
     * the database. The newly created patient's ID and appointment IDs are set based on the generated keys.</p>
     *
     * <p>If the creation is successful, a 200 (OK) response containing the created patient and their appointments
     * is returned. If there is a failure during the creation process, an error response is returned.</p>
     *
     * @param patient the {@link Patient} object containing patient details and associated appointments to be created.
     * @return ResponseEntity containing the APIResponse with the created patient or an error message if the creation fails.
     *
     * <p>Possible HTTP status codes:</p>
     * <ul>
     *   <li>200 OK - If the patient and their appointments are successfully created.</li>
     *   <li>400 Bad Request - If the patient creation fails.</li>
     *   <li>500 Internal Server Error - If any errors occur during the database insertion process.</li>
     * </ul>
     */
    ResponseEntity<APIResponse> createPatient(Patient patient);

    ResponseEntity<APIResponse> updatePatient();

    /**
     * Deletes a patient and all their associated appointments from the database.
     *
     * <p>This method performs a transactional delete operation where it first deletes
     * all the appointments associated with the given patient ID and then deletes the patient.
     * Both operations are wrapped in a single transaction to ensure that either both
     * operations succeed or neither is applied (rollback on failure).</p>
     *
     * <p>If any error occurs during the deletion process, the transaction is rolled back
     * and an error response is returned. If the deletion is successful, a success message
     * is logged, and a 200 (OK) response is returned.</p>
     *
     * @param patientId the ID of the patient to be deleted.
     * @return ResponseEntity containing the APIResponse indicating the result of the operation.
     *
     * <p>Possible HTTP status codes:</p>
     * <ul>
     *   <li>200 OK - If the patient and their appointments are deleted successfully.</li>
     *   <li>500 Internal Server Error - If any errors occur during the deletion process.</li>
     * </ul>
     */
    ResponseEntity<APIResponse> deletePatient(Long patientId);


    void retrievePatient();

    /**
     * Retrieves details of all patients from the database, along with their associated appointments.
     *
     * <p>This method queries the database to retrieve a list of all patients. For each patient,
     * it also retrieves a list of their associated appointments using their patient ID.
     * The patient and appointment details are then mapped to their respective entities,
     * and a list of patients (with appointments) is returned in the API response.</p>
     *
     * <p>If no patients are found, an error message is logged, and a 404 (Not Found) response is returned.
     * Otherwise, a success message is logged, and a 200 (OK) response with the list of patients is returned.</p>
     *
     * @return ResponseEntity containing the APIResponse with the list of patients and their appointments,
     * or an error response if no patients are found.
     *
     * <p>Possible HTTP status codes:</p>
     * <ul>
     *   <li>200 OK - If the list of patients is successfully retrieved.</li>
     *   <li>404 Not Found - If no patients are found in the database.</li>
     *   <li>500 Internal Server Error - If any errors occur during database querying or processing.</li>
     * </ul>
     */
    ResponseEntity<APIResponse> retrievePatientDetails();
}
