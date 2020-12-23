import pt.isel.canvas.*

const val FONT_SIZE = 25
const val SPACE_BETWEEN_BALLS = 5

data class Area(val width: Int, val height: Int)
data class Game(val area:Area, val racket:Racket, val balls:List<Ball>, val blocks:List<Blocks>)

fun startGame(a: Area): Game {
    val r = Racket(a.width/2,RACKET_Y,RACKET_WIDTH, RACKET_HEIGHT)
    return Game(a,r, listOf(), listOf())
}

fun draw(cv: Canvas, g: Game){
    cv.erase()
    cv.drawRacket(g.racket)
    g.balls.forEach{balls -> cv.drawBalls(balls)}
    g.blocks.forEach{blocks -> cv.drawBlocks(blocks)}
}

fun moveRacket(x: Int, g: Game): Game {
    val r: Racket = move(x,g.area, g.racket)
    return Game(g.area,r,g.balls,g.blocks)
}

fun step(g:Game): Game {
    val movedBalls: List<Ball> = g.balls.map{ balls -> step(g.area.width,balls,g)}
    val leftBalls: List<Ball> =  movedBalls.filter{ balls -> !ballLeavesCanvas(balls,g)}
    return Game(g.area,g.racket,leftBalls,g.blocks)
}

fun startingBalls(g: Game): Game {
    val starting: List<Ball> = g.balls + createBalls(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,0,0) + createBalls(BALL_RADIUS + BALL_RADIUS*2 + SPACE_BETWEEN_BALLS*2,g.area.height - BALL_RADIUS,0,0) + createBalls(BALL_RADIUS + BALL_RADIUS*4 + SPACE_BETWEEN_BALLS*3,g.area.height - BALL_RADIUS,0,0) + createBalls(BALL_RADIUS + BALL_RADIUS*6 + SPACE_BETWEEN_BALLS*4,g.area.height - BALL_RADIUS,0,0) + createBalls(BALL_RADIUS + BALL_RADIUS*8 + SPACE_BETWEEN_BALLS*5,g.area.height - BALL_RADIUS,0,0) + createBalls(g.racket.x + RACKET_CENTER_POSITION,g.racket.y - RACKET_CENTER_WIDTH,-4,-4)
    return Game(g.area,g.racket,starting,g.blocks)
}

fun addBalls(g: Game): Game{
    when{
        g.balls.size == 4 -> {
            g.balls[3].x = g.racket.x + RACKET_CENTER_POSITION
            g.balls[3].y = g.racket.y + RACKET_CENTER_WIDTH
            g.balls[3].dx = -4
            g.balls[3].dy = -4
        }
        g.balls.size == 3 -> {
            g.balls[2].x = g.racket.x + RACKET_CENTER_POSITION
            g.balls[2].y = g.racket.y + RACKET_CENTER_WIDTH
            g.balls[2].dx = -4
            g.balls[2].dy = -4
        }
        g.balls.size == 2 -> {
            g.balls[1].x = g.racket.x + RACKET_CENTER_POSITION
            g.balls[1].y = g.racket.y + RACKET_CENTER_WIDTH
            g.balls[1].dx = -4
            g.balls[1].dy = -4
        }
        g.balls.size == 1 -> {
            g.balls[0].x = g.racket.x + RACKET_CENTER_POSITION
            g.balls[0].y = g.racket.y + RACKET_CENTER_WIDTH
            g.balls[0].dx = -4
            g.balls[0].dy = -4
        }
        else -> {
            g.balls[4].x = g.racket.x + RACKET_CENTER_POSITION
            g.balls[4].y = g.racket.y + RACKET_CENTER_WIDTH
            g.balls[4].dx = -4
            g.balls[4].dy = -4
        }
    }
    return Game(g.area,g.racket,g.balls,g.blocks)
}

fun drawCounter(cv: Canvas, counter: Int){
    cv.drawText(cv.width/2 - FONT_SIZE/2,cv.height,counter.toString(),WHITE,FONT_SIZE)
}

fun main(){
    onStart {
        val area = Area(416,600)
        val cv = Canvas(area.width,area.height, BLACK)
        var game: Game = startGame(area)
        game = startingBalls(game)

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

        cv.onMouseDown { me: MouseEvent ->
            game = addBalls(game)
        }

        cv.onTime(5000){
            cv.onTimeProgress(10){
                if(game.balls.isEmpty()){
                    cv.drawText(FONT_SIZE,game.area.height,"Game Over",RED,FONT_SIZE)
                }
            }
        }
    }

    onFinish {

    }
}