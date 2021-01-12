import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE
import pt.isel.canvas.RED

const val RACKET_Y : Int = 549
const val RACKET_WIDTH: Int = 60
const val RACKET_HEIGHT: Int = 10
const val RACKET_RED_WIDTH: Int = 10
const val RACKET_CORAL_WIDTH: Int = 15
const val RACKET_CENTER_POSITION: Int = RACKET_WIDTH - 35
const val RACKET_CENTER_WIDTH: Int = RACKET_WIDTH - 50
const val RACKET_RED_POSITION: Int = RACKET_RED_WIDTH + 2*RACKET_CORAL_WIDTH + RACKET_CENTER_WIDTH
const val RACKET_CORAL_POSITION: Int = RACKET_RED_WIDTH + RACKET_CORAL_WIDTH + RACKET_CENTER_WIDTH

data class Racket(val x: Int, val y: Int = RACKET_Y,val width: Int,val height: Int)

fun move(x: Int,a: Area, r: Racket): Racket = Racket(x,RACKET_Y,RACKET_WIDTH,RACKET_HEIGHT)

fun Canvas.drawRacket(r: Racket){
    this.erase()
    this.drawRect(r.x,r.y,r.width,r.height/2,WHITE) //BASE
    this.drawRect(r.x + RACKET_CENTER_POSITION,r.y-r.height/2,RACKET_CENTER_WIDTH,r.height/2,WHITE)         //UPPER CENTER
    this.drawRect(r.x,r.y-r.height/2,RACKET_RED_WIDTH,r.height/2,RED)                                          //LEFT RED PART
    this.drawRect(r.x + RACKET_RED_WIDTH,r.y-r.height/2,RACKET_CORAL_WIDTH,r.height/2,0xFF7F50)        //LEFT CORAL PART
    this.drawRect(r.x + RACKET_RED_POSITION,r.y-r.height/2,RACKET_RED_WIDTH,r.height/2,RED)                 //RIGHT RED PART
    this.drawRect(r.x + RACKET_CORAL_POSITION,r.y-r.height/2,RACKET_CORAL_WIDTH,r.height/2,0xFF7F50)   //RIGHT CORAL PART
}