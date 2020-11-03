package com.example.ry_myview

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.icu.number.IntegerWidth
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.ButtonBarLayout
import androidx.core.view.drawToBitmap
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

/**
 * TODO: document your custom view class.
 */
class MyView : View {
    private lateinit var  res : Resources
    private lateinit var ball : Bitmap
    private var isInit = false
    private var ballX : Float
    private var ballY : Float
    private var bX : Float
    private var bY : Float
    private lateinit var timer : Timer


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){

        res = context!!.resources
        ball = BitmapFactory.decodeResource(res, R.drawable.ball)

        ballX = 0f
        ballY = 0f
        bX = 0f
        bY = 0f

        timer = Timer()
    }

    private fun init() {
        var ballW = width/4f
        var mat = Matrix()
        mat.postScale(ballW/ball.width, ballW/ball.height)
        ball = Bitmap.createBitmap(ball, 0, 0, ball.width, ball.height, mat, true)

        bX = 10f
        bY = 10f

        timer.schedule(timerTask{
            if(ballX < 0 || ballX + ball.width > width)
                bX *= -1
            if (ballY < 0 || ballY + ball.height > height)
                bY *= -1

            ballX += bX
            ballY += bY

                postInvalidate()
        }, 1000, 30)

        isInit = true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!isInit) init()

        canvas!!.drawBitmap(ball, ballX!!, ballY!!, null)
    }

}
