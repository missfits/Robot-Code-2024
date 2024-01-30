package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.Autos;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.IntakeBackwardCommand;
import frc.robot.commands.IntakeForwardCommand;
import frc.robot.commands.PivotBackwardCommand;
import frc.robot.commands.PivotForwardCommand;
import frc.robot.commands.DefaultIntakeCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the Robot
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // Instance variables
    // The robot's subsystems and commands are defined here
    private static final OI m_humanControl = new OI();
    public static final Drivetrain m_drivetrain = new Drivetrain(m_humanControl);
    private static final Intake m_intake = new Intake();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
        m_drivetrain.setDefaultCommand(new ArcadeDriveCommand(m_drivetrain, m_humanControl));
        m_intake.setDefaultCommand(new DefaultIntakeCommand(m_intake));
    }

    /**
     * Use this method to define your trigger -> command mappings. Triggers can be created via the
     * Trigger (java.util.function.BooleanSupplier) constructor with an arbitrary predicate, or via
     * the named factories in CommandGenericHID's subclasses for CommandXboxController or
     * CommandPS4Controller controllers or CommandJoystick Flight joysticks.
     */
    private void configureBindings() {
        OI.m_coPilotXbox.b().whileTrue(new IntakeForwardCommand(m_intake));
        OI.m_coPilotXbox.a().whileTrue(new IntakeBackwardCommand(m_intake));
        OI.m_coPilotXbox.x().whileTrue(new PivotBackwardCommand(m_intake));
        OI.m_coPilotXbox.y().whileTrue(new PivotForwardCommand(m_intake));
    }

    /**
     * Use this to pass the autonomous command to the main Robot class.
     *
     * @return  the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // Taxi command that will be run in autonomous
        return Autos.taxiAuto(m_drivetrain);
    }
}