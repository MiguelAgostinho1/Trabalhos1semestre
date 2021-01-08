import pt.isel.canvas.CYAN
import pt.isel.canvas.Canvas

fun Canvas.drawLifesLeft(g:Game){
    when(g.livesLeft){
        6-> {
            for(i in (1..5)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        5-> {
            for(i in (1..4)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        4-> {
            for(i in (1..3)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        3-> {
            for(i in (1..2)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        2-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
        }
    }
}