package com.example.report;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

/**
 * Facade that simplifies the report export process by orchestrating
 * JsonWriter, Zipper, and AuditLog subsystems.
 * 
 * Implements the Facade pattern to provide a simplified interface
 * to a complex subsystem.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles report export orchestration
 * - Open/Closed: Can be extended without modifying existing utility classes
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 * - Facade Pattern: Provides a simplified interface to a complex subsystem
 */
public class ReportBundleFacade {
    
    private final JsonWriter jsonWriter;
    private final Zipper zipper;
    private final AuditLog auditLog;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param jsonWriter The JSON writer utility
     * @param zipper The zipper utility
     * @param auditLog The audit log utility
     * @throws NullPointerException if any parameter is null
     */
    public ReportBundleFacade(JsonWriter jsonWriter, Zipper zipper, AuditLog auditLog) {
        this.jsonWriter = Objects.requireNonNull(jsonWriter, "jsonWriter cannot be null");
        this.zipper = Objects.requireNonNull(zipper, "zipper cannot be null");
        this.auditLog = Objects.requireNonNull(auditLog, "auditLog cannot be null");
    }
    
    /**
     * Default constructor that creates default instances of utilities.
     * This provides convenience while still allowing dependency injection.
     */
    public ReportBundleFacade() {
        this(new JsonWriter(), new Zipper(), new AuditLog());
    }
    
    /**
     * Exports a report bundle by orchestrating JSON writing, zipping, and audit logging.
     * 
     * @param data The data to export
     * @param outDir The output directory
     * @param baseName The base name for the output files
     * @return Path to the created zip file
     * @throws NullPointerException if any parameter is null
     * @throws UncheckedIOException if any IO operation fails
     */
    public Path export(Map<String, Object> data, Path outDir, String baseName) {
        Objects.requireNonNull(data, "data cannot be null");
        Objects.requireNonNull(outDir, "outDir cannot be null");
        Objects.requireNonNull(baseName, "baseName cannot be null");
        
        try {
            // Step 1: Write JSON file
            Path jsonFile = jsonWriter.write(data, outDir, baseName);
            
            // Step 2: Create zip file
            Path zipFile = outDir.resolve(baseName + ".zip");
            Path zipPath = zipper.zip(jsonFile, zipFile);
            
            // Step 3: Log the export
            auditLog.log("exported " + zipPath);
            
            return zipPath;
            
        } catch (UncheckedIOException e) {
            // Re-throw IO errors as specified in requirements
            throw e;
        } catch (Exception e) {
            // Wrap any other exceptions as UncheckedIOException
            throw new UncheckedIOException(new java.io.IOException("Failed to export report bundle", e));
        }
    }
}
