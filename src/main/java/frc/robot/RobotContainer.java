package frc.robot;

import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTableEntry;
// Wpilib
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Commandes
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.LifterDown;
import frc.robot.commands.LifterUp;
//import frc.robot.commands.ShootingCommand;
import frc.robot.commands.ShootingSpeedCommand;
// Sous-systemes 
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.LifterSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
//import frc.robot.subsystems.AhrsSubsystem;
import frc.robot.subsystems.CameraSubsystem;

// Wpilib 2: electric boogalo
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final CameraSubsystem cameraSubsystem = new CameraSubsystem();
  //private final LifterSubsystem lifterSubsystem = new LifterSubsystem();
  //private final AhrsSubsystem ahrsSubsystem = new AhrsSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private ShootingSpeedCommand ShooterCommand;


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

    if(Constants.has_shooter)
    {
      System.out.println("Creation d'un shooter");
      this.shooterSubsystem = new ShooterSubsystem();
      this.ShooterCommand = new ShootingSpeedCommand(shooterSubsystem, cameraSubsystem);
    }
    else
    {
      System.out.println("Pas de shooter");
      this.shooterSubsystem = null;
      this.ShooterCommand = null; // Peut etre pas une bonne chose
    }
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //Shooter
    if(Constants.has_shooter)
    {
      new JoystickButton(coJoystick, Constants.shooter_btn_id).whenActive(new ShootingSpeedCommand(shooterSubsystem, cameraSubsystem));
    }

    //Lifter
    if(Constants.has_lifter)
    {
      //new JoystickButton(coJoystick, 11).toggleWhenPressed(new LifterUp(lifterSubsystem));
      //new JoystickButton(coJoystick, 12).toggleWhenPressed(new LifterDown(lifterSubsystem));
    }
  
    //Drive
    ShuffleboardTab drive = Shuffleboard.getTab("Drive");
    NetworkTableEntry maxSpeed = drive.add("Max Speed", 
   1).getEntry();
    NetworkTableEntry minSpeed = drive.add("Min Speed", 1).getEntry();
          
    vitesse1 = minSpeed.getDouble(0);
    vitesse2 = maxSpeed.getDouble(0);
    
    if(new JoystickButton(piJoystick, Constants.speedy_btn_id).get()){
      speedbtn = !speedbtn;
      SmartDashboard.putBoolean("Speedy Boi", speedbtn);
    }

    new JoystickButton(piJoystick, Constants.drive_btn_id).toggleWhenPressed(new DefaultDrive(driveTrainSubsystem, 
    () -> piJoystick.getRawAxis(2)-piJoystick.getRawAxis(3), 
    () -> piJoystick.getRawAxis(0), 
    vitesse1, vitesse2, speedbtn));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public double shooterSpeed(){
    return (piJoystick.getRawAxis(0)+1)/2;
  }
}
