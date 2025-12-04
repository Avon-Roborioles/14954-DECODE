package org.firstinspires.ftc.teamcode.OpModes.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
@Disabled
public class DistanceTest extends LinearOpMode {
    private DigitalChannel fSensor, mSensor, bSensor;
    private Servo test;




    @Override
    public void runOpMode() {

        fSensor = hardwareMap.get(DigitalChannel.class, "fSensor");

        mSensor = hardwareMap.get(DigitalChannel.class, "mSensor");

        bSensor = hardwareMap.get(DigitalChannel.class, "bSensor");

        fSensor.setMode(DigitalChannel.Mode.INPUT);
        mSensor.setMode(DigitalChannel.Mode.INPUT);
        bSensor.setMode(DigitalChannel.Mode.INPUT);
        test = hardwareMap.get(Servo.class, "turnServo");
        test.setPosition(0.6);




    waitForStart();

    while (opModeIsActive() && !isStopRequested()){

        boolean stateHighF = fSensor.getState();
        boolean stateHighM = mSensor.getState();
        boolean stateHighB = bSensor.getState();

        boolean detectedF = stateHighF;
        boolean detectedM = stateHighM;
        boolean detectedB = stateHighB;

        if (detectedF) {
            telemetry.addLine( "Fdetected");
        } else {
            telemetry.addLine("Fnotdetected");
        }

        if (detectedM) {
            telemetry.addLine("Mdetected");
        } else {
            telemetry.addLine("Mnotdetected");
        }
        if (detectedB) {
            telemetry.addLine("Bdetected");
        } else {
            telemetry.addLine("Bnotdetected");
        }


        telemetry.addData("Servo pos", test.getPosition());

        telemetry.update();

    }

    }

}
