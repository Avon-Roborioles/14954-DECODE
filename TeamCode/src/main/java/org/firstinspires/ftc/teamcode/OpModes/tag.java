package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous
public class tag extends LinearOpMode {
    public final org.firstinspires.ftc.teamcode.OpModes.DriveLimelightOpMode tag = new DriveLimelightOpMode();
@Override
    public void runOpMode() throws InterruptedException {


    waitForStart();
    while (opModeIsActive()) {
        telemetry.addData("tag#",tag.getTag());

        if (tag.getTag() == 21){
            telemetry.addData(" tag;", "g,p,p");
        } else if (tag.getTag() == 22) {
            telemetry.addData(" tag;", "p,g,p");

        } else if (tag.getTag() == 23) {

            telemetry.addData(" tag;", "p,p,g");
        }

        telemetry.update();
    }
    }
}
