package org.firstinspires.ftc.teamcode.tele;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.CRServo;


import org.firstinspires.ftc.teamcode.commands.DuckSpinnerCommand;
import org.firstinspires.ftc.teamcode.subsystems.DuckSpinnerSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Duck Spinner Test")
public class DuckSpinnerTest extends CommandOpMode {

    private CRServo duckSpinner;
    private GamepadEx driverController;
    private DuckSpinnerSubsystem duckSpinnerSubsystem;
    private DuckSpinnerCommand duckSpinnerCommandCW, duckSpinnerCommandCCW;

    @Override
    public void initialize() {
        duckSpinner = new CRServo(hardwareMap, "duckSpinner");

        duckSpinnerSubsystem = new DuckSpinnerSubsystem(duckSpinner);
        duckSpinnerCommandCW = new DuckSpinnerCommand(duckSpinnerSubsystem, -1);
        duckSpinnerCommandCCW = new DuckSpinnerCommand(duckSpinnerSubsystem, 1);

        driverController = new GamepadEx(gamepad1);
        GamepadButton buttonB = new GamepadButton(
                driverController, GamepadKeys.Button.B
        );
        buttonB.whileHeld(duckSpinnerCommandCW);

        GamepadButton buttonY = new GamepadButton(
                driverController, GamepadKeys.Button.Y
        );
        buttonY.whileHeld(duckSpinnerCommandCCW);
        
        schedule(duckSpinnerCommandCCW, duckSpinnerCommandCW);
        register(duckSpinnerSubsystem);
    }
}
