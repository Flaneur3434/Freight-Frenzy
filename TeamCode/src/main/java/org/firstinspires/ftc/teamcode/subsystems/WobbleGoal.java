package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

@Config
public class WobbleGoal {
    private final Servo grabber;
    private final Servo arm;

    public static double grabberGrab = 0;
    public static double grabberRelease = 1;
    public static double armUp = 0.3;
    public static double armDown = 0.75;

    public WobbleGoal(Servo grabber, Servo arm) {
        this.grabber = grabber;
        this.arm = arm;
    }

    public void grab() throws InterruptedException {
        arm.setPosition(armDown);
        sleep(500);
        grabber.setPosition(grabberGrab);
    }

    public void release() throws InterruptedException {
        arm.setPosition(armDown);
        sleep(500);
        grabber.setPosition(grabberRelease);
    }

    public void armUp() throws InterruptedException {
        arm.setPosition(armUp);
        sleep(500);
        grabber.setPosition(grabberGrab);
    }
}
