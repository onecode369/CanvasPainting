package com.github.onecode369.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import kotlin.math.sqrt


class Canvas(context: Context, attrs: AttributeSet) : View(context, attrs){

    private var path = Path()

    private var isRect : Boolean = false
    private var isLine : Boolean = false
    private var isCircle : Boolean = false

    private var STROKE_WIDTH : Float
    private var paintColor : Int
    private var paintBackgroundColor : Int
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private var StX : Float = 0f
    private var StY : Float = 0f


    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Canvas,
            0,0
        ).apply {
            try {

                STROKE_WIDTH = getFloat(R.styleable.Canvas_strokeWidth,5f)
                paintColor = getColor(R.styleable.Canvas_paintColor,Color.BLUE)
                paintBackgroundColor = getColor(R.styleable.Canvas_backgroundColor,Color.WHITE)

            } finally {
                recycle()
            }
        }
    }

    private val paint = Paint().apply {
        color = paintColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var currentX = 0f

    private var currentY = 0f

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(paintBackgroundColor)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
        if(isRect || isLine || isCircle){
            StX = currentX
            StY = currentY
        }
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to save it.
            if(!isRect && !isLine && !isCircle){
                extraCanvas.drawPath(path, paint)
            }
        }
        // Invalidate() is inside the touchMove() under ACTION_MOVE because there are many other
        // types of motion events passed into this listener, and we don't want to invalidate the
        // view for those.
        invalidate()
    }

    private fun touchUp() {
        if(isRect){
            extraCanvas.drawRect(StX,StY,currentX,currentY,paint)
            isRect = false
        }
        if(isLine){
            extraCanvas.drawLine(StX,StY,currentX,currentY,paint)
            isLine = false
        }

        if(isCircle){
            extraCanvas.drawCircle(StX,StY, sqrt(((currentX-StX)*(currentX-StX))+((currentY-StY)*(currentY-StY))),paint)
            isCircle = false
        }
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    fun paintColor(color : Int){
        paint.color = color
    }

    fun strokeWidth(strokeWidth : Float){
        paint.strokeWidth = strokeWidth
    }

    fun drawRect(){
        isLine = false
        isRect = true
        isCircle = false
    }

    fun drawLine(){
        isRect = false
        isLine = true
        isCircle = false
    }

    fun drawCircle(){
        isRect = false
        isLine = false
        isCircle = true
    }

}