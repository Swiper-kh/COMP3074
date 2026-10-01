package com.example.lab2counter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var count = 0
    private var step = 1 // default behaviour: +/- 1. Step button changes this to 2.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val output = findViewById<TextView>(R.id.textViewOutput)
        val addButton = findViewById<Button>(R.id.buttonAdd)
        val subtractButton = findViewById<Button>(R.id.buttonSubtract)
        val resetButton = findViewById<Button>(R.id.buttonReset)
        val stepButton = findViewById<Button>(R.id.buttonStep)

        fun updateOutput() {
            output.text = count.toString()
        }

        addButton.setOnClickListener {
            count += step
            updateOutput()
        }

        subtractButton.setOnClickListener {
            count -= step
            updateOutput()
        }

        resetButton.setOnClickListener {
            count = 0
            step = 1 // back to default behaviour
            updateOutput()
        }

        stepButton.setOnClickListener {
            step = 2 // switch to new behaviour: +/- 2
        }
    }
}
