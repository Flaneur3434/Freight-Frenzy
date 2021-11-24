package org.firstinspires.ftc.teamcode.tele;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.DuckSpinnerCommand;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinnerSubsystem;

public class DuckSpinnerTest extends CommandOpMode {

    private SimpleServo duckSpinner;
    private GamepadEx driverController;
    private DuckSpinnerSubsystem duckSpinnerSubsystem;
    private DuckSpinnerCommand duckSpinnerCommandCW, duckSpinnerCommandCCW;

    @Override
    public void initialize() {
        duckSpinner = new SimpleServo(hardwareMap, "duckSpinner", 0, 10000);
        duckSpinnerSubsystem = new DuckSpinnerSubsystem(duckSpinner);
        duckSpinnerCommandCW = new DuckSpinnerCommand(duckSpinnerSubsystem, -1);
        duckSpinnerCommandCCW = new DuckSpinnerCommand(duckSpinnerSubsystem, 1);

        driverController = new GamepadEx(gamepad1);
        GamepadButton buttonB = new GamepadButton(
                driverController, GamepadKeys.Button.B
        );
        buttonB.whileHeld(duckSpinnerCommandCW);

        driverController = new GamepadEx(gamepad1);
        GamepadButton buttonY = new GamepadButton(
                driverController, GamepadKeys.Button.Y
        );
        buttonY.whileHeld(duckSpinnerCommandCCW);
        
        schedule(duckSpinnerCommandCCW, duckSpinnerCommandCW);
        register(duckSpinnerSubsystem);
    }




}
