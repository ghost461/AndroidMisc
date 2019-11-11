package android.support.v4.app;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition$EpicenterCallback;
import android.transition.Transition$TransitionListener;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

@RequiresApi(value=21) class FragmentTransitionCompat21 {
    FragmentTransitionCompat21() {
        super();
    }

    static String access$000(Map arg0, String arg1) {
        return FragmentTransitionCompat21.findKeyForValue(arg0, arg1);
    }

    public static void addTarget(Object arg0, View arg1) {
        if(arg0 != null) {
            ((Transition)arg0).addTarget(arg1);
        }
    }

    public static void addTargets(Object arg3, ArrayList arg4) {
        int v0;
        if(arg3 == null) {
            return;
        }

        int v1 = 0;
        if((arg3 instanceof TransitionSet)) {
            v0 = ((TransitionSet)arg3).getTransitionCount();
            while(v1 < v0) {
                FragmentTransitionCompat21.addTargets(((TransitionSet)arg3).getTransitionAt(v1), arg4);
                ++v1;
            }
        }
        else if(!FragmentTransitionCompat21.hasSimpleTarget(((Transition)arg3)) && (FragmentTransitionCompat21.isNullOrEmpty(((Transition)arg3).getTargets()))) {
            v0 = arg4.size();
            while(v1 < v0) {
                ((Transition)arg3).addTarget(arg4.get(v1));
                ++v1;
            }
        }
    }

    public static void beginDelayedTransition(ViewGroup arg0, Object arg1) {
        TransitionManager.beginDelayedTransition(arg0, ((Transition)arg1));
    }

    private static void bfsAddViewChildren(List arg6, View arg7) {
        int v0 = arg6.size();
        if(FragmentTransitionCompat21.containedBeforeIndex(arg6, arg7, v0)) {
            return;
        }

        arg6.add(arg7);
        int v7;
        for(v7 = v0; v7 < arg6.size(); ++v7) {
            Object v1 = arg6.get(v7);
            if((v1 instanceof ViewGroup)) {
                int v2 = ((ViewGroup)v1).getChildCount();
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    View v4 = ((ViewGroup)v1).getChildAt(v3);
                    if(!FragmentTransitionCompat21.containedBeforeIndex(arg6, v4, v0)) {
                        arg6.add(v4);
                    }
                }
            }
        }
    }

    public static void captureTransitioningViews(ArrayList arg3, View arg4) {
        if(arg4.getVisibility() == 0) {
            if(!(arg4 instanceof ViewGroup)) {
                arg3.add(arg4);
            }
            else if(((ViewGroup)arg4).isTransitionGroup()) {
                arg3.add(arg4);
            }
            else {
                int v0 = ((ViewGroup)arg4).getChildCount();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    FragmentTransitionCompat21.captureTransitioningViews(arg3, ((ViewGroup)arg4).getChildAt(v1));
                }
            }
        }
    }

    public static Object cloneTransition(Object arg0) {
        Transition v0;
        if(arg0 != null) {
            v0 = ((Transition)arg0).clone();
        }
        else {
            arg0 = null;
        }

        return v0;
    }

    private static boolean containedBeforeIndex(List arg3, View arg4, int arg5) {
        int v1;
        for(v1 = 0; v1 < arg5; ++v1) {
            if(arg3.get(v1) == arg4) {
                return 1;
            }
        }

        return 0;
    }

    private static String findKeyForValue(Map arg2, String arg3) {
        Object v0;
        Iterator v2 = arg2.entrySet().iterator();
        do {
            if(!v2.hasNext()) {
                return null;
            }

            v0 = v2.next();
        }
        while(!arg3.equals(((Map$Entry)v0).getValue()));

        return ((Map$Entry)v0).getKey();
    }

    public static void findNamedViews(Map arg3, View arg4) {
        if(arg4.getVisibility() == 0) {
            String v0 = arg4.getTransitionName();
            if(v0 != null) {
                arg3.put(v0, arg4);
            }

            if(!(arg4 instanceof ViewGroup)) {
                return;
            }

            int v0_1 = ((ViewGroup)arg4).getChildCount();
            int v1;
            for(v1 = 0; v1 < v0_1; ++v1) {
                FragmentTransitionCompat21.findNamedViews(arg3, ((ViewGroup)arg4).getChildAt(v1));
            }
        }
    }

    public static void getBoundsOnScreen(View arg6, Rect arg7) {
        int[] v0 = new int[2];
        arg6.getLocationOnScreen(v0);
        arg7.set(v0[0], v0[1], v0[0] + arg6.getWidth(), v0[1] + arg6.getHeight());
    }

    private static boolean hasSimpleTarget(Transition arg1) {
        boolean v1 = !FragmentTransitionCompat21.isNullOrEmpty(arg1.getTargetIds()) || !FragmentTransitionCompat21.isNullOrEmpty(arg1.getTargetNames()) || !FragmentTransitionCompat21.isNullOrEmpty(arg1.getTargetTypes()) ? true : false;
        return v1;
    }

    private static boolean isNullOrEmpty(List arg0) {
        boolean v0 = arg0 == null || (arg0.isEmpty()) ? true : false;
        return v0;
    }

    public static Object mergeTransitionsInSequence(Object arg1, Object arg2, Object arg3) {
        TransitionSet v1;
        if(arg1 != null && arg2 != null) {
            v1 = new TransitionSet().addTransition(((Transition)arg1)).addTransition(((Transition)arg2)).setOrdering(1);
        }
        else if(arg1 != null) {
        }
        else if(arg2 != null) {
            arg1 = arg2;
        }
        else {
            arg1 = null;
        }

        if(arg3 != null) {
            TransitionSet v2 = new TransitionSet();
            if(v1 != null) {
                v2.addTransition(((Transition)v1));
            }

            v2.addTransition(((Transition)arg3));
            return v2;
        }

        return v1;
    }

    public static Object mergeTransitionsTogether(Object arg1, Object arg2, Object arg3) {
        TransitionSet v0 = new TransitionSet();
        if(arg1 != null) {
            v0.addTransition(((Transition)arg1));
        }

        if(arg2 != null) {
            v0.addTransition(((Transition)arg2));
        }

        if(arg3 != null) {
            v0.addTransition(((Transition)arg3));
        }

        return v0;
    }

    public static ArrayList prepareSetNameOverridesReordered(ArrayList arg5) {
        ArrayList v0 = new ArrayList();
        int v1 = arg5.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg5.get(v2);
            v0.add(((View)v3).getTransitionName());
            ((View)v3).setTransitionName(null);
        }

        return v0;
    }

    public static void removeTarget(Object arg0, View arg1) {
        if(arg0 != null) {
            ((Transition)arg0).removeTarget(arg1);
        }
    }

    public static void replaceTargets(Object arg4, ArrayList arg5, ArrayList arg6) {
        int v0;
        int v1 = 0;
        if((arg4 instanceof TransitionSet)) {
            v0 = ((TransitionSet)arg4).getTransitionCount();
            while(v1 < v0) {
                FragmentTransitionCompat21.replaceTargets(((TransitionSet)arg4).getTransitionAt(v1), arg5, arg6);
                ++v1;
            }
        }
        else if(!FragmentTransitionCompat21.hasSimpleTarget(((Transition)arg4))) {
            List v0_1 = ((Transition)arg4).getTargets();
            if(v0_1 != null && v0_1.size() == arg5.size() && (v0_1.containsAll(((Collection)arg5)))) {
                v0 = arg6 == null ? 0 : arg6.size();
                while(v1 < v0) {
                    ((Transition)arg4).addTarget(arg6.get(v1));
                    ++v1;
                }

                int v6;
                for(v6 = arg5.size() - 1; v6 >= 0; --v6) {
                    ((Transition)arg4).removeTarget(arg5.get(v6));
                }
            }
        }
    }

    public static void scheduleHideFragmentView(Object arg1, View arg2, ArrayList arg3) {
        ((Transition)arg1).addListener(new Transition$TransitionListener(arg2, arg3) {
            public void onTransitionCancel(Transition arg1) {
            }

            public void onTransitionEnd(Transition arg4) {
                arg4.removeListener(((Transition$TransitionListener)this));
                this.val$fragmentView.setVisibility(8);
                int v4 = this.val$exitingViews.size();
                int v1;
                for(v1 = 0; v1 < v4; ++v1) {
                    this.val$exitingViews.get(v1).setVisibility(0);
                }
            }

            public void onTransitionPause(Transition arg1) {
            }

            public void onTransitionResume(Transition arg1) {
            }

            public void onTransitionStart(Transition arg1) {
            }
        });
    }

    public static void scheduleNameReset(ViewGroup arg1, ArrayList arg2, Map arg3) {
        OneShotPreDrawListener.add(((View)arg1), new Runnable(arg2, arg3) {
            public void run() {
                int v0 = this.val$sharedElementsIn.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    Object v2 = this.val$sharedElementsIn.get(v1);
                    ((View)v2).setTransitionName(this.val$nameOverrides.get(((View)v2).getTransitionName()));
                }
            }
        });
    }

    public static void scheduleRemoveTargets(Object arg8, Object arg9, ArrayList arg10, Object arg11, ArrayList arg12, Object arg13, ArrayList arg14) {
        ((Transition)arg8).addListener(new Transition$TransitionListener(arg9, arg10, arg11, arg12, arg13, arg14) {
            public void onTransitionCancel(Transition arg1) {
            }

            public void onTransitionEnd(Transition arg1) {
            }

            public void onTransitionPause(Transition arg1) {
            }

            public void onTransitionResume(Transition arg1) {
            }

            public void onTransitionStart(Transition arg3) {
                ArrayList v0 = null;
                if(this.val$enterTransition != null) {
                    FragmentTransitionCompat21.replaceTargets(this.val$enterTransition, this.val$enteringViews, v0);
                }

                if(this.val$exitTransition != null) {
                    FragmentTransitionCompat21.replaceTargets(this.val$exitTransition, this.val$exitingViews, v0);
                }

                if(this.val$sharedElementTransition != null) {
                    FragmentTransitionCompat21.replaceTargets(this.val$sharedElementTransition, this.val$sharedElementsIn, v0);
                }
            }
        });
    }

    public static void setEpicenter(Object arg1, Rect arg2) {
        if(arg1 != null) {
            ((Transition)arg1).setEpicenterCallback(new Transition$EpicenterCallback(arg2) {
                public Rect onGetEpicenter(Transition arg1) {
                    if(this.val$epicenter != null) {
                        if(this.val$epicenter.isEmpty()) {
                        }
                        else {
                            return this.val$epicenter;
                        }
                    }

                    return null;
                }
            });
        }
    }

    public static void setEpicenter(Object arg1, View arg2) {
        if(arg2 != null) {
            Rect v0 = new Rect();
            FragmentTransitionCompat21.getBoundsOnScreen(arg2, v0);
            ((Transition)arg1).setEpicenterCallback(new Transition$EpicenterCallback(v0) {
                public Rect onGetEpicenter(Transition arg1) {
                    return this.val$epicenter;
                }
            });
        }
    }

    public static void setNameOverridesOrdered(View arg1, ArrayList arg2, Map arg3) {
        OneShotPreDrawListener.add(arg1, new Runnable(arg2, arg3) {
            public void run() {
                int v0 = this.val$sharedElementsIn.size();
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    Object v2 = this.val$sharedElementsIn.get(v1);
                    String v3 = ((View)v2).getTransitionName();
                    if(v3 != null) {
                        ((View)v2).setTransitionName(FragmentTransitionCompat21.findKeyForValue(this.val$nameOverrides, v3));
                    }
                }
            }
        });
    }

    public static void setNameOverridesReordered(View arg8, ArrayList arg9, ArrayList arg10, ArrayList arg11, Map arg12) {
        int v1 = arg10.size();
        ArrayList v5 = new ArrayList();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = arg9.get(v2);
            String v4 = ((View)v3).getTransitionName();
            v5.add(v4);
            if(v4 == null) {
            }
            else {
                ((View)v3).setTransitionName(null);
                v3 = arg12.get(v4);
                int v6 = 0;
                while(v6 < v1) {
                    if(((String)v3).equals(arg11.get(v6))) {
                        arg10.get(v6).setTransitionName(v4);
                    }
                    else {
                        ++v6;
                        continue;
                    }

                    break;
                }
            }
        }

        OneShotPreDrawListener.add(arg8, new Runnable(v1, arg10, arg11, arg9, v5) {
            public void run() {
                int v0;
                for(v0 = 0; v0 < this.val$numSharedElements; ++v0) {
                    this.val$sharedElementsIn.get(v0).setTransitionName(this.val$inNames.get(v0));
                    this.val$sharedElementsOut.get(v0).setTransitionName(this.val$outNames.get(v0));
                }
            }
        });
    }

    public static void setSharedElementTargets(Object arg4, View arg5, ArrayList arg6) {
        List v0 = ((TransitionSet)arg4).getTargets();
        v0.clear();
        int v1 = arg6.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            FragmentTransitionCompat21.bfsAddViewChildren(v0, arg6.get(v2));
        }

        v0.add(arg5);
        arg6.add(arg5);
        FragmentTransitionCompat21.addTargets(arg4, arg6);
    }

    public static void swapSharedElementTargets(Object arg1, ArrayList arg2, ArrayList arg3) {
        if(arg1 != null) {
            ((TransitionSet)arg1).getTargets().clear();
            ((TransitionSet)arg1).getTargets().addAll(((Collection)arg3));
            FragmentTransitionCompat21.replaceTargets(arg1, arg2, arg3);
        }
    }

    public static Object wrapTransitionInSet(Object arg1) {
        if(arg1 == null) {
            return null;
        }

        TransitionSet v0 = new TransitionSet();
        v0.addTransition(((Transition)arg1));
        return v0;
    }
}

