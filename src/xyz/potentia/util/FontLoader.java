package xyz.potentia.util;

import xyz.potentia.Potentia;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class FontLoader
{
    public Font loadFont(String path, float size)
    {
        Font font;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(getClass().getResource(path)).toURI())).deriveFont(size);
            ge.registerFont(font);
            return font;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
