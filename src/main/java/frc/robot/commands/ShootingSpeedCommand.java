package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.CameraSubsystem;

public class ShootingSpeedCommand extends CommandBase {
  /**
   * Creates a new ShootingSpeedCommand.
   * */
   ShooterSubsystem shooterSubsystem;
   CameraSubsystem cameraSubsystem;
   
  public ShootingSpeedCommand(ShooterSubsystem subsystem1, CameraSubsystem subsystem2) {
    shooterSubsystem = subsystem1;
    addRequirements(subsystem1);
    cameraSubsystem = subsystem2;
    addRequirements(subsystem2);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*if(cameraSubsystem.getTargetNumber() > 0){
      shooterSubsystem.shoot(cameraSubsystem.getDistance());
    }
    else{
      shooterSubsystem.stop();
    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //if(cameraSubsystem.getTargetNumber() > 0){
      //shooterSubsystem.shoot(cameraSubsystem.getDistance());
    //}
    //else{
      //shooterSubsystem.stop();
    //}
    SmartDashboard.putBoolean("fuck", true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
