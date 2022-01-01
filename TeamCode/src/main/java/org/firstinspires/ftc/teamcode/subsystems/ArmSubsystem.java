package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

@Config
public class ArmSubsystem extends SubsystemBase {

    private final SimpleServo arm;

    public static double layerOneAngle = 30;
    public static double layerTwoAndThreeAngle = 60;
    public static double deltaAngle = 5;
    public static double defaultAngle = 90;

    public ArmSubsystem(SimpleServo arm){
        this.arm = arm;
        // returnDefault();  // this runs on init not start
    }

    public void layerOne (){
        arm.turnToAngle(layerOneAngle);
    }

    public void layerTwoAndThree (){
        arm.turnToAngle(layerTwoAndThreeAngle);
    }

    public void changeAnglePos (){
        arm.rotateByAngle(Math.abs(deltaAngle));
    }

    public void changeAngleNeg (){
        arm.rotateByAngle(-Math.abs(deltaAngle));
    }

    public void returnDefault (){
        arm.turnToAngle(defaultAngle);
    }
}
