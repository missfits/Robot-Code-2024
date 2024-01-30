package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * Subsystem example.
 */
public class ExampleSubsystem extends SubsystemBase {
    /**
     * Constructs an ExampleSubsystem subsystem.
     */
    public ExampleSubsystem() {}

    /**
     * Factory method to create command (not being used right now).
     *
     * @return  a command
     */
    public Command exampleMethodCommand() {
        // Inline construction of command
        // Subsystem::RunOnce implicitly requires `this` subsystem
        return runOnce(
            () -> {
            // One-time action
            }
        );
    }

    /**
     * Method to query a boolean state of the subsystem (not being used right now).
     *
     * @return  value of some boolean subsystem state
     */
    public boolean exampleCondition() {
        // Query some boolean state, such as a digital sensor
        return false;
    }

    /**
     * Method to be called once every scheduler run (not being used right now).
     */
    @Override
    public void periodic() {
        // Typically used for telemetry and other periodic actions that do not interfere with commands
    }

    /**
     * Method to be called once every scheduler run during simulation (not being used right now).
     */
    @Override
    public void simulationPeriodic() {
        // Can be used to update state of robot
    }
}
