import pt.isel.canvas.Canvas
import pt.isel.canvas.CYAN

const val BALL_RADIUS: Int = 7

data class Ball(val x: Int, val y: Int, var dx: Int, var dy: Int, val radius: Int)

fun createBalls(maxWidth: Int, maxHeight: Int): Ball =
        Ball((0..maxWidth-BALL_RADIUS).random(),maxHeight-BALL_RADIUS,(-6..6).random(),-4,BALL_RADIUS)

fun drawBalls(cv:Canvas, b:Ball){
    cv.drawCircle(b.x,b.y,b.radius,CYAN)
}

fun collide(b: Ball, g: Game): Int{
    return when(b.dy > -4) {
        g.racket.x == b.x + b.radius -> 0
        b.x + BALL_RADIUS in g.racket.x .. g.racket.x + 10  -> -3                  //LeftRedArea
        b.x + BALL_RADIUS in g.racket.x + 10 .. g.racket.x + 25 -> -1              //LeftCoralArea
        b.x + BALL_RADIUS in g.racket.x + 65 .. g.racket.x + 80  -> -1             //RightCoralArea
        else -> 3                                                                  //RightRedArea
    }
}

fun ballLeavesCanvas(b: Ball,g: Game): Boolean =
        b.y >= g.area.height

fun step(maxWidth: Int, b: Ball,g:Game): Ball =
        when{
            b.x !in 0..maxWidth - b.radius -> Ball(b.x-b.dx,b.y,-b.dx,b.dy,b.radius)
            b.y < b.radius  -> Ball(b.x,b.y - b.dy,b.dx,-b.dy,b.radius)
            b.x + b.radius in (g.racket.x .. g.racket.x + RACKET_WIDTH) && b.y == g.racket.y && b.dy == 4 -> Ball(b.x,b.y - b.dy,b.dx + collide(b,g), -b.dy,b.radius)
            else -> Ball(b.x + b.dx,b.y + b.dy,b.dx,b.dy,b.radius)
        }



