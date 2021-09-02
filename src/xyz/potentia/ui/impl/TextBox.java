package xyz.potentia.ui.impl;

import xyz.potentia.Potentia;
import xyz.potentia.enums.KeyboardEventType;
import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;
import xyz.potentia.ui.Element;
import xyz.potentia.util.SimpleSelection;

import java.awt.*;

public class TextBox extends Element
{
    protected StringBuilder text;
    protected int x, y, width, height;
    protected boolean hovered;
    protected SimpleSelection selection;
    public boolean selected = false;

    public TextBox(int id, StringBuilder text, int x, int y, int width, int height)
    {
        this.id = id;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public TextBox(int id, int x, int y, int width, int height)
    {
        this.id = id;
        this.text = new StringBuilder();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g)
    {
        this.g = g;
        drawRectO(x, y, width, height, hovered && !selected ? Potentia.getTheme().textBoxHovered() : Potentia.getTheme().textBox());
        drawString(text.toString(), Potentia.getNormalFont(), x + 4, y + 18, Potentia.getTheme().textUI());
        if (selected && selection != null) {
            selection.setStartIndex(Math.min(selection.getStartIndex(), text.length()));
            selection.setEndIndex(Math.min(selection.getEndIndex(), text.length()));
            int caretX = x + 4 + (int) (selection.getStartIndex() * 8.8);
            drawRect(caretX, y + 2, caretX + 2, y + 22, Potentia.getTheme().textUI());
        } else {
            selection = null;
        }
    }

    @Override
    public void onMouse(MouseEvent e)
    {
        if (e.getType() == MouseEventType.MOVE) {
            hovered = e.getX() > x && e.getY() > y && e.getX() < x + width && e.getY() < y + height;
        }
    }

    @Override
    public void onKeyboard(KeyboardEvent e)
    {
        if (e.getType() == KeyboardEventType.PRESS) {
            if (e.getKey().equalsIgnoreCase("shift"))
                return;
            if (e.getKey().equalsIgnoreCase("comma")) {
                text.insert(selection.getStartIndex(), ",");
                selection.setStartIndex(selection.getStartIndex() + 1);
                return;
            }
            if (e.getKey().equalsIgnoreCase("period")) {
                text.insert(selection.getStartIndex(), ".");
                selection.setStartIndex(selection.getStartIndex() + 1);
                return;
            }
            if (e.getKey().equalsIgnoreCase("backspace")) {
                if (selection.getStartIndex() <= 0)
                    return;
                text.replace(selection.getStartIndex() - 1, selection.getStartIndex(), "");
                selection.setStartIndex(selection.getStartIndex() - 1);
                return;
            }
            if (e.getKey().equalsIgnoreCase("left")) {
                selection.setStartIndex(Math.max(selection.getStartIndex() - 1, 0));
                return;
            }
            if (e.getKey().equalsIgnoreCase("right")) {
                selection.setStartIndex(Math.min(selection.getStartIndex() + 1, text.length()));
                return;
            }
            if (e.getKey().equalsIgnoreCase("up") || e.getKey().equalsIgnoreCase("home")) {
                selection.setStartIndex(0);
                return;
            }
            if (e.getKey().equalsIgnoreCase("down") || e.getKey().equalsIgnoreCase("end")) {
                selection.setStartIndex(text.length());
                return;
            }
            if (selection.getStartIndex() + 1 > width / 8.8)
                return;
            if (e.getKey().equalsIgnoreCase("space")) {
                text.insert(selection.getStartIndex(), " ");
                selection.setStartIndex(selection.getStartIndex() + 1);
                return;
            }
            if (e.getKey().length() != 1) {
                System.out.println(e.getKey());
                return;
            }
            text.insert(selection.getStartIndex(), e.getKey());
            selection.setStartIndex(selection.getStartIndex() + 1);
        }
    }

    public boolean isHovered()
    {
        return hovered;
    }

    public void select(double clickX, double clickY)
    {
        select((int) clickX, (int) clickY);
    }

    public void select(int clickX, int clickY)
    {
        clickX -= x;
        clickY -= y;
        int charIndex = (int) Math.round(clickX / 8.8);
        int lineIndex = (int) Math.round(clickY / 18.48);
        selection = new SimpleSelection(charIndex, charIndex);
    }
}
