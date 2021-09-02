package xyz.potentia.ui.impl;

import xyz.potentia.Potentia;
import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;
import xyz.potentia.ui.Element;

import java.awt.*;

public class Button extends Element
{
    int x, y, width, height;
    String label;
    boolean hovered;

    public Button(String label, int x, int y, int width, int height)
    {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g)
    {
        this.g = g;
        drawRectO(x, y, width, height, hovered ? Potentia.getTheme().buttonHovered() : Potentia.getTheme().button());
        drawCenteredString(label, Potentia.getNormalFont(), x, y, width, height, Potentia.getTheme().textUI());
    }

    @Override
    public void onMouse(MouseEvent e)
    {
        if (e.getType() == MouseEventType.MOVE) {
            hovered = e.getX() > x && e.getY() > y && e.getX() <= x + width && e.getY() <= y + height;
        } else if (e.getType() == MouseEventType.CLICK && hovered) {

        }
    }

    @Override public void onKeyboard(KeyboardEvent e) { }
}
