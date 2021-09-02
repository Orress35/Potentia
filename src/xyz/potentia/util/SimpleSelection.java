package xyz.potentia.util;

public class SimpleSelection
{
    int startIndex, endIndex;

    public SimpleSelection(int startIndex, int endIndex)
    {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex()
    {
        return startIndex;
    }

    public int getEndIndex()
    {
        return endIndex;
    }

    public void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    public void setEndIndex(int endIndex)
    {
        this.endIndex = endIndex;
    }
}
