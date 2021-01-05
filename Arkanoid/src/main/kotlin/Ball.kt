import pt.isel.canvas.Canvas
import pt.isel.canvas.CYAN

const val BALL_RADIUS: Int = 7

data class Ball(var x: Int, var y: Int, var dx: Int, var dy: Int, val radius: Int)

fun createBalls(x: Int, y: Int, dx: Int, dy: Int): Ball =
        Ball(x,y,dx,dy,BALL_RADIUS)

fun Canvas.drawBalls(b:Ball){
    this.drawCircle(b.x,b.y,b.radius,CYAN)
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
            b.x !in 0..maxWidth - b.radius -> {
                //println(g.racket.x)
                //println(b.x)
                Ball(b.x-b.dx,b.y,-b.dx,b.dy,b.radius)
            }
            b.y < b.radius  -> {
                //println(g.racket.x)
                //println(b.x)
                Ball(b.x,b.y - b.dy,b.dx,-b.dy,b.radius)
            }
            b.x + b.radius in (g.racket.x .. g.racket.x + RACKET_WIDTH) && b.y == g.racket.y && b.dy == 4 -> {
                println(g.racket.x)
                println(g.balls[0].x)
                Ball(b.x,b.y - b.dy,b.dx + collide(b,g), -b.dy,b.radius)
            }
            b.y  < BLOCK_HEIGHT*12 && b.x +b.radius in (BLOCK_WIDTH*7..BLOCK_WIDTH*8) -> {
                //println(g.racket.x)
                Ball(b.x,b.y - b.dy,b.dx,-b.dy,b.radius)}
            else -> {
                println(g.racket.x)
                println(b.x)
                Ball(b.x + b.dx,b.y + b.dy,b.dx,b.dy,b.radius)
            }
        }



