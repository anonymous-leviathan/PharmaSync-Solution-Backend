INSERT INTO MEDICINE (name, generic_name, description, manufacturer, category, form, dosage, batch_number, expiry_date,
                      stock_quantity, reorder_level, price, purchase_price, storage_conditions, prescriptions_required,
                      controlled_substance, side_effects, instructions, barcode, country_of_origin, dosage_unit,
                      contraindications, interactions, warnings)
VALUES ('Paracetamol', 'Acetaminophen',
        'Paracetamol is widely used to relieve mild to moderate pain, including headaches, muscle aches, arthritis, backaches, toothaches, colds, and fevers. It is often recommended for its safety profile when used as directed. It is suitable for adults and children. Overdose can lead to severe liver damage.',
        'ABC Pharma', 'Pain Relief', 'Tablet', '500mg', 'BATCH001', '2025-12-31', 100, 20, 1.50, 0.80,
        'Store in a cool, dry place, away from direct sunlight. Keep out of reach of children.',
        TRUE, FALSE,
        'Nausea, rash, abdominal pain, liver toxicity in overdose cases.',
        'Take 1-2 tablets every 4-6 hours as needed. Do not exceed 8 tablets in 24 hours. Consult a doctor if pain persists for more than 3 days or if fever lasts more than 3 days.',
        '1234567890123', 'USA', 'mg',
        'Severe liver impairment, active liver disease, or hypersensitivity to paracetamol.',
        'May interact with alcohol, other hepatotoxic medications, and warfarin.',
        'Warning: Overdose can be fatal. Seek emergency medical attention if you suspect an overdose. Avoid using with other medications containing paracetamol.');

INSERT INTO MEDICINE (name, generic_name, description, manufacturer, category, form, dosage, batch_number, expiry_date,
                      stock_quantity, reorder_level, price, purchase_price, storage_conditions, prescriptions_required,
                      controlled_substance, side_effects, instructions, barcode, country_of_origin, dosage_unit,
                      contraindications, interactions, warnings)
VALUES ('Ibuprofen', 'Ibuprofen',
        'Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) that effectively reduces fever, pain, and inflammation. Commonly used for conditions such as arthritis, menstrual cramps, headaches, toothaches, and muscle aches, it provides relief by inhibiting the production of prostaglandins, which are responsible for pain and inflammation.',
        'XYZ Pharma', 'Anti-Inflammatory', 'Tablet', '200mg', 'BATCH002', '2024-11-30', 150, 30, 2.00, 1.00,
        'Store at room temperature in a dry place, away from heat and moisture.',
        TRUE, FALSE,
        'Dizziness, gastrointestinal upset, headache, and rash.',
        'Take 1 tablet every 4-6 hours as needed for pain. Do not exceed 1200 mg in 24 hours without medical advice. Consult a healthcare provider if symptoms persist or worsen.',
        '9876543210987', 'USA', 'mg',
        'Severe renal impairment, active peptic ulcer disease, or hypersensitivity to ibuprofen or other NSAIDs.',
        'May interact with blood thinners, diuretics, and other NSAIDs.',
        'Warning: Long-term use may increase the risk of heart attack or stroke. Use the lowest effective dose for the shortest duration possible. Consult your doctor if you have a history of heart disease.');
