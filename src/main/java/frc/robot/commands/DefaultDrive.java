/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DefaultDrive extends CommandBase {
  /**
   * Creates a new DefaultDrive.
   */
  DriveTrainSubsystem driveTrainSubsystem;
  Double xSpeed;
  Double ySpeed;
  Double mult1;
  Double mult2;
  Boolean isFast;
  
  public DefaultDrive(DriveTrainSubsystem subsystem, double forward, double rotation, double speed1, double speed2, boolean isSpeedyBoi) {
    driveTrainSubsystem = subsystem;

    xSpeed = forward;
    ySpeed = rotation;
    mult1 = speed1;
    mult2 = speed2;
    isFast = isSpeedyBoi;



    addRequirements(driveTrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    driveTrainSubsystem.drive(xSpeed, xSpeed, mult1, mult2, isFast);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
