package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Commands.MotorCommand;
import org.firstinspires.ftc.teamcode.Commands.ServoCommand;
import org.firstinspires.ftc.teamcode.Subsystems.MotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ServoSubsystem;

@TeleOp
public class MotorOpMode extends CommandOpMode {
    private MotorSubsystem motor;
    private ServoSubsystem servo;
    private GamepadEx control1, control2;
    @Override
    public void initialize(){
        control1 = new GamepadEx(gamepad1);
        servo = new ServoSubsystem(hardwareMap.get(CRServo.class, "servo"), hardwareMap.get(CRServo.class, "servo2"));
        motor = new MotorSubsystem(hardwareMap.get(DcMotor.class, "motor1"), (hardwareMap.get(DcMotor.class, "motor2")), (hardwareMap.get(DcMotor.class, "motor3")), (hardwareMap.get(DcMotor.class, "motor4")));
        servo.setDefaultCommand(new ServoCommand(servo, control1::getLeftY));


    }
}
