package xyz.potentia.ui.impl;

import xyz.potentia.Potentia;

public class TopMenuButton
{
    protected int id;
    protected String label;
    protected boolean hovered;
    protected boolean extended = false;

    public TopMenuButton(int id, String label)
    {
        this.id = id;
        this.label = label;
    }

    public void onClick()
    {
        if (id == 1) {
            // TODO: add creation of projects, files, folders
        } else if (id == 2) {
            // TODO: add a folder browser dialog
        } else if (id == 3) {
            // TODO: add a project configuration dialog
        } else if (id == 4) {
            Potentia.getSettingWindow().open();
        } else if (id == 6) {
            System.exit(0);
        } else if (id == 8) {
            // TODO: add a github project creation dialog
        } else if (id == 9) {
            // TODO: add a github commit dialog
        } else if (id == 10) {
            // TODO: add a github push dialog
        } else if (id == 11) {
            // TODO: add a github fetch dialog
        } else if (id == 12) {
            // TODO: add a github pull dialog
        } else if (id == 14) {
            // TODO: add a generate dialog
            extended = false;
        } else if (id == 15) {
            // TODO: add a search dialog
            extended = false;
        } else if (id == 16) {
            // TODO: add a replace dialog
            extended = false;
        } else if (id == 17) {
            // TODO: add a build dialog (exe/elf/jar/etc)
            extended = false;
        }
    }
}
