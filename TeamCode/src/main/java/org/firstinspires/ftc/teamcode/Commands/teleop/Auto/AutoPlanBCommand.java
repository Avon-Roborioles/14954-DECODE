package org.firstinspires.ftc.teamcode.commands.teleop.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;

import java.util.concurrent.TimeUnit;

public class AutoPlanBCommand extends CommandBase {
    private Timing.Timer timer;
    public AutoPlanBCommand (){

    }
    public void initialize(){
        timer = new Timing.Timer(3000, TimeUnit.MILLISECONDS);
        timer.start();
    }

    public boolean isFinished(){
        return timer.done();
    }
}
