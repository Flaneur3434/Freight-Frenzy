package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

@Config
public class LifterSubsystem extends SubsystemBase {

    private final SimpleServo arm;

    public static double liftAngle = 120;
    public static double lowerAngle = 0;

    public LifterSubsystem(SimpleServo arm){
        this.arm = arm;
        // returnDefault();  // this runs on init not start
    }

    public void lift (){
        arm.turnToAngle(liftAngle);
    }

    public void lower (){
        arm.turnToAngle(lowerAngle);
    }
}
