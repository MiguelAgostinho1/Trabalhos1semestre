import pt.isel.canvas.*

const val FONT_SIZE = 25
const val SPACE_BETWEEN_BALLS = 5
const val GOLD = 0xFFD700
const val ORANGE = 0xFFA500
const val SILVER = 0xC0C0C0

data class Area(val width: Int, val height: Int)
data class Game(val area:Area, val racket:Racket, val balls:List<Ball>, var blocks:List<Blocks>)

fun startGame(a: Area): Game {
    val r = Racket(a.width/2,RACKET_Y,RACKET_WIDTH, RACKET_HEIGHT,false)
    return Game(a,r, listOf(), listOf())
}

fun draw(cv: Canvas, g: Game){
    cv.erase()
    cv.drawRacket(g.racket)
    g.balls.forEach{balls -> cv.drawBalls(balls)}
    g.blocks.forEach{blocks -> cv.drawBlocks(blocks,g)}
}

fun moveRacket(x: Int, g: Game): Game {
    val r: Racket = move(x,g.area, g.racket)
    return Game(g.area,r,g.balls,g.blocks)
}

fun step(g:Game): Game {
    val movedBalls: List<Ball> = g.balls.map{ balls -> step(g.area.width,balls,g)}
    val leftBalls: List<Ball> =  movedBalls.filter{ balls -> !ballLeavesCanvas(balls,g)}
    val leftBlocks: List<Blocks> = g.blocks.filter{ blocks -> !blocksWithZeroHp(blocks)}
    return Game(g.area,g.racket,leftBalls,leftBlocks)
}

fun startingBalls(g: Game): Game {
    var startingBalls: List<Ball> = g.balls + createBalls(g.racket.x + RACKET_CENTER_POSITION,g.racket.y - RACKET_CENTER_WIDTH,0,-4)
    for(i in 1..5){
        startingBalls = startingBalls + createBalls(BALL_RADIUS + BALL_RADIUS*i*2 + SPACE_BETWEEN_BALLS*i,g.area.height - BALL_RADIUS,0,0)
    }
    return Game(g.area,g.racket,startingBalls,g.blocks)
}

fun addRightBlocksToList(g: Game):List<Blocks>{
    var rightStarting: List<Blocks> = g.blocks
    for (i: Int in 0..2) {
        rightStarting = rightStarting + createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*4, 1, YELLOW) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*5, 1, MAGENTA) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*6, 1, BLUE) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*7, 1, RED) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*8, 1, GREEN) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*9, 1, CYAN) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i, BLOCK_HEIGHT*10, 1, ORANGE) +
                createBlocks(BLOCK_WIDTH + BLOCK_WIDTH * i,  BLOCK_HEIGHT*11, 1, WHITE)
    }
    return rightStarting
}

fun addLeftBlocksToList(g: Game):List<Blocks>{
    var leftStarting: List<Blocks> = g.blocks
    for (i: Int in 0..2) {
        leftStarting = leftStarting + createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i,  BLOCK_HEIGHT*4, 1, YELLOW) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*5, 1, MAGENTA) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*6, 1, BLUE) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*7, 1, RED) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*8, 1, GREEN) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*9, 1, CYAN) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*10, 1, ORANGE) +
                createBlocks(BLOCK_WIDTH*11 - BLOCK_WIDTH * i, BLOCK_HEIGHT*11, 1, WHITE)
    }
    return leftStarting
}

fun addCenterBlocksToList(g: Game):List<Blocks>{
    var centerStarting: List<Blocks> = g.blocks + createBlocks(BLOCK_WIDTH*5,BLOCK_HEIGHT + BLOCK_HEIGHT*3, 1, WHITE) +
            createBlocks(BLOCK_WIDTH*7,BLOCK_HEIGHT*4, 1, WHITE)
    for (i: Int in 0..2) {
        centerStarting = centerStarting + createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*5, 1, MAGENTA) +
                createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*6, 1, CYAN) +
                createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*7, 1, GREEN) +
                createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*8, 1, RED) +
                createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*9, 1, BLUE) +
                createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*10, 1, MAGENTA) +
                createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*11, 2, SILVER)
    }
    return centerStarting
}

fun startingBlocks(g: Game): Game {
    val starting: List<Blocks> = addRightBlocksToList(g) + addCenterBlocksToList(g) + addLeftBlocksToList(g)
    g.blocks = starting
    return Game(g.area,g.racket,g.balls,g.blocks)
}

fun addBalls(g: Game): Game{
        when(g.balls.size){
            6 -> {
                g.balls[5].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[5].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[5].dx = 0
                g.balls[5].dy = -4
            }
            5 -> {
                g.balls[4].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[4].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[4].dx = 0
                g.balls[4].dy = -4
            }
            4 -> {
                g.balls[3].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[3].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[3].dx = 0
                g.balls[3].dy = -4
            }
            3 -> {
                g.balls[2].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[2].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[2].dx = 0
                g.balls[2].dy = -4
            }
            2 -> {
                g.balls[1].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[1].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[1].dx = 0
                g.balls[1].dy = -4
            }
            1 -> {
                g.balls[0].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[0].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[0].dx = 0
                g.balls[0].dy = -4
            }
            else -> {
                g.balls[5].x = g.racket.x + RACKET_CENTER_POSITION
                g.balls[5].y = g.racket.y + RACKET_CENTER_WIDTH
                g.balls[5].dx = 0
                g.balls[5].dy = -4
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
        game = startingBlocks(game)

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

        cv.onMouseDown {
            game = addBalls(game)
        }

        cv.onTime(5000){
            cv.onTimeProgress(10){
                if(game.balls.isEmpty()){
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