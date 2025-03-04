package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleops.Mark_I;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.System;
import org.firstinspires.ftc.teamcode.teleops.Mark_II;

@TeleOp
public class teleop extends LinearOpMode {

    public Drive drive;
    public System system;
    public Mark_II run;

    @Override
    public void runOpMode() {
        drive = new Drive(hardwareMap);
        system = new System(hardwareMap);
        run = new Mark_II(drive, system);
        double speed = 35;
        double z = 0;
        double x = 0;
        double r = 0;
        double outSpeed = 1;

        drive.Run_without_encoder();

        telemetry.addData("Initialized", "Ready to start");
        waitForStart();

        while (opModeIsActive()) {
            double[] returned = run.Run(drive, system, gamepad1, gamepad2, speed, z, x, r, telemetry, outSpeed);
            speed = returned[0];
            z = returned[1];
            x = returned[2];
            r = returned[3];
            outSpeed = returned[4];
            telemetry.update();
        }
    }
}
// Hi Susan, good luck :) I believe in you!
