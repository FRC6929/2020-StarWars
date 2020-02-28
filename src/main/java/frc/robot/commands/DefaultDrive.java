/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.LifterSubsystem;

public class DefaultDrive extends CommandBase {
  /**
   * Creates a new DefaultDrive.
   */
  DriveTrainSubsystem driveTrainSubsystem;
  LifterSubsystem lifterSubsystem;
  DoubleSupplier xSpeed;
  DoubleSupplier ySpeed;
  NetworkTableEntry mult1;
  NetworkTableEntry mult2;
  Boolean isFast;


  
  public DefaultDrive(DriveTrainSubsystem drive, LifterSubsystem lifter, DoubleSupplier forward, DoubleSupplier rotation, NetworkTableEntry speed1, NetworkTableEntry speed2, boolean speedChoice) {
    driveTrainSubsystem = drive;
    lifterSubsystem = lifter;
    
    xSpeed = forward;
    ySpeed = rotation;
    mult1 = speed1;
    mult2 = speed2;
    isFast = speedChoice;


    addRequirements(driveTrainSubsystem);
    addRequirements(lifterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lifterSubsystem.retract();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    
    
    driveTrainSubsystem.drive(xSpeed.getAsDouble(), ySpeed.getAsDouble(), mult1.getDouble(1), mult2.getDouble(1), isFast);
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
