package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class closeBackSetPointCommand extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private TurnTableSubsystem turnTableSubsystem;
    private LightSubsystem lightSubsystem;
    private boolean redAlliance;


    public closeBackSetPointCommand(LaunchSubsystem launchSubsystem, TurnTableSubsystem turnTableSubsystem, LightSubsystem lightSubsystem,boolean redAlliance){
        this.launchSubsystem = launchSubsystem;
        this.turnTableSubsystem = turnTableSubsystem;
        this.lightSubsystem = lightSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(launchSubsystem, turnTableSubsystem);
    }

    public void initialize(){
        launchSubsystem.closeBackSetPoint();
        turnTableSubsystem.closeBackSetPoints(redAlliance);

    }
    public void execute() {
        boolean ready = launchSubsystem.isLauncherReady();

        // Only change color once when state flips
        if (ready){
            lightSubsystem.lightViolet();
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
