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

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while(opModeIsActive()){

            //RIGHT STICK
            if(gamepad1.right_stick_y > 0){ //move forward

                frontLeft.setPower(1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(1.0);
            }
            if(gamepad1.right_stick_y > 0){ //move backward
                frontLeft.setPower(-1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(-1.0);
            }
            if(gamepad1.right_stick_x > 0){ //move right
                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }
            if(gamepad1.right_stick_x < 0){ //move left
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }
            else{ //stop
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

            //LEFT STICK
            if(gamepad1.left_stick_x > 0){ //rotate right

                frontLeft.setPower(1.0);
                frontRight.setPower(-1.0);
                backLeft.setPower(1.0);
                backRight.setPower(-1.0);
            }
            if(gamepad1.left_stick_x > 0) { //rotate left
                frontLeft.setPower(-1.0);
                frontRight.setPower(1.0);
                backLeft.setPower(-1.0);
                backRight.setPower(1.0);
            }
            else{ //stop
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
        }

    }
}
