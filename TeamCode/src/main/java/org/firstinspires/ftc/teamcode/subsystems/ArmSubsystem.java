package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

public class ArmSubsystem extends SubsystemBase {

    private final SimpleServo arm;

    public ArmSubsystem(SimpleServo arm){
        this.arm = arm;
    }

    public void layerOne (){
        arm.rotateByAngle(0);
    }

    public void layerTwo (){
        arm.rotateByAngle(10);
    }

    public void layerThree (){
        arm.rotateBy(180);

    }

    public void returnDefault (){
        arm.rotateByAngle(-arm.getAngle());
    }
}
