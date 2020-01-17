/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {
  CANSparkMax Left1;
  CANSparkMax Left2;
  CANSparkMax Right1;
  CANSparkMax Right2;
  DifferentialDrive Drive;
  /**
   * Creates a new DriveTrainSubsystem.
   */
  public DriveTrainSubsystem() {
    Left1 = new CANSparkMax(1, MotorType.kBrushless);
    Left2 = new CANSparkMax(2, MotorType.kBrushless);
    Right1 = new CANSparkMax(3, MotorType.kBrushless);
    Right2 = new CANSparkMax(4, MotorType.kBrushless);
    Drive = new DifferentialDrive(Left1, Right1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double x, double y, double mult1, double mult2, boolean chosenMult){
    
    double xSpeed = x;
    double zRotation = y;
    double speed1 = mult1;
    double speed2 = mult2;
    boolean isGoinFast = chosenMult;
    
    if(isGoinFast){
      Drive.arcadeDrive(xSpeed*speed2, zRotation*speed2);
    }
    else{
      Drive.arcadeDrive(xSpeed*speed1, zRotation*speed1);
    }
    
    Left2.follow(Left1);
    Right2.follow(Right1);
  }
}
