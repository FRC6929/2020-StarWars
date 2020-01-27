package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
// Wpilib
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Commandes
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ShootingSpeedCommand;

// Sous-systemes 
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.CameraSubsystem;

// Wpilib 2: electric boogalo
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final CameraSubsystem cameraSubsystem = new CameraSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  //private final ShootingSpeedCommand ShooterCommand = new ShootingSpeedCommand(shooterSubsystem, cameraSubsystem);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  Joystick piJoystick = new Joystick(0);
  Joystick coJoystick = new Joystick(1);

  double vitesse1;
  double vitesse2;

  boolean speedbtn = false;

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(coJoystick, 1).toggleWhenPressed(new ShootingSpeedCommand(shooterSubsystem, cameraSubsystem));

    
    
    if(new JoystickButton(piJoystick, 0).get()){
      speedbtn = !speedbtn;
      SmartDashboard.putBoolean("Speedy Boi", speedbtn);

      vitesse1 = SmartDashboard.getNumber("vitesse 1", 0);
      vitesse2 = SmartDashboard.getNumber("vitesse2", 0);
    
    }

     new JoystickButton(piJoystick, 1).whenInactive(new DefaultDrive(driveTrainSubsystem, piJoystick.getRawAxis(2)-piJoystick.getRawAxis(3), piJoystick.getRawAxis(0), vitesse1, vitesse2, speedbtn));
  
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public double shooterSpeed(){
    return (piJoystick.getRawAxis(0)+1)/2;
  }
}
