/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootingAngleCommand extends PIDCommand {
  /**
   * Creates a new ShootingAngleCommand.
   */
  CameraSubsystem cameraSubsystem;
  DriveTrainSubsystem driveTrainSubsystem;

  public ShootingAngleCommand( DriveTrainSubsystem drive, CameraSubsystem camera) {
    super(
        // The controller that the command will use
        new PIDController(0, 0, 0),
        // This should return the measurement
        () -> camera.getAngleOffset(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {drive.PIDControl(output);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  
        addRequirements(camera);
        addRequirements(drive);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    
    return false;
  }
}
