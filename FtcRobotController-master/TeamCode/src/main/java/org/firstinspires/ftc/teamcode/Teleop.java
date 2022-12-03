package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Teleop")
public class Teleop extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;
    private DcMotor arm = null;
    private Servo claw = null;
    private double clawPosition;

    //FILL THESE IN
    //static final double CLAWCLOSE = ;
    //static final double CLAWOPEN = ;

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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset(); //resets the

        while(opModeIsActive()){

            //                                   [GAME PAD 1]
            double speed;
            double strafe;
            double turn;

            speed = -gamepad1.right_stick_y; //needs to be negative if right motors are flipped
            strafe = gamepad1.right_stick_x * 1.1;
            turn = gamepad1.left_stick_x;

            // WHEEL MOVEMENT(left stick) & ROTATION(right stick)
            frontLeft.setPower(speed+turn+strafe);
            frontRight.setPower(speed-turn-strafe);
            backLeft.setPower(speed+turn-strafe);
            backRight.setPower(speed-turn+strafe);

            //                                  [GAME PAD 2]

            // ARM TEST(button x & y)
            if(gamepad2.x){
                arm.setPower(0.3);
            }
            else if(gamepad2.y){
                arm.setPower(-0.3);;
            }
            else{
                arm.setPower(0);
            }

            // ARM(right stick)
            /*
            if(gamepad2.right_stick_y > ){ //moves up or down
                arm.setPower();
            }
            else if(gamepad2.right_stick_y < ){ //moves up or down
                arm.setPower();;
            }
            else{
                arm.setPower(0);
            }
            */

            // CLAW TEST(button a & b)
            if(gamepad2.a){ //open or close
                clawPosition += 0.01;
            }
            else if(gamepad2.b){ //open or close
                clawPosition -= 0.01;
            }
            clawPosition = Range.clip(clawPosition, -1.0, 1.0);
            claw.setPosition(clawPosition);
            telemetry.addData("Servo Position: ", clawPosition);
            telemetry.update();

            //CLAW(button a & b)
            /*
            if(gamepad2.a){ //open or close
                clawPosition = CLAWCLOSE;
            }
            else if(gamepad2.b){ //open or close
                clawPosition = CLAWOPEN;
            }
            clawPosition = Range.clip(clawPosition, -1.0, 1.0);
            claw.setPosition(clawPosition);
             */
        }
    }

}
