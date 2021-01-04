import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE
import pt.isel.canvas.CYAN
import pt.isel.canvas.GREEN
import pt.isel.canvas.BLUE
import pt.isel.canvas.MAGENTA
import pt.isel.canvas.YELLOW

const val BLOCK_WIDTH: Int = 32
const val BLOCK_HEIGHT:Int = 15

data class Blocks(val x:Int, val y:Int, val hp:Int, val color: Int)

fun blocksWithZeroHp(b:Blocks):Boolean =
        b.hp == 0

fun createBlocks(x: Int, y: Int, hp: Int, color: Int):Blocks =
        Blocks(x,y,hp,color)

fun Canvas.drawBlocks(b: Blocks,game: Game){
        //(3Y,3P,3B,3R,3C,3O,3W)-L&R
        //(1W1G1W,3O,3C,3G,3R,3B,3P,3GR)-C
    for(i in 0..game.blocks.size-1){
        if(game.blocks[i].hp > 0){
            this.drawRect(game.blocks[i].x,game.blocks[i].y,BLOCK_WIDTH,BLOCK_HEIGHT,game.blocks[i].color,1)
            this.drawRect(game.blocks[i].x,game.blocks[i].y,BLOCK_WIDTH-1,BLOCK_HEIGHT-1,game.blocks[i].color)
        }
    }
}