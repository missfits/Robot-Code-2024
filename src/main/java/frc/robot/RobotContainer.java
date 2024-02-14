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
import frc.robot.commands.DefaultIntakeCommand;
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.commands.RotationCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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

  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  private DistanceDriveCommand m_distanceDriveCommand = new DistanceDriveCommand(m_drivetrain, 2);
  private RotationCommand m_rotationCommand = new RotationCommand(m_drivetrain, 90);
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
    m_intake.setDefaultCommand(new DefaultIntakeCommand(m_intake));

    m_chooser.addOption("Drive 2 meters", m_distanceDriveCommand);
    m_chooser.addOption("Rotate 180 degrees", m_rotationCommand);
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
  private void configureBindings() {

    OI.m_coPilotXbox.b().whileTrue(new IntakeForwardCommand(m_intake));
    OI.m_coPilotXbox.a().whileTrue(new IntakeBackwardCommand(m_intake));
    OI.m_coPilotXbox.x().whileTrue(new PivotBackwardCommand(m_intake));
    OI.m_coPilotXbox.y().whileTrue(new PivotForwardCommand(m_intake));
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