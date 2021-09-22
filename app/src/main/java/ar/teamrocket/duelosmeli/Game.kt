package ar.teamrocket.duelosmeli

class Game{
    var state: Boolean = true
    var points: Int = 0
    var errors: Int = 0

    fun pointsCounter(game: Game){
        game.points++
    }

    fun errorsCounter(game: Game) {
        game.errors++
    }
}
