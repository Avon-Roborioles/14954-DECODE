//// LimelightCommand.java
//package org.firstinspires.ftc.teamcode.commands;
//
//// NO LONGER IMPORTING from BlocksOpModeCompanion
//
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.qualcomm.hardware.limelightvision.LLResult;
//import org.firstinspires.ftc.robotcore.external.Telemetry; // Import the correct class
//import org.firstinspires.ftc.teamcode.Subsystems.LimeLightSubsystem;
//
//public class LimelightCommand extends CommandBase {
//    private final LimeLightSubsystem limelightSubsystem;
//    private final Telemetry telemetry; // <-- ADD THIS
//    private LLResult lastResult;
//
//    // UPDATE THE CONSTRUCTOR
//    public LimelightCommand(LimeLightSubsystem limelightSubsystem, LLResult lastResult, Telemetry telemetry){
//        this.limelightSubsystem = limelightSubsystem;
//        this.lastResult = lastResult;
//        this.telemetry = telemetry; // <-- ADD THIS
//        addRequirements(limelightSubsystem);
//    }
//
//    @Override
//    public void initialize(){
//        limelightSubsystem.setPipeline(0);
//    }
//
//    @Override
//    public void execute(){
//        limelightSubsystem.readAprilTag();
//        // This telemetry object is now the one from TeleOp3 and is not null
//        telemetry.addData("tag", limelightSubsystem.readAprilTag());
//        telemetry.update();
//    }
//
//    public boolean isFinished(){
//        return false;
//    }
//}
