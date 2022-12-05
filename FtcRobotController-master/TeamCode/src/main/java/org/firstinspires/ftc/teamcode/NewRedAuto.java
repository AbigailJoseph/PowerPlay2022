package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="NewRedAuto")
public class NewRedAuto extends LinearOpMode {
    public DcMotor frontRight; //wheel1
    public DcMotor backRight; //wheel 3
    public DcMotor frontLeft; //wheel2
    public DcMotor backLeft; //wheel 4
    public ColorSensor sensor;

    //1 foot = 1000
    //1 inch is 83.3

    public void runOpMode() throws InterruptedException {
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");//need to rename
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        sensor = hardwareMap.get(ColorSensor.class, "sensor");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        frontRight.setPower(0.8);
        frontLeft.setPower(0.8);
        backRight.setPower(0.8);
        backLeft.setPower(0.8);

        moveForward(1000);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        //moveRight(2160);
        //moveBack(250);

        /*
        if(red()){ // ZONE 1

        }
        else if(green()){ // ZONE 2

        }
        else if(blue()){ // ZONE 3

        }

        */


    }

    public void runWheels() {
        while (frontRight.getCurrentPosition() != frontRight.getTargetPosition()) {

            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void reset() {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void encoderStop() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

    }

    public void moveForward(int distance) {
        reset();
        frontRight.setTargetPosition(-distance);
        frontLeft.setTargetPosition(distance);
        backRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(distance);
        runWheels();
        encoderStop();
    }

    public void moveBack(int distance) {
        reset();
        frontRight.setTargetPosition(-distance);
        frontLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);

        runWheels();
    }

    public void moveLeft(int distance) {
        reset();
        frontRight.setTargetPosition(-distance);
        frontLeft.setTargetPosition(distance);
        backRight.setTargetPosition(distance);
        backLeft.setTargetPosition(-distance);

        runWheels();
    }

    public void moveRight(int distance) {
        reset();
        frontRight.setTargetPosition(distance);
        frontLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(distance);

        runWheels();
    }

    public void rotateRight(int distance) {
        reset();
        frontRight.setTargetPosition(-distance);
        frontLeft.setTargetPosition(distance);
        backRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(distance);

        runWheels();
    }

    public void rotateLeft(int distance) {
        reset();
        frontRight.setTargetPosition(distance);
        frontLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(distance);
        backLeft.setTargetPosition(-distance);

        runWheels();
    }

    public boolean red() {
        if (sensor.red() >= 700)
        {
            if (sensor.red() > sensor.blue() && sensor.red() > sensor.green())
            {
                return true;
            }

        }
        return false;

    }

    public boolean blue(){
        if (sensor.blue() >= 700)
        {
            if (sensor.blue() > sensor.green() && sensor.blue() > sensor.red())
            {
                return true;
            }

        }
        return false;
    }
    public boolean green(){
        if (sensor.green() >= 700)
        {
            if (sensor.green() > sensor.blue() && sensor.green() > sensor.red())
            {
                return true;
            }

        }
        return false;
    }


}
