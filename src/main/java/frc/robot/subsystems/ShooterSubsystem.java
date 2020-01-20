package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new FlywheelSubsystem.
   */

    CANSparkMax m_shooter1;
    CANSparkMax m_shooter2;
    CANEncoder m_shooterEncoder;
    CANPIDController m_shooterPID;

  public ShooterSubsystem() {
     m_shooter1 = new CANSparkMax(5, MotorType.kBrushless);
     m_shooter2 = new CANSparkMax(6, MotorType.kBrushless);

     m_shooterPID = m_shooter1.getPIDController();
     m_shooterEncoder = m_shooter1.getEncoder();

     m_shooterPID.setP(0.0001);
     m_shooterPID.setI(0);
     m_shooterPID.setD(0.01);
     m_shooterPID.setOutputRange(0, 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot(double distance){
    double motorSpeed;
    double finalMotorSpeed;

    motorSpeed = distance;

    //TODO - Add distance to speed conversion function

    finalMotorSpeed = motorSpeed;

    m_shooterPID.setFF(15/5700*finalMotorSpeed);

    m_shooterPID.setReference(finalMotorSpeed, ControlType.kVelocity);
    m_shooter2.follow(m_shooter1);
    SmartDashboard.putNumber("Shooter RPM", m_shooterEncoder.getVelocity());
  }

  public void stop(){
    m_shooter1.set(0);
    m_shooter2.follow(m_shooter1);
  }
}
