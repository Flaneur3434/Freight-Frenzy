package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.auto.trajectory.WobbleFourTrajectory;
import org.firstinspires.ftc.teamcode.auto.trajectory.WobbleOneTrajectory;
import org.firstinspires.ftc.teamcode.auto.trajectory.WobbleZeroTrajectory;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.VisionSubsystem;
import org.firstinspires.ftc.teamcode.vision.stack_pos;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Auto using FTCLib")
public class Autonomous extends LinearOpMode {
    private FtcDashboard dashboard = FtcDashboard.getInstance(); // Dashboard
    private Telemetry dashboardTelemetry = dashboard.getTelemetry(); // Dashboard Telemetry

    @Override
    public void runOpMode() throws InterruptedException{

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap); // Robot

        VisionSubsystem vision = new VisionSubsystem(dashboardTelemetry); // Camera System

        vision.initCamera(); // Camera starts recording, pipeline also starts

        vision.initDashBoardCamera(); // Camera streams to dashboard

        while (!isStarted() && !isStopRequested()) {
            vision.getCameraData(); // Stream telemetry data to dashboard
        }

        stack_pos stack_pos = vision.getStack(); //Get the stack_pos (NONE, ONE, FOUR)

        vision.stopStream(); // Does what it says

        switch (stack_pos) {
            case NONE:
                WobbleZeroTrajectory wzt = new WobbleZeroTrajectory(drive, hardwareMap);
                wzt.run();
                break;
            case ONE:
                WobbleOneTrajectory wot = new WobbleOneTrajectory(drive, hardwareMap);
                wot.run();
                break;
            case FOUR:
                WobbleFourTrajectory wft = new WobbleFourTrajectory(drive, hardwareMap);
                wft.run();
                break;
            default:
                dashboardTelemetry.addData("Status", "shit broke");
                break;
        }

        // No turning back now ...
        if (isStopRequested()) return;
    }
}
