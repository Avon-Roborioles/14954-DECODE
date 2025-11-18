package org.firstinspires.ftc.teamcode.OpModes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class DistanceTest extends LinearOpMode {
    private DigitalChannel fSensor, mSensor, bSensor;
    private Telemetry telemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        fSensor = hardwareMap.get(DigitalChannel.class, "fSensor");
        mSensor = hardwareMap.get(DigitalChannel.class, "mSensor");
        bSensor = hardwareMap.get(DigitalChannel.class, "bSensor");

    waitForStart();

    while (opModeIsActive()){
        telemetry.addData("front (cm): ", fSensor.getState());
        telemetry.addData("middle (cm): ", mSensor.getState());
        telemetry.addData("back", bSensor.getState());
        telemetry.update();

    }

    }

}
