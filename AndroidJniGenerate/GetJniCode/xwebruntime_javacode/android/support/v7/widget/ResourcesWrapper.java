package android.support.v7.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

class ResourcesWrapper extends Resources {
    private final Resources mResources;

    public ResourcesWrapper(Resources arg4) {
        super(arg4.getAssets(), arg4.getDisplayMetrics(), arg4.getConfiguration());
        this.mResources = arg4;
    }

    public XmlResourceParser getAnimation(int arg2) throws Resources$NotFoundException {
        return this.mResources.getAnimation(arg2);
    }

    public boolean getBoolean(int arg2) throws Resources$NotFoundException {
        return this.mResources.getBoolean(arg2);
    }

    public int getColor(int arg2) throws Resources$NotFoundException {
        return this.mResources.getColor(arg2);
    }

    public ColorStateList getColorStateList(int arg2) throws Resources$NotFoundException {
        return this.mResources.getColorStateList(arg2);
    }

    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    public float getDimension(int arg2) throws Resources$NotFoundException {
        return this.mResources.getDimension(arg2);
    }

    public int getDimensionPixelOffset(int arg2) throws Resources$NotFoundException {
        return this.mResources.getDimensionPixelOffset(arg2);
    }

    public int getDimensionPixelSize(int arg2) throws Resources$NotFoundException {
        return this.mResources.getDimensionPixelSize(arg2);
    }

    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    public Drawable getDrawable(int arg2) throws Resources$NotFoundException {
        return this.mResources.getDrawable(arg2);
    }

    @RequiresApi(value=21) public Drawable getDrawable(int arg2, Resources$Theme arg3) throws Resources$NotFoundException {
        return this.mResources.getDrawable(arg2, arg3);
    }

    @RequiresApi(value=15) public Drawable getDrawableForDensity(int arg2, int arg3) throws Resources$NotFoundException {
        return this.mResources.getDrawableForDensity(arg2, arg3);
    }

    @RequiresApi(value=21) public Drawable getDrawableForDensity(int arg2, int arg3, Resources$Theme arg4) {
        return this.mResources.getDrawableForDensity(arg2, arg3, arg4);
    }

    public float getFraction(int arg2, int arg3, int arg4) {
        return this.mResources.getFraction(arg2, arg3, arg4);
    }

    public int getIdentifier(String arg2, String arg3, String arg4) {
        return this.mResources.getIdentifier(arg2, arg3, arg4);
    }

    public int[] getIntArray(int arg2) throws Resources$NotFoundException {
        return this.mResources.getIntArray(arg2);
    }

    public int getInteger(int arg2) throws Resources$NotFoundException {
        return this.mResources.getInteger(arg2);
    }

    public XmlResourceParser getLayout(int arg2) throws Resources$NotFoundException {
        return this.mResources.getLayout(arg2);
    }

    public Movie getMovie(int arg2) throws Resources$NotFoundException {
        return this.mResources.getMovie(arg2);
    }

    public String getQuantityString(int arg2, int arg3) throws Resources$NotFoundException {
        return this.mResources.getQuantityString(arg2, arg3);
    }

    public String getQuantityString(int arg2, int arg3, Object[] arg4) throws Resources$NotFoundException {
        return this.mResources.getQuantityString(arg2, arg3, arg4);
    }

    public CharSequence getQuantityText(int arg2, int arg3) throws Resources$NotFoundException {
        return this.mResources.getQuantityText(arg2, arg3);
    }

    public String getResourceEntryName(int arg2) throws Resources$NotFoundException {
        return this.mResources.getResourceEntryName(arg2);
    }

    public String getResourceName(int arg2) throws Resources$NotFoundException {
        return this.mResources.getResourceName(arg2);
    }

    public String getResourcePackageName(int arg2) throws Resources$NotFoundException {
        return this.mResources.getResourcePackageName(arg2);
    }

    public String getResourceTypeName(int arg2) throws Resources$NotFoundException {
        return this.mResources.getResourceTypeName(arg2);
    }

    public String getString(int arg2) throws Resources$NotFoundException {
        return this.mResources.getString(arg2);
    }

    public String getString(int arg2, Object[] arg3) throws Resources$NotFoundException {
        return this.mResources.getString(arg2, arg3);
    }

    public String[] getStringArray(int arg2) throws Resources$NotFoundException {
        return this.mResources.getStringArray(arg2);
    }

    public CharSequence getText(int arg2) throws Resources$NotFoundException {
        return this.mResources.getText(arg2);
    }

    public CharSequence getText(int arg2, CharSequence arg3) {
        return this.mResources.getText(arg2, arg3);
    }

    public CharSequence[] getTextArray(int arg2) throws Resources$NotFoundException {
        return this.mResources.getTextArray(arg2);
    }

    public void getValue(int arg2, TypedValue arg3, boolean arg4) throws Resources$NotFoundException {
        this.mResources.getValue(arg2, arg3, arg4);
    }

    public void getValue(String arg2, TypedValue arg3, boolean arg4) throws Resources$NotFoundException {
        this.mResources.getValue(arg2, arg3, arg4);
    }

    @RequiresApi(value=15) public void getValueForDensity(int arg2, int arg3, TypedValue arg4, boolean arg5) throws Resources$NotFoundException {
        this.mResources.getValueForDensity(arg2, arg3, arg4, arg5);
    }

    public XmlResourceParser getXml(int arg2) throws Resources$NotFoundException {
        return this.mResources.getXml(arg2);
    }

    public TypedArray obtainAttributes(AttributeSet arg2, int[] arg3) {
        return this.mResources.obtainAttributes(arg2, arg3);
    }

    public TypedArray obtainTypedArray(int arg2) throws Resources$NotFoundException {
        return this.mResources.obtainTypedArray(arg2);
    }

    public InputStream openRawResource(int arg2) throws Resources$NotFoundException {
        return this.mResources.openRawResource(arg2);
    }

    public InputStream openRawResource(int arg2, TypedValue arg3) throws Resources$NotFoundException {
        return this.mResources.openRawResource(arg2, arg3);
    }

    public AssetFileDescriptor openRawResourceFd(int arg2) throws Resources$NotFoundException {
        return this.mResources.openRawResourceFd(arg2);
    }

    public void parseBundleExtra(String arg2, AttributeSet arg3, Bundle arg4) throws XmlPullParserException {
        this.mResources.parseBundleExtra(arg2, arg3, arg4);
    }

    public void parseBundleExtras(XmlResourceParser arg2, Bundle arg3) throws XmlPullParserException, IOException {
        this.mResources.parseBundleExtras(arg2, arg3);
    }

    public void updateConfiguration(Configuration arg2, DisplayMetrics arg3) {
        super.updateConfiguration(arg2, arg3);
        if(this.mResources != null) {
            this.mResources.updateConfiguration(arg2, arg3);
        }
    }
}

