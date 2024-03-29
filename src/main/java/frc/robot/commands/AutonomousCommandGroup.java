/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.AhrsSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LifterSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousCommandGroup extends ParallelCommandGroup {
  /**
   * Creates a new AutonomousCommandGroup.
   */
  public AutonomousCommandGroup(DriveTrainSubsystem drive, AhrsSubsystem ahrs, ShooterSubsystem shooter, CameraSubsystem camera, LifterSubsystem lifter, IntakeSubsystem intake, int position) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    addCommands(
      new ShootingCommand(shooter, drive, camera, lifter),
      parallel(
        new AutonomousCommands(drive, ahrs, position),
        new RunCommand(() -> intake.ballsIn(), intake)
      ),
      new ShootingCommand(shooter, drive, camera, lifter)
    ); 
  }
}
