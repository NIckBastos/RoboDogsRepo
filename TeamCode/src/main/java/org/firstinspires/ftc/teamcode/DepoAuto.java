/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

import static com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.Servo.Direction.REVERSE;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Depo_to_CraterAuto", group="DepoAuto")
public class DepoAuto extends LinearOpMode {

    //Defining robot//
    BotDawg robot;

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
//    static final double REVERSE_SPEED = -0.6;
//
//    static final double FORWARD_SPEED = 0.6;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot = new BotDawg();
        robot.init(hardwareMap);

        robot.resetAllEncoders();

        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        robot.teamMarker.setPosition(0);
        robot.latchServo.setPosition(1);
        robot.liftMotor.setPower(0);
        robot.leftFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way
    // Calculate lift lowering time, using phone encoder or calculating gear ratios with rpm of motor.
        // Step 1:  Lower lift for .5 seconds
        //robot.teamMarker.setPosition(1);

////            // Step 2: Unlatch the robot from the center for 1.2 seconds.
            robot.liftMotor.setPower(-1);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.2)) {

        }
//                // Step 3: Un hook the robot .75 seconds
                robot.teamMarker.setPosition(0);
                robot.latchServo.setPosition(0);
                robot.liftMotor.setPower(0);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.75)) {

                }
                // Drive forward for 1.9 twards depo
                int targetEncoder = (int)(115*robot.tickPerCentimeter);
                robot.leftFront.setTargetPosition(targetEncoder);
                robot.leftFront.setTargetPosition(targetEncoder);
                robot.leftBack.setTargetPosition(targetEncoder);
                robot.rightFront.setTargetPosition(targetEncoder);
                robot.rightBack.setTargetPosition(targetEncoder);

                robot.leftFront.setPower(1);
                robot.leftBack.setPower(1);
                robot.rightFront.setPower(1);
                robot.rightBack.setPower(1);
                telemetry.addData("TargetEncoder",targetEncoder);
                telemetry.update();
//                while (robot.leftFront.isBusy() || robot.rightFront.isBusy())  {
        while (opModeIsActive() && robot.leftFront.isBusy()) {
        telemetry.addData("leftFront",robot.leftFront.getCurrentPosition());
        telemetry.addData("TargetEncoder",targetEncoder);
        telemetry.update();

                }
// Trying out the encoders
                robot.leftFront.setPower(0);
                robot.leftBack.setPower(0);
//                robot.resetAllEncoders();
                sleep(50);
                targetEncoder += (int)(60*robot.tickPerCentimeter);

                robot.rightFront.setTargetPosition(targetEncoder);
                robot.rightBack.setTargetPosition(targetEncoder);
                while (opModeIsActive() && robot.rightFront.isBusy()) {
                    telemetry.addData("Right Front", robot.rightFront.getCurrentPosition());
                    telemetry.addData("TargetEncoder",targetEncoder);
                    telemetry.update();
                }
        //                runtime.reset();
//                while (opModeIsActive() && (runtime.seconds() <1.9)) {
//
//                }
                //turns left for .7 seconds
//
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);

//            runtime.reset();
//            while (opModeIsActive() && (runtime.seconds() <.7)) {
//
//            }
                // drops team marker
                robot.leftFront.setPower(0);
                robot.leftBack.setPower(0);
                robot.rightFront.setPower(0);
                robot.rightBack.setPower(0);
                robot.teamMarker.setPosition(1);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() <1)) {

                }
                //turns from the depo
            robot.rightFront.setPower(1);
            robot.rightBack.setPower(1);
//                robot.resetAllEncoders();
            sleep(50);
            targetEncoder += (int)(25*robot.tickPerCentimeter);
            robot.rightFront.setTargetPosition(targetEncoder);
            robot.rightBack.setTargetPosition(targetEncoder);

            while (opModeIsActive() && robot.rightFront.isBusy()) {
                telemetry.addData("Right Front", robot.rightFront.getCurrentPosition());
                telemetry.addData("TargetEncoder",targetEncoder);
                telemetry.update();
            }
            robot.leftFront.setPower(1);
            robot.leftBack.setPower(1);
//                robot.resetAllEncoders();
            sleep(50);
            targetEncoder += (int)(140*robot.tickPerCentimeter);
            robot.rightFront.setTargetPosition(targetEncoder);
            robot.rightBack.setTargetPosition(targetEncoder);
            robot.leftFront.setTargetPosition(targetEncoder);
            robot.leftBack.setTargetPosition(targetEncoder);
            while (opModeIsActive() && robot.rightFront.isBusy()) {
                telemetry.addData("Right Front", robot.rightFront.getCurrentPosition());
                telemetry.addData("TargetEncoder",targetEncoder);
                telemetry.update();
        }
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);
            robot.rightFront.setPower(0);

    }

}
