package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import java.util.ArrayList;

public final class ShareCompat {
    public class IntentBuilder {
        private Activity mActivity;
        private ArrayList mBccAddresses;
        private ArrayList mCcAddresses;
        private CharSequence mChooserTitle;
        private Intent mIntent;
        private ArrayList mStreams;
        private ArrayList mToAddresses;

        private IntentBuilder(Activity arg4) {
            super();
            this.mActivity = arg4;
            this.mIntent = new Intent().setAction("android.intent.action.SEND");
            this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", arg4.getPackageName());
            this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", arg4.getComponentName());
            this.mIntent.addFlags(0x80000);
        }

        public IntentBuilder addEmailBcc(String arg2) {
            if(this.mBccAddresses == null) {
                this.mBccAddresses = new ArrayList();
            }

            this.mBccAddresses.add(arg2);
            return this;
        }

        public IntentBuilder addEmailBcc(String[] arg2) {
            this.combineArrayExtra("android.intent.extra.BCC", arg2);
            return this;
        }

        public IntentBuilder addEmailCc(String arg2) {
            if(this.mCcAddresses == null) {
                this.mCcAddresses = new ArrayList();
            }

            this.mCcAddresses.add(arg2);
            return this;
        }

        public IntentBuilder addEmailCc(String[] arg2) {
            this.combineArrayExtra("android.intent.extra.CC", arg2);
            return this;
        }

        public IntentBuilder addEmailTo(String arg2) {
            if(this.mToAddresses == null) {
                this.mToAddresses = new ArrayList();
            }

            this.mToAddresses.add(arg2);
            return this;
        }

        public IntentBuilder addEmailTo(String[] arg2) {
            this.combineArrayExtra("android.intent.extra.EMAIL", arg2);
            return this;
        }

        public IntentBuilder addStream(Uri arg4) {
            Parcelable v0 = this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            if(this.mStreams == null && v0 == null) {
                return this.setStream(arg4);
            }

            if(this.mStreams == null) {
                this.mStreams = new ArrayList();
            }

            if(v0 != null) {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
                this.mStreams.add(v0);
            }

            this.mStreams.add(arg4);
            return this;
        }

        private void combineArrayExtra(String arg5, ArrayList arg6) {
            String[] v0 = this.mIntent.getStringArrayExtra(arg5);
            int v2 = v0 != null ? v0.length : 0;
            String[] v3 = new String[arg6.size() + v2];
            arg6.toArray(((Object[])v3));
            if(v0 != null) {
                System.arraycopy(v0, 0, v3, arg6.size(), v2);
            }

            this.mIntent.putExtra(arg5, v3);
        }

        private void combineArrayExtra(String arg6, String[] arg7) {
            Intent v0 = this.getIntent();
            String[] v1 = v0.getStringArrayExtra(arg6);
            int v3 = v1 != null ? v1.length : 0;
            String[] v4 = new String[arg7.length + v3];
            if(v1 != null) {
                System.arraycopy(v1, 0, v4, 0, v3);
            }

            System.arraycopy(arg7, 0, v4, v3, arg7.length);
            v0.putExtra(arg6, v4);
        }

        public Intent createChooserIntent() {
            return Intent.createChooser(this.getIntent(), this.mChooserTitle);
        }

        public static IntentBuilder from(Activity arg1) {
            return new IntentBuilder(arg1);
        }

        Activity getActivity() {
            return this.mActivity;
        }

        public Intent getIntent() {
            ArrayList v1 = null;
            if(this.mToAddresses != null) {
                this.combineArrayExtra("android.intent.extra.EMAIL", this.mToAddresses);
                this.mToAddresses = v1;
            }

            if(this.mCcAddresses != null) {
                this.combineArrayExtra("android.intent.extra.CC", this.mCcAddresses);
                this.mCcAddresses = v1;
            }

            if(this.mBccAddresses != null) {
                this.combineArrayExtra("android.intent.extra.BCC", this.mBccAddresses);
                this.mBccAddresses = v1;
            }

            int v3 = 1;
            if(this.mStreams == null || this.mStreams.size() <= 1) {
                v3 = 0;
            }
            else {
            }

            boolean v0 = this.mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if(v3 == 0 && (v0)) {
                this.mIntent.setAction("android.intent.action.SEND");
                if(this.mStreams == null || (this.mStreams.isEmpty())) {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                }
                else {
                    this.mIntent.putExtra("android.intent.extra.STREAM", this.mStreams.get(0));
                }

                this.mStreams = v1;
            }

            if(v3 != 0 && !v0) {
                this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                if(this.mStreams != null && !this.mStreams.isEmpty()) {
                    this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
                    goto label_70;
                }

                this.mIntent.removeExtra("android.intent.extra.STREAM");
            }

        label_70:
            return this.mIntent;
        }

        public IntentBuilder setChooserTitle(@StringRes int arg2) {
            return this.setChooserTitle(this.mActivity.getText(arg2));
        }

        public IntentBuilder setChooserTitle(CharSequence arg1) {
            this.mChooserTitle = arg1;
            return this;
        }

        public IntentBuilder setEmailBcc(String[] arg3) {
            this.mIntent.putExtra("android.intent.extra.BCC", arg3);
            return this;
        }

        public IntentBuilder setEmailCc(String[] arg3) {
            this.mIntent.putExtra("android.intent.extra.CC", arg3);
            return this;
        }

        public IntentBuilder setEmailTo(String[] arg3) {
            if(this.mToAddresses != null) {
                this.mToAddresses = null;
            }

            this.mIntent.putExtra("android.intent.extra.EMAIL", arg3);
            return this;
        }

        public IntentBuilder setHtmlText(String arg3) {
            this.mIntent.putExtra("android.intent.extra.HTML_TEXT", arg3);
            if(!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
                this.setText(Html.fromHtml(arg3));
            }

            return this;
        }

        public IntentBuilder setStream(Uri arg3) {
            if(!this.mIntent.getAction().equals("android.intent.action.SEND")) {
                this.mIntent.setAction("android.intent.action.SEND");
            }

            this.mStreams = null;
            this.mIntent.putExtra("android.intent.extra.STREAM", ((Parcelable)arg3));
            return this;
        }

        public IntentBuilder setSubject(String arg3) {
            this.mIntent.putExtra("android.intent.extra.SUBJECT", arg3);
            return this;
        }

        public IntentBuilder setText(CharSequence arg3) {
            this.mIntent.putExtra("android.intent.extra.TEXT", arg3);
            return this;
        }

        public IntentBuilder setType(String arg2) {
            this.mIntent.setType(arg2);
            return this;
        }

        public void startChooser() {
            this.mActivity.startActivity(this.createChooserIntent());
        }
    }

    public class IntentReader {
        private static final String TAG = "IntentReader";
        private Activity mActivity;
        private ComponentName mCallingActivity;
        private String mCallingPackage;
        private Intent mIntent;
        private ArrayList mStreams;

        private IntentReader(Activity arg2) {
            super();
            this.mActivity = arg2;
            this.mIntent = arg2.getIntent();
            this.mCallingPackage = ShareCompat.getCallingPackage(arg2);
            this.mCallingActivity = ShareCompat.getCallingActivity(arg2);
        }

        public static IntentReader from(Activity arg1) {
            return new IntentReader(arg1);
        }

        public ComponentName getCallingActivity() {
            return this.mCallingActivity;
        }

        public Drawable getCallingActivityIcon() {
            Drawable v1 = null;
            if(this.mCallingActivity == null) {
                return v1;
            }

            PackageManager v0 = this.mActivity.getPackageManager();
            try {
                return v0.getActivityIcon(this.mCallingActivity);
            }
            catch(PackageManager$NameNotFoundException v0_1) {
                Log.e("IntentReader", "Could not retrieve icon for calling activity", ((Throwable)v0_1));
                return v1;
            }
        }

        public Drawable getCallingApplicationIcon() {
            Drawable v1 = null;
            if(this.mCallingPackage == null) {
                return v1;
            }

            PackageManager v0 = this.mActivity.getPackageManager();
            try {
                return v0.getApplicationIcon(this.mCallingPackage);
            }
            catch(PackageManager$NameNotFoundException v0_1) {
                Log.e("IntentReader", "Could not retrieve icon for calling application", ((Throwable)v0_1));
                return v1;
            }
        }

        public CharSequence getCallingApplicationLabel() {
            CharSequence v1 = null;
            if(this.mCallingPackage == null) {
                return v1;
            }

            PackageManager v0 = this.mActivity.getPackageManager();
            try {
                return v0.getApplicationLabel(v0.getApplicationInfo(this.mCallingPackage, 0));
            }
            catch(PackageManager$NameNotFoundException v0_1) {
                Log.e("IntentReader", "Could not retrieve label for calling application", ((Throwable)v0_1));
                return v1;
            }
        }

        public String getCallingPackage() {
            return this.mCallingPackage;
        }

        public String[] getEmailBcc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
        }

        public String[] getEmailCc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
        }

        public String[] getEmailTo() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        public String getHtmlText() {
            String v0 = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
            if(v0 == null) {
                CharSequence v1 = this.getText();
                if((v1 instanceof Spanned)) {
                    v0 = Html.toHtml(((Spanned)v1));
                }
                else if(v1 != null) {
                    if(Build$VERSION.SDK_INT >= 16) {
                        v0 = Html.escapeHtml(v1);
                    }
                    else {
                        StringBuilder v0_1 = new StringBuilder();
                        IntentReader.withinStyle(v0_1, v1, 0, v1.length());
                        v0 = v0_1.toString();
                    }
                }
            }

            return v0;
        }

        public Uri getStream() {
            return this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public Uri getStream(int arg4) {
            if(this.mStreams == null && (this.isMultipleShare())) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }

            if(this.mStreams != null) {
                return this.mStreams.get(arg4);
            }

            if(arg4 == 0) {
                return this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Stream items available: ");
            v1.append(this.getStreamCount());
            v1.append(" index requested: ");
            v1.append(arg4);
            throw new IndexOutOfBoundsException(v1.toString());
        }

        public int getStreamCount() {
            if(this.mStreams == null && (this.isMultipleShare())) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }

            if(this.mStreams != null) {
                return this.mStreams.size();
            }

            return this.mIntent.hasExtra("android.intent.extra.STREAM");
        }

        public String getSubject() {
            return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
        }

        public CharSequence getText() {
            return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        public String getType() {
            return this.mIntent.getType();
        }

        public boolean isMultipleShare() {
            return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        }

        public boolean isShareIntent() {
            String v0 = this.mIntent.getAction();
            boolean v0_1 = ("android.intent.action.SEND".equals(v0)) || ("android.intent.action.SEND_MULTIPLE".equals(v0)) ? true : false;
            return v0_1;
        }

        public boolean isSingleShare() {
            return "android.intent.action.SEND".equals(this.mIntent.getAction());
        }

        private static void withinStyle(StringBuilder arg3, CharSequence arg4, int arg5, int arg6) {
            while(arg5 < arg6) {
                char v0 = arg4.charAt(arg5);
                if(v0 == 60) {
                    arg3.append("&lt;");
                }
                else if(v0 == 62) {
                    arg3.append("&gt;");
                }
                else if(v0 == 38) {
                    arg3.append("&amp;");
                }
                else {
                    if(v0 <= 0x7E) {
                        char v1 = ' ';
                        if(v0 < v1) {
                        }
                        else {
                            if(v0 == v1) {
                                while(true) {
                                    int v0_1 = arg5 + 1;
                                    if(v0_1 < arg6 && arg4.charAt(v0_1) == v1) {
                                        arg3.append("&nbsp;");
                                        arg5 = v0_1;
                                        continue;
                                    }

                                    break;
                                }

                                arg3.append(v1);
                                goto label_44;
                            }

                            arg3.append(v0);
                            goto label_44;
                        }
                    }

                    arg3.append("&#" + v0 + ";");
                }

            label_44:
                ++arg5;
            }
        }
    }

    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    private ShareCompat() {
        super();
    }

    public static void configureMenuItem(Menu arg1, int arg2, IntentBuilder arg3) {
        MenuItem v1 = arg1.findItem(arg2);
        if(v1 == null) {
            StringBuilder v3 = new StringBuilder();
            v3.append("Could not find menu item with id ");
            v3.append(arg2);
            v3.append(" in the supplied menu");
            throw new IllegalArgumentException(v3.toString());
        }

        ShareCompat.configureMenuItem(v1, arg3);
    }

    public static void configureMenuItem(MenuItem arg3, IntentBuilder arg4) {
        ActionProvider v0 = arg3.getActionProvider();
        if(!(v0 instanceof ShareActionProvider)) {
            ShareActionProvider v0_1 = new ShareActionProvider(arg4.getActivity());
        }

        ((ShareActionProvider)v0).setShareHistoryFileName(".sharecompat_" + arg4.getActivity().getClass().getName());
        ((ShareActionProvider)v0).setShareIntent(arg4.getIntent());
        arg3.setActionProvider(v0);
        if(Build$VERSION.SDK_INT < 16 && !arg3.hasSubMenu()) {
            arg3.setIntent(arg4.createChooserIntent());
        }
    }

    public static ComponentName getCallingActivity(Activity arg1) {
        ComponentName v0 = arg1.getCallingActivity();
        if(v0 == null) {
            Parcelable v0_1 = arg1.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
        }

        return v0;
    }

    public static String getCallingPackage(Activity arg1) {
        String v0 = arg1.getCallingPackage();
        if(v0 == null) {
            v0 = arg1.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
        }

        return v0;
    }
}

