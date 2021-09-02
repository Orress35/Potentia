package xyz.potentia.event.events;

import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.Event;

@SuppressWarnings("unused")
public class MouseEvent extends Event
{
    private final int button;
    private final MouseEventType type;
    private final double x, y;

    public MouseEvent(int button, MouseEventType type, double x, double y)
    {
        this.button = button;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getButton()
    {
        return button;
    }

    public MouseEventType getType()
    {
        return type;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
