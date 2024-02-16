// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.Autos;
import frc.robot.commands.IndexerUpCommand;
import frc.robot.commands.IndexerDownCommand;
// import frc.robot.commands.ExampleCommand;
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
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_drivetrain.setDefaultCommand(new ArcadeDriveCommand(m_drivetrain, m_humanControl));
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
    // the taxi command that will be run in autonomous
    return Autos.taxiAuto(m_drivetrain);
  }
}