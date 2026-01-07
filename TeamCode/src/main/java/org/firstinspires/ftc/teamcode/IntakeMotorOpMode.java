package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeServoCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

@TeleOp
public class IntakeMotorOpMode extends CommandOpMode {
    private DriveSubsystem motor;
    private IntakeServoSubsystem servo;
    private GamepadEx control1, control2;
    @Override
    public void initialize(){
        control1 = new GamepadEx(gamepad1);
        control2 = new GamepadEx(gamepad2);
        servo = new IntakeServoSubsystem(hardwareMap.get(CRServo.class, "servo"), hardwareMap.get(CRServo.class, "servo2"));

        Motor frontLeft = new Motor(hardwareMap,"motor1");
        Motor frontRight = new Motor(hardwareMap,"motor2");
        Motor backLeft = new Motor(hardwareMap, "motor3");
        Motor backRight = new Motor(hardwareMap, "motor4");
        motor = new DriveSubsystem(frontLeft,frontRight,backLeft,backRight);
        control1.getGamepadButton(GamepadKeys.Button.A)
                        .whenHeld(new IntakeServoCommand(servo))
                                .whenReleased(new IntakeStopServoCommand(servo));
        motor.setDefaultCommand(new DriveCommand(motor, control1::getLeftX, control1::getLeftY, control1::getRightX));


    }
}
