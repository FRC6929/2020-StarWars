/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
  /**
   * Creates a new ColorSensor.
   */
  ColorSensorV3 cV3;
  public ColorSensor() {
    cV3 = new ColorSensorV3(Port.kOnboard);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public String GetColor(){
    SmartDashboard.putNumber("red", cV3.getRed());  
    SmartDashboard.putNumber("blue", cV3.getBlue());  
    SmartDashboard.putNumber("green", cV3.getGreen()); 
    
    
    return "o";
  }

  public String getTargetColor(){
    return DriverStation.getInstance().getGameSpecificMessage();
  }

  public boolean isOnTarget(){
    if(GetColor() == getTargetColor()){
      return true;
    }
    else{
      return false;
    }
  }


}
