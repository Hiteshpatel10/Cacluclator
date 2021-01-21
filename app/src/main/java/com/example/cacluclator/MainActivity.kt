package com.example.cacluclator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import com.example.cacluclator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // numbers
        binding.one.setOnClickListener { appendOnExpression("1", true) }
        binding.two.setOnClickListener { appendOnExpression("2", true) }
        binding.three.setOnClickListener { appendOnExpression("3", true) }
        binding.four.setOnClickListener { appendOnExpression("4", true) }
        binding.five.setOnClickListener { appendOnExpression("5", true) }
        binding.six.setOnClickListener { appendOnExpression("6", true) }
        binding.seven.setOnClickListener { appendOnExpression("7", true) }
        binding.eight.setOnClickListener { appendOnExpression("8", true) }
        binding.nine.setOnClickListener { appendOnExpression("9", true) }
        binding.zero.setOnClickListener { appendOnExpression("0", true) }
        binding.dot.setOnClickListener { appendOnExpression(".", true) }

        //operator
        binding.plus.setOnClickListener { appendOnExpression("+", false) }
        binding.minus.setOnClickListener { appendOnExpression("-", false) }
        binding.divide.setOnClickListener { appendOnExpression("/", false) }
        binding.multiply.setOnClickListener { appendOnExpression("*", false) }
        binding.bracketOpen.setOnClickListener { appendOnExpression("(", false) }
        binding.bracketClose.setOnClickListener { appendOnExpression(")", false) }

        //clear button
        binding.clear.setOnClickListener {
            display1.text = ""
            display2.text = ""
        }

        //backspace
        binding.backSpace.setOnClickListener {
            val string = display1.text.toString()
            if (string.isNotEmpty()) {
                display1.text = string.substring(0, string.length - 1)
            }
            display2.text = ""
        }

        binding.equalTo.setOnClickListener {
            try {
                val expression = ExpressionBuilder(display1.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble())
                    display2.text = longResult.toString()
                else
                    display2.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", "Message" + e.message)
            }
        }

    }

    fun appendOnExpression(string: String, onClear: Boolean) {

        if (display2.text.isNotEmpty())
            display1.text = ""

        if (onClear) {
            display2.text = ""
            display1.append(string)
        } else {
            display1.append(display2.text)
            display1.append(string)
            display2.text = ""
        }
    }
}