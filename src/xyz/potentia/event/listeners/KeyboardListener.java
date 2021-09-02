package xyz.potentia.event.listeners;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import xyz.potentia.Potentia;
import xyz.potentia.enums.KeyboardEventType;
import xyz.potentia.event.events.KeyboardEvent;

public class KeyboardListener implements NativeKeyListener
{

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            KeyboardEvent e = new KeyboardEvent(nativeKeyEvent.getKeyCode(), nativeKeyEvent.getKeyChar(), KeyboardEventType.TYPE);
            Potentia.getMainWindow().onKeyboard(e);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            KeyboardEvent e = new KeyboardEvent(nativeKeyEvent.getKeyCode(), nativeKeyEvent.getKeyChar(), KeyboardEventType.PRESS);
            Potentia.getMainWindow().onKeyboard(e);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent)
    {
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            KeyboardEvent e = new KeyboardEvent(nativeKeyEvent.getKeyCode(), nativeKeyEvent.getKeyChar(), KeyboardEventType.RELEASE);
            Potentia.getMainWindow().onKeyboard(e);
        }
    }
}
