package org.firstinspires.ftc.teamcode.OpModes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTest extends LinearOpMode {

    private CRServo servo;
    private CRServo servo2;
    private Servo flipper;
    private DcMotor shooterMotor;
    private CRServo shooterServo;


    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "intakeServo1");
        servo2 = hardwareMap.get(CRServo.class, "intakeServo2");
        flipper = hardwareMap.get(Servo.class, "flipper");
        shooterMotor = hardwareMap.get(DcMotor.class, "launchMotor");
        shooterServo = hardwareMap.get(CRServo.class, "launchServo");


        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a){
                servo.setPower(1);
                servo2.setPower(-1);
            } else if (gamepad1.y) {
                servo.setPower(-1);
                servo2.setPower(1);

            } else{
                servo.setPower(0);
                servo2.setPower(0);
            }

            if (gamepad1.b){
                flipper.setPosition(0);
            } else {
                flipper.setPosition(0.15);
            }

            if (gamepad1.x){
                shooterMotor.setPower(0.7);
                shooterServo.setPower(1);
            } else {
                shooterMotor.setPower(0);
                shooterServo.setPower(0);
            }


        }


    }
}
