package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public class LimeLightSubsystem extends SubsystemBase {
    private Limelight3A limelight;
    // It's better to fetch the result inside the methods that need it
    // to ensure you're always working with the latest data.
    // private LLResult result;

    public LimeLightSubsystem(Limelight3A limelight) {
        this.limelight = limelight;
    }

    public void start() {
        limelight.start();
    }
    public void stop(){limelight.stop();}

    public void setPipeline(int pipeline) {
        limelight.pipelineSwitch(pipeline);
    }

    /**
     * Gets the horizontal offset (tx) from the Limelight.
     * It now fetches the latest result internally.
     *
     * @return The tx value, or 0 if no valid target is seen.
     */
    public double getTx() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            return result.getTx();
        }
        // Return 0 if there's no valid result to prevent errors
        return 0;
    }

    /**
     * Gets the full latest result from the Limelight.
     *
     * @return The LLResult object.
     */
    public LLResult getResult() {
        return limelight.getLatestResult();
    }

    public double getDistance() {

        LLResult result = limelight.getLatestResult();
        double targetOffsetAngle_Vertical = result.getTy();

        double limelightMountAngleDegrees = 24.0;
        double limelightLensHeightInches = 11.5;
        double goalHeightInches = 28.5;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        return (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
    }


    public void getTelemetry(Telemetry telemetry) {
        LLStatus status = limelight.getStatus();
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            Pose3D botpose = result.getBotpose();
            telemetry.addData("tx", result.getTx());
            telemetry.addData("ty", result.getTy());
            telemetry.addData("Botpose", botpose.toString());
            telemetry.addData("tags", result.getFiducialResults());
            telemetry.addData("distance", getDistance());
            telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                    status.getTemp(), status.getCpu(), (int) status.getFps());
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
            }
        }

    }
}
