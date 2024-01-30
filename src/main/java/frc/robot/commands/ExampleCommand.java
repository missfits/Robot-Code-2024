package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ExampleSubsystem;

/**
 * Command example. Uses ExampleSubsystem subsystem.
 */
public class ExampleCommand extends Command {
    // Instance variables
    private final ExampleSubsystem m_subsystem;

    /**
     * Constructs an ExampleCommand command.
     */
    public ExampleCommand(ExampleSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    /**
     * Method called once per time the command is scheduled.
     */
    @Override
    public void initialize() {}

    /**
     * Method called repeatedly while the command is scheduled (every time the scheduler runs).
     */
    @Override
    public void execute() {}

    /**
     * Method called once when the command ends (whether it finishes normally or is interrupted).
     */
    @Override
    public void end(boolean interrupted) {}

    /**
     * Method called repeatedly while the command is scheduled (returns false while the command is
     * scheduled, true when the command should end).
     * 
     * @return  whether command is finished
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
