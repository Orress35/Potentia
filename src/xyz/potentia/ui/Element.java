package xyz.potentia.ui;

import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("unused")
public abstract class Element
{
    protected final Graphics g;

    public Element(Graphics g)
    {
        this.g = g;
    }

    protected int id;

    public abstract void draw();
    public abstract void onMouse(MouseEvent e);
    public abstract void onKeyboard(KeyboardEvent e);

    @SuppressWarnings("unused")
    public int getId()
    {
        return id;
    }

    protected void drawRect(int minX, int minY, int maxX, int maxY, Color color)
    {
        int width = maxX - minX;
        int height = maxY - minY;
        g.setColor(color);
        g.fillRect(minX, minY, width, height);
    }

    protected void drawString(String text, Font font, int x, int y, Color color) {
        g.setColor(color);
        g.setFont(font.deriveFont((float) font.getSize()));
        g.drawString(text, x, y);
    }

    protected void drawChar(char c, Font font, int x, int y, Color color) {
        g.setColor(color);
        g.setFont(font.deriveFont((float) font.getSize()));
        g.drawString(Character.toString(c), x, y);
    }

    protected double[] getStringBounds(String text, Font font) {
        Font ofont = font.deriveFont((float) font.getSize());
        Rectangle2D rect = ofont.getStringBounds(text, ((Graphics2D) g).getFontRenderContext());
        return new double[] { rect.getWidth(), rect.getHeight() };
    }
}
