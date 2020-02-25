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

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  CANSparkMax Left1;
  CANSparkMax Left2;
  CANSparkMax Right1;
  CANSparkMax Right2;
  CANEncoder leftEncoder;
  CANEncoder rightEncoder;
  DifferentialDrive Drive;
  Boolean isGoinFast;
  /**
   * Creates a new DriveTrainSubsystem.
   */
  public DriveTrainSubsystem() {
    Left1 = new CANSparkMax(DriveConstants.kLeftFollower, MotorType.kBrushless);
    Left2 = new CANSparkMax(DriveConstants.kLeftMaster, MotorType.kBrushless);
    Right1 = new CANSparkMax(DriveConstants.kRightFollower, MotorType.kBrushless);
    Right2 = new CANSparkMax(DriveConstants.kRightMaster, MotorType.kBrushless);
    Right1.setInverted(false);
    Right2.setInverted(true);
    Drive = new DifferentialDrive(Left2, Right2);
    
    Left1.follow(Left2);
    Right1.follow(Right2);

    Left2.setInverted(true);

    
    
    leftEncoder = new CANEncoder(Left1);
    rightEncoder = new CANEncoder(Right1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double x, double y, double mult1, double mult2, boolean SpeedToggle){
    
    double xSpeed = x;
    double zRotation = y;
    double speed1 = mult1;
    double speed2 = mult2;

    isGoinFast = SpeedToggle;

    

    SmartDashboard.putBoolean("isGoinFast", isGoinFast);
    SmartDashboard.putNumber("speed1", speed1);
    SmartDashboard.putNumber("speed2", speed2);
    SmartDashboard.putNumber("xSpeed", xSpeed);
    SmartDashboard.putNumber("zRotation", zRotation);
    
    if(isGoinFast){
      
      Drive.arcadeDrive(xSpeed*speed2, zRotation*speed2);
      
      
      
    }
    else{
      
      Drive.arcadeDrive(xSpeed*speed1, zRotation*speed1);
      /*Left1.follow(Left2);
      Right1.follow(Right2);*/
      //Left1.set(0.5);
      
    }
    //Left1.set(0.2);
    //Left1.follow(Left2);
  }
  public void PIDControl(double turn){
    Drive.arcadeDrive(0, turn);
  }

  public void autoDrive(double x, double z){
    double xSpeed = x;
    double zRotation = z;
    Drive.arcadeDrive(xSpeed * 0.4, zRotation*-0.5);
  }

  public double getForPos(){
    return (leftEncoder.getPosition() - rightEncoder.getPosition())/2;
  }

  public void resetForPos(){
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public void setSpeedFast(){
    isGoinFast = true;
  }

  public void setSpeedSlow(){
    isGoinFast = false;
  }
  
  public void toggleSpeed(){
    isGoinFast = !isGoinFast;
  }
  public void avance(){
    Drive.arcadeDrive(0.3, 0);
  }
  public void tourne(){
    Drive.arcadeDrive(0, 0.3);
  }
}
