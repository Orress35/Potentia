package xyz.potentia.util;

import xyz.potentia.Potentia;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ThemeLoader
{
    public void loadAll()
    {
        String runningDirectory = System.getProperty("user.dir");
        for (String file: SystemUtil.list(runningDirectory + "/themes"))
            load(runningDirectory + "/themes/" + file);
    }

    public void load(String file)
    {
        String[] lines = new String[32];
        try {
            BufferedReader br = new BufferedReader(new FileReader(SystemUtil.getFile(file)));
            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                lines[i] = line;
                i++;
            }
            br.close();
        } catch (IOException ignored) {
            System.out.println("An error occured while reading " + file);
        }
        load(lines);
    }

    public void load(String[] lines) {
        try {
            Constructor<?> constructor = Theme.class.getDeclaredConstructors()[0];
            Potentia.addTheme((Theme) constructor.newInstance(parseColors(lines)));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object[] parseColors(String[] lines) {
        ArrayList<Color> colors_ = parseColors_(lines);
        Color[] colors = new Color[colors_.size()];
        int i = 0;
        for (Color color: colors_) {
            colors[i] = color;
            i++;
        }
        return colors;
    }

    public ArrayList<Color> parseColors_(String[] lines) {
        ArrayList<Color> colors = new ArrayList<>();
        for (String line: lines) {
            if (line == null)
                break;
            if (line.contains(":") && !line.contains(","))
                continue;
            if (parseColor(line) == null)
                break;
            colors.add(parseColor(line));
        }
        return colors;
    }

    public Color parseColor(String color) {
        try {
            for (int i = 0; i < 10; i++)
                color = color.replace(", ", ",");
            color = color.split(" ")[color.split(" ").length - 1];
            int r = Integer.parseInt(color.split(",")[0]);
            int g = Integer.parseInt(color.split(",")[1]);
            int b = Integer.parseInt(color.split(",")[2]);
            return new Color(r, g, b);
        } catch (Exception ignored) {
            System.out.println("Couldn't parse color | " + color);
            return null;
        }
    }
}
