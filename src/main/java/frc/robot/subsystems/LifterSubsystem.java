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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.controller.PIDController;

public class LifterSubsystem extends SubsystemBase {
  /**
   * Creates a new LifterSubsystem.
   */

  Spark Lifter1;
  Spark Lifter2;
  Spark Lifter3;
  Encoder LifterEncoder;
  DoubleSolenoid lifterSolenoid;
  PIDController PIDLift;
  DigitalInput encoderReset;
  DigitalOutput quoi;
  //Setpoints
  private int liftMax = 23000;
  private int liftMin = 4000;
  private int liftPeek = 10000;

  public Boolean active = false;

  public LifterSubsystem() {

    Lifter1 = new Spark(0);
    Lifter2 = new Spark(1);
    Lifter3 = new Spark(2);
    Lifter1.setInverted(true);
    Lifter2.setInverted(true);
    Lifter3.setInverted(true);
    LifterEncoder = new Encoder(0, 1, true);
    LifterEncoder.setDistancePerPulse(1/25000);
    lifterSolenoid = new DoubleSolenoid(2, 3);
    PIDLift = new PIDController(0.000012, 0, 0);
    encoderReset = new DigitalInput(8);
    LifterEncoder.reset();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("LiftEncoder", LifterEncoder.get());
    SmartDashboard.putNumber("LiftDistance", LifterEncoder.get()/250);
    
    if(!encoderReset.get()){
      LifterEncoder.reset();
    
    }
  }
  public void manualUp(){
    if(active)
    {
      
    Lifter1.set(0.35);
    Lifter2.set(0.35);
    Lifter3.set(0.35);
    }
  }

  public void manualDown(){
    if(active)
    {
      Lifter1.set(-0.25);
      Lifter2.set(-0.25);
      Lifter3.set(-0.25);
    }
  }

  public void panique(){
    if(active)
    {
      if(LifterEncoder.get() < liftMax){
        Lifter1.set(0.55);
        Lifter2.set(0.55);
        Lifter3.set(0.55);
      }
      else{
        Lifter1.set(0);
        Lifter2.set(0);
        Lifter3.set(0);
      }
    }

  }
  public void retract(){
    if(active)
    {    
      if(LifterEncoder.get() > liftMin){
      Lifter1.set(-0.7);
      Lifter2.set(-0.7);
      Lifter3.set(-0.7);
    }
    else{
      Lifter1.set(0);
      Lifter2.set(0);
      Lifter3.set(0);
    }
    }
  }

  public void lift(){
    double speed = PIDLift.calculate(LifterEncoder.getDistance(), liftMin);
    Lifter1.set(speed);
    Lifter2.set(speed);
    Lifter3.set(speed);
  }

  public void extend(){
    double speed = PIDLift.calculate(LifterEncoder.getDistance(), liftMax);
    Lifter1.set(speed);
    Lifter2.set(speed);
    Lifter3.set(speed);

  }

  public void lock(){
    lifterSolenoid.set(Value.kForward);
  }

  public void peek(){
    if(LifterEncoder.get() < liftPeek){
      Lifter1.set(0.5);
      Lifter2.set(0.5);
      Lifter3.set(0.5);
    }
    else{
      Lifter1.set(0);
      Lifter2.set(0);
      Lifter3.set(0);
    }
  }
  
  public void stop(){
    Lifter1.set(0);
    Lifter2.set(0);
    Lifter3.set(0);
    PIDLift.reset();
  }

  public void toggle(boolean isActive)
  {
    active = isActive;
    System.out.println("Toggling Lifter vers " + active);
  }

  public double getHeight(){
    return LifterEncoder.get()/25000;
  }
}
