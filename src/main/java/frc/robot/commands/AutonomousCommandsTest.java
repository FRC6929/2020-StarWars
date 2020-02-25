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

public class AutonomousCommandsTest extends CommandBase {
  /**
   * Creates a new AutonomousCommands.
   */
  DriveTrainSubsystem drive;
double m_forward;
double m_rotate;


  public AutonomousCommandsTest(DriveTrainSubsystem subsystem, double forward, double rotate) {
    drive = subsystem;
    addRequirements(subsystem);

    m_forward = forward;
    m_rotate = rotate;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {



    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    drive.autoDrive(m_forward, m_rotate);


  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.autoDrive(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
}
