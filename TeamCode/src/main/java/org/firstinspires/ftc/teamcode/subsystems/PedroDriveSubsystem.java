package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class PedroDriveSubsystem extends SubsystemBase {
    private Follower follower;
    public PedroDriveSubsystem(Follower follower){
        this.follower = follower;
    }
    public void startTeleopDrive(){
        follower.startTeleopDrive();

    }
    public void setTeleOpDrive(double forward, double strafe, double turn){
        follower.setTeleOpDrive(forward, strafe, turn, true);
    }
    public void setMaxPower(double maxPower){
        follower.setMaxPower(maxPower);
    }
    public void setTeleOpDrive(double forward, double strafe, double turn, boolean fieldCentric){
        follower.setTeleOpDrive(forward, strafe, turn, !fieldCentric);
    }
    public void update(){
        follower.update();
    }
    public void updateDrivetrain(){
        follower.updateDrivetrain();
    }
}
