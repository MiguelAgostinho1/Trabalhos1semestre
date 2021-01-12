import pt.isel.canvas.Canvas
import pt.isel.canvas.CYAN

fun Canvas.drawLifesLeft(g:Game){
    for(i in (1 until g.livesLeft)){
        drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
    }
    /*when(g.livesLeft){
        6-> {
            for(i in (1 until g.livesLeft-1)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        5-> {
            for(i in (1 until g.livesLeft-1)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        4-> {
            for(i in (1 until g.livesLeft-1)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        3-> {
            for(i in (1 until g.livesLeft-1)){
                drawCircle(BALL_RADIUS*i + SPACE_BETWEEN_BALLS*(2*i-1),g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            }
        }
        2-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS,CYAN)
        }
    }*/
}