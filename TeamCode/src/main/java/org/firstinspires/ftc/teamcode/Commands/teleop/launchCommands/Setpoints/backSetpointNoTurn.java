package org.firstinspires.ftc.teamcode.Commands.teleop.launchCommands.Setpoints;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LaunchSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurnTableSubsystem;

public class backSetpointNoTurn extends CommandBase {
    private LaunchSubsystem launchSubsystem;
    private LightSubsystem lightSubsystem;
    private boolean redAlliance;


    public backSetpointNoTurn(LaunchSubsystem launchSubsystem,LightSubsystem lightSubsystem,boolean redAlliance){
        this.launchSubsystem = launchSubsystem;
        this.lightSubsystem = lightSubsystem;
        this.redAlliance = redAlliance;
        addRequirements(launchSubsystem, lightSubsystem);
    }

    public void initialize(){
        launchSubsystem.backSetPoint(false);



    }
    public void execute() {
        boolean ready = launchSubsystem.isLauncherReady();

        // Only change color once when state flips
        if (ready){
            lightSubsystem.lightViolet();
        } else {
            lightSubsystem.lightAzure();
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
