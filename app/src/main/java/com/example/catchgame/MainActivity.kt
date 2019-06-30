package com.example.catchgame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.contains
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    val sec : Long = 1000
    var score = 0

    var handler = Handler()
    var runnable = Runnable {  }

    var picList = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picList = arrayListOf(pos0_0, pos0_1, pos1_0, pos1_1, pos2_0, pos2_1)
        for (image in picList){
            image.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    fun startGame(view: View){
        timerStart.isEnabled = false
        score = 0
        scoreboard.text = "Current Score: " + score
        hide()
        startTimer()
    }

    fun startTimer() {
        object : CountDownTimer(sec * 10 + 10, sec) {
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                textView.text = "Time Left: " + (p0 / sec)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                textView.text = "Time Expiered"
                Toast.makeText(applicationContext, "Game Stopped", Toast.LENGTH_LONG).show()
                timerStart.isEnabled = true
                handler.removeCallbacks(runnable)
                for (image in picList){
                    image.visibility = View.INVISIBLE
                }
            }

        }.start()
    }

    fun hide(){
        runnable = object : Runnable {
            override fun run() {
                for (image in picList){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val index = random.nextInt(5 - 0)
                picList[index].visibility = View.VISIBLE
                handler.postDelayed(runnable, sec / 4)
            }

        }
        handler.post(runnable)

    }

    @SuppressLint("SetTextI18n")
    fun increaseScore(view: View){
        score++
        scoreboard.text = "Current Score: " + score
    }

}
