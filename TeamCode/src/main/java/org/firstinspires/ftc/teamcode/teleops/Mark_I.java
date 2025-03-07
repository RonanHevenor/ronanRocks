package org.firstinspires.ftc.teamcode.teleops;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.System;

public class Mark_I {
    public Mark_I(Drive drive, System system) {}

    public double[] Run(Drive drive, System system, Gamepad gamepad1, double sM, double z, double x, double r, Telemetry telemetry, double outSpeed) {
        double speed = -gamepad1.left_stick_y * 0.02 * sM;
        double strafe = -gamepad1.left_stick_x * 0.02 * sM;
        double turn = -gamepad1.right_stick_x * 0.02 * sM;

        drive.frontLeft.setPower    (speed + turn - strafe);
        drive.frontRight.setPower   (speed - turn + strafe);
        drive.backLeft.setPower     (speed + turn + strafe);
        drive.backRight.setPower    (speed - turn - strafe);

//        telemetry.addData("frontLeft", (speed - turn + strafe));
//        telemetry.addData("frontRight", (speed + turn - strafe));
//        telemetry.addData("backLeft", (speed - turn - strafe));
//        telemetry.addData("backRight", (speed + turn + strafe));


        if (gamepad1.dpad_down) {
            system.leftUp.setPower(-0.1);
            system.rightUp.setPower(-0.1);
        } else if (gamepad1.dpad_up) {
            system.leftUp.setPower(0.75);
            system.rightUp.setPower(0.75);
        } else {
            system.leftUp.setPower(0.1);
            system.rightUp.setPower(0.1);
        }

        if (gamepad1.dpad_left) {
            system.carousel.setPower(-.5*outSpeed);
        } else if (gamepad1.dpad_right) {
            system.carousel.setPower(.5*outSpeed);
        } else {
            system.carousel.setPower(0);
        }

        if (gamepad1.a) { //close
            system.grabber.setPower(-1);
        } else if (gamepad1.b) { //open
            system.grabber.setPower(1);
            sleep(210);
            system.grabber.setPower(0);
        }
        //        } else {
        //            system.grabber.setPower(0);
        //        }

        if (gamepad1.x) {
            system.out.setPower(outSpeed);
        } else if (gamepad1.y) {
            system.out.setPower(-outSpeed);
        } else {
            system.out.setPower(0);
        }

        if (gamepad1.right_bumper) {
            if (sM > 20) {
                sM -= 1;
            }
        } else if (gamepad1.left_bumper) {
            if (sM < 35) {
                sM += 1;
            }
        }

        //slowmode
        if (gamepad1.right_bumper) {
            outSpeed = 1;
        } else if (gamepad1.left_bumper) {
            outSpeed = 0.5;
        }

        //Return sM, z, x, and r using an int array
        double[] toReturn = {sM, z, x, r};
        return toReturn;
    }
}
