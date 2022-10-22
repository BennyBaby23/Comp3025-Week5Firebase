package com.mdev.comp3025_week5_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    val calculatorClass:CalculatorClass = CalculatorClass()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorClass.ResultLabel = findViewById<TextView>(R.id.Result_TextView)

    }

    fun NumberButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text
        if((calculatorClass.ResultLabel?.text == "0") || !calculatorClass.inputReady)
        {
            calculatorClass.ResultLabel?.text = buttonText
            calculatorClass.inputReady = true
        }
        else
        {
            if (buttonText == ".")
            {
                if(calculatorClass.ResultLabel?.text?.contains(".") != true)
                {
                    calculatorClass.ResultLabel?.text = calculatorClass.ResultLabel?.text.toString().plus(buttonText)
                }
            }
            else
            {
                calculatorClass.ResultLabel?.text = calculatorClass.ResultLabel?.text.toString().plus(buttonText)
            }
        }
    }

    fun OperatorButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text

        if(calculatorClass.haveLHS)
        {
            calculatorClass.rhs = calculatorClass.ResultLabel?.text.toString()
            calculatorClass.haveRHS = true
            calculatorClass.inputReady = false
        }
        else
        {
            calculatorClass.lhs = calculatorClass.ResultLabel?.text.toString()
            calculatorClass.haveLHS = true
            calculatorClass.inputReady = false
        }

        if(calculatorClass.haveLHS && calculatorClass.haveRHS)
        {
            Evaluate()
        }

        when(buttonText)
        {
            "+" -> calculatorClass.operation = "+"
            "-" -> calculatorClass.operation = "-"
            "X" -> calculatorClass.operation = "X"
            "/" -> calculatorClass.operation = "/"
            "=" -> Evaluate()
        }

    }

    fun ExtraButtons(view: View)
    {
        val buttonInfo = view as Button
        val buttonText = buttonInfo.text
        if(buttonText == "C")
        {
            calculatorClass.ResultLabel?.text = "0"
            Reset()
        }
        else
        {
            if(calculatorClass.ResultLabel?.text?.count() == 1)
            {
                calculatorClass.ResultLabel?.text = "0"
            }
            else
            {
                calculatorClass.ResultLabel?.text = calculatorClass.ResultLabel?.text.toString().dropLast(1)
            }
        }

    }

    // Calculator Evaluation Functions
    fun Reset()
    {
        calculatorClass.result = 0.0f
        calculatorClass.lhs = ""
        calculatorClass.rhs = ""
        calculatorClass.haveLHS = false
        calculatorClass.haveRHS = false
        calculatorClass.operation = ""
        var inputReady: Boolean = true
    }

    fun Addition(lhs: Float, rhs: Float): Float
    {
        return lhs + rhs
    }

    fun Subtraction(lhs: Float, rhs: Float): Float
    {
        return lhs - rhs
    }

    fun Multiplication(lhs: Float, rhs: Float): Float
    {
        return lhs * rhs
    }

    fun Division (lhs: Float, rhs: Float): Float
    {
        return lhs / rhs
    }

    fun Evaluate()
    {
        when(calculatorClass.operation)
        {
            "+" -> calculatorClass.result = Addition(calculatorClass.lhs.toFloat(), calculatorClass.rhs.toFloat())
            "-" -> calculatorClass.result = Subtraction(calculatorClass.lhs.toFloat(), calculatorClass.rhs.toFloat())
            "/" -> calculatorClass.result = Division(calculatorClass.lhs.toFloat(), calculatorClass.rhs.toFloat())
            "X" -> calculatorClass.result = Multiplication(calculatorClass.lhs.toFloat(), calculatorClass.rhs.toFloat())
        }

        calculatorClass.ResultLabel?.text  = calculatorClass.result.toString()
        calculatorClass.lhs = calculatorClass.result.toString()
        calculatorClass.rhs = ""
        calculatorClass.haveRHS = false
    }


}