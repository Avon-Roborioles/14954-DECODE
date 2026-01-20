package org.firstinspires.ftc.teamcode.OpModes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


@TeleOp(name = "LauncherPIDTune", group = "prototype")
public class LauncherPIDTune extends OpMode {
    public DcMotorEx launcher1;
    public DcMotorEx launcher2;
    public double maxSpeed = 1800;
    public double minSpeed = 1000;
    public double P = 0;
    public double F = 0;
    public double[] stepSizes = {10.0, 1.0, 0.1, 0.001, 0.0001};
    public int stepIndex = 1;
    public double targetSpeed = maxSpeed;
    @Override
    public void init(){
        launcher1 = hardwareMap.get(DcMotorEx.class, "launchMotor");
        launcher2 = hardwareMap.get(DcMotorEx.class, "launchMotor2");
        launcher1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher2.setDirection(DcMotorSimple.Direction.REVERSE);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        launcher1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        launcher2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

    }

    public void loop(){

        if(gamepad1.aWasPressed()){
            if (targetSpeed == maxSpeed){
                targetSpeed = minSpeed;
            } else if (targetSpeed == minSpeed){
                targetSpeed = maxSpeed;
            }
        }
        if (gamepad1.bWasPressed()){
            stepIndex = (stepIndex + 1) % stepSizes.length;
        }
        if (gamepad1.dpadLeftWasPressed()){
            F += stepSizes[stepIndex];
        } else if (gamepad1.dpadRightWasPressed()){
            F -= stepSizes[stepIndex];
        }

        if (gamepad1.dpadUpWasPressed()){
            P += stepSizes[stepIndex];
        } else if (gamepad1.dpadDownWasPressed()){
            P -= stepSizes[stepIndex];
        }

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        launcher1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        launcher2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        launcher1.setVelocity(targetSpeed);
        launcher2.setVelocity(targetSpeed);

        telemetry.addData("TargetSpeed ", targetSpeed);
        telemetry.addData("V1 ", launcher1.getVelocity());
        telemetry.addData("V2 ", launcher2.getVelocity());
        telemetry.addData("F ", F);
        telemetry.addData("P ", P);
        telemetry.addData("stepSize ", stepSizes[stepIndex]);

    }
}
