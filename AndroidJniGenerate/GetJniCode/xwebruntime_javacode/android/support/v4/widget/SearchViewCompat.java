package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.widget.SearchView$OnCloseListener;
import android.widget.SearchView$OnQueryTextListener;
import android.widget.SearchView;

@Deprecated public final class SearchViewCompat {
    @Deprecated public interface OnCloseListener {
        boolean onClose();
    }

    @Deprecated public abstract class OnCloseListenerCompat implements OnCloseListener {
        public OnCloseListenerCompat() {
            super();
        }

        public boolean onClose() {
            return 0;
        }
    }

    @Deprecated public interface OnQueryTextListener {
        boolean onQueryTextChange(String arg1);

        boolean onQueryTextSubmit(String arg1);
    }

    @Deprecated public abstract class OnQueryTextListenerCompat implements OnQueryTextListener {
        public OnQueryTextListenerCompat() {
            super();
        }

        public boolean onQueryTextChange(String arg1) {
            return 0;
        }

        public boolean onQueryTextSubmit(String arg1) {
            return 0;
        }
    }

    private SearchViewCompat(Context arg1) {
        super();
    }

    private static void checkIfLegalArg(View arg1) {
        if(arg1 == null) {
            throw new IllegalArgumentException("searchView must be non-null");
        }

        if(!(arg1 instanceof SearchView)) {
            throw new IllegalArgumentException("searchView must be an instance of android.widget.SearchView");
        }
    }

    @Deprecated public static CharSequence getQuery(View arg0) {
        SearchViewCompat.checkIfLegalArg(arg0);
        return ((SearchView)arg0).getQuery();
    }

    @Deprecated public static boolean isIconified(View arg0) {
        SearchViewCompat.checkIfLegalArg(arg0);
        return ((SearchView)arg0).isIconified();
    }

    @Deprecated public static boolean isQueryRefinementEnabled(View arg0) {
        SearchViewCompat.checkIfLegalArg(arg0);
        return ((SearchView)arg0).isQueryRefinementEnabled();
    }

    @Deprecated public static boolean isSubmitButtonEnabled(View arg0) {
        SearchViewCompat.checkIfLegalArg(arg0);
        return ((SearchView)arg0).isSubmitButtonEnabled();
    }

    private static SearchView$OnCloseListener newOnCloseListener(OnCloseListener arg1) {
        return new SearchView$OnCloseListener(arg1) {
            public boolean onClose() {
                return this.val$listener.onClose();
            }
        };
    }

    private static SearchView$OnQueryTextListener newOnQueryTextListener(OnQueryTextListener arg1) {
        return new SearchView$OnQueryTextListener(arg1) {
            public boolean onQueryTextChange(String arg2) {
                return this.val$listener.onQueryTextChange(arg2);
            }

            public boolean onQueryTextSubmit(String arg2) {
                return this.val$listener.onQueryTextSubmit(arg2);
            }
        };
    }

    @Deprecated public static View newSearchView(Context arg1) {
        return new SearchView(arg1);
    }

    @Deprecated public static void setIconified(View arg0, boolean arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setIconified(arg1);
    }

    @Deprecated public static void setImeOptions(View arg0, int arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setImeOptions(arg1);
    }

    @Deprecated public static void setInputType(View arg0, int arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setInputType(arg1);
    }

    @Deprecated public static void setMaxWidth(View arg0, int arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setMaxWidth(arg1);
    }

    @Deprecated public static void setOnCloseListener(View arg0, OnCloseListener arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setOnCloseListener(SearchViewCompat.newOnCloseListener(arg1));
    }

    @Deprecated public static void setOnQueryTextListener(View arg0, OnQueryTextListener arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setOnQueryTextListener(SearchViewCompat.newOnQueryTextListener(arg1));
    }

    @Deprecated public static void setQuery(View arg0, CharSequence arg1, boolean arg2) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setQuery(arg1, arg2);
    }

    @Deprecated public static void setQueryHint(View arg0, CharSequence arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setQueryHint(arg1);
    }

    @Deprecated public static void setQueryRefinementEnabled(View arg0, boolean arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setQueryRefinementEnabled(arg1);
    }

    @Deprecated public static void setSearchableInfo(View arg2, ComponentName arg3) {
        SearchViewCompat.checkIfLegalArg(arg2);
        ((SearchView)arg2).setSearchableInfo(arg2.getContext().getSystemService("search").getSearchableInfo(arg3));
    }

    @Deprecated public static void setSubmitButtonEnabled(View arg0, boolean arg1) {
        SearchViewCompat.checkIfLegalArg(arg0);
        ((SearchView)arg0).setSubmitButtonEnabled(arg1);
    }
}

