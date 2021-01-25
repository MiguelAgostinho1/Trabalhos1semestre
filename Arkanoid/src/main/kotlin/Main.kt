import pt.isel.canvas.*

fun main(){
    onStart {
        val area = Area(416,600)
        val cv = Canvas(area.width,area.height, BLACK)
        var game: Game = startGame(area)
        game = startingBall(game)
        game = startingBlocks(game)

        cv.onMouseMove { me ->
            if(me.x <area.width - RACKET_WIDTH/2 && me.x > RACKET_WIDTH/2){
                game = moveRacket(me.x - RACKET_WIDTH/2,game)
            }
        }

        cv.onTimeProgress(10){
            game = step(game)
            draw(cv,game)
            if(game.blocks.isNotEmpty()){
                drawCounter(cv,game.points)
            }
            else{
                drawCounter(cv,game.points + 10*game.livesLeft)
            }
            cv.drawLifesLeft(game)
        }

        cv.onMouseDown {
            if(game.livesLeft > 0 && game.blocks.isNotEmpty()){
                game = addBalls(game)
            }
        }

        cv.onTimeProgress(10){
            if(game.livesLeft == 0){
                cv.drawText(0,game.area.height,"Game Over",RED,FONT_SIZE)
            }
            if(game.blocks.isEmpty()){
                game = finishedGame(cv,game)
                cv.drawText(game.area.width - FONT_SIZE*4,game.area.height,"Finish", YELLOW,FONT_SIZE)
            }
        }
    }

    onFinish {

    }
}