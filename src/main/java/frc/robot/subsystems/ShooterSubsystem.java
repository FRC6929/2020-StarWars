package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new FlywheelSubsystem.
   */

    CANSparkMax Shooter1;
    CANSparkMax Shooter2;


  public ShooterSubsystem() {
     Shooter1 = new CANSparkMax(1, MotorType.kBrushless);
     Shooter2 = new CANSparkMax(2, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot(double distance){
    
    double motorSpeed = distance;//add function

    Shooter1.set(motorSpeed);
    Shooter2.set(motorSpeed);
  }
}