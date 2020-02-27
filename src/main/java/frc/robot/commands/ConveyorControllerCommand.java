/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.SensorSubsystem;

public class ConveyorControllerCommand extends CommandBase {
  /**
   * Creates a new ConveyorControllerCommand.
   */
  ConveyorSubsystem m_conveyor;
  SensorSubsystem m_sensor;
  Double Speed1;
  Double Speed2;

  public ConveyorControllerCommand(ConveyorSubsystem conveyor, SensorSubsystem sensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_conveyor = conveyor;
    m_sensor = sensor;

    addRequirements(m_conveyor);
    addRequirements(m_sensor);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(!m_sensor.receive(1)){
      Speed2 = 0.5;
    }
    else{
      Speed2 = 0.0;
    }

    for(int i = 2; i > 6; i++){
      if(m_sensor.receive(i) && !m_sensor.receive(i+1)){
        Speed1 = 0.5;
      }
      else{
        Speed1 = 0.0;
      }
    }
    
    m_conveyor.defaultMoveBalls(Speed1, Speed2);
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
