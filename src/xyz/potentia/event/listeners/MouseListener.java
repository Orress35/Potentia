package xyz.potentia.event.listeners;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import xyz.potentia.Potentia;
import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.events.MouseEvent;

public class MouseListener implements NativeMouseInputListener, NativeMouseWheelListener
{
    protected static double x, y;

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            MouseEvent e = new MouseEvent(nativeMouseEvent.getButton(), MouseEventType.CLICK, x, y);
            Potentia.getMainWindow().onMouse(e);
        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            MouseEvent e = new MouseEvent(nativeMouseEvent.getButton(), MouseEventType.PRESS, x, y);
            Potentia.getMainWindow().onMouse(e);
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            MouseEvent e = new MouseEvent(nativeMouseEvent.getButton(), MouseEventType.RELEASE, x, y);
            Potentia.getMainWindow().onMouse(e);
        }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            x = (nativeMouseEvent.getX() - Potentia.getMainWindow().getFrame().getX() * 1.25) / 1.25 - 4;
            y = (nativeMouseEvent.getY() - Potentia.getMainWindow().getFrame().getY() * 1.25) / 1.25 - 32;
            MouseEvent e = new MouseEvent(0, MouseEventType.MOVE, x, y);
            Potentia.getMainWindow().onMouse(e);
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) { }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent)
    {

    }
}
