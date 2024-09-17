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
        public static final String INSERT_TPS_BANK_CODE_TYPE_DETAILS = "";

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