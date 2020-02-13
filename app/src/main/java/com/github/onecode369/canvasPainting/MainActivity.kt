package com.github.onecode369.canvasPainting

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SeekBar
import com.github.onecode369.andy_handy_animations.slideInLeft
import com.github.onecode369.andy_handy_animations.slideOutLeft
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.progress = 30

        canvas.strokeWidth(seekBar.progress.toFloat())

        resetText()

        colors.setOnClickListener {

            if(colors_list.visibility == View.GONE){
                colors_list.visibility = View.VISIBLE
                slideInLeft(colors_list,500)
            }else{
                slideOutLeft(colors_list,500)
                colors_list.visibility = View.GONE
            }

        }

        circle.setOnClickListener {
            canvas.drawCircle()
        }

        line.setOnClickListener {
            canvas.drawLine()
        }

        rectangle.setOnClickListener {
            canvas.drawRect()
        }

        color1.setOnClickListener {
            resetText()
            color1.setText("^")
            canvas.paintColor(Color.parseColor("#F44336"))
        }

        color2.setOnClickListener {
            resetText()
            color2.setText("^")
            canvas.paintColor(Color.parseColor("#E91E63"))
        }

        color3.setOnClickListener {
            resetText()
            color3.setText("^")
            canvas.paintColor(Color.parseColor("#9C27B0"))
        }

        color4.setOnClickListener {
            resetText()
            color4.setText("^")
            canvas.paintColor(Color.parseColor("#673AB7"))
        }

        color5.setOnClickListener {
            resetText()
            color5.setText("^")
            canvas.paintColor(Color.parseColor("#3F51B5"))
        }

        color6.setOnClickListener {
            resetText()
            color6.setText("^")
            canvas.paintColor(Color.parseColor("#2196F3"))
        }

        color7.setOnClickListener {
            resetText()
            color7.setText("^")
            canvas.paintColor(Color.parseColor("#03A9F4"))
        }

        color8.setOnClickListener {
            resetText()
            color8.setText("^")
            canvas.paintColor(Color.parseColor("#00BCD4"))
        }

        color9.setOnClickListener {
            resetText()
            color9.setText("^")
            canvas.paintColor(Color.parseColor("#009688"))
        }

        color10.setOnClickListener {
            resetText()
            color10.setText("^")
            canvas.paintColor(Color.parseColor("#4CAF50"))
        }

        color11.setOnClickListener {
            resetText()
            color11.setText("^")
            canvas.paintColor(Color.parseColor("#8BC34A"))
        }

        color12.setOnClickListener {
            resetText()
            color12.setText("^")
            canvas.paintColor(Color.parseColor("#CDDC39"))
        }

        color13.setOnClickListener {
            resetText()
            color13.setText("^")
            canvas.paintColor(Color.parseColor("#FFEB3B"))
        }

        color14.setOnClickListener {
            resetText()
            color14.setText("^")
            canvas.paintColor(Color.parseColor("#FFC107"))
        }

        color15.setOnClickListener {
            resetText()
            color15.setText("^")
            canvas.paintColor(Color.parseColor("#FF9800"))
        }

        color16.setOnClickListener {
            resetText()
            color16.setText("^")
            canvas.paintColor(Color.parseColor("#FF5722"))
        }

        erase.setOnClickListener {
            canvas.paintColor(Color.parseColor("#FFFFFF"))
        }


        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                canvas.strokeWidth(seekBar.progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                canvas.strokeWidth(seekBar.progress.toFloat())
            }
        })

        color_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                try {
                    color_output.setBackgroundColor(Color.parseColor(color_input.text.toString()))
                    color_output.setOnClickListener {
                        try {
                            canvas.paintColor(Color.parseColor(color_input.text.toString()))
                        } catch (e : Exception){}
                    }
                } catch (e: Exception) {}
            }
        })

    }

    fun resetText(){

        color1.setText("")
        color2.setText("")
        color3.setText("")
        color4.setText("")
        color5.setText("")
        color6.setText("")
        color7.setText("")
        color8.setText("")
        color9.setText("")
        color10.setText("")
        color11.setText("")
        color12.setText("")
        color13.setText("")
        color14.setText("")
        color15.setText("")
        color16.setText("")
    }
}
