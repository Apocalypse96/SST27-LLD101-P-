package com.example.video;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        // Use facade to simplify the complex video processing pipeline
        // This follows the Facade pattern and Single Responsibility Principle
        VideoPipelineFacade facade = new VideoPipelineFacade();
        
        // Process video with grayscale, scaling, and sharpening
        Path out = facade.process(
            Path.of("in.mp4"), 
            Path.of("out.mp4"), 
            true,      // grayscale
            0.5,       // scale factor
            3          // sharpen strength
        );
        
        System.out.println("Wrote " + out);
    }
}
