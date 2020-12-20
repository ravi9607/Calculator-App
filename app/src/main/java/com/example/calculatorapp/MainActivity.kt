package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var lastNumaric: Boolean=false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumaric=true

    }
    fun onClear(view: View){
        tvInput.text=""
        lastDot=false
        lastNumaric=false
    }

    fun onDecimalPoint(view: View) {
        if(lastNumaric && !lastDot){
            tvInput.append(".")
            lastDot=true
            lastNumaric=false
        }
    }

    fun onOperator(view: View){
        if(lastNumaric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)

            lastNumaric=false
            lastDot=false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")||
            value.contains("*")||
            value.contains("+")||
            value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if(lastNumaric){

            var tvValue=tvInput.text.toString()
            var prefix=""

            try {
                if(tvValue.startsWith("-")){
                     prefix="-"
                    tvValue=tvValue.substring(1)

                }
                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    var ans=(one.toDouble() - two.toDouble()).toString()
                    DecimalFormat(ans)
                    tvInput.text=ans
                }

                if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    var ans=(one.toDouble() + two.toDouble()).toString()
                    DecimalFormat(ans)
                    tvInput.text=ans.toString()
                }
                if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    var ans=(one.toDouble() * two.toDouble()).toString()
                    DecimalFormat(ans)
                    tvInput.text=ans
                }
                if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    var ans=(one.toDouble() / two.toDouble()).toString()
                    DecimalFormat(ans)
                    tvInput.text=ans
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }
}