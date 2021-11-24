package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.DuckSpinnerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class DuckSpinnerCommand extends CommandBase {

    private final DuckSpinnerSubsystem spinner;
    int spindir = 0;

    public DuckSpinnerCommand (DuckSpinnerSubsystem spinner, int spindir){
        this.spinner = spinner;
        this.spindir = spindir;
        addRequirements(this.spinner);
    }

    @Override
    public void execute(){
        spinner.spin(spindir);
    }
}
