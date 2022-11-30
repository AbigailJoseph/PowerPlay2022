package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="singlemotortest")
public class SInglemotortest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    @Override
    public void runOpMode(){
        //frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
       // backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
       // backRight = hardwareMap.get(DcMotor.class, "backRight");

      //  frontLeft.setDirection(DcMotor.Direction.REVERSE);
      //  backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
      //  backRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset(); //resets the

        while(opModeIsActive()){
            if (gamepad1.left_stick_y > 0.3) {
                frontRight.setPower(0.6);
            }
            if (gamepad1.left_stick_y < -0.3) {
                frontRight.setPower(-0.6);
            }
        }

    }

}
