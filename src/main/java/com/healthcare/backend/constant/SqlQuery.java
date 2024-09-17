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
        public static final String SELECT_ALL_TPS_PURPOSE = "";

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

        public static final String UPDATE_TPS_BANK_CODE_TYPE = "";


        private UpdateQuery() {
        }
    }

    /**
     * This holds all  delete queries
     */
    public static class DeleteQuery {
        private DeleteQuery() {
        }
    }
}