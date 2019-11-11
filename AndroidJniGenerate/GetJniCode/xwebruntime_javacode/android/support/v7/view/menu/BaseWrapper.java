package android.support.v7.view.menu;

class BaseWrapper {
    final Object mWrappedObject;

    BaseWrapper(Object arg2) {
        super();
        if(arg2 == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }

        this.mWrappedObject = arg2;
    }

    public Object getWrappedObject() {
        return this.mWrappedObject;
    }
}

