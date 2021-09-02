package xyz.potentia.ui;

import xyz.potentia.Potentia;
import xyz.potentia.enums.MouseEventType;
import xyz.potentia.event.events.KeyboardEvent;
import xyz.potentia.event.events.MouseEvent;
import xyz.potentia.ui.impl.Button;
import xyz.potentia.ui.impl.TextBox;
import xyz.potentia.ui.impl.TopMenu;
import xyz.potentia.ui.impl.TopMenuButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("unused")
public class Window extends JPanel
{
    protected int id;
    protected Graphics g;
    protected String name;
    protected JFrame frame;
    protected Image icon;
    protected final ArrayList<Element> elements = new ArrayList<>();
    protected TextBox selectedTextBox;

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
            HashMap<TopMenuButton, ArrayList<TopMenuButton>> dropDowns = new HashMap<>();

            ArrayList<TopMenuButton> file = new ArrayList<>();
            file.add(new TopMenuButton(1, "New (-)"));
            file.add(new TopMenuButton(2, "Open (-)"));
            file.add(new TopMenuButton(3, "Project (-)"));
            file.add(new TopMenuButton(4, "Settings"));
            file.add(new TopMenuButton(5, "Save All (-)"));
            file.add(new TopMenuButton(6, "Exit"));
            dropDowns.put(new TopMenuButton(0, "File (-)"), file);

            ArrayList<TopMenuButton> git = new ArrayList<>();
            git.add(new TopMenuButton(8, "Create (-)"));
            git.add(new TopMenuButton(9, "Commit (-)"));
            git.add(new TopMenuButton(10, "Push (-)"));
            git.add(new TopMenuButton(11, "Fetch (-)"));
            git.add(new TopMenuButton(12, "Pull (-)"));
            dropDowns.put(new TopMenuButton(7, "Git (-)"), git);

            ArrayList<TopMenuButton> refactor = new ArrayList<>();
            refactor.add(new TopMenuButton(14, "Generate (-)"));
            refactor.add(new TopMenuButton(15, "Search (-)"));
            refactor.add(new TopMenuButton(16, "Replace (-)"));
            dropDowns.put(new TopMenuButton(13, "Code (-)"), refactor);

            ArrayList<TopMenuButton> build = new ArrayList<>();
            dropDowns.put(new TopMenuButton(17, "Build (-)"), build);

            elements.add(new TopMenu(this, 30, dropDowns));

            elements.add(new Button(this, 1, "Run", 60, 0, 60, 30));

            elements.add(new TextBox(1, new StringBuilder("hello"), 10, 40, 100, 24));

            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            open();
        } else if (id == 2) {
            // TODO: implement the Settings GUI
            frame.setLocation(getMaximumSize().width / 2, getMaximumSize().height / 2);
            centerWindow();
        } else if (id == 3) {
            // TODO: implement the Run GUI
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
            if (el instanceof TextBox textBox) {
                textBox.selected = textBox == selectedTextBox;
            }
            el.draw(g);
        }
    }

    public void onMouse(MouseEvent e)
    {
        this.updateUI();
        for (Element el: elements) {
            if (e.getType() == MouseEventType.CLICK && el instanceof TextBox) {
                boolean hovered = ((TextBox) el).isHovered();
                if (hovered) {
                    selectedTextBox = (TextBox) el;
                    ((TextBox) el).select(e.getX(), e.getY());
                }
            }
            el.onMouse(e);
        }
        this.updateUI();
    }

    public void onKeyboard(KeyboardEvent e)
    {
        this.updateUI();
        if (selectedTextBox != null)
            selectedTextBox.onKeyboard(e);
        for (Element el: elements) {
            if (el instanceof TextBox)
                continue;
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
