package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;

public class FlapSubsystem extends SubsystemBase {
    private SimpleServo flap;

    public FlapSubsystem(SimpleServo flap){
        this.flap = flap;
    }

    public void setPos(int degrees){
        flap.rotateDegrees(degrees);
    }

    public void reset(){
        // I dont know if this is correct
        flap.rotateDegrees(0);
    }
}
