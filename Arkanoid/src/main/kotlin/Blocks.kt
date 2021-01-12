import pt.isel.canvas.Canvas
import pt.isel.canvas.YELLOW
import pt.isel.canvas.MAGENTA
import pt.isel.canvas.BLUE
import pt.isel.canvas.RED
import pt.isel.canvas.GREEN
import pt.isel.canvas.CYAN
import pt.isel.canvas.WHITE

const val BLOCK_WIDTH: Int = 32
const val BLOCK_HEIGHT:Int = 15
const val GOLDEN_BLOCK_X = BLOCK_WIDTH*6
const val GOLDEN_BLOCK_Y = BLOCK_HEIGHT*4

data class Blocks(val x:Int, val y:Int, val hp:Int, val color: Int)

fun blocksWithZeroHp(b:Blocks):Boolean =
        b.hp == 0

fun createBlocks(x: Int, y: Int, hp: Int, color: Int):Blocks =
        Blocks(x,y,hp,color)

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
        centerStarting = centerStarting + createBlocks(BLOCK_WIDTH*5 + BLOCK_WIDTH * i, BLOCK_HEIGHT*5, 1, ORANGE) +
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
    return Game(g.area,g.racket,g.balls,g.blocks,g.livesLeft)
}

fun Canvas.drawBlocks(game: Game){
    //(3Y,3M,3B,3R,3G,3C,3O,3W)-L&R
    //(1W1G1W,3O,3C,3G,3R,3B,3M,3S)-C
    this.drawRect(GOLDEN_BLOCK_X,GOLDEN_BLOCK_Y,BLOCK_WIDTH,BLOCK_HEIGHT,GOLD,1)
    this.drawRect(GOLDEN_BLOCK_X,GOLDEN_BLOCK_Y,BLOCK_WIDTH-1,BLOCK_HEIGHT-1,GOLD)
    for(i in game.blocks.indices){
        if(game.blocks[i].hp > 0){
            this.drawRect(game.blocks[i].x,game.blocks[i].y,BLOCK_WIDTH,BLOCK_HEIGHT,game.blocks[i].color,1)
            this.drawRect(game.blocks[i].x,game.blocks[i].y,BLOCK_WIDTH-1,BLOCK_HEIGHT-1,game.blocks[i].color)
        }
    }
}