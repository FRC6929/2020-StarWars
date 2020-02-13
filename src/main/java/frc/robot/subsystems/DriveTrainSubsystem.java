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

public class DriveTrainSubsystem extends SubsystemBase {
  CANSparkMax Left1;
  CANSparkMax Left2;
  CANSparkMax Right1;
  CANSparkMax Right2;
  CANEncoder leftEncoder;
  CANEncoder rightEncoder;
  DifferentialDrive Drive;
  /**
   * Creates a new DriveTrainSubsystem.
   */
  public DriveTrainSubsystem() {
    Left1 = new CANSparkMax(3, MotorType.kBrushless);
    Left2 = new CANSparkMax(4, MotorType.kBrushless);
    Right1 = new CANSparkMax(2, MotorType.kBrushless);
    Right2 = new CANSparkMax(1, MotorType.kBrushless);
    Right1.setInverted(false);
    Right2.setInverted(true);
    Drive = new DifferentialDrive(Left2, Right2);
    
    Left1.follow(Left2);
    Right1.follow(Right2);


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

    

    //leftEncoder = new CANEncoder(Left1);
    //rightEncoder = new CANEncoder(Right1);

    SmartDashboard.putBoolean("isGoinFast", isGoinFast);
    SmartDashboard.putNumber("speed1", speed1);
    SmartDashboard.putNumber("speed2", speed2);
    SmartDashboard.putNumber("xSpeed", xSpeed);
    SmartDashboard.putNumber("zRotation", zRotation);
    
    if(isGoinFast){
      
      Drive.arcadeDrive(xSpeed, zRotation);
      
      
      
    }
    else{
      
      Drive.arcadeDrive(xSpeed, zRotation);
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
    Drive.arcadeDrive(xSpeed * 0.4, zRotation * 0.4);
  }

  public double getForPos(){
    return (leftEncoder.getPosition() + rightEncoder.getPosition())/2;
  }


}
