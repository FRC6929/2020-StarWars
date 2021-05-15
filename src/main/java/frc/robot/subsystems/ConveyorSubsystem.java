/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConveyorSubsystem extends SubsystemBase {
  /**
   * Creates a new ConveyorSubsystem.
   */

   CANSparkMax Conveyor1;
   CANSparkMax Conveyor2;
   SensorSubsystem sensorSubsystem;

  public ConveyorSubsystem() {
    Conveyor1 = new CANSparkMax(8, MotorType.kBrushless);
    Conveyor2 = new CANSparkMax(9, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void defaultMoveBalls(double speed1, double speed2){
    Conveyor1.set(speed1);
    Conveyor2.set(speed2);
  }

  public void ManualMoveBallsForward(){
    Conveyor1.set(1);
    Conveyor2.set(1);
    }

  public void ManualMoveBallsReverse(){
    Conveyor1.set(-1);
    Conveyor2.set(-1);
  }

  public void stop(){
    Conveyor1.set(0);
    Conveyor2.set(0);
  }

}
