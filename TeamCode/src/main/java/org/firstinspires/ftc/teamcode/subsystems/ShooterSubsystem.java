package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

public class ShooterSubsystem extends SubsystemBase {
    private final double kP = 30.00;
    private final double kI = 0.00;
    private final double kD = 7.00;
    private final double kS = 0.10;
    private final double kV = 0.0003;
    private MotorEx m_motor;
    private MotorEx m_reverse_motor;
    private MotorEx m_intake;

    public ShooterSubsystem(MotorEx m_motor_1, MotorEx m_reverse_2, MotorEx m_intake) {
        this.m_motor = m_motor_1;
        this.m_reverse_motor = m_reverse_2;
        this.m_intake = m_intake;

        this.m_motor.setRunMode(Motor.RunMode.VelocityControl);
        this.m_reverse_motor.setRunMode(Motor.RunMode.VelocityControl);
        this.m_intake.setRunMode(Motor.RunMode.RawPower);
        this.m_intake.setInverted(true);

        this.m_motor.setVeloCoefficients(kP, kI, kD);
        this.m_reverse_motor.setVeloCoefficients(kP, kI, kD);

        this.m_motor.setFeedforwardCoefficients(kS, kV);
        this.m_reverse_motor.setFeedforwardCoefficients(kS, kV);
    }

    public void shootAt(double p) {
        m_motor.set(p);
        m_reverse_motor.set(p);
    }

    public void runIntake() {
        m_intake.set(1);
    }

    public void stop() {
        m_motor.stopMotor();
        m_reverse_motor.stopMotor();
        m_intake.stopMotor();
    }
}
