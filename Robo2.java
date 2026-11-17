package grupo;

import robocode.*;
import java.awt.Color;

public class Robo2 extends TeamRobot {

    public void run() {
        setBodyColor(Color.BLUE);
        setGunColor(Color.BLACK);
        setRadarColor(Color.GREEN);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        setAhead(120);

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
        setFire(1);

        // Move de lado em relação ao inimigo para dificultar que ele acerte.
        setTurnRight(e.getBearing() + 90);
        setAhead(120);
    }

    private double normalizar(double angulo) {
        while (angulo > 180) angulo -= 360;
        while (angulo < -180) angulo += 360;
        return angulo;
    }

    public void onHitByBullet(HitByBulletEvent e) {
        setTurnRight(90);
        setAhead(100);
    }

    public void onHitWall(HitWallEvent e) {
        setBack(80);
        setTurnRight(90);
    }
}
