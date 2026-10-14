package com.amranhawaga.paycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Input fields
    private EditText etHours, etRate, etTax;

    // Output fields
    private TextView tvPay, tvOvertime, tvTotal, tvTax;

    // Formats numbers as money, e.g. 1234.5 -> $1,234.50
    private final NumberFormat money = NumberFormat.getCurrencyInstance(Locale.CANADA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemBars.enable(this);
        setContentView(R.layout.activity_main);

        // Use the Toolbar from the layout as this screen's ActionBar
        setSupportActionBar(findViewById(R.id.toolbar));
        SystemBars.applyPadding(findViewById(R.id.appBar), findViewById(R.id.content));

        // Connect Java variables to the views in activity_main.xml
        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        etTax = findViewById(R.id.etTax);
        tvPay = findViewById(R.id.tvPay);
        tvOvertime = findViewById(R.id.tvOvertime);
        tvTotal = findViewById(R.id.tvTotal);
        tvTax = findViewById(R.id.tvTax);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> calculate());
    }

    /** Reads the 3 inputs, runs the formulas, and shows the 4 results. */
    private void calculate() {
        Double hours = readNumber(etHours);
        Double rate = readNumber(etRate);
        Double taxPercent = readNumber(etTax);

        // Stop if any input is missing or invalid (the error is shown on that field)
        if (hours == null || rate == null || taxPercent == null) {
            return;
        }

        // Tax rate is typed as a percent (13 = 13%), so divide by 100
        PayCalculator result = new PayCalculator(hours, rate, taxPercent / 100);

        tvPay.setText(money.format(result.pay));
        tvOvertime.setText(money.format(result.overtimePay));
        tvTotal.setText(money.format(result.totalPay));
        tvTax.setText(money.format(result.tax));
    }

    /** Returns the number typed in a field, or null (and shows an error) if it isn't valid. */
    private Double readNumber(EditText field) {
        String text = field.getText().toString().trim();
        if (text.isEmpty()) {
            field.setError(getString(R.string.error_required));
            return null;
        }
        try {
            double value = Double.parseDouble(text);
            if (value < 0) {
                field.setError(getString(R.string.error_negative));
                return null;
            }
            return value;
        } catch (NumberFormatException e) {
            field.setError(getString(R.string.error_required));
            return null;
        }
    }

    // ===== Menu: adds "About" to the ActionBar =====

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
