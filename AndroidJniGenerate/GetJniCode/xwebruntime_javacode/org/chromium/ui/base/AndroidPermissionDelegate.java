package org.chromium.ui.base;

public interface AndroidPermissionDelegate {
    boolean canRequestPermission(String arg1);

    boolean hasPermission(String arg1);

    boolean isPermissionRevokedByPolicy(String arg1);

    void onRequestPermissionsResult(int arg1, String[] arg2, int[] arg3);

    void requestPermissions(String[] arg1, PermissionCallback arg2);
}

