package com.smaqu.tictactoe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener,ResultDialogFragment.Callback {

    // 0 - yellow, 1 = red

    var activePlayer = 0
    var items = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView.setOnClickListener(this)
        imageView1.setOnClickListener(this)
        imageView2.setOnClickListener(this)
        imageView3.setOnClickListener(this)
        imageView4.setOnClickListener(this)
        imageView5.setOnClickListener(this)
        imageView6.setOnClickListener(this)
        imageView7.setOnClickListener(this)
        imageView8.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            imageView.id -> animateCoin(imageView)
            imageView1.id -> animateCoin(imageView1)
            imageView2.id -> animateCoin(imageView2)
            imageView3.id -> animateCoin(imageView3)
            imageView4.id -> animateCoin(imageView4)
            imageView5.id -> animateCoin(imageView5)
            imageView6.id -> animateCoin(imageView6)
            imageView7.id -> animateCoin(imageView7)
            imageView8.id -> animateCoin(imageView8)
        }
    }

    private fun animateCoin(imageView: ImageView) {

        val counter = imageView.tag.toString()

        if (items[counter.toInt()] == 2) {

            items[counter.toInt()] = activePlayer

            imageView.translationY = -1000f
            activePlayer = if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.yellow)
                1
            } else {
                imageView.setImageResource(R.drawable.red)
                0
            }

            imageView.animate().translationYBy(1000f).duration = 300

            Log.e("abc", "${items.asList()}")

            if (2 in items) {
                if (items[0] == items[1] && items[1] == items[2] && items[0] != 2) {
                    winner(items[0])
                }
                if (items[0] == items[4] && items[4] == items[8] && items[0] != 2) {
                    winner(items[0])
                }
                if (items[0] == items[3] && items[3] == items[6] && items[0] != 2) {
                    winner(items[0])
                }
                if (items[3] == items[4] && items[4] == items[5] && items[3] != 2) {
                    winner(items[3])
                }
                if (items[6] == items[7] && items[7] == items[8] && items[6] != 2) {
                    winner(items[6])
                }
                if (items[1] == items[4] && items[4] == items[7] && items[1] != 2) {
                    winner(items[1])
                }
                if (items[2] == items[5] && items[5] == items[8] && items[2] != 2) {
                    winner(items[2])
                }
                if (items[2] == items[4] && items[4] == items[6] && items[2] != 2) {
                    winner(items[2])
                }
            } else {
                winner(2)
            }
        }
    }
    private fun winner(gameState: Int){
        val dialogFragment = ResultDialogFragment.createFragment(gameState)
        dialogFragment.isCancelable = false
        dialogFragment.show(fragmentManager,"TAG")
    }

    override fun restartGame() {
    }
}

