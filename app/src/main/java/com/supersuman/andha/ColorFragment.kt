package com.supersuman.andha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorSwatch

class ColorFragment : Fragment() {

    private lateinit var button : Button
    private lateinit var canvas : FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_color, container, false)
        button = rootView.findViewById(R.id.colorFragmentColorButton)
        canvas = rootView.findViewById(R.id.colorFragmentCanvas)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        canvas.setBackgroundColor(resources.getColor(com.github.dhaval2404.colorpicker.R.color.red_600))
        button.setOnClickListener {
            showColorDialog()
        }
    }

    private fun showColorDialog(){
        MaterialColorPickerDialog
            .Builder(this.requireContext())
            .setTitle("Pick Color")
            .setColorSwatch(ColorSwatch._600)
            .setColorListener { color, _ ->
                canvas.setBackgroundColor(color)
            }
            .show()
    }
}