/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
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
  CANEncoder LifterEncoder;

  public LifterSubsystem() {

    //Lifter1 = new CANSparkMax(7, MotorType.kBrushless);
    //Lifter2 = new CANSparkMax(8, MotorType.kBrushless);
    //Lifter3 = new CANSparkMax(9, MotorType.kBrushless);
    //LifterEncoder = new CANEncoder(Lifter1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  /*public void panique(){

    if(LifterEncoder.getPosition() < 10){
      Lifter1.set(1);
      Lifter2.follow(Lifter1);
      Lifter3.follow(Lifter2);
    }
    else{
      Lifter1.set(0);
      Lifter2.follow(Lifter1);
      Lifter3.follow(Lifter2);
    }

  }
  public void suspense(){
    
    if(LifterEncoder.getPosition() > 0){
      Lifter1.set(-1);
      Lifter2.follow(Lifter1);
      Lifter3.follow(Lifter2);
    }
    else{
      Lifter1.set(0);
      Lifter2.follow(Lifter1);
      Lifter3.follow(Lifter2);
    }
  
  }*/
}
