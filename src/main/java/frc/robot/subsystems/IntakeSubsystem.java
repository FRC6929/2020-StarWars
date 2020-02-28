/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  //DoubleSolenoid intakeSolenoid;

  CANSparkMax intakeMotor;

  public IntakeSubsystem() {
    //intakeSolenoid = new DoubleSolenoid(0, 1, 2);
    intakeMotor = new CANSparkMax(7, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeOut(){
    //intakeSolenoid.set(Value.kForward);
  }

  public void intakeIn(){
    //intakeSolenoid.set(Value.kReverse);
  }

  public void ballsIn(){
    intakeMotor.set(1);
  }

  public void stop(){
    intakeMotor.set(0);
  }

  public void pushBalls(){
    intakeMotor.set(-1);
  }

}
