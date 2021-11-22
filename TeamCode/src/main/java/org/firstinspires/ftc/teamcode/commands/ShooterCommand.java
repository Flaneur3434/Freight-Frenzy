package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.FlapSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooter;
    private final FlapSubsystem m_flap;

    public ShooterCommand(ShooterSubsystem shooter, FlapSubsystem flap){
        this.m_shooter = shooter;
        this.m_flap = flap;
        addRequirements(this.m_shooter, this.m_flap);
    }
    @Override
    public void initialize(){
        m_flap.reset();
        m_shooter.runIntake();
        new WaitCommand(300);
    }

    @Override
    public void execute(){
        m_shooter.shootAt(0.7);
        new WaitCommand(100);
        m_flap.setPos(90); // I dont know if this is correct
        new WaitCommand(100);
    }

    @Override
    public void end(boolean interrupted){
        m_flap.reset();
        m_shooter.stop();
    }
}
