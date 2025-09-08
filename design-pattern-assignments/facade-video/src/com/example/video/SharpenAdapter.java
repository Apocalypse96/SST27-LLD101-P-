package com.example.video;

import java.util.Objects;

/**
 * Adapter that adapts LegacySharpen to work with Frame arrays.
 * Implements the Adapter pattern to bridge incompatible interfaces.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles sharpen filter adaptation
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 * - Open/Closed: Can be extended without modifying existing code
 * - Liskov Substitution: Properly implements the filter contract
 */
public class SharpenAdapter {
    
    private final LegacySharpen legacySharpen;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param legacySharpen The legacy sharpen filter to adapt
     * @throws NullPointerException if legacySharpen is null
     */
    public SharpenAdapter(LegacySharpen legacySharpen) {
        this.legacySharpen = Objects.requireNonNull(legacySharpen, "legacySharpen cannot be null");
    }
    
    /**
     * Default constructor that creates a default LegacySharpen instance.
     */
    public SharpenAdapter() {
        this(new LegacySharpen());
    }
    
    /**
     * Applies sharpen filter to an array of frames.
     * 
     * @param frames The frames to sharpen
     * @param strength The sharpen strength
     * @return The sharpened frames (same array for simplicity)
     * @throws NullPointerException if frames is null
     * @throws IllegalArgumentException if strength is negative
     */
    public Frame[] sharpen(Frame[] frames, int strength) {
        Objects.requireNonNull(frames, "frames cannot be null");
        if (strength < 0) {
            throw new IllegalArgumentException("strength cannot be negative");
        }
        
        if (strength == 0) {
            // No sharpening needed
            return frames;
        }
        
        // Convert frames to a handle string for the legacy API
        String framesHandle = createFramesHandle(frames);
        
        // Apply sharpen using legacy API
        String sharpenedHandle = legacySharpen.applySharpen(framesHandle, strength);
        
        // For simplicity, we return the same frames array
        // In a real implementation, we would convert the handle back to frames
        System.out.println("Applied sharpen filter with strength " + strength + 
                          " to " + frames.length + " frames (handle: " + sharpenedHandle + ")");
        
        return frames;
    }
    
    /**
     * Creates a handle string from frames for the legacy API.
     * 
     * @param frames The frames to convert
     * @return A handle string representing the frames
     */
    private String createFramesHandle(Frame[] frames) {
        StringBuilder handle = new StringBuilder();
        handle.append("FRAMES:");
        for (int i = 0; i < frames.length; i++) {
            if (i > 0) handle.append(",");
            handle.append(frames[i].w).append("x").append(frames[i].h);
        }
        return handle.toString();
    }
}
