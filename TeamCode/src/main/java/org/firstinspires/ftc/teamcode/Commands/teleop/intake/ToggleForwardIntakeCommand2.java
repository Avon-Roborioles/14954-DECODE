package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class ToggleForwardIntakeCommand2 extends CommandBase {
    private IntakeServoSubsystem subsystem;
    private boolean isOn;
    public boolean intakeIsRunning;
    private Telemetry telemetry;
    public ToggleForwardIntakeCommand2(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
        isOn = intakeIsRunning;
        addRequirements(subsystem);
    }
    @Override
    public void execute() {
        if (!isOn){
            subsystem.runServo1();
            isOn = true;
            intakeIsRunning = true;
        } else {
            subsystem.stopServo1();
            isOn = false;
            intakeIsRunning = false;
        }
    }
}
