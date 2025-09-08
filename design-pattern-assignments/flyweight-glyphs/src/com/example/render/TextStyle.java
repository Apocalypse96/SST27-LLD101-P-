package com.example.render;

import java.util.Objects;

/**
 * Immutable TextStyle class representing the intrinsic state of text styling.
 * This is the flyweight object that will be shared among multiple Glyphs.
 * 
 * Implements the Flyweight pattern to reduce memory usage by sharing
 * common styling information across multiple objects.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles text style representation
 * - Immutability: Ensures thread safety and prevents accidental modifications
 * - Value Object: Equality based on content, not identity
 */
public final class TextStyle {
    
    private final String font;
    private final int size;
    private final boolean bold;
    
    /**
     * Constructor for TextStyle.
     * 
     * @param font The font name
     * @param size The font size
     * @param bold Whether the text is bold
     * @throws NullPointerException if font is null
     * @throws IllegalArgumentException if size is negative
     */
    public TextStyle(String font, int size, boolean bold) {
        this.font = Objects.requireNonNull(font, "font cannot be null");
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative");
        }
        this.size = size;
        this.bold = bold;
    }
    
    /**
     * Gets the font name.
     * 
     * @return The font name
     */
    public String getFont() {
        return font;
    }
    
    /**
     * Gets the font size.
     * 
     * @return The font size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Checks if the text is bold.
     * 
     * @return true if bold, false otherwise
     */
    public boolean isBold() {
        return bold;
    }
    
    /**
     * Calculates the drawing cost for this style.
     * 
     * @return The drawing cost
     */
    public int getDrawCost() {
        return size + (bold ? 10 : 0);
    }
    
    /**
     * Creates a cache key for this style.
     * 
     * @return A string key for caching
     */
    public String getCacheKey() {
        return font + "|" + size + "|" + (bold ? "B" : "N");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TextStyle that = (TextStyle) obj;
        return size == that.size && 
               bold == that.bold && 
               Objects.equals(font, that.font);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(font, size, bold);
    }
    
    @Override
    public String toString() {
        return "TextStyle{font='" + font + "', size=" + size + ", bold=" + bold + "}";
    }
}
