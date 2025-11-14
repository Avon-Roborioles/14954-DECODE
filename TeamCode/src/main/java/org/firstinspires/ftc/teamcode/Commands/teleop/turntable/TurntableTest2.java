package org.firstinspires.ftc.teamcode.commands.teleop.turntable;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class TurntableTest2 extends CommandBase {

    private TurnTableSubsystem subsystem;

    public TurntableTest2 (TurnTableSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    public void execute() {
        subsystem.testmove2();
    }

    public boolean isFinished(){
        return true;
    }
}
