package com.gardner.honorsmobileapps.tipcalculatorpart1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

lateinit var tipSeekBar: SeekBar
var subtotal = 0
var numOfGuest = 0
var tipPercent = 0
var finalTotal = 0
lateinit var radButton1: RadioButton
lateinit var radButton2: RadioButton
lateinit var radButton3: RadioButton
lateinit var radButton4: RadioButton
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subtotal =100
        radButton1 =findViewById(R.id.button_one)
         radButton2 =findViewById(R.id.button_two)
         radButton3 =findViewById(R.id.button_three)
         radButton4 =findViewById(R.id.button_four)
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
    }
    fun setUpRadioButtons(){
        val list1: List<View> = listOf(radButton1,radButton2,radButton3,radButton4)
        for (eachButton in list1){
                eachButton.setOnClickListener{ view ->
                    when(view.id){
                        R.id.button_one -> tipPercent = 10
                        R.id.button_two -> tipPercent = 15
                        R.id.button_three -> tipPercent = 18
                        R.id.button_four -> tipPercent = 25
                    }
                    tipSeekBar.progress= tipPercent
                    setTipAndTotalTextViews()
                }
        }

    }
    fun setUpTipSeekBar(){
        tipSeekBar = findViewById(R.id.seek)
        tipSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                tipPercent= tipSeekBar.progress
                setTipAndTotalTextViews()
                setRadioButtonAsChecked()

            }
        })
    }
    fun setUpNumOfGuestsSpinner(){
        val spinner: Spinner = findViewById(R.id.guest_spinner)
        val guestArrayAdapter = ArrayAdapter.createFromResource(this, R.array.guest_number, android.R.layout.simple_spinner_item)
        guestArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = guestArrayAdapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                numOfGuest = position+1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
    fun setRadioButtonAsChecked(){
        val radGroup: RadioGroup = findViewById(R.id.rad_group)
        radGroup.clearCheck()
        if(tipPercent == 10){
            radButton1.isChecked=true
        }
        if(tipPercent == 15){
            radButton2.isChecked=true
        }
        if(tipPercent == 18){
            radButton3.isChecked=true
        }
        if(tipPercent == 25){
            radButton4.isChecked=true
        }
    }
    fun setTipAndTotalTextViews(){
        val tipText: TextView = findViewById(R.id.tip)
        tipText.text= "${tipPercent}%"
        val totalText: TextView = findViewById(R.id.total)
        finalTotal =subtotal.times(tipPercent).div(100).plus(100)
        totalText.text = "$${finalTotal}"
    }
}