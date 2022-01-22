package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

@Config
public class GrabberSubsystem  extends SubsystemBase {
    private final SimpleServo grabber;

    public static double intakePos = 250;
    public static double originPos = 205;
    public static double dropPos = 270;

    public GrabberSubsystem(SimpleServo grabber) {
        this.grabber = grabber;
    }

    public void grab()  {
//        sleep(500);
        grabber.turnToAngle(originPos);
    }

    public void release()  {
//        sleep(500);
        grabber.turnToAngle(dropPos);
    }

    public void intakePos () {
        grabber.turnToAngle(intakePos);
    }

}