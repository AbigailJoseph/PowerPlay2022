package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Servo Test")
public class ServoTest extends LinearOpMode {

    Servo servo = null;
    private double servoPosition;

    @Override
    public void runOpMode(){

        while (opModeIsActive()){
            if(gamepad1.a){
                servoPosition += 0.01;
            }
            else if(gamepad1.y){
                servoPosition -= 0.01;
            }

            servoPosition = Range.clip(servoPosition, -1.0, 1.0);
            servo.setPosition(servoPosition);
        }

    }
}
