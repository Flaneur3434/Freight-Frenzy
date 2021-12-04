package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;

public class SliderSubsystem extends SubsystemBase {

    private final CRServo slider;

    public SliderSubsystem (CRServo slider){
        this.slider = slider;
    }


    //test extract and extend later.
    public void extend(){
        slider.set(1);
    }

    public void retract(){
//        slider.setInverted(true);
        slider.set(-1);
    }

    public void stopSpinning ()
    {
        slider.stop();
    }


}
