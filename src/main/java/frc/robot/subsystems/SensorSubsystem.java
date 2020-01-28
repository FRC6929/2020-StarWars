/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SensorSubsystem extends SubsystemBase {
  DigitalOutput Emitter = new DigitalOutput(0);
  DigitalInput Receiver = new DigitalInput(1);
  /**
   * Creates a new SensorSubsystem.
   */
  public SensorSubsystem() {

  }

  public void emit()
  {
    Emitter.set(true);
  }

  public void stop()
  {
    Emitter.set(false);
  }

  public boolean receive()
  {
    return Receiver.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
