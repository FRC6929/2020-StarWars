/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ahrsSubsystem;

public class AutonomousCommands extends CommandBase {
  /**
   * Creates a new AutonomousCommands.
   */
  int AutoPosition;
  DriveTrainSubsystem drive;
  ahrsSubsystem ahrs;

  double forTarget;
  double rotTarget;
  double forPosition;
  double rotPosition;

  PIDController turnController;

  int etape;
  boolean isReady;

  public AutonomousCommands(DriveTrainSubsystem subsystem, ahrsSubsystem ahrsSub, int position) {
    drive = subsystem;
    ahrs = ahrsSub;
    addRequirements(subsystem);
    addRequirements(ahrsSub);
     AutoPosition = position;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    isReady = false;

    

    if(AutoPosition == 1){
      forTarget = 0;
      rotTarget = 0;
    }

    if(AutoPosition == 2){
      forTarget = 100;
      rotTarget = 45;
    }

    if(AutoPosition == 3){
      forTarget = 200;
      rotTarget = 45;
    }
    etape = 1;

    turnController = new PIDController(0, 0, 0);
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    
    forPosition = drive.getForPos();
    rotPosition = ahrs.getAngle();
    

    if(etape == 1){
      if(forTarget > forPosition){
        drive.autoDrive(1, forTarget-forPosition);
      }
      else{
        etape++;
      }
    }
    if(etape == 2){
      if(!turnController.atSetpoint()){
        drive.autoDrive(0, turnController.calculate(ahrs.getAngle(), 180)); 
      }
      else{
        etape++;
      }
    }
    if(etape == 3){
      drive.autoDrive(0, 0);
      end(false);
    }

  }
  public boolean getIsReady(){

  return isReady;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  
  isReady = true;

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
