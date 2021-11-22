package org.firstinspires.ftc.teamcode.auto.trajectory;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoal;

import static java.lang.Thread.sleep;

@Config
public class WobbleOneTrajectory{
    public static double start_x = -56;
    public static double start_y = -48;
    public static double zone_b_forward = 94;
    public static double zone_b_strafe = 12;
    public static double third_x = -36;
    public static double third_y = -45;
    public static double third_h = -90;
    public static double forth_x = 38;
    public static double forth_y = -36;
    public static double forth_h = 0;
    public static double fifth_back = 20;
    private Servo grabber;
    private Servo arm;
    private WobbleGoal wg;

    private Pose2d startPose = new Pose2d(start_x, start_y, Math.toRadians(0));
    private SampleMecanumDrive drive;
    private Trajectory traj_1;
    private Trajectory traj_2;
    private Trajectory traj_3;
    private Trajectory traj_4;
    private Trajectory traj_5;
    private Trajectory traj_end;

    public WobbleOneTrajectory(SampleMecanumDrive drive, HardwareMap hardwareMap){
        this.drive = drive;
        this.grabber = hardwareMap.servo.get("grabber");
        this.arm = hardwareMap.servo.get("arm");
        this.wg = new WobbleGoal(this.grabber, this.arm);

        this.drive.setPoseEstimate(startPose);

        traj_1 = this.drive.trajectoryBuilder(startPose)
                .forward(zone_b_forward)
                .build();

        traj_2 = this.drive.trajectoryBuilder(traj_1.end())
                .strafeLeft(zone_b_strafe)
                .build();

        traj_3 = this.drive.trajectoryBuilder(traj_2.end())
                .splineTo(new Vector2d(third_x, third_y), Math.toRadians(third_h))
                .build();

        traj_4 = this.drive.trajectoryBuilder(traj_3.end())
                .splineTo(new Vector2d(forth_x, forth_y), Math.toRadians(forth_h))
                .build();

        traj_5 = this.drive.trajectoryBuilder(traj_4.end())
                .back(fifth_back)
                .build();

        traj_end = this.drive.trajectoryBuilder(traj_1.end())
                .strafeRight(24)
                .back(20)
                .build();
    }

    public void run(){
        try {
            wg.grab();
            sleep(500);
            drive.followTrajectory(traj_1);
            sleep(500);
            drive.followTrajectory(traj_2);
            sleep(500);
            wg.release();
            drive.followTrajectory(traj_end);
//            drive.followTrajectory(traj_3);
//            sleep(500);
//            drive.followTrajectory(traj_4);
//            sleep(500);
//            drive.followTrajectory(traj_5);
        }catch (Exception e){
            System.out.println("WobbleZeroTrajectory");
            System.out.println(e.fillInStackTrace());
        }
    }

}