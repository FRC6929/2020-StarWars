package frc.robot;

import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTableEntry;
// Wpilib
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Commandes
//import frc.robot.commands.ShootingCommand;
import frc.robot.commands.*;
// Sous-systemes 
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
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
 
  final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final CameraSubsystem cameraSubsystem = new CameraSubsystem();
  private final LifterSubsystem lifterSubsystem = new LifterSubsystem();
  final AhrsSubsystem ahrsSubsystem = new AhrsSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private ShootingSpeedCommand ShooterCommand;
  //private final ShooterTest shooterTest = new ShooterTest();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ConveyorSubsystem conveyorSubsystem = new ConveyorSubsystem();

  String JoystickMode;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  
   SendableChooser<Command> autoPos = new SendableChooser<>();
  
  Joystick piJoystick = new Joystick(0);
  Joystick coJoystick1 = new Joystick(1);
  Joystick coJoystick2 = new Joystick(2);

  double vitesse1;
  double vitesse2;

  public boolean speedbtn = false;

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    if(new JoystickButton(coJoystick1, 7).get()){
      JoystickMode = "Intake";
    }
    if(new JoystickButton(coJoystick1, 8).get()){
      JoystickMode = "Lifter";
    }



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

/*
    autoPos.setDefaultOption("no auto1", new AutonomousCommandsTest(driveTrainSubsystem, 0, 0).withTimeout(2));
    autoPos.addOption("shooter2", new AutonomousCommandsTest(driveTrainSubsystem, 0.4, 0).withTimeout(2));
    autoPos.addOption("middle3", new AutonomousCommandsTest(driveTrainSubsystem, -0.4, 0).withTimeout(2));
    autoPos.addOption("charger4", new AutonomousCommandsTest(driveTrainSubsystem, 0, 0.4).withTimeout(2));
    */
    autoPos.setDefaultOption("no auto1", new AutonomousCommandGroup(driveTrainSubsystem,ahrsSubsystem,shooterSubsystem,cameraSubsystem,0));
    autoPos.addOption("shooter2", new AutonomousCommandGroup(driveTrainSubsystem,ahrsSubsystem,shooterSubsystem,cameraSubsystem,1));
    autoPos.addOption("middle3", new AutonomousCommandGroup(driveTrainSubsystem,ahrsSubsystem,shooterSubsystem,cameraSubsystem,2));
    autoPos.addOption("charger4", new AutonomousCommandGroup(driveTrainSubsystem,ahrsSubsystem,shooterSubsystem,cameraSubsystem,3));
    SmartDashboard.putData(autoPos);

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
      new JoystickButton(coJoystick1, Constants.kShooter.shooter_btn_id).whenActive(new ShootingSpeedCommand(shooterSubsystem, cameraSubsystem));
    }

    //Lifter
    if(Constants.has_lifter)
    {
      if(JoystickMode == "Lifter"){
        if(!new JoystickButton(coJoystick2, 2).get() && !new JoystickButton(coJoystick2, 3).get() && !new JoystickButton(coJoystick2, 6).get()){
      new JoystickButton(coJoystick2, 0).toggleWhenPressed(new InstantCommand(() -> lifterSubsystem.panique(), lifterSubsystem));
      new JoystickButton(coJoystick2, 1).toggleWhenPressed(new InstantCommand(() -> lifterSubsystem.retract(), lifterSubsystem));
    }
      new JoystickButton(coJoystick2, 2).whileHeld(new RunCommand(() -> lifterSubsystem.manualUp(), lifterSubsystem));
      new JoystickButton(coJoystick2, 3).whileHeld(new RunCommand(() -> lifterSubsystem.manualDown(), lifterSubsystem));
    }
    }
  
    
    //Conveyor
      if(JoystickMode == "Intake"){
        
    new JoystickButton(coJoystick2, 0).whenPressed(new RunCommand(() -> intakeSubsystem.ballsIn(), intakeSubsystem));
    new JoystickButton(coJoystick2, 1).whenPressed(new RunCommand(() -> intakeSubsystem.pushBalls(), intakeSubsystem));
    if(!new JoystickButton(coJoystick2, 0).get() && !new JoystickButton(coJoystick2, 1).get() ){
    new JoystickButton(coJoystick2, 2).whenPressed(new RunCommand(() -> intakeSubsystem.intakeOut(), intakeSubsystem));
    new JoystickButton(coJoystick2, 3).whenPressed(new RunCommand(() -> intakeSubsystem.intakeIn(), intakeSubsystem));
    }

    new JoystickButton(coJoystick1, 4).whileHeld(() -> conveyorSubsystem.ManualMoveBallsForward(), conveyorSubsystem);
    new JoystickButton(coJoystick1, 5).whileHeld(() -> conveyorSubsystem.ManualMoveBallsReverse(), conveyorSubsystem);
  }
    //Drive


    
    
     


          
    ShuffleboardTab drive = Shuffleboard.getTab("Drive");
    NetworkTableEntry maxSpeed = drive.add("Max Speed", 1).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    NetworkTableEntry minSpeed = drive.add("Min Speed", 1).withWidget(BuiltInWidgets.kNumberSlider).getEntry();
    
   /*SmartDashboard.putBoolean("quoi", new JoystickButton(piJoystick, 2).get());
    if(new JoystickButton(piJoystick, 2).get()){
      speedbtn = !speedbtn;
      
      SmartDashboard.putBoolean("Speedy Boi", speedbtn);
     
      
      
      
    }
    //new JoystickButton(piJoystick, Constants.speedy_btn_id).whenPressed(() -> speedbtn = true).whenReleased(() -> speedbtn = false);
if(speedbtn){
        driveTrainSubsystem.setSpeedFast();
      }
      else{
        driveTrainSubsystem.setSpeedSlow();
      }
*/
      //new JoystickButton(piJoystick, Constants.speedy_btn_id).whenPressed(new ToggleSpeedCommand(driveTrainSubsystem));
    
    /*new JoystickButton(piJoystick, Constants.drive_btn_id).whenInactive(new DefaultDrive(driveTrainSubsystem, 
    () -> piJoystick.getRawAxis(2)-piJoystick.getRawAxis(3), 
    () -> piJoystick.getRawAxis(0), 
    minSpeed, maxSpeed, ));*/

      new JoystickButton(piJoystick, Constants.kDrive.drive_btn_id).
      whenPressed(new DefaultDrive(
        driveTrainSubsystem,
      lifterSubsystem,
      () -> piJoystick.getRawAxis(2)-piJoystick.getRawAxis(3), 
      () -> piJoystick.getRawAxis(0), 
      minSpeed, maxSpeed, true));
      
      new JoystickButton(piJoystick, 2).
      whenPressed(new DefaultDrive(
        driveTrainSubsystem,
        lifterSubsystem,
      () -> piJoystick.getRawAxis(2)-piJoystick.getRawAxis(3), 
      () -> piJoystick.getRawAxis(0), 
      minSpeed, maxSpeed, false)/*.withTimeout(1).andThen(new ShootingTestComm(shooterTest).withTimeout(2))*/);

      /*new JoystickButton(piJoystick, 3).
      whenPressed(new DefaultDrive(driveTrainSubsystem,
      () -> piJoystick.getRawAxis(2)-piJoystick.getRawAxis(3), 
      () -> piJoystick.getRawAxis(0), 
      minSpeed, maxSpeed, false).withTimeout(1).alongWith(new ShootingTestComm(shooterTest).withTimeout(2)));
*/
      //new JoystickButton(piJoystick, 10).whileHeld(new CommandGroupExamples(driveTrainSubsystem, shooterSubsystem, cameraSubsystem, minSpeed, maxSpeed, shooterTest));
      //new JoystickButton(piJoystick, 9).whileHeld(new ShootingTestComm(shooterTest));
      

    }

  


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  //public double shooterSpeed(){
    //return (piJoystick.getRawAxis(0)+1)/2;
  //}

  public Command getAutonomousCommand(){
    //return autoPos.getSelected();
    return autoPos.getSelected();
  }
}
