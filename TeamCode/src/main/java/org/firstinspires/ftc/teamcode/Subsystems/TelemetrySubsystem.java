package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetrySubsystem extends SubsystemBase {

    private Telemetry telemetry;
    private TurnTableSubsystem turnTable;
    private LimeLightSubsystem limelight;
    private LaunchSubsystem launchSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private DistanceSubsystem distanceSubsystem;

    public TelemetrySubsystem(Telemetry telemetry, TurnTableSubsystem turnTable, LimeLightSubsystem limelight, LaunchSubsystem launchSubsystem, IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem) {
        this.telemetry = telemetry;
        this.turnTable = turnTable;
        this.limelight = limelight;
        this.launchSubsystem = launchSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
    }
    public TelemetrySubsystem(Telemetry telemetry, TurnTableSubsystem turnTable,LaunchSubsystem launchSubsystem, IntakeSubsystem intakeSubsystem, DistanceSubsystem distanceSubsystem){
        this.telemetry = telemetry;
        this.turnTable = turnTable;
        this.launchSubsystem = launchSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.distanceSubsystem = distanceSubsystem;
    }
    public TelemetrySubsystem(Telemetry telemetry, LaunchSubsystem launchSubsystem){
        this.telemetry = telemetry;
        this.launchSubsystem = launchSubsystem;
    }
    public void getTelemetry(){
        clearTelemetry();
//        turnTable.getTelemetry(telemetry);

//        pedroDriveSubsystem.telemetryDebug(telemetry);
        launchSubsystem.getTelemetry(telemetry);
//        intakeSubsystem.getTelemetry(telemetry);
//        distanceSubsystem.getTelemetry(telemetry);
//        limelight.getTelemetry(telemetry);
        updateTelemetry();
    }

    public void autoTestTelemetry(String state){
        clearTelemetry();
        telemetry.addData("State", state);
        updateTelemetry();
    }

    public void compTelemetry() {
        clearTelemetry();
        launchSubsystem.compTelemetry(telemetry);
        turnTable.compTelemetry(telemetry);
        updateTelemetry();
    }


        public void updateTelemetry(){
            telemetry.update();
        }
        public void clearTelemetry(){
            telemetry.clearAll();
        }


}
