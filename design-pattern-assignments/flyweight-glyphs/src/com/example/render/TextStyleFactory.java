package com.example.render;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Factory that creates and caches TextStyle instances to implement the Flyweight pattern.
 * Ensures that identical style configurations reuse the same TextStyle instance.
 * 
 * Implements the Flyweight pattern to reduce memory usage by sharing
 * common styling information across multiple objects.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles TextStyle creation and caching
 * - Open/Closed: Can be extended without modifying existing code
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 * - Singleton Pattern: Single instance manages all TextStyle instances
 */
public class TextStyleFactory {
    
    // Thread-safe cache for TextStyle instances
    private final Map<String, TextStyle> styleCache = new ConcurrentHashMap<>();
    
    // Singleton instance
    private static final TextStyleFactory INSTANCE = new TextStyleFactory();
    
    /**
     * Private constructor to prevent instantiation.
     * Use getInstance() to get the singleton instance.
     */
    private TextStyleFactory() {
    }
    
    /**
     * Gets the singleton instance of TextStyleFactory.
     * 
     * @return The singleton instance
     */
    public static TextStyleFactory getInstance() {
        return INSTANCE;
    }
    
    /**
     * Gets or creates a TextStyle instance with the specified properties.
     * If a TextStyle with the same properties already exists, returns the cached instance.
     * 
     * @param font The font name
     * @param size The font size
     * @param bold Whether the text is bold
     * @return A TextStyle instance (cached or newly created)
     * @throws NullPointerException if font is null
     * @throws IllegalArgumentException if size is negative
     */
    public TextStyle getTextStyle(String font, int size, boolean bold) {
        Objects.requireNonNull(font, "font cannot be null");
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative");
        }
        
        // Create a temporary TextStyle to generate the cache key
        TextStyle tempStyle = new TextStyle(font, size, bold);
        String key = tempStyle.getCacheKey();
        
        // Use computeIfAbsent to atomically get or create the TextStyle
        return styleCache.computeIfAbsent(key, k -> tempStyle);
    }
    
    /**
     * Gets the number of cached TextStyle instances.
     * 
     * @return The number of cached instances
     */
    public int getCacheSize() {
        return styleCache.size();
    }
    
    /**
     * Clears the cache. Useful for testing or memory management.
     */
    public void clearCache() {
        styleCache.clear();
    }
    
    /**
     * Gets a string representation of all cached styles.
     * 
     * @return A string representation of the cache
     */
    public String getCacheInfo() {
        return "TextStyleFactory{cacheSize=" + styleCache.size() + 
               ", styles=" + styleCache.keySet() + "}";
    }
}
