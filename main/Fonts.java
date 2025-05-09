package main;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Fonts {

    private static final String FONT_PATH = "res\\fonts\\";
    private static final String EXTENSION = ".ttf";
    private static final Map<String, Font> fontCache = new HashMap<>();

    public static Font load(String fontName, int size) {
        return load(fontName, size, Font.PLAIN);
    }

    public static Font load(String fontName, int style, int size) {
        try {
            String key = fontName + "-" + size + "-" + style;
            String fullFileName = FONT_PATH + fontName + EXTENSION;

            if (fontCache.containsKey(key)) {
                return fontCache.get(key);
            }

            Font baseFont;

            File fontFile = new File(fullFileName);
            if (fontFile.exists()) {
                baseFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            } else {
                InputStream is = Fonts.class.getResourceAsStream("/" + fullFileName);
                if (is == null) throw new Exception("Font not found: " + fullFileName);
                baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            }

            Font derived = baseFont.deriveFont(style, size);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(derived);
            fontCache.put(key, derived);
            return derived;

        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Serif", style, size);
        }
    }
}
