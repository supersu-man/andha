package com.supersuman.andha

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText

class TextFragment : Fragment() {

    private lateinit var editText : TextInputEditText
    private lateinit var parentLayout : RelativeLayout
    private lateinit var slider : Slider
    private var myFontSize = 100f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_text, container, false)
        editText = rootView.findViewById(R.id.textFragmentEditText)
        parentLayout = rootView.findViewById(R.id.parentLayout)
        slider = rootView.findViewById(R.id.textFragmentSlider)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modifyViews()
        initListeners()

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