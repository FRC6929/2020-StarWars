package frc.robot;

import frc.robot.Constants;
import frc.robot.Constants.kSensors;
import frc.robot.subsystems.AhrsSubsystem;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.SensorSubsystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.AutonomousCommands;
import frc.robot.commands.UpdateSensor;
import frc.robot.subsystems.SensorSubsystem;
//import frc.robot.subsystems.DriveTrainSubsystem;

//import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_updatesensor;
  //private Command m_autoCommand;
  //private Command shooterCommand;

  private RobotContainer m_robotContainer;
  private SensorSubsystem sensorSubsystem;
  private ColorSensor colorSensor;
  private final AhrsSubsystem ahrs = new AhrsSubsystem();
  //private final DriveTrainSubsystem drive = new DriveTrainSubsystem();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    
    //ShuffleboardTab autonomous = Shuffleboard.getTab("auto");
    //NetworkTableEntry autoPos = autonomous.add("position", 0).withWidget(widgetType).getEntry();
    


    m_robotContainer.driveTrainSubsystem.resetForPos();
    ahrs.resetAngle();
    
    //m_autonomousCommand = new AutonomousCommands(m_robotContainer.driveTrainSubsystem, ahrs);

    
    if(Constants.has_sensor)
    {
      colorSensor = new ColorSensor();
      sensorSubsystem =  new SensorSubsystem();
      m_updatesensor = new UpdateSensor(sensorSubsystem);
          
    }
    else
    {
      m_updatesensor = null;
      sensorSubsystem = null;
    }
  //m_autoCommand = new AutonomousCommands(drive, ahrs, autoPos.getDouble(0));
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    if(Constants.has_sensor)
    {
      colorSensor.GetColor();
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    m_robotContainer.driveTrainSubsystem.resetForPos();
    ahrs.resetAngle();
    
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    ahrs.resetAngle();
    // schedule the autonomous command (example)
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null){
      m_autonomousCommand.schedule();
    
    }
      
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if(m_autonomousCommand != null){
      m_autonomousCommand.cancel();
    }
    m_robotContainer.ahrsSubsystem.resetAngle();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(Constants.has_sensor)
    {
      m_updatesensor.execute();
    }
    SmartDashboard.putNumber("EncoderInMeters", m_robotContainer.driveTrainSubsystem.getForMeterPos());
    SmartDashboard.putNumber("DriveEncoders", m_robotContainer.driveTrainSubsystem.getForPos());
    SmartDashboard.putNumber("AhrsAngle", m_robotContainer.ahrsSubsystem.getAngle());
    //m_robotContainer.updateJoystickMode();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
