package xyz.potentia.enums;

@SuppressWarnings("unused")
public enum Language
{
    NASM("NASM (Linux x86-64 Assembly)"), MASM("MASM (DOS and Windows Assembly)"), FASM("FASM (Flat Assembly)");

    private final String name;

    Language(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
