package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootingSpeedCommand extends CommandBase {
  /**
   * Creates a new ShootingSpeedCommand.
   * */
   ShooterSubsystem shooterSubsystem;
   
  public ShootingSpeedCommand(ShooterSubsystem subsystem) {
    shooterSubsystem = subsystem;
    addRequirements(subsystem);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterSubsystem.shoot(0.4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.shoot(-0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
