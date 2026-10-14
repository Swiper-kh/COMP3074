package com.amranhawaga.paycalculator;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemBars.enable(this);
        setContentView(R.layout.activity_about);

        // Use the Toolbar from the layout as this screen's ActionBar
        setSupportActionBar(findViewById(R.id.toolbar));
        SystemBars.applyPadding(findViewById(R.id.appBar), findViewById(R.id.content));

        // Show the back arrow in the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Called when the ActionBar back arrow is pressed.
     * finish() closes this screen and returns to the MainActivity that is
     * already in the back stack, so no new MainActivity is created.
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
