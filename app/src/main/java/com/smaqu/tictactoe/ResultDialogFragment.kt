package com.smaqu.tictactoe

import android.app.Activity
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by SmaQu on 2018-01-31.
 */
class ResultDialogFragment : DialogFragment() {

    interface Callback {
        fun restartGame()
    }

    private var callback: Callback? = null
    private var gameState: Int? = null

    companion object {
        const val KEY_GAME_STATE = "key_game_state"
        fun createFragment(gameState: Int): ResultDialogFragment {
            val resultDialogFragment = ResultDialogFragment()
            val bundle = Bundle()
            bundle.putInt(Companion.KEY_GAME_STATE, gameState)
            resultDialogFragment.arguments = bundle
            return resultDialogFragment
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        callback = activity as Callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
        gameState = arguments.getInt(KEY_GAME_STATE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_fragment, container, false)
        val textViewWiner = view?.findViewById<TextView>(R.id.tv_dialog_fragment_label)
        val imageWinner = view?.findViewById<ImageView>(R.id.iv_dialog_fragment_icon)
        val buttonRestart = view?.findViewById<Button>(R.id.b_dialog_fragment_start)
        when (gameState) {
            1 -> {
                textViewWiner?.text = getString(R.string.yellow_won)
                imageWinner?.setImageResource(R.drawable.yellow)
            }
            0 -> {
                textViewWiner?.text = getString(R.string.red_won)
                imageWinner?.setImageResource(R.drawable.red)
            }
            2 -> {
                textViewWiner?.text = getString(R.string.draw)
                imageWinner?.setImageResource(R.drawable.red_yellow)
            }
        }

        buttonRestart?.setOnClickListener {
            this.dismiss()
            callback?.restartGame()
        }
        return view
    }
}