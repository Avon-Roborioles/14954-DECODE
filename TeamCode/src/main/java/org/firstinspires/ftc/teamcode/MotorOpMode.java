package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Commands.MotorCommand;
import org.firstinspires.ftc.teamcode.Commands.ServoCommand;
import org.firstinspires.ftc.teamcode.Commands.StopServoCommand;
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
        control2 = new GamepadEx(gamepad2);
        servo = new ServoSubsystem(hardwareMap.get(CRServo.class, "servo"), hardwareMap.get(CRServo.class, "servo2"));

        Motor frontLeft = new Motor(hardwareMap,"motor1");
        Motor frontRight = new Motor(hardwareMap,"motor2");
        Motor backLeft = new Motor(hardwareMap, "motor3");
        Motor backRight = new Motor(hardwareMap, "motor4");
        motor = new MotorSubsystem(frontLeft,frontRight,backLeft,backRight);
        control1.getGamepadButton(GamepadKeys.Button.A)
                        .whenHeld(new ServoCommand(servo))
                                .whenReleased(new StopServoCommand(servo));
        motor.setDefaultCommand(new MotorCommand(motor, control1::getLeftX, control1::getLeftY, control1::getRightX));


    }
}
