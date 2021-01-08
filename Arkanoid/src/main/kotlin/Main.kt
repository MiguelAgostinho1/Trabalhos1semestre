import pt.isel.canvas.*

fun main(){
    onStart {
        val area = Area(416,600)
        val cv = Canvas(area.width,area.height, BLACK)
        var game: Game = startGame(area)

        cv.onMouseMove { me ->
            if(me.x <area.width - RACKET_WIDTH/2 && me.x > RACKET_WIDTH/2){
                game = moveRacket(me.x - RACKET_WIDTH/2,game)
            }
        }

        cv.onTimeProgress(10){
            game = step(game)
            cv.draw(game)
            cv.drawCounter(game.livesLeft)
            cv.drawLifesLeft(game)
        }

        cv.onMouseDown {
            if(game.livesLeft > 0 || game.blocks.isEmpty()){
                game = addBalls(game)
            }
        }

        cv.onTime(5000){
            cv.onTimeProgress(10){
                if(game.livesLeft == 0){
                    cv.drawText(0,game.area.height,"Game Over",RED,FONT_SIZE)
                }
                if(game.blocks.isEmpty()){
                    cv.drawText(game.area.width,game.area.height,"Finish",YELLOW,FONT_SIZE)
                }
            }
        }
    }

    onFinish {

    }
}