import pt.isel.canvas.*

data class Area(val width: Int, val height: Int)
data class Game(val area:Area, val racket:Racket, val balls:List<Ball>)

fun startGame(a: Area): Game {
    val r = Racket(a.width/2,RACKET_Y,RACKET_WIDTH, RACKET_HEIGHT)
    return Game(a,r, listOf())
}

fun draw(cv: Canvas, g: Game){
    cv.erase()
    cv.drawRacket(g.racket)
    g.balls.forEach{balls -> drawBalls(cv,balls)}
}

fun moveRacket(x: Int, g: Game): Game {
    val r: Racket = move(x,g.area, g.racket)
    return Game(g.area,r,g.balls)
}

fun step(g:Game): Game {
    val movedBalls: List<Ball> = g.balls.map{ balls -> step(g.area.width,g.area.height,balls,g)}
    val leftBalls: List<Ball> =  movedBalls.filter{ balls -> !ballLeavesCanvas(balls,g)}
    return Game(g.area,g.racket,leftBalls)
}

fun addBalls(g: Game): Game {
    val plus: List<Ball> = g.balls + createBalls(g.area.width,g.area.height)
    return Game(g.area,g.racket,plus)
}

fun drawCounter(cv: Canvas, counter: Int){
    cv.drawText(cv.width/2,cv.height,counter.toString(),WHITE,50)
}

fun main(){
    onStart {
        val area = Area(400,600)
        val cv = Canvas(area.width,area.height, BLACK)
        var game: Game = startGame(area)

        cv.onMouseMove { me ->
            if(me.x <area.width - RACKET_WIDTH/2 && me.x > RACKET_WIDTH/2){
                game = moveRacket(me.x - RACKET_WIDTH/2,game)
            }
        }

        cv.onTimeProgress(10){
            game = step(game)
            draw(cv,game)
            drawCounter(cv,game.balls.size)
        }

        cv.onTimeProgress(5000) {
            game = addBalls(game)
        }

        cv.onTime(5000){
            cv.onTimeProgress(10){
                if(game.balls.isEmpty()){
                    cv.close()
                }
            }
        }
    }

    onFinish {

    }
}