package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorSubsystem extends SubsystemBase {
    private DcMotor motor1, motor2, motor3, motor4;

    public MotorSubsystem (DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4){
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.motor3 = motor3;
        this.motor4 = motor4;
    }
    public void runMotor(double power){
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);
    }

}
