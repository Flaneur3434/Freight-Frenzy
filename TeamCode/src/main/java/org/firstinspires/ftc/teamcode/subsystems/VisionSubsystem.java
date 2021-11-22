package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.StackDetector_contour;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.teamcode.vision.stack_pos;
import org.openftc.easyopencv.OpenCvWebcam;

public class VisionSubsystem extends SubsystemBase {
    private OpenCvWebcam webcam;
    private StackDetector_contour pipeline;
    private Telemetry dashboardTelemetry;

    public VisionSubsystem(Telemetry dashboardTelemetry){
        this.dashboardTelemetry = dashboardTelemetry;
        pipeline = new StackDetector_contour();
    }

    public void initCamera(){
        int cameraMonitorViewId = hardwareMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory
                .getInstance()
                .createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(pipeline); // Adding detector to camera stream

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened() {
                //These numbers are not real, find out real ones
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

    public void initDashBoardCamera(){
        FtcDashboard.getInstance().startCameraStream(webcam, 10);
    }

    public void getCameraData(){
        dashboardTelemetry.addData("Frame Count", webcam.getFrameCount());
        dashboardTelemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
        dashboardTelemetry.addData("Total frame time ms", webcam.getTotalFrameTimeMs());
        dashboardTelemetry.addData("Pipeline time ms", webcam.getPipelineTimeMs());
        dashboardTelemetry.addData("Overhead time ms", webcam.getOverheadTimeMs());
        dashboardTelemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
        dashboardTelemetry.addData("Stack: ", pipeline.getInfo());
        dashboardTelemetry.update();
    }

    public void stopStream(){
        FtcDashboard.getInstance().stopCameraStream();
        webcam.stopStreaming();
    }

    public stack_pos getStack(){
        return pipeline.getInfo();
    }
}
