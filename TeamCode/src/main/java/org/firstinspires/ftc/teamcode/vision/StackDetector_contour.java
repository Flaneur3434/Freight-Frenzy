package org.firstinspires.ftc.teamcode.vision;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Config
public class StackDetector_contour extends OpenCvPipeline {
    private final Scalar lowHSV = new Scalar(10, 130, 85); //Hue, Saturation, Value
    private final Scalar highHSV = new Scalar(180, 255, 255);
    private int stackHeight;
    private int stackWidth;
    Mat threshHoldedOrange = new Mat();
    private stack_pos stackPos = stack_pos.NONE;

    // Make it easier to see the stack
    void ThreshHoldingOrange(Mat input, Mat output) {
        Imgproc.blur(input, output, new Size(9, 9));
        Imgproc.cvtColor(output, output, Imgproc.COLOR_BGR2HSV);
        Core.inRange(output, lowHSV, highHSV, threshHoldedOrange);
    }

    @Override
    public void init(Mat firstFrame) {
        ThreshHoldingOrange(firstFrame, threshHoldedOrange);
    }

    @Override
    public final Mat processFrame(Mat input) {
        if (input.empty()) return input;

        ThreshHoldingOrange(input, threshHoldedOrange);
        try{
            Rect found_stack = getStack(threshHoldedOrange, input);
            stackHeight = found_stack.height;
            stackWidth = found_stack.width;
            getStackPos(found_stack.width);
        }catch (Exception e){
            return input;
        }

        return input;
    }

    // Get the Orange Object(the stack hopefully)
    private Rect getStack(Mat workingMatrix, Mat output) {
        List<MatOfPoint> contours = new ArrayList<>(); //List of contours stored as a list of points
        Imgproc.findContours(workingMatrix, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        Rect[] boundRect_arr = new Rect[contours.size()]; // Store the boundRect

        // Get the shapes
        for (int i = 0; i < contours.size(); i++) {
            boundRect_arr[i] = Imgproc.boundingRect(new MatOfPoint(contours.get(i)));
        }

        Rect max = boundRect_arr[0];

        for (int i = 0; i < boundRect_arr.length; i++) {
            if (boundRect_arr[i].area() > max.area()) {
                max = boundRect_arr[i];
            }
        }

        Imgproc.rectangle(output, max.tl(), max.br(), new Scalar(0, 204, 0));
        return max;
    }

    // Determine the stackPos
    public void getStackPos(int stackHeight) {
        if (stackHeight < 14) {
            stackPos = stack_pos.NONE;
        } else if (stackHeight > 14 && stackHeight < 40) {
            stackPos = stack_pos.ONE;
        } else if (stackHeight > 60) {
            stackPos = stack_pos.FOUR;
        }
    }

    public stack_pos getInfo() {
        return stackPos;

    }

    public double getHeight(){
        return stackHeight;
    }

    public double getwidth(){
        return stackWidth;
    }
}