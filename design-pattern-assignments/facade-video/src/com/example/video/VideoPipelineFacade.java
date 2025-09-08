package com.example.video;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Facade that simplifies the video processing pipeline by orchestrating
 * Decoder, FilterEngine, Encoder, and SharpenAdapter subsystems.
 * 
 * Implements the Facade pattern to provide a simplified interface
 * to a complex video processing subsystem.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles video pipeline orchestration
 * - Open/Closed: Can be extended without modifying existing utility classes
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 * - Facade Pattern: Provides a simplified interface to a complex subsystem
 */
public class VideoPipelineFacade {
    
    private final Decoder decoder;
    private final FilterEngine filterEngine;
    private final Encoder encoder;
    private final SharpenAdapter sharpenAdapter;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param decoder The video decoder
     * @param filterEngine The filter engine
     * @param encoder The video encoder
     * @param sharpenAdapter The sharpen adapter
     * @throws NullPointerException if any parameter is null
     */
    public VideoPipelineFacade(Decoder decoder, FilterEngine filterEngine, 
                              Encoder encoder, SharpenAdapter sharpenAdapter) {
        this.decoder = Objects.requireNonNull(decoder, "decoder cannot be null");
        this.filterEngine = Objects.requireNonNull(filterEngine, "filterEngine cannot be null");
        this.encoder = Objects.requireNonNull(encoder, "encoder cannot be null");
        this.sharpenAdapter = Objects.requireNonNull(sharpenAdapter, "sharpenAdapter cannot be null");
    }
    
    /**
     * Default constructor that creates default instances of all utilities.
     * This provides convenience while still allowing dependency injection.
     */
    public VideoPipelineFacade() {
        this(new Decoder(), new FilterEngine(), new Encoder(), new SharpenAdapter());
    }
    
    /**
     * Processes a video file through the complete pipeline.
     * 
     * @param src The source video file path
     * @param out The output video file path
     * @param gray Whether to apply grayscale filter
     * @param scale The scale factor (null for no scaling)
     * @param sharpenStrength The sharpen strength (null for no sharpening)
     * @return Path to the processed video file
     * @throws NullPointerException if src or out is null
     * @throws IllegalArgumentException if scale is negative or zero
     */
    public Path process(Path src, Path out, boolean gray, Double scale, Integer sharpenStrength) {
        Objects.requireNonNull(src, "src cannot be null");
        Objects.requireNonNull(out, "out cannot be null");
        
        if (scale != null && scale <= 0) {
            throw new IllegalArgumentException("scale must be positive");
        }
        
        if (sharpenStrength != null && sharpenStrength < 0) {
            throw new IllegalArgumentException("sharpenStrength cannot be negative");
        }
        
        System.out.println("Starting video processing pipeline...");
        
        // Step 1: Decode video to frames
        System.out.println("Decoding video: " + src);
        Frame[] frames = decoder.decode(src);
        System.out.println("Decoded " + frames.length + " frames");
        
        // Step 2: Apply grayscale filter if requested
        if (gray) {
            System.out.println("Applying grayscale filter");
            frames = filterEngine.grayscale(frames);
        }
        
        // Step 3: Apply scaling if requested
        if (scale != null) {
            System.out.println("Applying scale filter with factor: " + scale);
            frames = filterEngine.scale(frames, scale);
        }
        
        // Step 4: Apply sharpen filter if requested
        if (sharpenStrength != null && sharpenStrength > 0) {
            System.out.println("Applying sharpen filter with strength: " + sharpenStrength);
            frames = sharpenAdapter.sharpen(frames, sharpenStrength);
        }
        
        // Step 5: Encode frames to video
        System.out.println("Encoding video: " + out);
        Path result = encoder.encode(frames, out);
        
        System.out.println("Video processing completed successfully");
        return result;
    }
}
