import pt.isel.canvas.Canvas
import pt.isel.canvas.CYAN

const val BALL_RADIUS: Int = 7

data class Ball(val x: Int, val y: Int, var dx: Int, var dy: Int, val radius: Int)

fun createBalls(x: Int, y: Int, dx: Int, dy: Int): Ball =
        Ball(x-30,y-12,dx,dy,BALL_RADIUS)

fun addBalls(g: Game): Game {
    val stuckBalls: List<Ball> = g.balls.filter{balls ->  balls.dy == 0}
    val plus: List<Ball>
    if(stuckBalls.isEmpty() && g.balls.isEmpty()){
        plus = g.balls + createBalls(g.racket.x,g.racket.y,0,0)
    }
    else{
        plus = g.balls
        plus.forEach { balls -> ballSpeed(balls)}
    }
    return Game(g.area,g.racket,plus,g.blocks,g.livesLeft)
}

fun Canvas.drawBalls(b:Ball){
    this.drawCircle(b.x,b.y,b.radius,CYAN)
}

fun collide(b: Ball, g: Game): Int{
    return when(b.dy > -4) {
        g.racket.x == b.x + b.radius -> 0
        b.x + BALL_RADIUS in g.racket.x .. g.racket.x + 10  -> -3                  //LeftRedArea
        b.x + BALL_RADIUS in g.racket.x + 10 .. g.racket.x + 25 -> -1              //LeftCoralArea
        b.x + BALL_RADIUS in g.racket.x + 35 .. g.racket.x + 50  -> 1              //RightCoralArea
        else -> 3                                                                  //RightRedArea
    }
}

fun ballLeavesCanvas(b: Ball,g: Game): Boolean{
    if(b.y >= g.area.height){
    g.livesLeft--
    }
    else{
        null
    }
    return b.y >= g.area.height
}

fun ballSpeed(b: Ball):Ball{
    when(b.dy){
        0 -> b.dy = -4
        4 -> b.dy = 4
        -4 -> b.dy = -4
    }
    return Ball(b.x,b.y,b.dx,b.dy,b.radius)
}

fun step(maxWidth: Int, b: Ball,g:Game): Ball =
        if(b.dy == 4 || b.dy == -4) {
            when {
                b.x !in 0..maxWidth - b.radius -> Ball(b.x - b.dx, b.y, -b.dx, b.dy, b.radius)
                b.y < b.radius -> Ball(b.x, b.y - b.dy, b.dx, -b.dy, b.radius)
                b.x in (g.racket.x..g.racket.x + RACKET_WIDTH) && b.y == g.racket.y && b.dy == 4 ->
                    Ball(b.x, b.y - b.dy, b.dx + collide(b, g), -b.dy, b.radius)
                b.y  < BLOCK_HEIGHT*5 && b.y > BLOCK_HEIGHT*4 && b.x + b.radius in (GOLDEN_BLOCK_X -BLOCK_WIDTH..GOLDEN_BLOCK_X + BLOCK_WIDTH*2 + BLOCK_WIDTH/2) ->
                    Ball(b.x,b.y - b.dy,b.dx,-b.dy,b.radius)
                b.x == GOLDEN_BLOCK_X - BLOCK_WIDTH && b.y in (GOLDEN_BLOCK_Y..GOLDEN_BLOCK_Y - BLOCK_HEIGHT) ->
                    Ball(b.x + b.dx, b.y, b.dx, b.dy, b.radius)
                b.x == GOLDEN_BLOCK_X + BLOCK_WIDTH*2 && b.y in (GOLDEN_BLOCK_Y..GOLDEN_BLOCK_Y - BLOCK_HEIGHT) ->
                    Ball(b.x - b.dx, b.y, -b.dx, b.dy, b.radius)
                else -> Ball(b.x + b.dx, b.y + b.dy, b.dx, b.dy, b.radius)
            }
        }
        else{
            Ball(g.racket.x + RACKET_WIDTH/2,g.racket.y - RACKET_HEIGHT/2 - BALL_RADIUS,b.dx,b.dy,b.radius)
        }



