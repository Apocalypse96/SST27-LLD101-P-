package com.example.render;

/**
 * Renderer class that uses TextStyleFactory to create memory-efficient Glyphs.
 * 
 * Implements the Flyweight pattern by using TextStyleFactory to share
 * common styling information across multiple Glyphs.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles text rendering
 * - Dependency Inversion: Depends on TextStyleFactory abstraction
 * - Flyweight Pattern: Uses shared TextStyle instances to reduce memory usage
 */
public class Renderer {
    
    private final TextStyleFactory styleFactory;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param styleFactory The TextStyle factory
     */
    public Renderer(TextStyleFactory styleFactory) {
        this.styleFactory = styleFactory;
    }
    
    /**
     * Default constructor that uses the singleton TextStyleFactory.
     */
    public Renderer() {
        this(TextStyleFactory.getInstance());
    }
    
    /**
     * Renders text using flyweight Glyphs for memory efficiency.
     * 
     * @param text The text to render
     * @return The total rendering cost
     */
    public int render(String text) {
        int cost = 0;
        for (char c : text.toCharArray()) {
            // Use TextStyleFactory to get shared TextStyle instances
            // This ensures identical styles reuse the same instance
            TextStyle style = styleFactory.getTextStyle("Inter", 14, (c % 7 == 0));
            Glyph g = new Glyph(c, style);
            cost += g.drawCost();
        }
        return cost;
    }
    
    /**
     * Gets the number of unique TextStyle instances created.
     * 
     * @return The number of cached TextStyle instances
     */
    public int getStyleCount() {
        return styleFactory.getCacheSize();
    }
    
    /**
     * Gets information about the TextStyle cache.
     * 
     * @return Cache information string
     */
    public String getCacheInfo() {
        return styleFactory.getCacheInfo();
    }
}
