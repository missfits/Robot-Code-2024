// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.Autos;
// import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.IntakeBackwardCommand;
import frc.robot.commands.IntakeForwardCommand;
import frc.robot.commands.PivotBackwardCommand;
import frc.robot.commands.PivotForwardCommand;
import frc.robot.commands.ShootAmpCommand;
import frc.robot.commands.ShootSpeakerCommand;
import frc.robot.commands.ShootBackwardCommand;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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
  private static final Intake m_intake = new Intake();
  private static final Hood m_hood = new Hood();
  private static final Shooter m_shooter = new Shooter();

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
  private void configureBindings() { // need to update

    OI.m_coPilotXbox.b().whileTrue(new ShootSpeakerCommand(m_shooter));
    OI.m_coPilotXbox.a().whileTrue(new ShootAmpCommand(m_shooter));
    OI.m_coPilotXbox.x().whileTrue(new PivotBackwardCommand(m_hood));
    OI.m_coPilotXbox.y().whileTrue(new PivotForwardCommand(m_hood));
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