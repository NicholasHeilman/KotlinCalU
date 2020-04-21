package com.e.kotlincalcu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val savable = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        if( savedInstanceState != null){
            savable.putAll(savedInstanceState.getBundle("_state"))
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Number Listeners
        btn1.setOnClickListener{ appendOnExpression("1", true)}
        btn2.setOnClickListener{ appendOnExpression("2", true)}
        btn3.setOnClickListener{ appendOnExpression("3", true)}
        btn4.setOnClickListener{ appendOnExpression("4", true)}
        btn5.setOnClickListener{ appendOnExpression("5", true)}
        btn6.setOnClickListener{ appendOnExpression("6", true)}
        btn7.setOnClickListener{ appendOnExpression("7", true)}
        btn8.setOnClickListener{ appendOnExpression("8", true)}
        btn9.setOnClickListener{ appendOnExpression("9", true)}
        btn0.setOnClickListener{ appendOnExpression("0", true)}
        btnDot.setOnClickListener{ appendOnExpression(".", true)}

        //Operators Listeners
        btnPlus.setOnClickListener{appendOnExpression("+", false) }
        btnMinus.setOnClickListener{appendOnExpression("-", false) }
        btnDivide.setOnClickListener{appendOnExpression("/", false) }
        btnMultiply.setOnClickListener{appendOnExpression("*", false) }

        //Clear
        btnClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        //Delete
        btnDelete.setOnClickListener {
            val string = tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }

        //Equal
        btnEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            } catch (e:Exception){
                Log.d("Exception","Message : " + e.message)
            }
        }
    }

    private fun appendOnExpression(string: String, canClear: Boolean){
        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }
        if(canClear){
            tvResult.text = ""
            tvExpression.append(string)
        } else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}




