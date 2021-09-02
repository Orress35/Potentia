package xyz.potentia.ui.impl;

public class TopMenuButton
{
    int id;
    String label;
    boolean hovered;

    public TopMenuButton(int id, String label)
    {
        this.id = id;
        this.label = label;
    }

    public void onClick()
    {

    }

    public String getLabel()
    {
        return label;
    }

    public void setHovered(boolean hovered)
    {
        this.hovered = hovered;
    }

    public boolean isHovered()
    {
        return hovered;
    }
}
