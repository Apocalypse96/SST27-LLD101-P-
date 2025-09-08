package com.example.render;

import java.util.Objects;

/**
 * Glyph class that uses TextStyle flyweight for memory efficiency.
 * 
 * Implements the Flyweight pattern by separating intrinsic state (TextStyle)
 * from extrinsic state (character).
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles glyph representation
 * - Dependency Inversion: Depends on TextStyle abstraction
 * - Flyweight Pattern: Shares intrinsic state to reduce memory usage
 */
public class Glyph {
    
    // Extrinsic state - unique per instance
    private final char ch;
    
    // Intrinsic state - shared among multiple instances
    private final TextStyle style;

    /**
     * Constructor for Glyph.
     * 
     * @param ch The character
     * @param style The text style (flyweight)
     * @throws NullPointerException if style is null
     */
    public Glyph(char ch, TextStyle style) {
        this.ch = ch;
        this.style = Objects.requireNonNull(style, "style cannot be null");
    }

    /**
     * Calculates the drawing cost for this glyph.
     * 
     * @return The drawing cost
     */
    public int drawCost() { 
        return style.getDrawCost(); 
    }
    
    /**
     * Gets the character.
     * 
     * @return The character
     */
    public char getCh() { 
        return ch; 
    }
    
    /**
     * Gets the font name from the style.
     * 
     * @return The font name
     */
    public String getFont() { 
        return style.getFont(); 
    }
    
    /**
     * Gets the font size from the style.
     * 
     * @return The font size
     */
    public int getSize() { 
        return style.getSize(); 
    }
    
    /**
     * Checks if the text is bold from the style.
     * 
     * @return true if bold, false otherwise
     */
    public boolean isBold() { 
        return style.isBold(); 
    }
    
    /**
     * Gets the TextStyle instance.
     * 
     * @return The TextStyle instance
     */
    public TextStyle getStyle() {
        return style;
    }
    
    @Override
    public String toString() {
        return "Glyph{ch='" + ch + "', style=" + style + "}";
    }
}
