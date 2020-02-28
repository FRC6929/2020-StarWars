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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LifterSubsystem extends SubsystemBase {
  /**
   * Creates a new LifterSubsystem.
   */

  Spark Lifter1;
  Spark Lifter2;
  Spark Lifter3;
  Encoder LifterEncoder;
  DoubleSolenoid lifterSolenoid;

  public LifterSubsystem() {

    Lifter1 = new Spark(0);
    Lifter2 = new Spark(1);
    Lifter3 = new Spark(2);
    LifterEncoder = new Encoder(0, 1);
    lifterSolenoid = new DoubleSolenoid(2, 3);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void manualUp(){
    Lifter1.set(0.25);
    Lifter2.set(0.25);
    Lifter3.set(0.25);
  }

  public void manualDown(){
    Lifter1.set(-0.25);
    Lifter2.set(-0.25);
    Lifter3.set(-0.25);
  }

  public void panique(){

    if(LifterEncoder.get() < 10){
      Lifter1.set(0.25);
      Lifter2.set(0.25);
      Lifter3.set(0.25);
    }
    else{
      Lifter1.set(0);
      Lifter2.set(0);
      Lifter3.set(0);
    }

  }
  public void retract(){
    
    if(LifterEncoder.get() > 2){
      Lifter1.set(-0.25);
      Lifter2.set(-0.25);
      Lifter3.set(-0.25);
    }
    else{
      Lifter1.set(0);
      Lifter2.set(0);
      Lifter3.set(0);
    }
  
  }

  public void lock(){
    lifterSolenoid.set(Value.kForward);
  }

  public void peek(){
    if(LifterEncoder.get() < 1){
      Lifter1.set(0.25);
      Lifter2.set(0.25);
      Lifter3.set(0.25);
    }
  }
  
}
