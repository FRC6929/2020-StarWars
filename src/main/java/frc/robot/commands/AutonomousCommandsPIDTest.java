/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.AhrsSubsystem;

public class AutonomousCommandsPIDTest extends CommandBase {
  /**
   * Creates a new AutonomousCommands.
   */
  int AutoPosition;
  DriveTrainSubsystem drive;
  AhrsSubsystem AHRS;

  double forTarget;
  double rotTarget;
  double forPosition;
  double rotPosition;

  PIDController turnController;

  int etape;
  boolean isReady;

  double p = 0.1;
  double i = 0;
  double d = 0;

  double Setpoint = 30;

  public AutonomousCommandsPIDTest(DriveTrainSubsystem subsystem, AhrsSubsystem ahrsSub) {
    drive = subsystem;
    AHRS = ahrsSub;
    addRequirements(subsystem);
    addRequirements(ahrsSub);

    //AutoPosition = position;

    

     etape = 1;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    AHRS.resetAngle();

    turnController = new PIDController(p,i,d);
    turnController.setTolerance(1);

    
    }

    /*

    if(AutoPosition == 1){
      forTarget = 0;
      rotTarget = 0;
      turnController.setSetpoint(0);
    }

    if(AutoPosition == 2){
      forTarget = 50;
      rotTarget = 45;
      turnController.setSetpoint(150);
    }

    if(AutoPosition == 3){
      forTarget = 75;
      rotTarget = 90;
      turnController.setSetpoint(120);
    }
    */


    

  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //turnController.setSetpoint(setSetpoint);
    //if(etape == 0){
    //  etape++;
    //}
      //System.out.println(etape);
      //System.out.println(turnController.getSetpoint());
      double error = Setpoint - AHRS.getAngle();
      System.out.println(AHRS.getAngle());
      
      //System.out.println(!turnController.atSetpoint());

    //double pidOutput = turnController.calculate(AHRS.getAngle());
    //System.out.println(pidOutput);
    //drive.PIDControl(pidOutput);
    //if(!turnController.atSetpoint()){
    //  end(false);
    //}
      System.out.println(Math.abs(error));
      if(Math.abs(error) < 1){
        drive.autoDrive(0, 0);
      }
      else{
        drive.autoDrive(0, p*error);
      }
      
  }
  public boolean getIsReady(){

  return isReady;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  drive.autoDrive(0, 0);
  isReady = true;

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
}
