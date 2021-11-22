package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class IntakeSubsystem extends SubsystemBase {
    private MotorEx m_intake;

    public IntakeSubsystem(MotorEx intake_motor){
        this.m_intake = intake_motor;
        this.m_intake.setRunMode(Motor.RunMode.RawPower);
    }

    public void turnOn(){
        m_intake.set(1);
    }

    public void turnOff(){
        m_intake.stopMotor();
    }
}
