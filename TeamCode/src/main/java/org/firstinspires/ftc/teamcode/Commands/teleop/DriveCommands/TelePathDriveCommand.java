//package org.firstinspires.ftc.teamcode.commands.teleop.DriveCommands;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.pedropathing.paths.*;
//import com.pedropathing.geometry.*;
//
//
//import org.firstinspires.ftc.teamcode.Storage.memory;
//import org.firstinspires.ftc.teamcode.subsystems.AutoDriveSubsystem;
//
//
//public class TelePathDriveCommand extends CommandBase {
//    private AutoDriveSubsystem autoDriveSubsystem;
//    private Pose pose;
//    private Path path;
//
//    public TelePathDriveCommand(AutoDriveSubsystem autoDriveSubsystem, Pose pose) {
//        this.pose = pose;
//        this.autoDriveSubsystem = autoDriveSubsystem;
//        addRequirements(autoDriveSubsystem);
//    }
//    @Override
//    public void initialize() {
//        path = new Path(path.getCurve());
//        path.setLinearHeadingInterpolation(autoDriveSubsystem.getPose().getHeading(), m.scorePose.getHeading());
//        autoDriveSubsystem.followPath(path, true);
//    }
//    @Override
//    public void execute() {
//        autoDriveSubsystem.update();
//    }
//    @Override
//    public boolean isFinished() {
//        return autoDriveSubsystem.atParametricEnd();
//    }
//    @Override
//    public void end(boolean interrupted) {
//        autoDriveSubsystem.breakFollowing();
//        autoDriveSubsystem.startTeleopDrive();
//    }
//
//}
