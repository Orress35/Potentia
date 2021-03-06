package xyz.potentia;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import xyz.potentia.event.listeners.KeyboardListener;
import xyz.potentia.event.listeners.MouseListener;
import xyz.potentia.ui.Window;
import xyz.potentia.util.Discord;
import xyz.potentia.util.FontLoader;
import xyz.potentia.util.Theme;
import xyz.potentia.util.ThemeLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Potentia
{
    private static Window mainWindow, settingWindow, runWindow;
    private static Theme theme;
    private static FontLoader fontLoader;
    private static ThemeLoader themeLoader;
    private static final ArrayList<Theme> themes = new ArrayList<>();
    private static String projectName = "NONE", fileName = "NONE";
    private static Font normalFont, italicFont;
    public static final String VERSION = "0.1";

    public static void main(String[] args)
    {
        fontLoader = new FontLoader();
        normalFont = fontLoader.loadFont("/JetBrainsMono-VariableFont_wght.ttf", 14f);
        italicFont = fontLoader.loadFont("/JetBrainsMono-Italic-VariableFont_wght.ttf", 14f);

        themeLoader = new ThemeLoader();
        themeLoader.loadAll();

        mainWindow = new Window(1, "Potentia v" + VERSION);
        mainWindow.init();

        settingWindow = new Window(2, "Settings");
        settingWindow.init();

        runWindow = new Window(3, "Run");
        runWindow.init();

        registerListeners();

        Discord.startRPC();
    }

    public static void registerListeners()
    {
        try {
            GlobalScreen.registerNativeHook();

            KeyboardListener keyboardListener = new KeyboardListener();
            GlobalScreen.addNativeKeyListener(keyboardListener);

            MouseListener mouseListener = new MouseListener();
            GlobalScreen.addNativeMouseListener(mouseListener);
            GlobalScreen.addNativeMouseMotionListener(mouseListener);
            GlobalScreen.addNativeMouseWheelListener(mouseListener);

            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.SEVERE);
            logger.setUseParentHandlers(false);
        } catch (NativeHookException ignored) {
            System.out.println("Couldn't register listeners");
            System.exit(1);
        }
    }

    public static Theme getTheme()
    {
        return theme == null ? themes.get(0) : theme;
    }

    public static Window getMainWindow()
    {
        return mainWindow;
    }

    public static Window getSettingWindow()
    {
        return settingWindow;
    }

    public static Window getRunWindow()
    {
        return runWindow;
    }

    public static ArrayList<Theme> getThemes()
    {
        return themes;
    }

    public static void addTheme(Theme theme)
    {
        themes.add(theme);
    }

    public static String getProjectName()
    {
        return projectName;
    }

    public static String getFileName()
    {
        return fileName;
    }

    public static void setProjectName(String projectName)
    {
        Potentia.projectName = projectName;
    }

    public static void setFileName(String fileName)
    {
        Potentia.fileName = fileName;
    }

    public static Font getNormalFont()
    {
        return normalFont;
    }

    public static Font getItalicFont()
    {
        return italicFont;
    }
}
