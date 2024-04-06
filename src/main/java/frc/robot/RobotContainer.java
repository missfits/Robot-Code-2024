// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.Constants.OperatorConstants;

// import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.ArcadeDriveCommand;

// indexer and intake commands
import frc.robot.commands.IndexerUpCommand;
import frc.robot.commands.IndexerDownCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.commands.IntakeInCommand;
import frc.robot.commands.IntakeIndexCommandBackup;
import frc.robot.commands.AutoIntakeCommand;
import frc.robot.commands.IntakeIndexCommand;
import frc.robot.commands.OuttakeIndexCommand;
import frc.robot.commands.PrintClimberEncoder;
import frc.robot.commands.BeamBreakCommand;
import frc.robot.commands.ClimberUpCommand;
import frc.robot.commands.ClimberDownLeftCommand;
import frc.robot.commands.ClimberDownRightCommand;
// hood pivot commands
import frc.robot.commands.HoodPivotBackwardCommand;
import frc.robot.commands.HoodPivotForwardCommand;
import frc.robot.commands.HoodRollerCommand;
import frc.robot.commands.HoodPivotBackwardBackup;
import frc.robot.commands.HoodPivotForwardBackup;
import frc.robot.commands.PrintHoodEncoder;

// shooting commands
import frc.robot.commands.ShooterAmpCommand;
import frc.robot.commands.ShooterSpeakerCommand;
import frc.robot.commands.ShooterOutCommand;
import frc.robot.commands.ShooterHoodBackward;

// auto commands
import frc.robot.commands.DistanceDriveCommand;
import frc.robot.commands.RotationCommand;
import frc.robot.commands.NavXRotationCommand;

// subsystems
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.LeftClimber;
import frc.robot.subsystems.RightClimber;
import frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;

import java.util.function.BooleanSupplier;

// ***NEED TO BE UPDATED FOR 2024 SEASON***

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // robot subsystems
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private static final OI m_humanControl = new OI();
  public static final Drivetrain m_drivetrain = new Drivetrain(m_humanControl);
  private static final Indexer m_indexer = new Indexer();
  private static final Intake m_intake = new Intake();
  private static final Shooter m_shooter = new Shooter();
  private static final Hood m_hood = new Hood();
  private static final LeftClimber m_leftClimber = new LeftClimber();
  private static final RightClimber m_rightClimber = new RightClimber();
  private static final XboxController pilot = new XboxController(0);
  private static final XboxController copilot = new XboxController(1);

  public SendableChooser<Command> m_chooser = new SendableChooser<>();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // set default commands
    m_drivetrain.setDefaultCommand(new ArcadeDriveCommand(m_drivetrain, m_humanControl));
    // m_hood.setDefaultCommand(new PrintHoodEncoder(m_hood));
    m_indexer.setDefaultCommand(new BeamBreakCommand(m_indexer)); // for testing
    // m_climber.setDefaultCommand(new PrintClimberEncoder(m_climber));

    m_chooser.addOption("Drive 1 meter (testing)", new DistanceDriveCommand(m_drivetrain, 1));
    m_chooser.addOption("Rotate 90 degrees (new/navx rotation)", new NavXRotationCommand(m_drivetrain, 90));


    m_chooser.addOption("SFR ELIMS BLUE", Autos.elimsBlue(m_drivetrain, m_indexer, m_shooter));
    m_chooser.addOption("SFR ELIMS RED", Autos.elimsRed(m_drivetrain, m_indexer, m_shooter));
    
    m_chooser.addOption("Rotate 90 degrees (old rotation)", new RotationCommand(m_drivetrain, 90));

    m_chooser.addOption("Just shoot", Autos.justShoot(m_indexer, m_shooter));

    m_chooser.addOption("2pc auto from front", Autos.frontSpeaker2pc(m_drivetrain, m_intake, m_indexer, m_shooter));
    m_chooser.addOption("2pc auto from left", Autos.leftSpeaker2pc(m_drivetrain, m_intake, m_indexer, m_shooter));
    m_chooser.addOption("2pc auto from right", Autos.rightSpeaker2pc(m_drivetrain, m_intake, m_indexer, m_shooter));

    m_chooser.addOption("3pc auto RED", Autos.frontSpeaker3pcRed(m_drivetrain, m_intake, m_indexer, m_shooter));
    m_chooser.addOption("3pc auto BLUE", Autos.frontSpeaker3pcBlue(m_drivetrain, m_intake, m_indexer, m_shooter));

    m_chooser.addOption("Taxi forward", Autos.taxiAuto(m_drivetrain));
    m_chooser.addOption("Shoot and taxi from front", Autos.shootTaxiFront(m_drivetrain, m_indexer, m_shooter));
    m_chooser.addOption("Shoot and taxi from left", Autos.shootTaxiLeft(m_drivetrain, m_indexer, m_shooter));
    m_chooser.addOption("Shoot and taxi from right", Autos.shootTaxiRight(m_drivetrain, m_indexer, m_shooter));
    
    ShuffleboardTab compTab = Shuffleboard.getTab("Comp HUD");
    compTab.add("Auto Chooser", m_chooser).withSize(6, 4);

    BooleanSupplier bbSupplier = () -> m_indexer.getBeamBreakSignal();
    compTab.addBoolean("Beam break signal", bbSupplier).withWidget(BuiltInWidgets.kBooleanBox);
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

    OI.m_coPilotXbox.a().whileTrue(new ShooterHoodBackward(m_shooter, m_hood)); // emergency use button that should not be pressed in normal circumstances
    OI.m_coPilotXbox.b().whileTrue(new OuttakeIndexCommand(m_indexer, m_intake)); // outtaking should not normally be necessary
    OI.m_coPilotXbox.x().whileTrue(new IntakeIndexCommand(m_indexer, m_intake, copilot, copilot));
    OI.m_coPilotXbox.y().whileTrue(new IndexerUpCommand(m_indexer));

    OI.m_coPilotXbox.leftTrigger().whileTrue(new ShooterSpeakerCommand(m_shooter));
    OI.m_coPilotXbox.rightTrigger().whileTrue(new ShooterAmpCommand(m_shooter, m_hood));

    OI.m_coPilotXbox.leftBumper().whileTrue(new HoodPivotForwardCommand(m_hood));
    OI.m_coPilotXbox.rightBumper().whileTrue(new HoodPivotBackwardCommand(m_hood));

    // back and start buttons are backup commands for if the hood encoders mess up
    OI.m_coPilotXbox.back().whileTrue(new HoodPivotForwardBackup(m_hood));
    OI.m_coPilotXbox.start().whileTrue(new HoodPivotBackwardBackup(m_hood));
  
    // OI.m_coPilotXbox.leftStick().whileTrue(new IntakeIndexCommandBackup(m_indexer, m_intake)); // backup intakeindex command in case beam break has issues
    // OI.m_coPilotXbox.leftStick().whileTrue(new ClimberUpCommand(m_climber)); // for testing only!
    OI.m_coPilotXbox.leftStick().whileTrue(new ClimberDownRightCommand(m_rightClimber)); 
    OI.m_coPilotXbox.rightStick().whileTrue(new ClimberDownLeftCommand(m_leftClimber));
    // are the CAN IDs switched?? CHECK

    // for unwinding the climber after match
    // requires driver to first hold down b button, then x button
    OI.m_driverXbox.b().whileTrue(new ClimberUpCommand(m_leftClimber, m_rightClimber, OI.m_driverXbox)); 
    
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