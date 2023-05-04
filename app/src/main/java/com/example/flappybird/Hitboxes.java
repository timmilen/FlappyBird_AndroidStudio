package com.example.flappybird;

import static com.example.flappybird.Bird.birdCordsX;
import static com.example.flappybird.Bird.birdCordsY;
import static com.example.flappybird.Bird.isRunningBird;
import static com.example.flappybird.Game.screenHeight;




public class Hitboxes{

    public static boolean hitCheck(double birdCordsX, double birdCordsY, double pipeCordsX, double pipeCordsY) {
        if (birdCordsY >= screenHeight-120 || birdCordsY <= 120) {
            return true;
        }
         else if (pipeCordsX != 0
                 && birdCordsX >= pipeCordsX - 50
                && birdCordsX <= pipeCordsX + 580
                && birdCordsY <= pipeCordsY + 1700
                ) {
            return true;
        }

        else if (pipeCordsX != 0
                && birdCordsX >= pipeCordsX - 50
                && birdCordsX <= pipeCordsX + 580
                && birdCordsY >= pipeCordsY + 2180
        ) {
            return true;
        }
        return false;
    }
}
