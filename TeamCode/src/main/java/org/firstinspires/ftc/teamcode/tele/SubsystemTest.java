package org.firstinspires.ftc.teamcode.tele;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinnerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SliderSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Duck Spinner Test")
public class SubsystemTest extends CommandOpMode {

    private CRServo duckSpinner, slider;
    private SimpleServo arm, grabber;
    private GamepadEx gp1, gp2;
    private DuckSpinnerSubsystem duckSpinnerSubsystem;
    private SliderSubsystem sliderSubsystem;
    private ArmSubsystem armSubsystem;
    private GrabberSubsystem grabberSubsystem;

    @Override
    public void initialize() {
        /* define hardware */
        duckSpinner = new CRServo(hardwareMap, "duckSpinner");
        slider = new CRServo(hardwareMap, "slider");
        arm = new SimpleServo(hardwareMap, "arm", -45, 45);
        grabber = new SimpleServo(hardwareMap, "grabber", -180, 180);

        /* define subsystems */
        sliderSubsystem = new SliderSubsystem(slider);
        armSubsystem = new ArmSubsystem(arm);
        grabberSubsystem = new GrabberSubsystem(grabber);
        duckSpinnerSubsystem = new DuckSpinnerSubsystem(duckSpinner);

        /* define buttons */
        gp1 = new GamepadEx(gamepad1);
        GamepadButton buttonB = new GamepadButton(gp1, GamepadKeys.Button.B);
        GamepadButton buttonY = new GamepadButton(gp1, GamepadKeys.Button.Y);
        GamepadButton lbumper = new GamepadButton(gp1, GamepadKeys.Button.LEFT_BUMPER);
        GamepadButton rbumper = new GamepadButton(gp1, GamepadKeys.Button.RIGHT_BUMPER);

        gp2 = new GamepadEx(gamepad2);
        GamepadButton buttonXtwo = new GamepadButton(gp2, GamepadKeys.Button.X);
        GamepadButton buttonYtwo = new GamepadButton(gp2, GamepadKeys.Button.Y);
        GamepadButton buttonBtwo = new GamepadButton(gp2, GamepadKeys.Button.B);
        GamepadButton buttonAtwo = new GamepadButton(gp2, GamepadKeys.Button.A);


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
        lbumper
                .whileHeld(new RunCommand(sliderSubsystem::retract, sliderSubsystem))
                .whenReleased(new InstantCommand(sliderSubsystem::stopSpinning, sliderSubsystem));
        rbumper
                .whileHeld(new RunCommand(sliderSubsystem::extend, sliderSubsystem))
                .whenReleased(new InstantCommand(sliderSubsystem::stopSpinning, sliderSubsystem));

        /* arm */
        buttonXtwo
                .whenPressed(new InstantCommand(armSubsystem::layerOne, armSubsystem))
                .whenReleased(new InstantCommand(armSubsystem::returnDefault, armSubsystem));
        buttonYtwo
                .whenPressed(new InstantCommand(armSubsystem::layerTwo, armSubsystem))
                .whenReleased(new InstantCommand(armSubsystem::returnDefault, armSubsystem));
        buttonBtwo
                .whenPressed(new InstantCommand(armSubsystem::layerThree, armSubsystem))
                .whenReleased(new InstantCommand(armSubsystem::returnDefault, armSubsystem));
        buttonAtwo
                .whenPressed(new InstantCommand(grabberSubsystem::grab, grabberSubsystem))
                .whenReleased(new InstantCommand(grabberSubsystem::release, grabberSubsystem));

        /* grabber */

        register(armSubsystem, duckSpinnerSubsystem, sliderSubsystem, grabberSubsystem);
    }
}
