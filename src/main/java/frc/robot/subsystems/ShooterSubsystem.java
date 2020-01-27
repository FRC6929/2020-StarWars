package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new FlywheelSubsystem.
   */

    CANSparkMax m_shooter1;
    CANSparkMax m_shooter2;
    CANEncoder m_shooterEncoder;
    

  public ShooterSubsystem() {
     m_shooter1 = new CANSparkMax(5, MotorType.kBrushless);
     m_shooter2 = new CANSparkMax(6, MotorType.kBrushless);

     m_shooter1.setInverted(false);
     m_shooter2.setInverted(false);

    
     m_shooterEncoder = m_shooter1.getEncoder();

     
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot(double distance){
    double motorSpeed;
    double finalMotorSpeed;

    motorSpeed = distance;

    //TODO Add distance to speed conversion function
    //TODO Ecrire les commentaires en francais et etre vraiment gossant
    //TODO non
    if(motorSpeed > -(m_shooterEncoder.getVelocity()/5700 - 0.1)){
      finalMotorSpeed = 1;
    }
    else{
    finalMotorSpeed = motorSpeed;
    }
    
    if(finalMotorSpeed>1){
      finalMotorSpeed = 1;
    }
    
    m_shooter1.set(finalMotorSpeed);
    m_shooter2.follow(m_shooter1);
    SmartDashboard.putNumber("Shooter RPM", m_shooterEncoder.getVelocity());
  }

  public void stop(){
    m_shooter1.set(0);
    m_shooter2.follow(m_shooter1);
  }
}
