package xyz.potentia.util;

import java.io.File;
import java.util.Objects;

public class SystemUtil
{
    public static File getFile(String path)
    {
        return new File(System.getProperty("os.name").startsWith("Win") ? path.replace("/", "\\") : path);
    }

    public static String[] list(String path)
    {
        return Objects.requireNonNull(getFile(path)).list();
    }

    public static File[] listFiles(String path)
    {
        return Objects.requireNonNull(getFile(path)).listFiles();
    }
}
