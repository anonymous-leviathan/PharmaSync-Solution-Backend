package com.healthcare.backend.constant;

/**
 * This holds all the queries related to pharma-sync-data saving, fetching , deleting and updating.
 *
 * @author Maneesha Gunawardhana
 * @project pharma-sync-erp-solutions
 * @created on 09/17/2024
 */
public class SqlQuery {
    private SqlQuery() {
    }

    /**
     * This holds all the select queries
     */
    public static class SelectQuery {
        // Query to select all patients
        public static final String SELECT_ALL_PATIENTS = "SELECT id, first_name, last_name, date_of_birth, gender, address, city, country, phone_number, email, " + "emergency_contact_name, emergency_contact_phone, blood_type, medical_history, current_medication, " + "allergies, insurance_provider, insurance_policy_number, patient_status, created_at, updated_at " + "FROM patients";

        // Query to select appointments by patient ID
        public static final String SELECT_APPOINTMENTS_BY_PATIENT_ID = "SELECT id, patient_id, appointment_date_time, reason, status, notes, is_active, created_at, updated_at " + "FROM appointments WHERE patient_id = ?";

        // Query to check if patient exists
        public static final String CHECK_PATIENT_EXISTS_BY_ID = "SELECT COUNT(*) FROM patients WHERE id = ?";


        private SelectQuery() {
        }
    }

    /**
     * This holds all the insert queries
     */
    public static class InsertQuery {
        public static final String INSERT_PATIENT = "INSERT INTO patients (\n" + "    first_name, last_name, date_of_birth, gender, address, city, country, phone_number, email, \n" + "    emergency_contact_name, emergency_contact_phone, blood_type, medical_history, current_medication, \n" + "    allergies, insurance_provider, insurance_policy_number, patient_status, created_at\n" + ")\n" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\n";

        public static final String CREATE_AND_APPOINTMENT = "INSERT INTO appointments (patient_id, appointment_date_time, reason, status, notes, is_active, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        private InsertQuery() {
        }
    }

    /**
     * This holds all the update queries
     */
    public static class UpdateQuery {

        public static final String UPDATE_PATIENT = "UPDATE patients \n" + "SET \n" + "    first_name = ?, \n" + "    last_name = ?, \n" + "    date_of_birth = ?, \n" + "    gender = ?, \n" + "    address = ?, \n" + "    city = ?, \n" + "    country = ?, \n" + "    phone_number = ?, \n" + "    email = ?, \n" + "    emergency_contact_name = ?, \n" + "    emergency_contact_phone = ?, \n" + "    blood_type = ?, \n" + "    medical_history = ?, \n" + "    current_medication = ?, \n" + "    allergies = ?, \n" + "    insurance_provider = ?, \n" + "    insurance_policy_number = ?, \n" + "    patient_status = ?, \n" + "    updated_at = ? \n" + "WHERE id = ?;\n";

        public static final String UPDATE_APPOINTMENT = "UPDATE appointments \n" + "SET \n" + "    appointment_date_time = ?, \n" + "    reason = ?, \n" + "    status = ?, \n" + "    notes = ?, \n" + "    is_active = ?, \n" + "    updated_at = ? \n" + "WHERE id = ?;\n";

        private UpdateQuery() {
        }
    }

    /**
     * This holds all  delete queries
     */
    public static class DeleteQuery {

        public static final String DELETE_APPOINTMENT = "DELETE FROM appointments WHERE patient_id = ?";

        public static final String DELETE_PATIENT = "DELETE FROM patients WHERE id = ?";

        private DeleteQuery() {

        }
    }
}