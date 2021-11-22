package org.firstinspires.ftc.teamcode.auto.trajectory;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoal;

import static java.lang.Thread.sleep;

@Config
public class WobbleZeroTrajectory{
    public static double start_x = -56;
    public static double start_y = -48;
    public static double zone_a_forward = 70;
    public static double second_x = -36;
    public static double second_y = -45;
    public static double second_h = -90;
    public static double third_x = 20;
    public static double third_y = -48;
    public static double third_h = 0;


    private Pose2d startPose = new Pose2d(start_x, start_y, Math.toRadians(0));
    private SampleMecanumDrive drive;
    private Trajectory traj_1;
    private Trajectory traj_2;
    private Trajectory traj_3;
    private Servo grabber;
    private Servo arm;
    private WobbleGoal wg;

    public WobbleZeroTrajectory(SampleMecanumDrive drive, HardwareMap hardwareMap){
        this.grabber = hardwareMap.servo.get("grabber");
        this.arm = hardwareMap.servo.get("arm");
        this.wg = new WobbleGoal(this.grabber, this.arm);
        this.drive = drive;

        this.drive.setPoseEstimate(startPose);

        traj_1 = this.drive.trajectoryBuilder(startPose)
                .forward(zone_a_forward)
                .build();

        traj_2 = this.drive.trajectoryBuilder(traj_1.end())
                .splineTo(new Vector2d(second_x, second_y), Math.toRadians(second_h))
                .build();

        traj_3 = this.drive.trajectoryBuilder(traj_2.end())
                .splineTo(new Vector2d(third_x, third_y), Math.toRadians(third_h))
                .build();

    }

    public void run(){
        try {
            wg.grab();
            sleep(2000);
            drive.followTrajectory(traj_1);
            wg.release();
            sleep(2000);
//            drive.followTrajectory(traj_2);
//            sleep(2000);
//            drive.followTrajectory(traj_3);
//             wg.release();
        }catch (Exception e){
            System.out.println("WobbleZeroTrajectory");
            System.out.println(e.fillInStackTrace());
        }
    }

}
