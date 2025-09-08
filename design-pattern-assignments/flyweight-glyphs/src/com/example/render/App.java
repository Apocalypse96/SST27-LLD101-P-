package com.example.render;

/**
 * Application that demonstrates the Flyweight pattern for memory-efficient text rendering.
 * 
 * This shows how the Flyweight pattern reduces memory usage by sharing
 * common TextStyle instances across multiple Glyphs.
 */
public class App {
    public static void main(String[] args) {
        Renderer r = new Renderer();
        
        // Render a long text to demonstrate memory efficiency
        String text = "Hello Flyweight! ".repeat(2000);
        int cost = r.render(text);
        
        System.out.println("Rendering cost: " + cost);
        System.out.println("Text length: " + text.length());
        System.out.println("Unique styles created: " + r.getStyleCount());
        System.out.println("Cache info: " + r.getCacheInfo());
        
        // Demonstrate that identical styles reuse the same instance
        System.out.println("\nDemonstrating flyweight pattern:");
        TextStyleFactory factory = TextStyleFactory.getInstance();
        TextStyle style1 = factory.getTextStyle("Inter", 14, true);
        TextStyle style2 = factory.getTextStyle("Inter", 14, true);
        System.out.println("Same style instance? " + (style1 == style2));
        System.out.println("Style 1: " + style1);
        System.out.println("Style 2: " + style2);
    }
}
