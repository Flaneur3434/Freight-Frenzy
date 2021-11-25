package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;

public class DuckSpinnerSubsystem extends SubsystemBase {

    private final CRServo spinner;

    public DuckSpinnerSubsystem (CRServo spinner){
        this.spinner = spinner;
    }

    public void spinCW(){
        spinner.set(1);
    }

    public void spinCCW(){
        spinner.setInverted(true);
        spinner.set(-1);
    }

    public void stopSpinning ()
    {
        spinner.stop();
    }


}
