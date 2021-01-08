import pt.isel.canvas.CYAN
import pt.isel.canvas.Canvas

fun Canvas.drawLifesLeft(g:Game){
    when(g.livesLeft){
        6-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*2 + SPACE_BETWEEN_BALLS*3,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*3 + SPACE_BETWEEN_BALLS*5,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*4 + SPACE_BETWEEN_BALLS*7,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*5 + SPACE_BETWEEN_BALLS*9,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
        }
        5-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*2 + SPACE_BETWEEN_BALLS*3,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*3 + SPACE_BETWEEN_BALLS*5,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*4 + SPACE_BETWEEN_BALLS*7,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
        }
        4-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*2 + SPACE_BETWEEN_BALLS*3,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*3 + SPACE_BETWEEN_BALLS*5,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
        }
        3-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
            drawCircle(BALL_RADIUS*2 + SPACE_BETWEEN_BALLS*3,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
        }
        2-> {
            drawCircle(BALL_RADIUS + SPACE_BETWEEN_BALLS,g.area.height - BALL_RADIUS,BALL_RADIUS, CYAN)
        }
    }
}