/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase {
  /**
   * Creates a new CameraSubsystem.
   */
  public CameraSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getVerticalAngle(){
    //return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return 0.6;
  }
  public double getAngleOffset(){
    //return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  return 90;
  }
  public double getTargetNumber(){
    //return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    return 1;
  }
  public double getDistance(double LifterHeight){

    double baseCameraHeight = 0.6604;
    double lifterHeight = LifterHeight;
    double targetHeight = 2.4892;

    
    //goals : find distance x of a rectangle triangle 
    /*known : 
      the encoder measure of the lifter with the camera
      the base height of the camera 
      the angle point as seen from the camera
    */

    //1) Camera height

    double CameraHeight = baseCameraHeight + lifterHeight;

    
    double y = CameraHeight - targetHeight;
    
    //2) Tangeant

    double cTan = Math.tan(getVerticalAngle());

    //3) distance

    double x = 1 / (cTan / y) ;

    return x;
  }
}
