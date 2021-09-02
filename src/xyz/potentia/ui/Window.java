package xyz.potentia.ui;

import xyz.potentia.Potentia;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;
import xyz.potentia.ui.impl.Button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Window extends JPanel
{
    protected int id;
    protected Graphics g;
    protected String name;
    protected JFrame frame;
    protected Image icon;
    protected final ArrayList<Element> elements = new ArrayList<>();

    public Window(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void init()
    {
        icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/potentia.png"));
        frame = new JFrame(name);
        frame.add(this);
        frame.setSize(900, 500);
        frame.setIconImage(icon);
        if (id == 1) {
            elements.add(new Button("Test Button", 10, 0, 130, 30));
            elements.add(new Button("Test Button 2", 10, 100, 130, 30));
            elements.add(new Button("Test Button 3", 10, 200, 130, 30));
            elements.add(new Button("Test Button 4", 10, 300, 130, 30));
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            open();
        } else if (id == 2) {
            frame.setLocation(getMaximumSize().width / 2, getMaximumSize().height / 2);
            centerWindow();
        }
    }

    public void centerWindow()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public void open()
    {
        frame.setVisible(true);
    }

    public void close()
    {
        frame.setVisible(false);
    }

    @Override
    public void paint(Graphics g)
    {
        this.setBackground(Potentia.getTheme().backgroundUI());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paint(g);
        this.g = g;
        draw();
    }

    public void draw()
    {
        for (Element el: elements) {
            el.draw(g);
        }
    }

    public void onMouse(MouseEvent e)
    {
        this.updateUI();
        for (Element el: elements) {
            el.onMouse(e);
        }
        this.updateUI();
    }

    public void onKeyboard(KeyboardEvent e)
    {
        this.updateUI();
        for (Element el: elements) {
            el.onKeyboard(e);
        }
        this.updateUI();
    }

    public ArrayList<Element> getElements()
    {
        return elements;
    }

    public void addElement(Element e)
    {
        elements.add(e);
    }

    public void clearElements()
    {
        elements.clear();
    }

    @Override
    public String getName()
    {
        return name;
    }

    public JFrame getFrame()
    {
        return frame;
    }
}
