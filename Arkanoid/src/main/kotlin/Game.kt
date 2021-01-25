import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE

data class Area(val width: Int, val height: Int)
data class Game(val area:Area, val racket:Racket, val balls:List<Ball>, var blocks:List<Blocks>, var livesLeft: Int, var points: Int)

const val FONT_SIZE = 25
const val GOLD = 0xFFD700
const val ORANGE = 0xFFA500
const val SILVER = 0xC0C0C0
const val SPACE_BETWEEN_BALLS = 5

fun startGame(a: Area): Game {
    val r = Racket(a.width/2 - RACKET_WIDTH/2,RACKET_Y,RACKET_WIDTH, RACKET_HEIGHT)
    return Game(a,r, listOf(), listOf(),6,0)
}

fun draw(cv: Canvas, g: Game){
    cv.erase()
    cv.drawRacket(g.racket)
    g.balls.forEach{balls -> drawBalls(cv,balls)}
    repeat(g.blocks.size) {cv.drawBlocks(g)}
}

fun finishedGame(cv: Canvas, g: Game): Game{
    cv.erase()
    cv.drawRacket(g.racket)
    g.balls.forEach{balls -> drawBalls(cv,balls)}
    repeat(g.livesLeft) {cv.drawLifesLeft(g)}
    cv.drawRect(GOLDEN_BLOCK_X,GOLDEN_BLOCK_Y,BLOCK_WIDTH,BLOCK_HEIGHT,GOLD,1)
    cv.drawRect(GOLDEN_BLOCK_X,GOLDEN_BLOCK_Y,BLOCK_WIDTH-1,BLOCK_HEIGHT-1,GOLD)
    return Game(g.area,g.racket,g.balls,g.blocks,g.livesLeft,g.points)
}

fun step(g:Game): Game {
    val movedBalls: List<Ball> = g.balls.map{ balls -> step(g.area.width,balls,g)}
    val leftBalls: List<Ball> =  movedBalls.filter{ balls -> !ballLeavesCanvas(balls,g)}
    val leftBlocks: List<Blocks> = g.blocks.filter{ blocks -> !blocksWithZeroHp(blocks,g)}
    return Game(g.area,g.racket,leftBalls,leftBlocks,g.livesLeft,g.points)
}

fun drawCounter(cv: Canvas, counter: Int){
    cv.drawText(cv.width/2 - FONT_SIZE,cv.height,counter.toString(),WHITE,FONT_SIZE)
}