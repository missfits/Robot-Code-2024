package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;
import frc.robot.Constants.OperatorConstants;

/**
 * Command to arcade drive. Uses Drivetrain and OI subsystems.
 */
public class ArcadeDriveCommand extends Command {
    // Instance variables
    private final Drivetrain m_drivetrain;
    private final OI m_humanControl;

    /**
     * Constructs an ArcadeDriveCommand command.
     */
    public ArcadeDriveCommand(Drivetrain drivetrain, OI humanControl) {
        m_drivetrain = drivetrain;
        m_humanControl = humanControl;
        addRequirements(drivetrain);
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
        double xJoy = m_humanControl.getDriverXBoxLeftJoyY();
        double yJoy = m_humanControl.getDriverXBoxRightJoyX();
        double x_val = Math.abs(xJoy) > OperatorConstants.DRIVER_JOYSTICK_DEADBAND ? xJoy : 0.0;
        double y_val = Math.abs(yJoy) > OperatorConstants.DRIVER_JOYSTICK_DEADBAND ? yJoy : 0.0;

        m_drivetrain.arcadeDrive(OperatorConstants.DRIVE_SPEED_ADJUSTMENT * x_val, OperatorConstants.DRIVE_SPEED_ADJUSTMENT * y_val);
    }

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
