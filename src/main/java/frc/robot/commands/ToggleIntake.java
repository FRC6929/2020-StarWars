/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.LifterIntakeToggle;
import frc.robot.subsystems.LifterSubsystem;

public class ToggleIntake extends CommandBase {
  /**
   * Creates a new ToggleIntake.
   */
  LifterSubsystem lsub;
   IntakeSubsystem isub;
  public ToggleIntake(LifterSubsystem a,IntakeSubsystem b) {
    lsub = a;
    isub = b;
    addRequirements(a);
    addRequirements(b);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*if(lsub.active == isub.active)
    {
      isub.toggle(true);
    }
    else
    {*/
      lsub.toggle(false);
      isub.toggle(true);
    //}
    //LIT.ToggleToggle(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
