package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

@Config
public class ArmSubsystem extends SubsystemBase {

    private final SimpleServo arm;

    public static double layerOneAngle = 60;
    public static double layerTwoAngle = 80;
    public static double layerThreeAngle = 200;
    public static double defaultAngle = 0;

    public ArmSubsystem(SimpleServo arm){
        this.arm = arm;
    }

    public void layerOne (){
        arm.turnToAngle(layerOneAngle);
    }

    public void layerTwo (){
        arm.turnToAngle(layerTwoAngle);
    }

    public void layerThree (){
        arm.turnToAngle(layerThreeAngle);

    }

    public void returnDefault (){
        arm.turnToAngle(defaultAngle);
    }
}
