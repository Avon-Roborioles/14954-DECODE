package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

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

    public void setPipeline(int pipeline) {
        limelight.pipelineSwitch(pipeline);
    }

    /**
     * Gets the horizontal offset (tx) from the Limelight.
     * It now fetches the latest result internally.
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
     * @return The LLResult object.
     */
    public LLResult getResult() {
        return limelight.getLatestResult();
    }

    // other methods from your class can be included here
}
