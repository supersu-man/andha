package com.supersuman.andha

import android.os.Bundle
import android.util.TypedValue
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var editText : TextInputEditText
    private lateinit var parentLayout : RelativeLayout
    private lateinit var slider : Slider
    private var myFontSize = 100f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()
        modifyViews()
        initListeners()


    }

    private fun initViews() {
        editText = findViewById(R.id.edit_text)
        parentLayout = findViewById(R.id.parentLayout)
        slider = findViewById(R.id.slider)
    }

    private fun modifyViews() {
        setTextSize(myFontSize)
    }

    private fun initListeners(){
        slider.addOnChangeListener { _, value, _ ->
            setTextSize(value)
        }
    }

    private fun setTextSize(f : Float){
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, f)
    }

}