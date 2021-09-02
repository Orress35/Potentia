package xyz.potentia.ui;

import xyz.potentia.Potentia;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("unused")
public abstract class Element
{
    protected Graphics g;
    protected int id;

    public abstract void draw(Graphics g);
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
        drawRectO(minX, minY, width, height, color);
    }

    protected void drawRectO(int x, int y, int width, int height, Color color)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    protected void drawString(String text, Font font, int x, int y, Color color)
    {
        g.setColor(color);
        g.setFont(font.deriveFont((float) font.getSize()));
        g.drawString(text, x, y);
    }

    protected void drawCenteredString(String text, Font font, int x, int y, int width, int height, Color color)
    {
        double[] labelBounds = getStringBounds(text, Potentia.getNormalFont());
        double labelWidth = labelBounds[0];
        double labelHeight = labelBounds[1];
        double oWidth = width - labelWidth;
        double oHeight = height - labelHeight;
        drawString(text, font, (int) (x + oWidth / 2), (int) (y + oHeight / 2 + labelHeight - 4), color);
    }

    protected void drawChar(char c, Font font, int x, int y, Color color) {
        g.setColor(color);
        g.setFont(font.deriveFont((float) font.getSize()));
        g.drawString(Character.toString(c), x, y);
    }

    protected double[] getStringBounds(String text, Font font)
    {
        Font ofont = font.deriveFont((float) font.getSize());
        Rectangle2D rect = ofont.getStringBounds(text, ((Graphics2D) g).getFontRenderContext());
        return new double[] { rect.getWidth(), rect.getHeight() };
    }
}
