package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;


import static java.lang.Thread.sleep;

public class DuckSpinnerSubsystem extends SubsystemBase {

    private final CRServo spinner;

    public DuckSpinnerSubsystem (CRServo spinner){
        this.spinner = spinner;
    }

    public void spin(int i){
        if (i < 0)
            spinner.setInverted(true);
        else
            spinner.setInverted(false);

        spinner.set(Math.abs(i)); // Only from 0 ~ 1
    }



}
