/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LifterSubsystem extends SubsystemBase {
  /**
   * Creates a new LifterSubsystem.
   */

  CANSparkMax Lifter1;
  CANSparkMax Lifter2;
  CANSparkMax Lifter3;

  public LifterSubsystem() {

    Lifter1 = new CANSparkMax(3, MotorType.kBrushless);
    Lifter2 = new CANSparkMax(4, MotorType.kBrushless);
    Lifter3 = new CANSparkMax(5, MotorType.kBrushless);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void panique(){

    Lifter1.set(1);
    Lifter2.follow(Lifter1);
    Lifter3.follow(Lifter2);

  }
  public void suspense(){
    
    Lifter1.set(-1);
    Lifter2.follow(Lifter1);
    Lifter3.follow(Lifter2);
  
  }
}
