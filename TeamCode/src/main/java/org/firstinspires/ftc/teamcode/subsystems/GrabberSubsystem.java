package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

@Config
public class GrabberSubsystem  extends SubsystemBase {
    private final SimpleServo grabber;

    public static double defaultt = 0;
    public static double drop = -90;

    public GrabberSubsystem(SimpleServo grabber) {
        this.grabber = grabber;
    }

    public void grab()  {
//        sleep(500);
        grabber.setPosition(defaultt);
    }

    public void release()  {
//        sleep(500);
        grabber.setPosition(drop);
    }

}