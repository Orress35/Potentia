package xyz.potentia.ui.impl;

import xyz.potentia.Potentia;
import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;
import xyz.potentia.ui.Element;
import xyz.potentia.ui.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TopMenu extends Element
{
    protected Window parent;
    protected int height;
    protected final HashMap<TopMenuButton, ArrayList<TopMenuButton>> dropDowns;

    public TopMenu(Window parent, int height, HashMap<TopMenuButton, ArrayList<TopMenuButton>> dropDowns)
    {
        this.parent = parent;
        this.height = height;
        this.dropDowns = dropDowns;
    }

    @Override
    public void draw(Graphics g)
    {
        this.g = g;
        drawRect(0, 0, parent.getFrame().getWidth(), height, Potentia.getTheme().button());
        int x = 10;
        for (TopMenuButton button: getSortedButtons(dropDowns.keySet())) {
            int width = (int) (getStringBounds(button.label, Potentia.getNormalFont())[0] + 10);
            drawRectO(x - 10, 0, width, height, button.hovered ? Potentia.getTheme().buttonHovered() : Potentia.getTheme().button());
            drawCenteredString(button.label, Potentia.getNormalFont(), x - 10, 0, width, height, Potentia.getTheme().textUI());
            if (button.extended) {
                int y = height;
                for (TopMenuButton obutton: dropDowns.get(button)) {
                    drawRectO(x - 10, y, 120, height, obutton.hovered ? Potentia.getTheme().buttonHovered() : Potentia.getTheme().button());
                    drawString(obutton.label, Potentia.getNormalFont(), x - 6, y + 20, Potentia.getTheme().textUI());
                    y += height;
                }
            }
            x += width;
        }
    }

    @Override
    public void onMouse(MouseEvent e)
    {
        if (e.getType() == MouseEventType.MOVE) {
            int x = 10;
            for (TopMenuButton button: getSortedButtons(dropDowns.keySet())) {
                int width = (int) (getStringBounds(button.label, Potentia.getNormalFont())[0] + 10);
                button.hovered = e.getX() > x && e.getY() > 0 && e.getX() < x + width && e.getY() < height;
                if (button.extended) {
                    int y = height;
                    for (TopMenuButton obutton: dropDowns.get(button)) {
                        obutton.hovered = e.getX() > x - 10 && e.getY() > y && e.getX() < x + 120 && e.getY() < y + height;
                        y += height;
                    }
                }
                x += width;
            }
        } else if (e.getType() == MouseEventType.CLICK) {
            for (TopMenuButton button: getSortedButtons(dropDowns.keySet())) {
                if (button.extended) {
                    for (TopMenuButton obutton: dropDowns.get(button)) {
                        if (obutton.hovered) {
                            obutton.onClick();
                        }
                    }
                }
            }

            for (TopMenuButton button: getSortedButtons(dropDowns.keySet())) {
                button.extended = button.hovered;
                button.onClick();
            }
        }
    }

    @Override
    public void onKeyboard(KeyboardEvent e)
    {

    }

    public ArrayList<TopMenuButton> getSortedButtons(Set<TopMenuButton> set)
    {
        return getSortedButtons(new ArrayList<>(set));
    }

    public ArrayList<TopMenuButton> getSortedButtons(ArrayList<TopMenuButton> list)
    {
        ArrayList<TopMenuButton> result = new ArrayList<>();
        while (!list.isEmpty()) {
            int index = 0;
            int id = Integer.MAX_VALUE;
            int lindex = 0;
            for (TopMenuButton button: list) {
                if (button.id < id) {
                    id = button.id;
                    lindex = index;
                }
                index++;
            }
            result.add(list.get(lindex));
            list.remove(lindex);
        }
        return result;
    }
}
