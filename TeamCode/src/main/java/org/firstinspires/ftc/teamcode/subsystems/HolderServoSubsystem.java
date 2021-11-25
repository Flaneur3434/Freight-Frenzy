package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

public class HolderServoSubsystem extends SubsystemBase {

    private final SimpleServo arm;

    public HolderServoSubsystem ( SimpleServo arm){
        this.arm = arm;
    }

    public void layerOne (){
        arm.rotateByAngle(80);
    }

    public void layerTwo (){
        arm.rotateByAngle(40);
    }

    public void layerThree (){
        arm.rotateBy(20);
    }

}
