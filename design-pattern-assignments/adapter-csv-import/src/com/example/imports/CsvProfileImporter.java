package com.example.imports;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Adapter that adapts NaiveCsvReader to ProfileImporter interface.
 * Implements the Adapter pattern to bridge incompatible interfaces.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles CSV to Profile conversion
 * - Dependency Inversion: Depends on abstractions (ProfileImporter interface)
 * - Open/Closed: Can be extended without modifying existing code
 */
public class CsvProfileImporter implements ProfileImporter {
    
    private final NaiveCsvReader csvReader;
    private final ProfileService profileService;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param csvReader The CSV reader to adapt
     * @param profileService The profile service to create profiles
     * @throws NullPointerException if any parameter is null
     */
    public CsvProfileImporter(NaiveCsvReader csvReader, ProfileService profileService) {
        this.csvReader = Objects.requireNonNull(csvReader, "csvReader cannot be null");
        this.profileService = Objects.requireNonNull(profileService, "profileService cannot be null");
    }
    
    /**
     * Imports profiles from CSV file.
     * 
     * @param csvFile Path to the CSV file
     * @return Number of successfully imported profiles
     * @throws NullPointerException if csvFile is null
     */
    @Override
    public int importFrom(Path csvFile) {
        Objects.requireNonNull(csvFile, "csvFile cannot be null");
        
        List<String[]> rows = csvReader.read(csvFile);
        int successCount = 0;
        
        for (int i = 0; i < rows.size(); i++) {
            String[] row = rows.get(i);
            
            try {
                if (isValidRow(row)) {
                    String id = row[0].trim();
                    String email = row[1].trim();
                    String displayName = row.length > 2 ? row[2].trim() : "";
                    
                    profileService.createProfile(id, email, displayName);
                    successCount++;
                } else {
                    System.out.println("Skipping invalid row " + (i + 1) + ": missing required fields or invalid email");
                }
            } catch (Exception e) {
                System.out.println("Skipping invalid row " + (i + 1) + ": " + e.getMessage());
            }
        }
        
        return successCount;
    }
    
    /**
     * Validates a CSV row for required fields and email format.
     * 
     * @param row The CSV row to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidRow(String[] row) {
        if (row == null || row.length < 2) {
            return false;
        }
        
        String id = row[0];
        String email = row[1];
        
        // Check for null, empty, or whitespace-only values
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            return false;
        }
        
        return true;
    }
}
