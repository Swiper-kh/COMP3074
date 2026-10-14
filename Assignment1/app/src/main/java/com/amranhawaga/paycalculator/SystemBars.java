package com.amranhawaga.paycalculator;

import android.graphics.Color;
import android.view.View;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * New Android versions draw the app behind the status bar and the navigation bar.
 * This pushes the app bar below the status bar and keeps the content above the
 * navigation bar, so nothing overlaps.
 */
public final class SystemBars {

    private SystemBars() { }

    /**
     * Draws the app behind the system bars on every Android version (so it looks the
     * same everywhere) with white status-bar icons on top of the blue app bar.
     * Call before setContentView().
     */
    public static void enable(ComponentActivity activity) {
        EdgeToEdge.enable(activity, SystemBarStyle.dark(Color.TRANSPARENT));
    }

    public static void applyPadding(View appBar, View content) {
        final int appBarTop = appBar.getPaddingTop();
        final int contentBottom = content.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(appBar, (v, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), appBarTop + bars.top, v.getPaddingRight(), v.getPaddingBottom());
            return windowInsets;
        });
        ViewCompat.setOnApplyWindowInsetsListener(content, (v, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), contentBottom + bars.bottom);
            return windowInsets;
        });
    }
}
