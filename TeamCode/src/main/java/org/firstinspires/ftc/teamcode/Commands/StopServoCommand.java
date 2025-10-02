package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ServoSubsystem;

public class StopServoCommand extends CommandBase {
    private ServoSubsystem subsystem;
    public StopServoCommand (ServoSubsystem subsystem ){
        this.subsystem = subsystem;
        addRequirements(subsystem);
}
    @Override
        public void execute() {
            subsystem.stopServo();
        }
}
