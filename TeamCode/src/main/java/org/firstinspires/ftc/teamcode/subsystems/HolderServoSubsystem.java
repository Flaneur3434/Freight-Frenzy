package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

public class HolderServoSubsystem extends SubsystemBase {

    private final SimpleServo arm;

    public HolderServoSubsystem ( SimpleServo arm){
        this.arm = arm;
    }

    public void layerOne{
        arm.rotateBy(/*to added*/);
    }

    public void layerTwo{
        arm.rotateBy(/*to added*/);
    }

    public void layerThree{
        arm.rotateBy(/*to added*/);
    }

}
