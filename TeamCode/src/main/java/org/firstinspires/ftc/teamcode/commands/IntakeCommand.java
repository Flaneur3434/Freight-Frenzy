package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem m_intake;

    public IntakeCommand(IntakeSubsystem intake){
        this.m_intake = intake;
        addRequirements(this.m_intake);
    }

    @Override
    public void execute(){
        m_intake.turnOn();
    }

    @Override
    public void end(boolean interrupted){
        m_intake.turnOff();
    }
}
