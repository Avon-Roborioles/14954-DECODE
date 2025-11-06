package org.firstinspires.ftc.teamcode.commands.teleop.intake;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class ToggleBackIntakeCommand2 extends CommandBase {
    private IntakeServoSubsystem subsystem;
    private boolean isOn;
    public boolean intakeIsRunning;
    public ToggleBackIntakeCommand2(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
        isOn = intakeIsRunning;
        addRequirements(subsystem);
    }
    @Override
    public void execute() {
        if (!isOn){
            subsystem.toggleBackDirection();
            subsystem.toggleFrontDirection();
            isOn = true;
            intakeIsRunning = true;
        } else {
            subsystem.stopAll();
            isOn = false;
            intakeIsRunning = false;
        }
    }
}
