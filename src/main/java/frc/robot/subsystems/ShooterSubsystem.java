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
    CANEncoder m_fuck;
    

  public ShooterSubsystem() {
     m_shooter1 = new CANSparkMax(5, MotorType.kBrushless);
     m_shooter2 = new CANSparkMax(6, MotorType.kBrushless);

     m_shooter1.setInverted(false);
     m_shooter2.setInverted(false);

     m_shooterEncoder = new CANEncoder(m_shooter1);
      m_fuck = new CANEncoder(m_shooter2);  

     
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    

      }
  public void shoot(double distance){
    double motorSpeed;
    double finalMotorSpeed;
    
    System.out.println(-m_shooterEncoder.getVelocity());
    //SmartDashboard.putNumber("rpm",-m_shooterEncoder.getVelocity());

    double trueMotorSpeed = m_shooterEncoder.getVelocity() /*/ m_shooterEncoder.getVelocityConversionFactor()*/;
    //SmartDashboard.putNumber("please", trueMotorSpeed);
    
    motorSpeed = distance;

    
    if(motorSpeed > -(trueMotorSpeed - 0.1)){
      finalMotorSpeed = 1;
      
    }
    else{
      finalMotorSpeed = motorSpeed;
      
    }
    
    if(finalMotorSpeed>1){
      finalMotorSpeed = 1;
    }
    
    m_shooter1.set(-finalMotorSpeed/4);
    m_shooter2.follow(m_shooter1);
    SmartDashboard.putNumber("eillecrisse", m_shooterEncoder.getVelocity());    
    SmartDashboard.putNumber("latabarnac", m_shooterEncoder.getPosition());

  }

  public void stop(){
    m_shooter1.set(0);
    m_shooter2.follow(m_shooter1);
  }
}
