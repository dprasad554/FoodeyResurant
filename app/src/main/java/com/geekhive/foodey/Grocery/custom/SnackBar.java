package com.geekhive.foodey.Grocery.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.geekhive.foodey.R;


public class SnackBar {
    public static final int LENGTH_SHORT = -1;

    public static Snackbar makeText(Context context, int resId, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), context.getResources().getText(resId), duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.colorBlack));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.colorWhite));
        return snackbar;
    }

    public static Snackbar makeText(Context context, String message, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), message, duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.colorBlack));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.colorWhite));
        return snackbar;
    }

    public static Snackbar makeText(Context context, View view, String message, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(view, message, duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.colorBlack));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.colorWhite));
        return snackbar;
    }

    public static Snackbar makeText(Context context, View view, int resId, int duration) {
        Typeface font;
        Snackbar snackbar = Snackbar.make(view, context.getResources().getText(resId), duration);
        View layout = snackbar.getView();
        layout.setBackgroundColor(context.getResources().getColor( R.color.colorBlack));
        TextView text = (TextView) layout.findViewById( R.id.snackbar_text);
        text.setMaxLines(5);
        text.setTextColor(context.getResources().getColor( R.color.colorWhite));
        return snackbar;
    }

    private static String readLocale(Context mContext) {
        return mContext.getSharedPreferences("Locale", 0).getString("Language", "en");
    }
}
