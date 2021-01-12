import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE

data class Area(val width: Int, val height: Int)
data class Game(val area:Area, val racket:Racket, val balls:List<Ball>, var blocks:List<Blocks>, var livesLeft: Int)

const val FONT_SIZE = 25
const val GOLD = 0xFFD700
const val ORANGE = 0xFFA500
const val SILVER = 0xC0C0C0
const val SPACE_BETWEEN_BALLS = 5

fun startGame(a: Area): Game {
    val r = Racket(a.width/2 - RACKET_WIDTH/2,RACKET_Y,RACKET_WIDTH, RACKET_HEIGHT)
    val startingBlocks: List<Blocks> = addRightBlocksToList() + addCenterBlocksToList() + addLeftBlocksToList()
    val startingBall: Ball = createBalls(r.x + RACKET_CENTER_POSITION,r.y - RACKET_CENTER_WIDTH,0,0)
    return Game(a,r, listOf(startingBall), startingBlocks,6)
}

fun Canvas.draw(g: Game){
    this.erase()
    this.drawRacket(g.racket)
    this.drawCounter(g.livesLeft)
    this.drawLifesLeft(g)
    g.balls.forEach{balls -> this.drawBalls(balls)}
    g.blocks.forEach{blocks -> this.drawBlocks(g)}
}

fun step(g:Game): Game {
    val movedBalls: List<Ball> = g.balls.map{ balls -> step(g.area.width,balls,g)}
    val leftBalls: List<Ball> =  movedBalls.filter{ balls -> !ballLeavesCanvas(balls,g)}
    val leftBlocks: List<Blocks> = g.blocks.filter{ blocks -> !blocksWithZeroHp(blocks)}
    return Game(g.area,g.racket,leftBalls,leftBlocks,g.livesLeft)
}

fun Canvas.drawCounter(counter: Int){
    this.drawText(this.width/2 - FONT_SIZE,this.height,counter.toString(),WHITE,FONT_SIZE)
}
