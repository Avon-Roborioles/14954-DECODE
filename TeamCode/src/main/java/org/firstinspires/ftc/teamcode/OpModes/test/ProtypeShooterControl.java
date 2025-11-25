/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.OpModes.test;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Shooter control code", group = "prototype")
public class ProtypeShooterControl extends LinearOpMode {

    public DcMotorEx shooterMotor = null;
    private double shooterV;
    public Servo shooterAngle = null;
    private Servo turn;
    private Limelight3A limelight;
    private LLResult result;

    @Override
    public void runOpMode() {
        final double motorIncrement = 50;
        double newShooterPower = 0.0;
        shooterV = 0.0;
        double launchServoAngle = 0.0;
        final double servoAngleChange = 0.002;
        double newServoAngle = 0.0;

        double turnAngle = 0.6;
        double newTurnAngle = 0.0;
        double turnAngleChange = 0.01;


        shooterMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");


        shooterAngle = hardwareMap.get(Servo.class, "launchAngle");

        turn = hardwareMap.get(Servo.class, "turnServo");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.pipelineSwitch(2);
        limelight.start();
        waitForStart();

        shooterAngle.setDirection(Servo.Direction.REVERSE);
//        shooterAngle.setPosition(0.0);
        launchServoAngle = shooterAngle.getPosition();
        turnAngle = turn.getPosition();


        while (opModeIsActive()) {


            if (gamepad1.dpadUpWasPressed()) {
                newShooterPower += motorIncrement;
            } else if (gamepad1.dpadDownWasPressed()) {
                newShooterPower -= motorIncrement;
            }

            shooterV = newShooterPower;
            shooterMotor.setVelocity(shooterV);

            if (gamepad1.dpadRightWasPressed()) {
                newServoAngle += servoAngleChange;
            } else if (gamepad1.dpadLeftWasPressed()) {
                newServoAngle -= servoAngleChange;
            }
            if (gamepad2.dpadRightWasPressed()) {
                newTurnAngle += turnAngleChange;
            } else if (gamepad2.dpadLeftWasPressed()) {
                newTurnAngle -= turnAngleChange;
            }


//            if (gamepad1.a) {
//                shooterPower = 1.0;
//                shooterMotor.setPower(shooterPower);
//            } else if(){
//                shooterPower = 0.8;
//                shooterMotor.setPower(shooterPower);
//            }


            if ((newServoAngle > 0.0) && (newServoAngle < 1.0)) {
                launchServoAngle = newServoAngle;
                shooterAngle.setPosition(launchServoAngle);
            }
            if ((newTurnAngle > 0.0) && (newTurnAngle < 1.0)) {
                turnAngle = newTurnAngle;
                turn.setPosition(turnAngle);
            }


            telemetry.addData("shooterMotorSpeed", "%.2f", shooterV);
            telemetry.addData("shooterLaunchPosition", "%.3f", launchServoAngle);
            telemetry.addData("turnAngle", "%.3f", turnAngle);
            telemetry.addData("getDistance", getDistance());
            telemetry.update();

            LLResult result = limelight.getLatestResult();

        }
    }

    double getDistance() {
        LLResult result = limelight.getLatestResult();
        double targetOffsetAngle_Vertical = result.getTy();

        double limelightMountAngleDegrees = 20.0;
        double limelightLensHeightInches = 14.5;
        double goalHeightInches = 28.5;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        return (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

    }
}
