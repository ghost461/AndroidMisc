package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class FragmentTransition {
    class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
            super();
        }
    }

    private static final int[] INVERSE_OPS;

    static {
        FragmentTransition.INVERSE_OPS = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    }

    FragmentTransition() {
        super();
    }

    static void access$000(ArrayList arg0, int arg1) {
        FragmentTransition.setViewVisibility(arg0, arg1);
    }

    static ArrayList access$100(Object arg0, Fragment arg1, ArrayList arg2, View arg3) {
        return FragmentTransition.configureEnteringExitingViews(arg0, arg1, arg2, arg3);
    }

    static void access$200(Fragment arg0, Fragment arg1, boolean arg2, ArrayMap arg3, boolean arg4) {
        FragmentTransition.callSharedElementStartEnd(arg0, arg1, arg2, arg3, arg4);
    }

    static ArrayMap access$300(ArrayMap arg0, Object arg1, FragmentContainerTransition arg2) {
        return FragmentTransition.captureInSharedElements(arg0, arg1, arg2);
    }

    static View access$400(ArrayMap arg0, FragmentContainerTransition arg1, Object arg2, boolean arg3) {
        return FragmentTransition.getInEpicenterView(arg0, arg1, arg2, arg3);
    }

    private static void addSharedElementsWithMatchingNames(ArrayList arg3, ArrayMap arg4, Collection arg5) {
        int v0;
        for(v0 = arg4.size() - 1; v0 >= 0; --v0) {
            Object v1 = arg4.valueAt(v0);
            if(arg5.contains(ViewCompat.getTransitionName(((View)v1)))) {
                arg3.add(v1);
            }
        }
    }

    private static void addToFirstInLastOut(BackStackRecord arg16, Op arg17, SparseArray arg18, boolean arg19, boolean arg20) {
        boolean v1_2;
        int v12;
        int v13;
        BackStackRecord v0 = arg16;
        Op v1 = arg17;
        SparseArray v2 = arg18;
        boolean v3 = arg19;
        Fragment v10 = v1.fragment;
        if(v10 == null) {
            return;
        }

        int v11 = v10.mContainerId;
        if(v11 == 0) {
            return;
        }

        int v1_1 = v3 ? FragmentTransition.INVERSE_OPS[v1.cmd] : v1.cmd;
        boolean v4 = false;
        if(v1_1 != 1) {
            switch(v1_1) {
                case 4: {
                    if(arg20) {
                        if(!v10.mHiddenChanged) {
                            goto label_60;
                        }
                        else if(!v10.mAdded) {
                            goto label_60;
                        }
                        else if(v10.mHidden) {
                            goto label_58;
                        }
                        else {
                            goto label_60;
                        }
                    }
                    else if(!v10.mAdded) {
                        goto label_60;
                    }
                    else if(!v10.mHidden) {
                        goto label_58;
                    }
                    else {
                        goto label_60;
                    }

                    goto label_84;
                }
                case 5: {
                    if(!arg20) {
                        v1_2 = v10.mHidden;
                        goto label_81;
                    }
                    else if(!v10.mHiddenChanged) {
                        goto label_80;
                    }
                    else if(v10.mHidden) {
                        goto label_80;
                    }
                    else if(v10.mAdded) {
                        goto label_78;
                    }
                    else {
                        goto label_80;
                    }
                }
                case 3: 
                case 6: {
                    if(arg20) {
                        if(v10.mAdded) {
                            goto label_60;
                        }
                        else if(v10.mView == null) {
                            goto label_60;
                        }
                        else if(v10.mView.getVisibility() == 0) {
                            if(v10.mPostponedAlpha < 0f) {
                                goto label_60;
                            }

                            goto label_58;
                        }
                        else {
                            goto label_60;
                        }
                    }
                    else if(!v10.mAdded) {
                    label_60:
                        v1_1 = 0;
                    label_67:
                        v13 = v1_1;
                        v1_1 = 0;
                        v12 = 1;
                    }
                    else if(!v10.mHidden) {
                    label_58:
                        v1_1 = 1;
                        goto label_67;
                    }
                    else {
                        goto label_60;
                    }

                    goto label_84;
                }
                case 7: {
                    goto label_71;
                }
                default: {
                    v1_1 = 0;
                    goto label_21;
                }
            }
        }
        else {
        label_71:
            if(arg20) {
                v1_2 = v10.mIsNewlyAdded;
                goto label_81;
            }
            else if(v10.mAdded) {
            label_80:
                v1_2 = false;
            label_81:
                v4 = v1_2;
                v1_1 = 1;
            label_21:
                v12 = 0;
                v13 = 0;
            }
            else if(!v10.mHidden) {
            label_78:
                v1_2 = true;
                goto label_81;
            }
            else {
                goto label_80;
            }
        }

    label_84:
        Object v6 = v2.get(v11);
        if(v4) {
            FragmentContainerTransition v6_1 = FragmentTransition.ensureContainer(((FragmentContainerTransition)v6), v2, v11);
            v6_1.lastIn = v10;
            v6_1.lastInIsPop = v3;
            v6_1.lastInTransaction = v0;
        }

        Object v14 = v6;
        Fragment v9 = null;
        if(!arg20 && v1_1 != 0) {
            if(v14 != null && ((FragmentContainerTransition)v14).firstOut == v10) {
                ((FragmentContainerTransition)v14).firstOut = v9;
            }

            FragmentManagerImpl v4_1 = v0.mManager;
            if(v10.mState >= 1) {
                goto label_113;
            }

            if(v4_1.mCurState < 1) {
                goto label_113;
            }

            if(v0.mReorderingAllowed) {
                goto label_113;
            }

            v4_1.makeActive(v10);
            v4_1.moveToState(v10, 1, 0, 0, false);
        }

    label_113:
        if(v13 != 0 && (v14 == null || ((FragmentContainerTransition)v14).firstOut == null)) {
            FragmentContainerTransition v14_1 = FragmentTransition.ensureContainer(((FragmentContainerTransition)v14), v2, v11);
            v14_1.firstOut = v10;
            v14_1.firstOutIsPop = v3;
            v14_1.firstOutTransaction = v0;
        }

        if(!arg20 && v12 != 0 && v14 != null && ((FragmentContainerTransition)v14).lastIn == v10) {
            ((FragmentContainerTransition)v14).lastIn = null;
        }
    }

    public static void calculateFragments(BackStackRecord arg4, SparseArray arg5, boolean arg6) {
        int v0 = arg4.mOps.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            FragmentTransition.addToFirstInLastOut(arg4, arg4.mOps.get(v2), arg5, false, arg6);
        }
    }

    private static ArrayMap calculateNameOverrides(int arg9, ArrayList arg10, ArrayList arg11, int arg12, int arg13) {
        ArrayList v1_1;
        ArrayList v2_1;
        ArrayMap v0 = new ArrayMap();
        --arg13;
        while(arg13 >= arg12) {
            Object v1 = arg10.get(arg13);
            if(!((BackStackRecord)v1).interactsWith(arg9)) {
            }
            else {
                boolean v2 = arg11.get(arg13).booleanValue();
                if(((BackStackRecord)v1).mSharedElementSourceNames != null) {
                    int v3 = ((BackStackRecord)v1).mSharedElementSourceNames.size();
                    if(v2) {
                        v2_1 = ((BackStackRecord)v1).mSharedElementSourceNames;
                        v1_1 = ((BackStackRecord)v1).mSharedElementTargetNames;
                    }
                    else {
                        ArrayList v8 = ((BackStackRecord)v1).mSharedElementSourceNames;
                        v2_1 = ((BackStackRecord)v1).mSharedElementTargetNames;
                        v1_1 = v8;
                    }

                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        Object v5 = v1_1.get(v4);
                        Object v6 = v2_1.get(v4);
                        Object v7 = v0.remove(v6);
                        if(v7 != null) {
                            v0.put(v5, v7);
                        }
                        else {
                            v0.put(v5, v6);
                        }
                    }
                }
            }

            --arg13;
        }

        return v0;
    }

    public static void calculatePopFragments(BackStackRecord arg3, SparseArray arg4, boolean arg5) {
        if(!arg3.mManager.mContainer.onHasView()) {
            return;
        }

        int v0;
        for(v0 = arg3.mOps.size() - 1; v0 >= 0; --v0) {
            FragmentTransition.addToFirstInLastOut(arg3, arg3.mOps.get(v0), arg4, true, arg5);
        }
    }

    private static void callSharedElementStartEnd(Fragment arg3, Fragment arg4, boolean arg5, ArrayMap arg6, boolean arg7) {
        SharedElementCallback v3 = arg5 ? arg4.getEnterTransitionCallback() : arg3.getEnterTransitionCallback();
        if(v3 != null) {
            ArrayList v4 = new ArrayList();
            ArrayList v5 = new ArrayList();
            int v0 = 0;
            int v1 = arg6 == null ? 0 : arg6.size();
            while(v0 < v1) {
                v5.add(arg6.keyAt(v0));
                v4.add(arg6.valueAt(v0));
                ++v0;
            }

            List v6 = null;
            if(arg7) {
                v3.onSharedElementStart(((List)v5), ((List)v4), v6);
                return;
            }

            v3.onSharedElementEnd(((List)v5), ((List)v4), v6);
        }
    }

    @RequiresApi(value=21) private static ArrayMap captureInSharedElements(ArrayMap arg4, Object arg5, FragmentContainerTransition arg6) {
        String v1_3;
        ArrayList v0_1;
        SharedElementCallback v6;
        Fragment v0 = arg6.lastIn;
        View v1 = v0.getView();
        if(!arg4.isEmpty() && arg5 != null) {
            if(v1 == null) {
            }
            else {
                ArrayMap v5 = new ArrayMap();
                FragmentTransitionCompat21.findNamedViews(((Map)v5), v1);
                BackStackRecord v1_1 = arg6.lastInTransaction;
                if(arg6.lastInIsPop) {
                    v6 = v0.getExitTransitionCallback();
                    v0_1 = v1_1.mSharedElementSourceNames;
                }
                else {
                    v6 = v0.getEnterTransitionCallback();
                    v0_1 = v1_1.mSharedElementTargetNames;
                }

                if(v0_1 != null) {
                    v5.retainAll(((Collection)v0_1));
                }

                if(v6 != null) {
                    v6.onMapSharedElements(((List)v0_1), ((Map)v5));
                    int v6_1;
                    for(v6_1 = v0_1.size() - 1; v6_1 >= 0; --v6_1) {
                        Object v1_2 = v0_1.get(v6_1);
                        Object v2 = v5.get(v1_2);
                        if(v2 == null) {
                            v1_3 = FragmentTransition.findKeyForValue(arg4, ((String)v1_2));
                            if(v1_3 != null) {
                                arg4.remove(v1_3);
                            }
                        }
                        else if(!((String)v1_2).equals(ViewCompat.getTransitionName(((View)v2)))) {
                            v1_3 = FragmentTransition.findKeyForValue(arg4, ((String)v1_2));
                            if(v1_3 != null) {
                                arg4.put(v1_3, ViewCompat.getTransitionName(((View)v2)));
                            }
                        }
                    }
                }
                else {
                    FragmentTransition.retainValues(arg4, v5);
                }

                return v5;
            }
        }

        arg4.clear();
        return null;
    }

    @RequiresApi(value=21) private static ArrayMap captureOutSharedElements(ArrayMap arg4, Object arg5, FragmentContainerTransition arg6) {
        ArrayList v6;
        SharedElementCallback v5_1;
        if(!arg4.isEmpty()) {
            if(arg5 == null) {
            }
            else {
                Fragment v5 = arg6.firstOut;
                ArrayMap v0 = new ArrayMap();
                FragmentTransitionCompat21.findNamedViews(((Map)v0), v5.getView());
                BackStackRecord v1 = arg6.firstOutTransaction;
                if(arg6.firstOutIsPop) {
                    v5_1 = v5.getEnterTransitionCallback();
                    v6 = v1.mSharedElementTargetNames;
                }
                else {
                    v5_1 = v5.getExitTransitionCallback();
                    v6 = v1.mSharedElementSourceNames;
                }

                v0.retainAll(((Collection)v6));
                if(v5_1 != null) {
                    v5_1.onMapSharedElements(((List)v6), ((Map)v0));
                    int v5_2;
                    for(v5_2 = v6.size() - 1; v5_2 >= 0; --v5_2) {
                        Object v1_1 = v6.get(v5_2);
                        Object v2 = v0.get(v1_1);
                        if(v2 == null) {
                            arg4.remove(v1_1);
                        }
                        else if(!((String)v1_1).equals(ViewCompat.getTransitionName(((View)v2)))) {
                            arg4.put(ViewCompat.getTransitionName(((View)v2)), arg4.remove(v1_1));
                        }
                    }
                }
                else {
                    arg4.retainAll(v0.keySet());
                }

                return v0;
            }
        }

        arg4.clear();
        return null;
    }

    @RequiresApi(value=21) private static ArrayList configureEnteringExitingViews(Object arg1, Fragment arg2, ArrayList arg3, View arg4) {
        ArrayList v0;
        if(arg1 != null) {
            v0 = new ArrayList();
            View v2 = arg2.getView();
            if(v2 != null) {
                FragmentTransitionCompat21.captureTransitioningViews(v0, v2);
            }

            if(arg3 != null) {
                v0.removeAll(((Collection)arg3));
            }

            if(v0.isEmpty()) {
                return v0;
            }

            v0.add(arg4);
            FragmentTransitionCompat21.addTargets(arg1, v0);
        }
        else {
            v0 = null;
        }

        return v0;
    }

    @RequiresApi(value=21) private static Object configureSharedElementsOrdered(ViewGroup arg15, View arg16, ArrayMap arg17, FragmentContainerTransition arg18, ArrayList arg19, ArrayList arg20, Object arg21, Object arg22) {
        View v11;
        Rect v1_1;
        Object v12;
        Object v4;
        ArrayMap v2;
        FragmentContainerTransition v3 = arg18;
        ArrayList v9 = arg19;
        Object v10 = arg21;
        Object v0 = arg22;
        Fragment v6 = v3.lastIn;
        Fragment v7 = v3.firstOut;
        Object v1 = null;
        if(v6 != null) {
            if(v7 == null) {
            }
            else {
                boolean v8 = v3.lastInIsPop;
                if(arg17.isEmpty()) {
                    v2 = arg17;
                    v4 = v1;
                }
                else {
                    v4 = FragmentTransition.getSharedElementTransition(v6, v7, v8);
                    v2 = arg17;
                }

                ArrayMap v5 = FragmentTransition.captureOutSharedElements(v2, v4, v3);
                if(arg17.isEmpty()) {
                    v12 = v1;
                }
                else {
                    v9.addAll(v5.values());
                    v12 = v4;
                }

                if(v10 == null && v0 == null && v12 == null) {
                    return v1;
                }

                FragmentTransition.callSharedElementStartEnd(v6, v7, v8, v5, true);
                if(v12 != null) {
                    v1_1 = new Rect();
                    v11 = arg16;
                    FragmentTransitionCompat21.setSharedElementTargets(v12, v11, v9);
                    FragmentTransition.setOutEpicenter(v12, v0, v5, v3.firstOutIsPop, v3.firstOutTransaction);
                    if(v10 != null) {
                        FragmentTransitionCompat21.setEpicenter(v10, v1_1);
                    }
                }
                else {
                    v11 = arg16;
                }

                OneShotPreDrawListener.add(arg15, new Runnable(v2, v12, v3, arg20, v11, v6, v7, v8, v9, v10, v1_1) {
                    public void run() {
                        ArrayMap v0 = FragmentTransition.captureInSharedElements(this.val$nameOverrides, this.val$finalSharedElementTransition, this.val$fragments);
                        if(v0 != null) {
                            this.val$sharedElementsIn.addAll(v0.values());
                            this.val$sharedElementsIn.add(this.val$nonExistentView);
                        }

                        FragmentTransition.callSharedElementStartEnd(this.val$inFragment, this.val$outFragment, this.val$inIsPop, v0, false);
                        if(this.val$finalSharedElementTransition != null) {
                            FragmentTransitionCompat21.swapSharedElementTargets(this.val$finalSharedElementTransition, this.val$sharedElementsOut, this.val$sharedElementsIn);
                            View v0_1 = FragmentTransition.getInEpicenterView(v0, this.val$fragments, this.val$enterTransition, this.val$inIsPop);
                            if(v0_1 != null) {
                                FragmentTransitionCompat21.getBoundsOnScreen(v0_1, this.val$inEpicenter);
                            }
                        }
                    }
                });
                return v12;
            }
        }

        return v1;
    }

    @RequiresApi(value=21) private static Object configureSharedElementsReordered(ViewGroup arg8, View arg9, ArrayMap arg10, FragmentContainerTransition arg11, ArrayList arg12, ArrayList arg13, Object arg14, Object arg15) {
        View v14;
        Rect v15;
        Fragment v0 = arg11.lastIn;
        Fragment v1 = arg11.firstOut;
        if(v0 != null) {
            v0.getView().setVisibility(0);
        }

        Object v2 = null;
        if(v0 != null) {
            if(v1 == null) {
            }
            else {
                boolean v3 = arg11.lastInIsPop;
                Object v4 = arg10.isEmpty() ? v2 : FragmentTransition.getSharedElementTransition(v0, v1, v3);
                ArrayMap v5 = FragmentTransition.captureOutSharedElements(arg10, v4, arg11);
                ArrayMap v6 = FragmentTransition.captureInSharedElements(arg10, v4, arg11);
                if(arg10.isEmpty()) {
                    if(v5 != null) {
                        v5.clear();
                    }

                    if(v6 != null) {
                        v6.clear();
                    }

                    v4 = v2;
                }
                else {
                    FragmentTransition.addSharedElementsWithMatchingNames(arg12, v5, arg10.keySet());
                    FragmentTransition.addSharedElementsWithMatchingNames(arg13, v6, arg10.values());
                }

                if(arg14 == null && arg15 == null && v4 == null) {
                    return v2;
                }

                FragmentTransition.callSharedElementStartEnd(v0, v1, v3, v5, true);
                if(v4 != null) {
                    arg13.add(arg9);
                    FragmentTransitionCompat21.setSharedElementTargets(v4, arg9, arg12);
                    FragmentTransition.setOutEpicenter(v4, arg15, v5, arg11.firstOutIsPop, arg11.firstOutTransaction);
                    Rect v9 = new Rect();
                    View v10 = FragmentTransition.getInEpicenterView(v6, arg11, arg14, v3);
                    if(v10 != null) {
                        FragmentTransitionCompat21.setEpicenter(arg14, v9);
                    }

                    v15 = v9;
                    v14 = v10;
                }
                else {
                    v14 = ((View)v2);
                    v15 = ((Rect)v14);
                }

                OneShotPreDrawListener.add(((View)arg8), new Runnable(v0, v1, v3, v6, v14, v15) {
                    public void run() {
                        FragmentTransition.callSharedElementStartEnd(this.val$inFragment, this.val$outFragment, this.val$inIsPop, this.val$inSharedElements, false);
                        if(this.val$epicenterView != null) {
                            FragmentTransitionCompat21.getBoundsOnScreen(this.val$epicenterView, this.val$epicenter);
                        }
                    }
                });
                return v4;
            }
        }

        return v2;
    }

    @RequiresApi(value=21) private static void configureTransitionsOrdered(FragmentManagerImpl arg18, int arg19, FragmentContainerTransition arg20, View arg21, ArrayMap arg22) {
        Object v0_2;
        FragmentManagerImpl v0 = arg18;
        FragmentContainerTransition v8 = arg20;
        View v9 = arg21;
        ArrayMap v10 = arg22;
        View v12 = v0.mContainer.onHasView() ? v0.mContainer.onFindViewById(arg19) : null;
        if(v12 == null) {
            return;
        }

        Fragment v13 = v8.lastIn;
        Fragment v14 = v8.firstOut;
        boolean v0_1 = v8.lastInIsPop;
        boolean v1 = v8.firstOutIsPop;
        Object v15 = FragmentTransition.getEnterTransition(v13, v0_1);
        Object v7 = FragmentTransition.getExitTransition(v14, v1);
        ArrayList v6 = new ArrayList();
        ArrayList v5 = new ArrayList();
        ArrayList v16 = v5;
        ArrayList v11 = v6;
        Object v17 = v7;
        v7 = FragmentTransition.configureSharedElementsOrdered(v12, v9, v10, v8, v6, v5, v15, v7);
        if(v15 != null || v7 != null) {
            v0_2 = v17;
        }
        else {
            v0_2 = v17;
            if(v0_2 == null) {
                return;
            }
        }

        v11 = FragmentTransition.configureEnteringExitingViews(v0_2, v14, v11, v9);
        Object v14_1 = v11 == null || (v11.isEmpty()) ? null : v0_2;
        FragmentTransitionCompat21.addTarget(v15, v9);
        Object v1_1 = FragmentTransition.mergeTransitions(v15, v14_1, v7, v13, v8.lastInIsPop);
        if(v1_1 != null) {
            ArrayList v0_3 = new ArrayList();
            FragmentTransitionCompat21.scheduleRemoveTargets(v1_1, v15, v0_3, v14_1, v11, v7, v16);
            FragmentTransition.scheduleTargetChange(v12, v13, v9, v16, v15, v0_3, v14_1, v11);
            FragmentTransitionCompat21.setNameOverridesOrdered(v12, v16, ((Map)v10));
            FragmentTransitionCompat21.beginDelayedTransition(((ViewGroup)v12), v1_1);
            FragmentTransitionCompat21.scheduleNameReset(((ViewGroup)v12), v16, ((Map)v10));
        }
    }

    @RequiresApi(value=21) private static void configureTransitionsReordered(FragmentManagerImpl arg17, int arg18, FragmentContainerTransition arg19, View arg20, ArrayMap arg21) {
        Object v4;
        FragmentManagerImpl v0 = arg17;
        FragmentContainerTransition v3 = arg19;
        View v8 = arg20;
        View v0_1 = v0.mContainer.onHasView() ? v0.mContainer.onFindViewById(arg18) : null;
        View v9 = v0_1;
        if(v9 == null) {
            return;
        }

        Fragment v10 = v3.lastIn;
        Fragment v11 = v3.firstOut;
        boolean v12 = v3.lastInIsPop;
        boolean v0_2 = v3.firstOutIsPop;
        ArrayList v13 = new ArrayList();
        ArrayList v14 = new ArrayList();
        Object v15 = FragmentTransition.getEnterTransition(v10, v12);
        Object v7 = FragmentTransition.getExitTransition(v11, v0_2);
        Object v16 = v7;
        Object v0_3 = FragmentTransition.configureSharedElementsReordered(v9, v8, arg21, v3, v14, v13, v15, v7);
        if(v15 != null || v0_3 != null) {
            v4 = v16;
        }
        else {
            v4 = v16;
            if(v4 == null) {
                return;
            }
        }

        ArrayList v5 = FragmentTransition.configureEnteringExitingViews(v4, v11, v14, v8);
        ArrayList v8_1 = FragmentTransition.configureEnteringExitingViews(v15, v10, v13, v8);
        FragmentTransition.setViewVisibility(v8_1, 4);
        Object v10_1 = FragmentTransition.mergeTransitions(v15, v4, v0_3, v10, v12);
        if(v10_1 != null) {
            FragmentTransition.replaceHide(v4, v11, v5);
            ArrayList v11_1 = FragmentTransitionCompat21.prepareSetNameOverridesReordered(v13);
            FragmentTransitionCompat21.scheduleRemoveTargets(v10_1, v15, v8_1, v4, v5, v0_3, v13);
            FragmentTransitionCompat21.beginDelayedTransition(((ViewGroup)v9), v10_1);
            FragmentTransitionCompat21.setNameOverridesReordered(v9, v14, v13, v11_1, arg21);
            FragmentTransition.setViewVisibility(v8_1, 0);
            FragmentTransitionCompat21.swapSharedElementTargets(v0_3, v14, v13);
        }
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition arg0, SparseArray arg1, int arg2) {
        if(arg0 == null) {
            arg0 = new FragmentContainerTransition();
            arg1.put(arg2, arg0);
        }

        return arg0;
    }

    private static String findKeyForValue(ArrayMap arg3, String arg4) {
        int v0 = arg3.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.equals(arg3.valueAt(v1))) {
                return arg3.keyAt(v1);
            }
        }

        return null;
    }

    @RequiresApi(value=21) private static Object getEnterTransition(Fragment arg0, boolean arg1) {
        if(arg0 == null) {
            return null;
        }

        Object v0 = arg1 ? arg0.getReenterTransition() : arg0.getEnterTransition();
        return FragmentTransitionCompat21.cloneTransition(v0);
    }

    @RequiresApi(value=21) private static Object getExitTransition(Fragment arg0, boolean arg1) {
        if(arg0 == null) {
            return null;
        }

        Object v0 = arg1 ? arg0.getReturnTransition() : arg0.getExitTransition();
        return FragmentTransitionCompat21.cloneTransition(v0);
    }

    private static View getInEpicenterView(ArrayMap arg0, FragmentContainerTransition arg1, Object arg2, boolean arg3) {
        BackStackRecord v1 = arg1.lastInTransaction;
        if(arg2 != null && arg0 != null && v1.mSharedElementSourceNames != null && !v1.mSharedElementSourceNames.isEmpty()) {
            Object v1_1 = arg3 ? v1.mSharedElementSourceNames.get(0) : v1.mSharedElementTargetNames.get(0);
            return arg0.get(v1_1);
        }

        return null;
    }

    @RequiresApi(value=21) private static Object getSharedElementTransition(Fragment arg0, Fragment arg1, boolean arg2) {
        if(arg0 != null) {
            if(arg1 == null) {
            }
            else {
                Object v0 = arg2 ? arg1.getSharedElementReturnTransition() : arg0.getSharedElementEnterTransition();
                return FragmentTransitionCompat21.wrapTransitionInSet(FragmentTransitionCompat21.cloneTransition(v0));
            }
        }

        return null;
    }

    @RequiresApi(value=21) private static Object mergeTransitions(Object arg0, Object arg1, Object arg2, Fragment arg3, boolean arg4) {
        boolean v3;
        if(arg0 == null || arg1 == null || arg3 == null) {
            v3 = true;
        }
        else if(arg4) {
            v3 = arg3.getAllowReturnTransitionOverlap();
        }
        else {
            v3 = arg3.getAllowEnterTransitionOverlap();
        }

        return v3 ? FragmentTransitionCompat21.mergeTransitionsTogether(arg1, arg0, arg2) : FragmentTransitionCompat21.mergeTransitionsInSequence(arg1, arg0, arg2);
    }

    @RequiresApi(value=21) private static void replaceHide(Object arg1, Fragment arg2, ArrayList arg3) {
        if(arg2 != null && arg1 != null && (arg2.mAdded) && (arg2.mHidden) && (arg2.mHiddenChanged)) {
            arg2.setHideReplaced(true);
            FragmentTransitionCompat21.scheduleHideFragmentView(arg1, arg2.getView(), arg3);
            OneShotPreDrawListener.add(arg2.mContainer, new Runnable(arg3) {
                public void run() {
                    FragmentTransition.setViewVisibility(this.val$exitingViews, 4);
                }
            });
        }
    }

    private static void retainValues(ArrayMap arg2, ArrayMap arg3) {
        int v0;
        for(v0 = arg2.size() - 1; v0 >= 0; --v0) {
            if(!arg3.containsKey(arg2.valueAt(v0))) {
                arg2.removeAt(v0);
            }
        }
    }

    @RequiresApi(value=21) private static void scheduleTargetChange(ViewGroup arg9, Fragment arg10, View arg11, ArrayList arg12, Object arg13, ArrayList arg14, Object arg15, ArrayList arg16) {
        OneShotPreDrawListener.add(arg9, new Runnable(arg13, arg11, arg10, arg12, arg14, arg16, arg15) {
            public void run() {
                if(this.val$enterTransition != null) {
                    FragmentTransitionCompat21.removeTarget(this.val$enterTransition, this.val$nonExistentView);
                    this.val$enteringViews.addAll(FragmentTransition.configureEnteringExitingViews(this.val$enterTransition, this.val$inFragment, this.val$sharedElementsIn, this.val$nonExistentView));
                }

                if(this.val$exitingViews != null) {
                    if(this.val$exitTransition != null) {
                        ArrayList v0 = new ArrayList();
                        v0.add(this.val$nonExistentView);
                        FragmentTransitionCompat21.replaceTargets(this.val$exitTransition, this.val$exitingViews, v0);
                    }

                    this.val$exitingViews.clear();
                    this.val$exitingViews.add(this.val$nonExistentView);
                }
            }
        });
    }

    @RequiresApi(value=21) private static void setOutEpicenter(Object arg1, Object arg2, ArrayMap arg3, boolean arg4, BackStackRecord arg5) {
        if(arg5.mSharedElementSourceNames != null && !arg5.mSharedElementSourceNames.isEmpty()) {
            Object v4 = arg4 ? arg5.mSharedElementTargetNames.get(0) : arg5.mSharedElementSourceNames.get(0);
            Object v3 = arg3.get(v4);
            FragmentTransitionCompat21.setEpicenter(arg1, ((View)v3));
            if(arg2 == null) {
                return;
            }

            FragmentTransitionCompat21.setEpicenter(arg2, ((View)v3));
        }
    }

    private static void setViewVisibility(ArrayList arg2, int arg3) {
        if(arg2 == null) {
            return;
        }

        int v0;
        for(v0 = arg2.size() - 1; v0 >= 0; --v0) {
            arg2.get(v0).setVisibility(arg3);
        }
    }

    static void startTransitions(FragmentManagerImpl arg7, ArrayList arg8, ArrayList arg9, int arg10, int arg11, boolean arg12) {
        if(arg7.mCurState < 1) {
            return;
        }

        if(Build$VERSION.SDK_INT >= 21) {
            SparseArray v0 = new SparseArray();
            int v1;
            for(v1 = arg10; v1 < arg11; ++v1) {
                Object v2 = arg8.get(v1);
                if(arg9.get(v1).booleanValue()) {
                    FragmentTransition.calculatePopFragments(((BackStackRecord)v2), v0, arg12);
                }
                else {
                    FragmentTransition.calculateFragments(((BackStackRecord)v2), v0, arg12);
                }
            }

            if(v0.size() == 0) {
                return;
            }

            View v1_1 = new View(arg7.mHost.getContext());
            int v2_1 = v0.size();
            int v3;
            for(v3 = 0; v3 < v2_1; ++v3) {
                int v4 = v0.keyAt(v3);
                ArrayMap v5 = FragmentTransition.calculateNameOverrides(v4, arg8, arg9, arg10, arg11);
                Object v6 = v0.valueAt(v3);
                if(arg12) {
                    FragmentTransition.configureTransitionsReordered(arg7, v4, ((FragmentContainerTransition)v6), v1_1, v5);
                }
                else {
                    FragmentTransition.configureTransitionsOrdered(arg7, v4, ((FragmentContainerTransition)v6), v1_1, v5);
                }
            }
        }
    }
}

