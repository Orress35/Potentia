package xyz.potentia.ui.impl;

import xyz.potentia.Potentia;
import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;
import xyz.potentia.ui.Element;
import xyz.potentia.ui.Window;

import java.awt.*;

public class Button extends Element
{
    protected Window parent = null;
    protected int xFromEdge = -1, x = -1, y, width, height;
    protected String label;
    protected boolean hovered;

    public Button(int id, String label, int x, int y, int width, int height)
    {
        this.id = id;
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Button(Window parent, int id, String label, int xFromEdge, int y, int width, int height)
    {
        this.parent = parent;
        this.id = id;
        this.label = label;
        this.xFromEdge = xFromEdge;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g)
    {
        this.g = g;
        if (parent == null) {
            drawRectO(x, y, width, height, hovered ? Potentia.getTheme().buttonHovered() : Potentia.getTheme().button());
            drawCenteredString(label, Potentia.getNormalFont(), x, y, width, height, Potentia.getTheme().textUI());
        } else {
            drawRectO(parent.getFrame().getWidth() - xFromEdge, y, width, height, hovered ? Potentia.getTheme().buttonHovered() : Potentia.getTheme().button());
            drawCenteredString(label, Potentia.getNormalFont(), parent.getFrame().getWidth() - xFromEdge, y, (int) (width / 1.3), height, Potentia.getTheme().textUI());
        }
    }

    @Override
    public void onMouse(MouseEvent e)
    {
        if (e.getType() == MouseEventType.MOVE) {
            if (parent == null) {
                hovered = e.getX() > x && e.getY() > y && e.getX() <= x + width && e.getY() <= y + height;
            } else {
                hovered = e.getX() > parent.getFrame().getWidth() - xFromEdge && e.getY() > y && e.getX() <= parent.getFrame().getWidth() - xFromEdge + width && e.getY() <= y + height;
            }
        } else if (e.getType() == MouseEventType.CLICK && hovered) {
            if (id == 1) {
                Potentia.getRunWindow().open();
            }
        }
    }

    @Override public void onKeyboard(KeyboardEvent e) { }
}
