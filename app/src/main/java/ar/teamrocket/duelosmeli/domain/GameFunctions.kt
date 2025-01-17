package ar.teamrocket.duelosmeli.domain

import android.content.Context
import android.widget.ImageView

interface GameFunctions {

    fun optionsSounds(context: Context, state: Boolean)

    fun mistakeCounterUpdater(game: Game, lifeThree: ImageView, lifeTwo: ImageView, lifeOne: ImageView)
}