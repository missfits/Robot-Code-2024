package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.button.Trigger;

// import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.ArcadeDriveCommand;

// indexer and intake commands
import frc.robot.commands.IndexerUpCommand;
import frc.robot.commands.IndexerDownCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.commands.IntakeInCommand;
import frc.robot.commands.IntakeIndexCommandBackup;
import frc.robot.commands.IntakeIndexCommand;
import frc.robot.commands.OuttakeIndexCommand;

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

import frc.robot.commands.DistanceDriveCommand;
import frc.robot.commands.RotationCommand;

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
 * "declarative" paradigm, very little robot logic should actually be handled in the Robot periodic
 * methods (other than the scheduler calls). Instead, the structure of the robot (including
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

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();

        // set default commands
        m_drivetrain.setDefaultCommand(new ArcadeDriveCommand(m_drivetrain, m_humanControl));
        // m_hood.setDefaultCommand(new PrintHoodEncoder(m_hood));

        // auto routines
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
        OI.m_coPilotXbox.a().whileTrue(new ShooterHoodBackward(m_shooter, m_hood)); // emergency use button that should not be pressed in normal circumstances
        OI.m_coPilotXbox.b().whileTrue(new OuttakeIndexCommand(m_indexer, m_intake)); // outtaking should not normally be necessary
        OI.m_coPilotXbox.x().whileTrue(new IntakeIndexCommand(m_indexer, m_intake));
        OI.m_coPilotXbox.y().whileTrue(new IndexerUpCommand(m_indexer));

        OI.m_coPilotXbox.leftTrigger().whileTrue(new ShooterSpeakerCommand(m_shooter));
        OI.m_coPilotXbox.rightTrigger().whileTrue(new ShooterAmpCommand(m_shooter, m_hood));

        OI.m_coPilotXbox.leftBumper().whileTrue(new HoodPivotForwardCommand(m_hood));
        OI.m_coPilotXbox.rightBumper().whileTrue(new HoodPivotBackwardCommand(m_hood));

        // back and start buttons are backup commands for if the hood encoders mess up
        OI.m_coPilotXbox.back().whileTrue(new HoodPivotForwardBackup(m_hood));
        OI.m_coPilotXbox.start().whileTrue(new HoodPivotBackwardBackup(m_hood));
    
        OI.m_coPilotXbox.leftStick().whileTrue(new IntakeIndexCommandBackup(m_indexer, m_intake)); // backup intakeindex command in case beam break has issues
        // OI.m_coPilotXbox.leftStick().whileTrue(new HoodRollerCommand(m_hood)); // for testing only! 
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