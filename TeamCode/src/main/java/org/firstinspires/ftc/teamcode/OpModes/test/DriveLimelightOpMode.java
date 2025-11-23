package org.firstinspires.ftc.teamcode.OpModes.test;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import org.firstinspires.ftc.teamcode.commands.teleop.DriveCommands.DriveCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;

import java.util.List;

@TeleOp
@Disabled

    public class DriveLimelightOpMode extends LinearOpMode {
        private Limelight3A limelight;
        private Servo servo;
        private double servoPos = 0.5;
        public static int tag;



        private DriveSubsystem driveSubsystem;
        private DriveCommand driveCommand;
        private LimeLightSubsystem limelightSubsystem;
        private LLResult result;

        @Override
        public void runOpMode() throws InterruptedException {
            // Hardware
            servo = hardwareMap.get(Servo.class, "swingArm");
            limelight = hardwareMap.get(Limelight3A.class, "limelight");

            // Motors for mecanum
            Motor frontLeft = new Motor(hardwareMap, "frontLeft");
            Motor frontRight = new Motor(hardwareMap, "frontRight");
            Motor backLeft = new Motor(hardwareMap, "backLeft");
            Motor backRight = new Motor(hardwareMap, "backRight");

            // Subsystem
            driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);

            // Command: map gamepad1 joysticks
            driveCommand = new DriveCommand(
                    driveSubsystem,
                    () -> gamepad1.left_stick_x,       // strafe
                    () -> -gamepad1.left_stick_y,      // forward (negated because up stick is -1)
                    () -> gamepad1.right_stick_x,      // turn
                    () -> 0.0,                         // heading (put IMU yaw here later)
                    false                              // field-centric off for now
            );


            telemetry.setMsTransmissionInterval(11);
            servo.setPosition(0.5);
           limelight.pipelineSwitch(4);
            limelight.start();

            // Schedule the drive command
            CommandScheduler.getInstance().schedule(driveCommand);



            waitForStart();

            while (opModeIsActive()) {
                // Run the FTCLib command scheduler
                CommandScheduler.getInstance().run();


                // ---- Limelight logic (servo adjustment) ----
                LLStatus status = limelight.getStatus();
                LLResult result = limelight.getLatestResult();
                if (result != null && result.isValid()) {
                    Pose3D botpose = result.getBotpose();
                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("Botpose", botpose.toString());
                    telemetry.addData("tags",result.getFiducialResults());
                    telemetry.addData("distance", getDistance());
                    telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                            status.getTemp(), status.getCpu(),(int)status.getFps());
                    List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
                    for (LLResultTypes.FiducialResult fr : fiducialResults) {
                        telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
                    }
                    for (LLResultTypes.FiducialResult fr : fiducialResults) {
                        if (fr.getFiducialId() == 21) {
                            tag = 21;
                            limelight.pipelineSwitch(3);
                        } else if (fr.getFiducialId() == 22) {
                            tag = 22;
                            limelight.pipelineSwitch(3);
                        } else if (fr.getFiducialId() == 23) {
                            tag = 23;
                            limelight.pipelineSwitch(3);
                        }
                    }
                    telemetry.addData("tag", tag);
                    telemetry.update();



                    if (result.getTx() != 0) {
                        servoPos -= 0.00006 * result.getTx();
                    }
                }

                if (servoPos > 0.65) {
                    servoPos = 0.65;
                } else if (servoPos < 0.35) {
                    servoPos = 0.35;
                }
                servo.setPosition(servoPos);
            }
            LLResult result = limelight.getLatestResult();
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();


        }

        int getTag(){
            return tag;
        }

        double getDistance() {
            LLResult result = limelight.getLatestResult();
            double targetOffsetAngle_Vertical = result.getTy();

            double limelightMountAngleDegrees = 24.0;
            double limelightLensHeightInches = 11.5;
            double goalHeightInches = 28.5;

            double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
            double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

            return (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
        }

        double getYawAprilTag() {
            LLResult result = limelight.getLatestResult();
            return result.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES);
        }
    }


