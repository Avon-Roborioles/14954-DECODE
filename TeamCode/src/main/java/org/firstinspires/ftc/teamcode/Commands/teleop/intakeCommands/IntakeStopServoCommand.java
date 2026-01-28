package org.firstinspires.ftc.teamcode.Commands.teleop.intakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeStopServoCommand extends CommandBase {
    private IntakeSubsystem subsystem;
    public IntakeStopServoCommand(IntakeSubsystem subsystem ){
        this.subsystem = subsystem;
        addRequirements(subsystem);
}
    @Override
        public void execute() {
            subsystem.stopAll();
        }
    public boolean isFinished(){
        return true;
    }
    public void end(boolean interrupted){
        subsystem.stopAll();

    }
}
