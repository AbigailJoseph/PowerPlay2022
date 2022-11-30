package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Sample Wheel Teleop")
public class SampleWheelCode extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while(opModeIsActive()){

            //RIGHT STICK
            if(gamepad1.right_stick_y > 0.3)
            { // move forward
                frontLeft.setPower(1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(1.0);
            }
            if(gamepad1.right_stick_y < -0.3)
            { // move backward
                frontLeft.setPower(-1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(-1.0);
            }
            if(gamepad1.right_stick_x > 0.3)
            { // move right
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }
            if(gamepad1.right_stick_x < -0.3)
            { // move left
                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }
            else//(gamepad1.right_stick_y <= 0.3 && gamepad1.right_stick_y >= -0.3 && gamepad1.right_stick_x <= 0.3 && gamepad1.right_stick_x >= -0.3)
            { //remain still
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

            //LEFT STICK
            if(gamepad1.left_stick_x > 0.3)
            { // rotate right
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }
            if(gamepad1.left_stick_x < 0.3)
            { // rotate left
                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }
            else//(gamepad1.left_stick_y <= 0.3 && gamepad1.left_stick_y >= -0.3 && gamepad1.left_stick_x <= 0.3 && gamepad1.left_stick_x >= -0.3)
            { // remain still
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
        }
    }
}