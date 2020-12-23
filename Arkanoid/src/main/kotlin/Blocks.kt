import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE
import pt.isel.canvas.CYAN
import pt.isel.canvas.GREEN
import pt.isel.canvas.BLUE
import pt.isel.canvas.MAGENTA
import pt.isel.canvas.YELLOW

const val BLOCK_WIDTH: Int = 32
const val BLOCK_HEIGHT:Int = 15

data class Blocks(val x:Int, val y:Int, val hp:Int)

fun creteBlocks(b: Blocks):Blocks =
        Blocks(b.x,b.y,b.hp)

fun Canvas.drawBlocks(b:Blocks){
        //(3Y,3P,3B,3R,3C,3O,3W)-L&R
        //(1W1G1W,3O,3C,3G,3R,3B,3P,3GR)-C
    this.drawRect(BLOCK_WIDTH,BLOCK_HEIGHT,BLOCK_WIDTH,BLOCK_HEIGHT, YELLOW)
    this.drawRect(BLOCK_WIDTH*2,BLOCK_HEIGHT*2,BLOCK_WIDTH,BLOCK_HEIGHT, YELLOW)
    this.drawRect(BLOCK_WIDTH*3,BLOCK_HEIGHT*3,BLOCK_WIDTH,BLOCK_HEIGHT, YELLOW)
}