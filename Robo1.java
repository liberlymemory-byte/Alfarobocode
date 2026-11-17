package grupo;

import robocode.*;
import java.awt.Color;

public class Robo1 extends TeamRobot {

    public void run() {
        setBodyColor(Color.RED);
        setGunColor(Color.BLACK);
        setRadarColor(Color.YELLOW);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            setTurnRadarRight(360);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (isTeammate(e.getName())) {
            return;
        }

        double angulo = getHeading() + e.getBearing();
        double giro = normalizar(angulo - getGunHeading());

        setTurnGunRight(giro);

        if (e.getDistance() < 150) {
            setFire(3);
        } else if (e.getDistance() < 400) {
            setFire(2);
        } else {
            setFire(1);
        }

        setAhead(40);
    }

    private double normalizar(double angulo) {
        while (angulo > 180) angulo -= 360;
        while (angulo < -180) angulo += 360;
        return angulo;
    }

    public void onHitWall(HitWallEvent e) {
        setBack(80);
        setTurnRight(90);
    }
}
