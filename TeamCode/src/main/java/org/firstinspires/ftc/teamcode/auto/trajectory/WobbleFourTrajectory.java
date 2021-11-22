package org.firstinspires.ftc.teamcode.auto.trajectory;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.camera.libuvc.api.UvcApiExposureControl;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoal;

import static java.lang.Thread.sleep;

@Config
public class WobbleFourTrajectory{
    public static double start_x = -56;
    public static double start_y = -48;
    public static double zone_c_forward = 115;
    public static double second_x = -30;
    public static double second_y = -24;
    public static double second_h = 90;
    public static double third_x = 56;
    public static double third_y = -48;
    public static double third_h = 0;
    public static double fourth_x = 32;
    public static double fourth_y = -48;
    public static double fourth_h = 0;
    private Servo grabber;
    private Servo arm;
    private WobbleGoal wg;


    private Pose2d startPose = new Pose2d(start_x, start_y, Math.toRadians(0));
    private SampleMecanumDrive drive;
    private Trajectory traj_1;
    private Trajectory traj_2;
    private Trajectory traj_3;
    private Trajectory traj_4;
    private Trajectory traj_end;

    public WobbleFourTrajectory(SampleMecanumDrive drive, HardwareMap hardwareMap){
        this.drive = drive;
        this.drive = drive;
        this.grabber = hardwareMap.servo.get("grabber");
        this.arm = hardwareMap.servo.get("arm");
        this.wg = new WobbleGoal(this.grabber, this.arm);

        this.drive.setPoseEstimate(startPose);

        traj_1 = this.drive.trajectoryBuilder(startPose)
                .forward(zone_c_forward)
                .build();

        traj_2 = this.drive.trajectoryBuilder(traj_1.end())
                .splineTo(new Vector2d(second_x, second_y), Math.toRadians(second_h))
                .build();

        traj_3 = this.drive.trajectoryBuilder(traj_2.end())
                .splineTo(new Vector2d(third_x, third_y), Math.toRadians(third_h))
                .build();

        traj_4 = this.drive.trajectoryBuilder(traj_3.end())
                .splineTo(new Vector2d(fourth_x, fourth_y), Math.toRadians(fourth_h))
                .build();

        traj_end = this.drive.trajectoryBuilder(traj_1.end())
                .back(40)
                .build();
    }

    public void run(){
        try {
            wg.grab();
            sleep(500);
            drive.followTrajectory(traj_1);
            sleep(500);
            wg.release();
            sleep(500);
            drive.followTrajectory(traj_end);
//            drive.followTrajectory(traj_2);
//            sleep(500);
//            drive.followTrajectory(traj_3);
//            sleep(500);
//            drive.followTrajectory(traj_4);
        }catch (Exception e){
            System.out.println("WobbleZeroTrajectory");
            System.out.println(e.fillInStackTrace());
        }
    }

}