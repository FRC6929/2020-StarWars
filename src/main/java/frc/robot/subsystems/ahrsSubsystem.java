/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AhrsSubsystem extends SubsystemBase {
  /**
   * Creates a new ahrsSubsystem.
   */
  AHRS ahrs;

  public AhrsSubsystem() {
    ahrs = new AHRS(SPI.Port.kMXP);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public double getAngle(){
    return -ahrs.getAngle();
  }
  public void resetAngle(){
    ahrs.reset();
  }
}
