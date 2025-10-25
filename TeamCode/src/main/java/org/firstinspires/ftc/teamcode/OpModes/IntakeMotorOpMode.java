package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeMotorCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeServoCommand;
import org.firstinspires.ftc.teamcode.commands.teleop.intake.IntakeStopServoCommand;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeMotorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

@TeleOp
public class IntakeMotorOpMode extends CommandOpMode {
    private IntakeMotorSubsystem motor;
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
        motor = new IntakeMotorSubsystem(frontLeft,frontRight,backLeft,backRight);
        control1.getGamepadButton(GamepadKeys.Button.A)
                        .whenHeld(new IntakeServoCommand(servo))
                                .whenReleased(new IntakeStopServoCommand(servo));
        motor.setDefaultCommand(new IntakeMotorCommand(motor, control1::getLeftX, control1::getLeftY, control1::getRightX));


    }
}
