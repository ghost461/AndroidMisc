package android.support.v4.text.util;

import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify$MatchFilter;
import android.text.util.Linkify$TransformFilter;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat {
    final class android.support.v4.text.util.LinkifyCompat$1 implements Comparator {
        android.support.v4.text.util.LinkifyCompat$1() {
            super();
        }

        public final int compare(LinkSpec arg5, LinkSpec arg6) {
            int v2 = -1;
            if(arg5.start < arg6.start) {
                return v2;
            }

            if(arg5.start > arg6.start) {
                return 1;
            }

            if(arg5.end < arg6.end) {
                return 1;
            }

            if(arg5.end > arg6.end) {
                return v2;
            }

            return 0;
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((LinkSpec)arg1), ((LinkSpec)arg2));
        }
    }

    class LinkSpec {
        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;

        LinkSpec() {
            super();
        }
    }

    @RestrictTo(value={Scope.LIBRARY_GROUP}) @Retention(value=RetentionPolicy.SOURCE) @public interface LinkifyMask {
    }

    private static final Comparator COMPARATOR;
    private static final String[] EMPTY_STRING;

    static {
        LinkifyCompat.EMPTY_STRING = new String[0];
        LinkifyCompat.COMPARATOR = new android.support.v4.text.util.LinkifyCompat$1();
    }

    private LinkifyCompat() {
        super();
    }

    private static void addLinkMovementMethod(@NonNull TextView arg1) {
        MovementMethod v0 = arg1.getMovementMethod();
        if((v0 == null || !(v0 instanceof LinkMovementMethod)) && (arg1.getLinksClickable())) {
            arg1.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public static final void addLinks(@NonNull TextView arg8, @NonNull Pattern arg9, @Nullable String arg10) {
        if(Build$VERSION.SDK_INT >= 26) {
            Linkify.addLinks(arg8, arg9, arg10);
            return;
        }

        LinkifyCompat.addLinks(arg8, arg9, arg10, null, null, null);
    }

    public static final void addLinks(@NonNull TextView arg7, @NonNull Pattern arg8, @Nullable String arg9, @Nullable String[] arg10, @Nullable Linkify$MatchFilter arg11, @Nullable Linkify$TransformFilter arg12) {
        if(Build$VERSION.SDK_INT >= 26) {
            Linkify.addLinks(arg7, arg8, arg9, arg10, arg11, arg12);
            return;
        }

        SpannableString v0 = SpannableString.valueOf(arg7.getText());
        if(LinkifyCompat.addLinks(v0, arg8, arg9, arg10, arg11, arg12)) {
            arg7.setText(((CharSequence)v0));
            LinkifyCompat.addLinkMovementMethod(arg7);
        }
    }

    public static final void addLinks(@NonNull TextView arg8, @NonNull Pattern arg9, @Nullable String arg10, @Nullable Linkify$MatchFilter arg11, @Nullable Linkify$TransformFilter arg12) {
        if(Build$VERSION.SDK_INT >= 26) {
            Linkify.addLinks(arg8, arg9, arg10, arg11, arg12);
            return;
        }

        LinkifyCompat.addLinks(arg8, arg9, arg10, null, arg11, arg12);
    }

    public static final boolean addLinks(@NonNull Spannable arg5, @NonNull Pattern arg6, @Nullable String arg7, @Nullable String[] arg8, @Nullable Linkify$MatchFilter arg9, @Nullable Linkify$TransformFilter arg10) {
        if(Build$VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(arg5, arg6, arg7, arg8, arg9, arg10);
        }

        if(arg7 == null) {
            arg7 = "";
        }

        if(arg8 == null || arg8.length < 1) {
            arg8 = LinkifyCompat.EMPTY_STRING;
        }

        String[] v1 = new String[arg8.length + 1];
        v1[0] = arg7.toLowerCase(Locale.ROOT);
        int v7 = 0;
        while(v7 < arg8.length) {
            String v3 = arg8[v7];
            ++v7;
            v3 = v3 == null ? "" : v3.toLowerCase(Locale.ROOT);
            v1[v7] = v3;
        }

        Matcher v6 = arg6.matcher(((CharSequence)arg5));
        boolean v7_1;
        for(v7_1 = false; v6.find(); v7_1 = true) {
            int v8 = v6.start();
            int v3_1 = v6.end();
            boolean v4 = arg9 != null ? arg9.acceptMatch(((CharSequence)arg5), v8, v3_1) : true;
            if(!v4) {
                continue;
            }

            LinkifyCompat.applyLink(LinkifyCompat.makeUrl(v6.group(0), v1, v6, arg10), v8, v3_1, arg5);
        }

        return v7_1;
    }

    public static final boolean addLinks(@NonNull Spannable arg10, int arg11) {
        if(Build$VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(arg10, arg11);
        }

        if(arg11 == 0) {
            return 0;
        }

        Object[] v1 = arg10.getSpans(0, arg10.length(), URLSpan.class);
        int v2;
        for(v2 = v1.length - 1; v2 >= 0; --v2) {
            arg10.removeSpan(v1[v2]);
        }

        if((arg11 & 4) != 0) {
            Linkify.addLinks(arg10, 4);
        }

        ArrayList v1_1 = new ArrayList();
        if((arg11 & 1) != 0) {
            LinkifyCompat.gatherLinks(v1_1, arg10, PatternsCompat.AUTOLINK_WEB_URL, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, null);
        }

        if((arg11 & 2) != 0) {
            LinkifyCompat.gatherLinks(v1_1, arg10, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{"mailto:"}, null, null);
        }

        if((arg11 & 8) != 0) {
            LinkifyCompat.gatherMapLinks(v1_1, arg10);
        }

        LinkifyCompat.pruneOverlaps(v1_1, arg10);
        if(v1_1.size() == 0) {
            return 0;
        }

        Iterator v11 = v1_1.iterator();
        while(v11.hasNext()) {
            Object v0 = v11.next();
            if(((LinkSpec)v0).frameworkAddedSpan != null) {
                continue;
            }

            LinkifyCompat.applyLink(((LinkSpec)v0).url, ((LinkSpec)v0).start, ((LinkSpec)v0).end, arg10);
        }

        return 1;
    }

    public static final boolean addLinks(@NonNull Spannable arg6, @NonNull Pattern arg7, @Nullable String arg8) {
        if(Build$VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(arg6, arg7, arg8);
        }

        return LinkifyCompat.addLinks(arg6, arg7, arg8, null, null, null);
    }

    public static final boolean addLinks(@NonNull Spannable arg6, @NonNull Pattern arg7, @Nullable String arg8, @Nullable Linkify$MatchFilter arg9, @Nullable Linkify$TransformFilter arg10) {
        if(Build$VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(arg6, arg7, arg8, arg9, arg10);
        }

        return LinkifyCompat.addLinks(arg6, arg7, arg8, null, arg9, arg10);
    }

    public static final boolean addLinks(@NonNull TextView arg4, int arg5) {
        if(Build$VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(arg4, arg5);
        }

        if(arg5 == 0) {
            return 0;
        }

        CharSequence v1 = arg4.getText();
        if((v1 instanceof Spannable)) {
            if(LinkifyCompat.addLinks(((Spannable)v1), arg5)) {
                LinkifyCompat.addLinkMovementMethod(arg4);
                return 1;
            }

            return 0;
        }

        SpannableString v1_1 = SpannableString.valueOf(v1);
        if(LinkifyCompat.addLinks(((Spannable)v1_1), arg5)) {
            LinkifyCompat.addLinkMovementMethod(arg4);
            arg4.setText(((CharSequence)v1_1));
            return 1;
        }

        return 0;
    }

    private static void applyLink(String arg1, int arg2, int arg3, Spannable arg4) {
        arg4.setSpan(new URLSpan(arg1), arg2, arg3, 33);
    }

    private static void gatherLinks(ArrayList arg4, Spannable arg5, Pattern arg6, String[] arg7, Linkify$MatchFilter arg8, Linkify$TransformFilter arg9) {
        Matcher v6 = arg6.matcher(((CharSequence)arg5));
        while(v6.find()) {
            int v0 = v6.start();
            int v1 = v6.end();
            if(arg8 != null && !arg8.acceptMatch(((CharSequence)arg5), v0, v1)) {
                continue;
            }

            LinkSpec v2 = new LinkSpec();
            v2.url = LinkifyCompat.makeUrl(v6.group(0), arg7, v6, arg9);
            v2.start = v0;
            v2.end = v1;
            arg4.add(v2);
        }
    }

    private static final void gatherMapLinks(ArrayList arg5, Spannable arg6) {
        String v6 = arg6.toString();
        int v0 = 0;
        try {
            while(true) {
                String v1 = WebView.findAddress(v6);
                if(v1 != null) {
                    int v2 = v6.indexOf(v1);
                    if(v2 < 0) {
                    }
                    else {
                        LinkSpec v3 = new LinkSpec();
                        int v4 = v1.length() + v2;
                        v3.start = v2 + v0;
                        v0 += v4;
                        v3.end = v0;
                        v6 = v6.substring(v4);
                        try {
                            v1 = URLEncoder.encode(v1, "UTF-8");
                        }
                        catch(UnsupportedEncodingException ) {
                            continue;
                        }

                        v3.url = "geo:0,0?q=" + v1;
                        arg5.add(v3);
                        continue;
                    }
                }

                return;
            }
        }
        catch(UnsupportedOperationException ) {
            return;
        }
    }

    private static String makeUrl(@NonNull String arg7, @NonNull String[] arg8, Matcher arg9, @Nullable Linkify$TransformFilter arg10) {
        int v6;
        if(arg10 != null) {
            arg7 = arg10.transformUrl(arg9, arg7);
        }

        int v10 = 0;
        while(true) {
            v6 = 1;
            if(v10 >= arg8.length) {
                break;
            }
            else if(!arg7.regionMatches(true, 0, arg8[v10], 0, arg8[v10].length())) {
                ++v10;
                continue;
            }
            else if(!arg7.regionMatches(false, 0, arg8[v10], 0, arg8[v10].length())) {
                arg7 = arg8[v10] + arg7.substring(arg8[v10].length());
            }

            goto label_38;
        }

        v6 = 0;
    label_38:
        if(v6 == 0 && arg8.length > 0) {
            arg7 = arg8[0] + arg7;
        }

        return arg7;
    }

    private static final void pruneOverlaps(ArrayList arg9, Spannable arg10) {
        int v2 = 0;
        Object[] v0 = arg10.getSpans(0, arg10.length(), URLSpan.class);
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            LinkSpec v3 = new LinkSpec();
            v3.frameworkAddedSpan = v0[v1];
            v3.start = arg10.getSpanStart(v0[v1]);
            v3.end = arg10.getSpanEnd(v0[v1]);
            arg9.add(v3);
        }

        Collections.sort(((List)arg9), LinkifyCompat.COMPARATOR);
        int v0_1 = arg9.size();
        while(v2 < v0_1 - 1) {
            Object v1_1 = arg9.get(v2);
            int v3_1 = v2 + 1;
            Object v4 = arg9.get(v3_1);
            if(((LinkSpec)v1_1).start <= ((LinkSpec)v4).start && ((LinkSpec)v1_1).end > ((LinkSpec)v4).start) {
                int v7 = -1;
                if(((LinkSpec)v4).end <= ((LinkSpec)v1_1).end || ((LinkSpec)v1_1).end - ((LinkSpec)v1_1).start > ((LinkSpec)v4).end - ((LinkSpec)v4).start) {
                    v1 = v3_1;
                    goto label_58;
                label_48:
                    v1 = ((LinkSpec)v1_1).end - ((LinkSpec)v1_1).start < ((LinkSpec)v4).end - ((LinkSpec)v4).start ? v2 : -1;
                }
                else {
                    goto label_48;
                }

            label_58:
                if(v1 == v7) {
                    goto label_66;
                }

                URLSpan v3_2 = arg9.get(v1).frameworkAddedSpan;
                if(v3_2 != null) {
                    arg10.removeSpan(v3_2);
                }

                arg9.remove(v1);
                --v0_1;
                continue;
            }

        label_66:
            v2 = v3_1;
        }
    }
}

