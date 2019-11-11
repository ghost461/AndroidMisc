package android.arch.lifecycle;

import android.support.annotation.MainThread;

public abstract class Lifecycle {
    public enum Event {
        public static final enum Event ON_ANY;
        public static final enum Event ON_CREATE;
        public static final enum Event ON_DESTROY;
        public static final enum Event ON_PAUSE;
        public static final enum Event ON_RESUME;
        public static final enum Event ON_START;
        public static final enum Event ON_STOP;

        static {
            Event.ON_CREATE = new Event("ON_CREATE", 0);
            Event.ON_START = new Event("ON_START", 1);
            Event.ON_RESUME = new Event("ON_RESUME", 2);
            Event.ON_PAUSE = new Event("ON_PAUSE", 3);
            Event.ON_STOP = new Event("ON_STOP", 4);
            Event.ON_DESTROY = new Event("ON_DESTROY", 5);
            Event.ON_ANY = new Event("ON_ANY", 6);
            Event.$VALUES = new Event[]{Event.ON_CREATE, Event.ON_START, Event.ON_RESUME, Event.ON_PAUSE, Event.ON_STOP, Event.ON_DESTROY, Event.ON_ANY};
        }

        private Event(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Event valueOf(String arg1) {
            return Enum.valueOf(Event.class, arg1);
        }

        public static Event[] values() {
            return Event.$VALUES.clone();
        }
    }

    public enum State {
        public static final enum State CREATED;
        public static final enum State DESTROYED;
        public static final enum State INITIALIZED;
        public static final enum State RESUMED;
        public static final enum State STARTED;

        static {
            State.DESTROYED = new State("DESTROYED", 0);
            State.INITIALIZED = new State("INITIALIZED", 1);
            State.CREATED = new State("CREATED", 2);
            State.STARTED = new State("STARTED", 3);
            State.RESUMED = new State("RESUMED", 4);
            State.$VALUES = new State[]{State.DESTROYED, State.INITIALIZED, State.CREATED, State.STARTED, State.RESUMED};
        }

        private State(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public boolean isAtLeast(State arg1) {
            boolean v1 = this.compareTo(((Enum)arg1)) >= 0 ? true : false;
            return v1;
        }

        public static State valueOf(String arg1) {
            return Enum.valueOf(State.class, arg1);
        }

        public static State[] values() {
            return State.$VALUES.clone();
        }
    }

    public Lifecycle() {
        super();
    }

    @MainThread public abstract void addObserver(LifecycleObserver arg1);

    @MainThread public abstract State getCurrentState();

    @MainThread public abstract void removeObserver(LifecycleObserver arg1);
}

