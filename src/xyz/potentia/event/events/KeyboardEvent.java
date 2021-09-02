package xyz.potentia.event.events;

import xyz.potentia.enums.KeyboardEventType;
import xyz.potentia.event.Event;

@SuppressWarnings("unused")
public class KeyboardEvent extends Event
{
    private final int keyCode;
    private final String key;
    private final KeyboardEventType type;

    public KeyboardEvent(int keyCode, String key, KeyboardEventType type)
    {
        this.keyCode = keyCode;
        this.key = key;
        this.type = type;
    }

    public int getKeyCode()
    {
        return keyCode;
    }

    public String getKey()
    {
        return key;
    }

    public KeyboardEventType getType()
    {
        return type;
    }
}
