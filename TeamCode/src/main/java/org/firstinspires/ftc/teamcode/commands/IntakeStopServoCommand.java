package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeServoSubsystem;

public class IntakeStopServoCommand extends CommandBase {
    private IntakeServoSubsystem subsystem;
    public IntakeStopServoCommand(IntakeServoSubsystem subsystem ){
        this.subsystem = subsystem;
        addRequirements(subsystem);
}
    @Override
        public void execute() {
            subsystem.stopServo1();
            subsystem.stopServo2();
        }
}
