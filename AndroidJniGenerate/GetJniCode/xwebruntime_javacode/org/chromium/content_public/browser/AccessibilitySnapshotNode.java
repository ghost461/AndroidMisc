package org.chromium.content_public.browser;

import java.util.ArrayList;

public class AccessibilitySnapshotNode {
    public int bgcolor;
    public boolean bold;
    public ArrayList children;
    public String className;
    public int color;
    public int endSelection;
    public boolean hasSelection;
    public boolean hasStyle;
    public int height;
    public boolean isRootNode;
    public boolean italic;
    public boolean lineThrough;
    public int startSelection;
    public String text;
    public float textSize;
    public boolean underline;
    public int width;
    public int x;
    public int y;

    public AccessibilitySnapshotNode(String arg2, String arg3) {
        super();
        this.children = new ArrayList();
        this.text = arg2;
        this.className = arg3;
    }

    public void addChild(AccessibilitySnapshotNode arg2) {
        this.children.add(arg2);
    }

    public void setLocationInfo(int arg1, int arg2, int arg3, int arg4, boolean arg5) {
        this.x = arg1;
        this.y = arg2;
        this.width = arg3;
        this.height = arg4;
        this.isRootNode = arg5;
    }

    public void setSelection(int arg2, int arg3) {
        this.hasSelection = true;
        this.startSelection = arg2;
        this.endSelection = arg3;
    }

    public void setStyle(int arg1, int arg2, float arg3, boolean arg4, boolean arg5, boolean arg6, boolean arg7) {
        this.color = arg1;
        this.bgcolor = arg2;
        this.textSize = arg3;
        this.bold = arg4;
        this.italic = arg5;
        this.underline = arg6;
        this.lineThrough = arg7;
        this.hasStyle = true;
    }
}

