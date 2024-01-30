package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Intake;
import frc.robot.Constants.IntakeConstants;

/**
 * Command to run intake backward. Uses Intake subsystem.
 */
public class IntakeBackwardCommand extends Command {
    // Instance variables
    private Intake m_intake;

    /**
     * Constructs an IntakeBackwardCommand command.
     */
    public IntakeBackwardCommand(Intake intake){
        m_intake = intake;
        addRequirements(intake);
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
    public void execute() {
        m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_BACKWARD);
    }

    /**
     * Method called once when the command ends (whether it finishes normally or is interrupted).
     */
    @Override
    public void end(boolean interrupted) {
        m_intake.intakeOff();
    }

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
