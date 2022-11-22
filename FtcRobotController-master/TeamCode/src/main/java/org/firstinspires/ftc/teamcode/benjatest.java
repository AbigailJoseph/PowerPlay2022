package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="testTeleopAbigal")
public class benjatest extends LinearOpMode
{

    //declaring variables
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    @Override
    public void runOpMode() {

        //mapping here
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset(); //resets the

        while (opModeIsActive()) {

            if (gamepad1.dpad_left)
            {
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }

            if (gamepad1.dpad_up)
            {
                frontLeft.setPower(1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(1.0);
            }

            if (gamepad1.dpad_down)
            {
                frontLeft.setPower(-1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(-1.0);
            }

            if (gamepad1.dpad_right)
            {
                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }

            if (gamepad1.right_stick_x > 0)
            {
                frontLeft.setPower(gamepad1.right_stick_x);
                frontRight.setPower(-gamepad1.right_stick_x);
                backLeft.setPower(-gamepad1.right_stick_x);
                backRight.setPower(gamepad1.right_stick_x);
            }

            if (gamepad1.right_stick_x < 0)
            {
                frontLeft.setPower(-gamepad1.right_stick_x);
                frontRight.setPower(gamepad1.right_stick_x);
                backLeft.setPower(gamepad1.right_stick_x);
                backRight.setPower(-gamepad1.right_stick_x);
            }

            if (gamepad1.right_stick_y > 0)
            {
                frontLeft.setPower(gamepad1.right_stick_y);
                frontRight.setPower(gamepad1.right_stick_y);
                backLeft.setPower(gamepad1.right_stick_y);
                backRight.setPower(gamepad1.right_stick_y);
            }

            if (gamepad1.right_stick_y < 0)
            {
                frontLeft.setPower(-gamepad1.right_stick_y);
                frontRight.setPower(-gamepad1.right_stick_y);
                backLeft.setPower(-gamepad1.right_stick_y);
                backRight.setPower(-gamepad1.right_stick_y);
            }

            if (gamepad1.left_stick_x > 0)
            { //right
                frontLeft.setPower(gamepad1.left_stick_x);
                frontRight.setPower(-gamepad1.left_stick_x);
                backLeft.setPower(gamepad1.left_stick_x);
                backRight.setPower(-gamepad1.left_stick_x);
            }

            if (gamepad1.left_stick_x < 0)
            {
                frontLeft.setPower(-gamepad1.left_stick_x);
                frontRight.setPower(gamepad1.left_stick_x);
                backLeft.setPower(-gamepad1.left_stick_x);
                backRight.setPower(gamepad1.left_stick_x);
            }
        }
    }
}
