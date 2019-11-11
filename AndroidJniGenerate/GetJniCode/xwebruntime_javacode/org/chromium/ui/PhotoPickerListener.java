package org.chromium.ui;

public interface PhotoPickerListener {
    public enum Action {
        public static final enum Action CANCEL;
        public static final enum Action LAUNCH_CAMERA;
        public static final enum Action LAUNCH_GALLERY;
        public static final enum Action PHOTOS_SELECTED;

        static {
            Action.CANCEL = new Action("CANCEL", 0);
            Action.PHOTOS_SELECTED = new Action("PHOTOS_SELECTED", 1);
            Action.LAUNCH_CAMERA = new Action("LAUNCH_CAMERA", 2);
            Action.LAUNCH_GALLERY = new Action("LAUNCH_GALLERY", 3);
            Action.$VALUES = new Action[]{Action.CANCEL, Action.PHOTOS_SELECTED, Action.LAUNCH_CAMERA, Action.LAUNCH_GALLERY};
        }

        private Action(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Action valueOf(String arg1) {
            return Enum.valueOf(Action.class, arg1);
        }

        public static Action[] values() {
            return Action.$VALUES.clone();
        }
    }

    public static final int SHOW_GALLERY = 2;
    public static final int TAKE_PHOTO_REQUEST = 1;

    void onPickerUserAction(Action arg1, String[] arg2);
}

