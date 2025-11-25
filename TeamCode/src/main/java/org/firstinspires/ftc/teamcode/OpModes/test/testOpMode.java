package org.firstinspires.ftc.teamcode.OpModes.test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.commands.teleop.DriveCommands.DriveCommand;


@TeleOp
public class testOpMode extends LinearOpMode {

    private CRServo frontIntake;
    private CRServo backIntake;
    private CRServo frontPass;
    private CRServo backPass;
    private Servo flipper;
    private DcMotor shooterMotor;
    private CRServo shooterServo;
    private SparkFunOTOS spark;
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DriveCommand drive;
    private DriveSubsystem driveSubsystem;
    private Limelight3A limelight;


    private Servo launchAngle; // 0 to .4
    private Servo turnServo;
    private double anglePos;
    private double servoPos = 0.5;


    @Override
    public void runOpMode() throws InterruptedException {
        turnServo = hardwareMap.get(Servo.class, "turnServo");
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        frontIntake = hardwareMap.get(CRServo.class, "frontIntake");
        backIntake = hardwareMap.get(CRServo.class, "backIntake");
        frontPass = hardwareMap.get(CRServo.class, "frontPass");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
//        Motor frontRight = new Motor(hardwareMap, "frontRight");
//        Motor backLeft = new Motor(hardwareMap, "backLeft");
//        Motor backRight = new Motor(hardwareMap, "backRight");
//        Motor frontLeft = new Motor(hardwareMap, "frontLeft");
        backPass = hardwareMap.get(CRServo.class, "backPass");
        frontPass = hardwareMap.get(CRServo.class, "frontPass");
        shooterMotor = hardwareMap.get(DcMotor.class, "launchMotor");
        shooterServo = hardwareMap.get(CRServo.class, "launchServo");
        //  spark = hardwareMap.get(SparkFunOTOS.class,"sparkfun");
        launchAngle = hardwareMap.get(Servo.class, "launchAngle");
        anglePos = 0.05;

        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);

//         Command: map gamepad1 joysticks
//        drive = new org.firstinspires.ftc.teamcode.commands.DriveCommand(
//                driveSubsystem,
//                () -> gamepad1.left_stick_x,       // strafe
//                () -> -gamepad1.left_stick_y,      // forward (negated because up stick is -1)
//                () -> gamepad1.right_stick_x,      // turn
//                () -> 0.0,                         // heading (put IMU yaw here later)
//                false                              // field-centric off for now
//        );

        //  CommandScheduler.getInstance().schedule(drive);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {


//            CommandScheduler.getInstance().run();

//            LLResult result = limelight.getLatestResult();
//            if (result.getTx() != 0) {
//                servoPos -= 0.00006 * result.getTx();
//            }
//        }
//        if (servoPos > 0.75) {
//            servoPos = 0.75;
//        } else if (servoPos < 0.35) {
//            servoPos = 0.35;
//        }
//        turnServo.setPosition(servoPos);

            if (gamepad1.a) {
                frontIntake.setPower(-1);
                frontPass.setPower(1);
                backPass.setPower(1);
            } else if (gamepad1.y) {
                frontIntake.setPower(-1);
                frontPass.setPower(1);
            } else if (gamepad1.b) {
                frontPass.setPower(1);
                backPass.setPower(-1);
            } else if (gamepad1.dpad_left) {
                frontPass.setPower(1);
            } else if (gamepad1.dpad_right) {
                backPass.setPower(-1);
            } else if (gamepad1.dpad_down) {
                frontPass.setPower(-1);
                backPass.setPower(1);
            } else {
                frontIntake.setPower(0);
                frontPass.setPower(0);
                backIntake.setPower(0);
                backPass.setPower(0);
            }


            if (gamepad1.x) {
                shooterMotor.setPower(0.9);
                shooterServo.setPower(1);

            }
//            } else {
//                shooterMotor.setPower(0);
//                shooterServo.setPower(0);
//            }
            // test launch angle servo
            if (gamepad2.dpad_down) {
                launchAngle.setPosition(launchAngle.getPosition() - .005);
                telemetry.addData("HoodServoPos:", launchAngle.getPosition());
                telemetry.update();
            } else if (gamepad2.dpad_up) {
                launchAngle.setPosition(launchAngle.getPosition() + .005);
                telemetry.addData("HoodServoPos:", launchAngle.getPosition());
                telemetry.update();
            }
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.FORWARD);

            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double frontLeftPower = axial + lateral + yaw;
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower = axial - lateral + yaw;
            double backRightPower = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower /= max;
                frontRightPower /= max;
                backLeftPower /= max;
                backRightPower /= max;
            }

            // This is test code:
            //
            // Uncomment the following code to test your motor directions.
            // Each button should make the corresponding motor run FORWARD.
            //   1) First get all the motors to take to correct positions on the robot
            //      by adjusting your Robot Configuration if necessary.
            //   2) Then make sure they run in the correct direction by modifying the
            //      the setDirection() calls above.
            // Once the correct motors move in the correct direction re-comment this code.

            /*
            frontLeftPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
            backLeftPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
            frontRightPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
            backRightPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
            */

            // Send calculated power to wheels
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

        }
    }
}

