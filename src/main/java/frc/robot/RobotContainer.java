// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.Constants.OperatorConstants;

// import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.IndexerUpCommand;
import frc.robot.commands.IndexerDownCommand;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.IndexerDownCommand;
import frc.robot.commands.IndexerUpCommand;
import frc.robot.commands.IntakeIndexCommand;

import frc.robot.commands.IntakeOutCommand;
import frc.robot.commands.IntakeInCommand;
import frc.robot.commands.IntakeIndexCommand;
import frc.robot.commands.OuttakeIndexCommand;
import frc.robot.commands.PivotBackwardCommand;
import frc.robot.commands.PivotForwardCommand;
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.commands.RotationCommand;
import frc.robot.commands.ShooterAmpCommand;
import frc.robot.commands.ShooterSpeakerCommand;
import frc.robot.commands.ShooterOutCommand;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

// ***NEED TO BE UPDATED FOR 2024 SEASON***

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private static final OI m_humanControl = new OI();
  public static final Drivetrain m_drivetrain = new Drivetrain(m_humanControl);
  private static final Indexer m_indexer = new Indexer();
  private static final Intake m_intake = new Intake();
  private static final Shooter m_shooter = new Shooter();
  private static final Hood m_hood = new Hood();

  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  // public static SequentialCommandGroup m_driveTwice = new SequentialCommandGroup(
  //       // new SuctionOnCommand(m_gripper),
  //       // new ArmPlaceHighCommand(m_arm),
  //       // new SuctionOffCommand(m_gripper),
  //       new DistanceDriveCommand(m_drivetrain, 1),
  //       new DistanceDriveCommand(m_drivetrain, 2)
  // );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_drivetrain.setDefaultCommand(new ArcadeDriveCommand(m_drivetrain, m_humanControl));

    m_chooser.addOption("Drive 2 meters", new DistanceDriveCommand(m_drivetrain, 2));
    m_chooser.addOption("Rotate 90 degrees", new RotationCommand(m_drivetrain, 90));
    // m_chooser.addOption("Double drive", m_driveTwice);

    ShuffleboardTab compTab = Shuffleboard.getTab("Comp HUD");
    compTab.add("Auto Chooser", m_chooser).withSize(3, 2);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() { // temp for testing 

    OI.m_coPilotXbox.a().whileTrue(new IntakeIndexCommand(m_indexer, m_intake));
    OI.m_coPilotXbox.b().whileTrue(new OuttakeIndexCommand(m_indexer, m_intake));
    OI.m_coPilotXbox.x().whileTrue(new IntakeOutCommand(m_intake));
    OI.m_coPilotXbox.y().whileTrue(new IntakeInCommand(m_intake));
    OI.m_coPilotXbox.leftStick().whileTrue(new ShooterOutCommand(m_shooter));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // the selected command from shuffleboard that will be run in autonomous
    return m_chooser.getSelected();
  }
}