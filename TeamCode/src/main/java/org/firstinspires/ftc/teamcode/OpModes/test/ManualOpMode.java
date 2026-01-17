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

import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.function.Supplier;


@TeleOp(name = "Manual Control", group = "prototype")
public class ManualOpMode extends LinearOpMode {

    public DcMotorEx shooterMotor = null;
    private double shooterV;
    public Servo shooterAngle = null;
    private Servo turn;
    private Limelight3A limelight;
    private LLResult result;
    private Follower follower;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private AutoDriveSubsystem autoDriveSubsystem;
    private DigitalChannel fSensor, mSensor, bSensor;

    // intake variables
    private CRServo frontIntakeServo;
    private CRServo frontPassServo;
    private CRServo backIntakeServo;
    private CRServo backPassServo;

    private IntakeSubsystem intakeSubsystem;

    @Override
    public void runOpMode() {
        final double motorIncrement = 50;
        double newShooterPower = 0.0;
        shooterV = 0.0;
        double launchServoAngle = 0.0;
        final double servoAngleChange = 0.01;
        double newServoAngle = 0.0;
        double turnAngle = 0.6;
        double newTurnAngle = 0.0;
        double turnAngleChange = 0.01;

        shooterMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");
        shooterAngle = hardwareMap.get(Servo.class, "launchAngle");

        turn = hardwareMap.get(Servo.class, "turnServo");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        // intake
        frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntake");
        frontPassServo = hardwareMap.get(CRServo.class, "frontPass");
        backIntakeServo = hardwareMap.get(CRServo.class, "backIntake");
        backPassServo = hardwareMap.get(CRServo.class, "backPass");

        intakeSubsystem = new IntakeSubsystem(frontIntakeServo, frontPassServo, backIntakeServo, backPassServo);
        limelight.pipelineSwitch(2);
        limelight.start();
        waitForStart();

        shooterAngle.setDirection(Servo.Direction.FORWARD);
        turnAngle = turn.getPosition();
        // distance Sensors
        fSensor = hardwareMap.get(DigitalChannel.class, "fSensor");
        mSensor = hardwareMap.get(DigitalChannel.class, "mSensor");
        bSensor = hardwareMap.get(DigitalChannel.class, "bSensor");
        fSensor.setMode(DigitalChannel.Mode.INPUT);
        mSensor.setMode(DigitalChannel.Mode.INPUT);
        bSensor.setMode(DigitalChannel.Mode.INPUT);
    // drive system
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(0, 0, PI));
        follower.setPose(new Pose(0, 0, PI));
        pathChain = () -> follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(45, 98, PI))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();
        autoDriveSubsystem = new AutoDriveSubsystem(follower, telemetry);
        follower.startTeleopDrive();

    while (opModeIsActive()) {

        // launch power
            if (gamepad2.dpadUpWasPressed()) {
                newShooterPower += motorIncrement;
            } else if (gamepad2.dpadDownWasPressed()) {
                newShooterPower -= motorIncrement;
            }
            shooterV = newShooterPower;
            shooterMotor.setVelocity(shooterV);

        // carrot angle
            if (gamepad2.dpadRightWasPressed()) {
                newServoAngle += servoAngleChange;
            } else if (gamepad2.dpadLeftWasPressed()) {
                newServoAngle -= servoAngleChange;
            }

        //  turntable
            if (gamepad2.rightBumperWasPressed()) {
                newTurnAngle += turnAngleChange;
            } else if (gamepad2.leftBumperWasPressed()) {
                newTurnAngle -= turnAngleChange;
            }
            launchServoAngle = newServoAngle;
            shooterAngle.setPosition(launchServoAngle);
            if ((newTurnAngle > 0.0) && (newTurnAngle < 1.0)) {
                turnAngle = newTurnAngle;
                turn.setPosition(turnAngle);
            }
        // intake controls

        // heading reset
            if (gamepad1.xWasPressed()){
                follower.setPose(new Pose(0,0,PI));
            }
        // drive
            follower.update();
            telemetryM.update();
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    false);

        // intake
            intakeSubsystem.proportionalLaunch(gamepad2.left_stick_y);
            if (gamepad1.aWasPressed()) {
                if (frontIntakeServo.getPower() == 0){
                    intakeSubsystem.manLaunch();
                } else {
                    intakeSubsystem.stopAll();
                }
            }
            if (gamepad1.bWasPressed()) {
                if (frontIntakeServo.getPower() == 0){
                    intakeSubsystem.Puke();
                } else {
                    intakeSubsystem.stopAll();
                }
            }

        // telemetry messages
            telemetry.addData("LauncherSpeed", "%.2f", shooterV);
            telemetry.addData("CarrotPosition", "%.3f", launchServoAngle);
            telemetry.addData("turnAngle", "%.3f", turnAngle);
            telemetry.addData("getDistance", getDistance());
            telemetry.addData("init complete", "init done");
            telemetry.addData("front/middle/back", fSensor.getState() + "/" + mSensor.getState() + "/" + bSensor.getState());
            telemetry.update();
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
