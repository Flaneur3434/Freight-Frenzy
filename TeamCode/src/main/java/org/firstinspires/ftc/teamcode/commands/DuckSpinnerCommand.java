package org.firstinspires.ftc.teamcode.commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinnerSubsystem;

public class DuckSpinnerCommand extends CommandBase {

    private final DuckSpinnerSubsystem spinner;
    int spindir = 0;

    public DuckSpinnerCommand (DuckSpinnerSubsystem spinner, int spindir){
        this.spinner = spinner;

        this.spinner.stopSpinning();

        this.spindir = spindir;
        addRequirements(this.spinner);
    }

    @Override
    public void initialize()
    {
        spinner.spin(0.01);
    }

    @Override
    public void execute(){
        spinner.spin(spindir);
    }

    @Override
    public void end(boolean interrupted) {
        spinner.stopSpinning();
    }
}
