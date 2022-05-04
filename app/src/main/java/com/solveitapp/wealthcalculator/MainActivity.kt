package com.solveitapp.wealthcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //let's take referance of all required UI element
        var editMonthlyInv : EditText= findViewById<EditText>(R.id.editMonthlyInv)
        var editInterestRate : EditText= findViewById<EditText>(R.id.editIntrest)
        var editTimePeriod : EditText= findViewById<EditText>(R.id.editYear)
        var calculateBtn : Button= findViewById<Button>(R.id.calcBtn)
        var myInvestmentText : TextView= findViewById<TextView>(R.id.investmentAmount)
        var myWealthText : TextView= findViewById<TextView>(R.id.wealthAmount)

        //when we click the calculate button
        calculateBtn.setOnClickListener {
            //values from edit text
            var monthelyInvestment = editMonthlyInv.text.toString().toDouble()
            var rateOfInterest = editInterestRate.text.toString().toDouble()
            var timeperiod = editTimePeriod.text.toString().toDouble()

            //interest rate in one month
            var interestPerMonth = rateOfInterest/12
            //total no of months
            var monthhs = timeperiod*12

            //calculate wealth
            var wealth = calculateWealth(monthelyInvestment, interestPerMonth, monthhs)
            val formatedWealth = currencyFormater(wealth)
            //invested amount
            var investedamount = 0.0
            for(i in 1..monthhs.toInt()){
                investedamount =investedamount + monthelyInvestment
            }

            val formatedInvestment = currencyFormater(investedamount)
            //let's display the amounts
            myInvestmentText.text = formatedInvestment
            myWealthText.text = formatedWealth
        }
    }

    private fun calculateWealth(monthelyInvestment: Double, interestPerMonth: Double, monthhs: Double): Double {

        var amount = monthelyInvestment
        var amountofNextMonth =monthelyInvestment
        for (i in 1..monthhs.toInt()){
            var interest = amount*interestPerMonth/100
            amount = amount + interest + amountofNextMonth
        }

        return amount
    }

    private fun currencyFormater (number :Double) :String {

        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits =2
        val convert :String = numberFormat.format(number)

        return convert
    }


}