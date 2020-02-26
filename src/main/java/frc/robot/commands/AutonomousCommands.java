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

public class AutonomousCommands extends CommandBase {
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

  double kP;
  double kI;
  double kD;

  public AutonomousCommands(DriveTrainSubsystem subsystem, AhrsSubsystem ahrsSub, int position) {
    drive = subsystem;
    AHRS = ahrsSub;
    addRequirements(subsystem);
    addRequirements(ahrsSub);

    AutoPosition = position;

    

     etape = 1;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    kP = 0.05;
    kI = 0.0005;
    kD = 0.005;

    AHRS.resetAngle();
    drive.resetForPos();

    turnController = new PIDController(kP,kI,kD);
    turnController.setTolerance(1);

    isReady = false;

    SmartDashboard.putNumber("Pos", AutoPosition);

    switch(AutoPosition)
    {
      default:
      end(true);
      break;

      case 1:
      forTarget = 0;
      rotTarget = 0;
      turnController.setSetpoint(0);
      break;

      case 2:
      forTarget = 2;
      rotTarget = 45;
      turnController.setSetpoint(150);
      break; 

      case 3:
      forTarget = 3;
      rotTarget = 90;
      turnController.setSetpoint(120);
      break;
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

    etape = 1;

    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //if(etape == 0){
    //  etape++;
    //}
      System.out.println(etape);
      System.out.println(turnController.getSetpoint());
      System.out.println(AHRS.getAngle());
      System.out.println(!turnController.atSetpoint());

    forPosition = drive.getForMeterPos();
    rotPosition = AHRS.getAngle();
    
    SmartDashboard.putNumber("For", forPosition);
    SmartDashboard.putNumber("Rot", rotPosition);
    SmartDashboard.putNumber("etape", etape);
    SmartDashboard.putNumber("ForT", forTarget);
    SmartDashboard.putNumber("RotT", rotTarget);


    if(etape == 1){
      if(forTarget > forPosition){
        drive.autoDrive(1, (rotTarget-rotPosition)/100);
        System.out.println("etape1");
      }
      else{
       etape = 2;
       System.out.println("finetape1");
      }
    }
    if(etape == 2){
      if(Math.abs(turnController.getSetpoint() - AHRS.getAngle()) > 1){
        double pidTurn = turnController.calculate(AHRS.getAngle());
        drive.autoDrive(0, pidTurn); 
        System.out.println(pidTurn);
        System.out.println("etape2");
      }
      else{
        etape = 3;
        double pidTurn2 = turnController.calculate(AHRS.getAngle());
        System.out.println(pidTurn2);
        System.out.println("finetape2");
      }
    }
    if(etape == 3){
      System.out.println("etape3");
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
