package xyz.potentia.event.listeners;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import xyz.potentia.Potentia;
import xyz.potentia.enums.KeyboardEventType;
import xyz.potentia.event.events.KeyboardEvent;

import java.util.HashMap;

public class KeyboardListener implements NativeKeyListener
{
    public HashMap<String, String> keyMap = new HashMap<>();
    
    public KeyboardListener()
    {
        keyMap.put("1", "!");
        keyMap.put("2", "@");
        keyMap.put("3", "#");
        keyMap.put("4", "$");
        keyMap.put("5", "%");
        keyMap.put("6", "^");
        keyMap.put("7", "&");
        keyMap.put("8", "*");
        keyMap.put("9", "(");
        keyMap.put("0", ")");
        keyMap.put("-", "_");
        keyMap.put("=", "+");
    }
    
    boolean shift = false;

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent)
    {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent)
    {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT)
            shift = true;
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            KeyboardEvent e = new KeyboardEvent(nativeKeyEvent.getKeyCode(), shift ? keyMap.getOrDefault(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()), NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())) : NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).toLowerCase(), KeyboardEventType.PRESS);
            Potentia.getMainWindow().onKeyboard(e);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent)
    {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT)
            shift = false;
        if (Potentia.getMainWindow().getFrame().isFocused()) {
            KeyboardEvent e = new KeyboardEvent(nativeKeyEvent.getKeyCode(), shift ? keyMap.getOrDefault(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()), NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())) : NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()).toLowerCase(), KeyboardEventType.RELEASE);
            Potentia.getMainWindow().onKeyboard(e);
        }
    }
}
