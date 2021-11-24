package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
//import com.qualcomm.robotcore.hardware.Servo;



import static java.lang.Thread.sleep;

public class DuckSpinnerSubsystem extends SubsystemBase {

    private final SimpleServo spinner;

    public DuckSpinnerSubsystem (SimpleServo spinner){
        this.spinner = spinner;
    }

    public void spin(int i){
        spinner.rotateBy(i);
    }



}
