package org.firstinspires.ftc.teamcode.tele;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;

import org.firstinspires.ftc.teamcode.subsystems.DuckSpinnerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LifterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SliderSubsystem;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import org.firstinspires.ftc.teamcode.commands.RobotCentricDriveComand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystemRobotCentric;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Duck Spinner Test")
public class SubsystemTest extends CommandOpMode {

    private CRServo duckSpinner, slider;
    private SimpleServo arm, grabber, encoderLifter;
    private DuckSpinnerSubsystem duckSpinnerSubsystem;
    private SliderSubsystem sliderSubsystem;
    private ArmSubsystem armSubsystem;
    private GrabberSubsystem grabberSubsystem;
    private MotorEx m_frontLeft, m_frontRight, m_bottomLeft, m_bottomRight, m_intake;
    private DriveSubsystemRobotCentric driveSubsys;
    private RobotCentricDriveComand driveCommand;
    private IntakeSubsystem intakeSubsystem;
    private LifterSubsystem lifterSubsystem;
    private GamepadEx gp1, gp2;

    @Override
    public void initialize() {
        /* define hardware */
        duckSpinner = new CRServo(hardwareMap, "duckSpinner");
        slider = new CRServo(hardwareMap, "slider");
        encoderLifter = new SimpleServo(hardwareMap, "encoderLifter", 0, 270);
        arm = new SimpleServo(hardwareMap, "arm", 0, 270);
        grabber = new SimpleServo(hardwareMap, "grabber", 0, 270);
        m_frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435);
        m_frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_435);
        m_bottomLeft = new MotorEx(hardwareMap, "bottomLeft", Motor.GoBILDA.RPM_435);
        m_bottomRight = new MotorEx(hardwareMap, "bottomRight", Motor.GoBILDA.RPM_435);
        m_intake = new MotorEx(hardwareMap, "intake", Motor.GoBILDA.RPM_435);

        m_frontRight.setInverted(true); // correct
        m_frontLeft.setInverted(true);  // correct
        m_intake.setInverted(true);
        m_bottomRight.setInverted(true); // correct
        m_bottomLeft.setInverted(true); // correct


        /* define subsystems */
        sliderSubsystem = new SliderSubsystem(slider);
        armSubsystem = new ArmSubsystem(arm);
        grabberSubsystem = new GrabberSubsystem(grabber);
        duckSpinnerSubsystem = new DuckSpinnerSubsystem(duckSpinner);
        lifterSubsystem = new LifterSubsystem(encoderLifter);
        driveSubsys = new DriveSubsystemRobotCentric(
                m_frontLeft,
                m_frontRight,
                m_bottomLeft,
                m_bottomRight
        );
        intakeSubsystem = new IntakeSubsystem(m_intake);

        /* define buttons */
        gp1 = new GamepadEx(gamepad1);
        GamepadButton buttonB = new GamepadButton(gp1, GamepadKeys.Button.B);
        GamepadButton buttonY = new GamepadButton(gp1, GamepadKeys.Button.Y);
        GamepadButton buttonX = new GamepadButton(gp1, GamepadKeys.Button.X);
        GamepadButton buttonA = new GamepadButton(gp1, GamepadKeys.Button.A);
        GamepadButton bumperL = new GamepadButton(gp1, GamepadKeys.Button.LEFT_BUMPER);
        GamepadButton bumperR = new GamepadButton(gp1, GamepadKeys.Button.RIGHT_BUMPER);

        gp2 = new GamepadEx(gamepad2);
        GamepadButton buttonXTwo = new GamepadButton(gp2, GamepadKeys.Button.X);
        GamepadButton buttonYTwo = new GamepadButton(gp2, GamepadKeys.Button.Y);
        GamepadButton buttonBTwo = new GamepadButton(gp2, GamepadKeys.Button.B);
        GamepadButton bumperLTwo = new GamepadButton(gp2, GamepadKeys.Button.LEFT_BUMPER);
        GamepadButton bumperRTwo = new GamepadButton(gp2, GamepadKeys.Button.RIGHT_BUMPER);


        /*
         * Dont need to create command file for simple tasks
         *
         * RunCommand --> for perpetual commands (commands that go on for indefinite amount of time like whileHeld commands)
         * InstantCommand --> for one off commands (commands that happen on button presses or releases)
         */

        /* duck spinner */
        buttonB
            .whileHeld(new RunCommand(duckSpinnerSubsystem::spinCW, duckSpinnerSubsystem))
            .whenReleased(new InstantCommand(duckSpinnerSubsystem::stopSpinning, duckSpinnerSubsystem));
        buttonY
            .whileHeld(new RunCommand(duckSpinnerSubsystem::spinCCW, duckSpinnerSubsystem))
            .whenReleased(new InstantCommand(duckSpinnerSubsystem::stopSpinning, duckSpinnerSubsystem));

        /* slider */
        bumperLTwo
                .whileHeld(new RunCommand(sliderSubsystem::retract, sliderSubsystem))
                .whenReleased(new InstantCommand(sliderSubsystem::stopSpinning, sliderSubsystem));
        bumperRTwo
                .whileHeld(new RunCommand(sliderSubsystem::extend, sliderSubsystem))
                .whenReleased(new InstantCommand(sliderSubsystem::stopSpinning, sliderSubsystem));
        buttonBTwo
                .whenPressed(new SequentialCommandGroup(
                        new InstantCommand(armSubsystem::layerTwoAndThree, armSubsystem),
                        new InstantCommand(grabberSubsystem::intakePos, grabberSubsystem)));
        buttonYTwo
                .whenPressed(new SequentialCommandGroup(
                        new InstantCommand(armSubsystem::returnDefault, armSubsystem),
                        new InstantCommand(grabberSubsystem::grab, grabberSubsystem)));

        /* grabber */
        buttonXTwo
                .whileHeld(new InstantCommand(grabberSubsystem::grab, grabberSubsystem))
                .whenReleased(new InstantCommand(grabberSubsystem::release, grabberSubsystem));

        /* drive train */
        driveCommand = new RobotCentricDriveComand(
                driveSubsys,
                () -> gp1.getLeftX(),
                () -> gp1.getLeftY(),
                () -> -gp1.getRightX()
        );

        /* intake */
        buttonX
                .whileHeld(new InstantCommand(intakeSubsystem::turnOn, intakeSubsystem))
                .whenReleased(new InstantCommand(intakeSubsystem::turnOff, intakeSubsystem));


        register(armSubsystem, duckSpinnerSubsystem, sliderSubsystem, grabberSubsystem, driveSubsys, intakeSubsystem);
        schedule(driveCommand, new InstantCommand(lifterSubsystem::lift, lifterSubsystem));
    }
}
