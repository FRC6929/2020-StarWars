/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterTest extends SubsystemBase {
  /**
   * Creates a new ShooterTest.
   */
    VictorSP testMotor;

  public ShooterTest() {

    testMotor = new VictorSP(ShooterConstants.kTestMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void sequantialTest(){
    testMotor.set(0.4);
  }
  public void stop(){
    testMotor.set(0);
  }
}
