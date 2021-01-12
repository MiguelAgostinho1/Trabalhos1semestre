import pt.isel.canvas.Canvas
import pt.isel.canvas.CYAN

const val BALL_RADIUS: Int = 7

data class Ball(val x: Int, val y: Int, var dx: Int, var dy: Int, val radius: Int)

fun createBalls(x: Int, y: Int, dx: Int, dy: Int): Ball =
        Ball(x-30,y-12,dx,dy,BALL_RADIUS)

fun drawBalls(cv:Canvas, b:Ball){
    cv.drawCircle(b.x,b.y,b.radius,CYAN)
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

fun ballLeavesCanvas(b: Ball,g: Game): Boolean =
    b.y >= g.area.height



fun step(maxWidth: Int, b: Ball,g:Game): Ball =
        if(b.dy == 4 || b.dy == -4) {
            when {
                b.x !in 0..maxWidth - b.radius -> Ball(b.x - b.dx, b.y, -b.dx, b.dy, b.radius)
                b.y < b.radius -> Ball(b.x, b.y - b.dy, b.dx, -b.dy, b.radius)
                b.x in (g.racket.x..g.racket.x + RACKET_WIDTH) && b.y == g.racket.y && b.dy == 4 ->
                    Ball(b.x, b.y - b.dy, b.dx + collide(b, g), -b.dy, b.radius)
                b.y  < BLOCK_HEIGHT*5 && b.y > BLOCK_HEIGHT*4 && b.x + b.radius in (GOLDEN_BLOCK_X..GOLDEN_BLOCK_X + BLOCK_WIDTH + BLOCK_WIDTH/2) ->
                    Ball(b.x,b.y - b.dy,b.dx,-b.dy,b.radius)
                b.x == GOLDEN_BLOCK_X && b.y in (GOLDEN_BLOCK_Y..GOLDEN_BLOCK_Y + BLOCK_HEIGHT) -> Ball(b.x - b.dx, b.y, -b.dx, b.dy, b.radius)
                b.x == GOLDEN_BLOCK_X + BLOCK_WIDTH && b.y in (GOLDEN_BLOCK_Y..GOLDEN_BLOCK_Y + BLOCK_HEIGHT) -> Ball(b.x + b.dx, b.y, b.dx, b.dy, b.radius)
                else -> Ball(b.x + b.dx, b.y + b.dy, b.dx, b.dy, b.radius)
            }
        }
        else{
            Ball(g.racket.x + RACKET_WIDTH/2,g.racket.y - RACKET_HEIGHT/2 - BALL_RADIUS,b.dx,b.dy,b.radius)
        }



