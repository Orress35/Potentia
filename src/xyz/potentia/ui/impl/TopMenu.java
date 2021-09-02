package xyz.potentia.ui.impl;

import xyz.potentia.Potentia;
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
            int width = (int) (getStringBounds(button.getLabel(), Potentia.getNormalFont())[0] + 10);
            drawRectO(x - 10, 0, width, height, button.isHovered() ? Potentia.getTheme().buttonHovered() : Potentia.getTheme().button());
            drawCenteredString(button.getLabel(), Potentia.getNormalFont(), x - 10, 0, width, height, Potentia.getTheme().textUI());
            x += width;
        }
    }

    @Override
    public void onMouse(MouseEvent e)
    {
        int x = 0;
        for (TopMenuButton button: getSortedButtons(dropDowns.keySet())) {
            int width = (int) getStringBounds(button.getLabel(), Potentia.getNormalFont())[0] + 5;

            x += width;
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
