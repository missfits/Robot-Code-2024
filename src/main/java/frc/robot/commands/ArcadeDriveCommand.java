package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;

public class ArcadeDriveCommand extends Command {
    private final Drivetrain m_drivetrain;
    private final OI m_humanControl;

    public ArcadeDriveCommand(Drivetrain drivetrain, OI humanControl) {
        m_drivetrain = drivetrain;
        m_humanControl = humanControl;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() { }

    // Called every time the scheduler runs while the command is scheduled.
    // NEEDS TESTING
    @Override
    public void execute() {
        double x_val = 0.0;
        double y_val = 0.0;
        if (m_humanControl.getDriverXBoxLeftJoyY() > 0.1) {
            x_val = m_humanControl.getDriverXBoxLeftJoyY();
        }
        if (m_humanControl.getDriverXBoxRightJoyX() > 0.1) {
            y_val = m_humanControl.getDriverXBoxRightJoyX();
        }

        m_drivetrain.arcadeDrive(x_val, y_val);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) { }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
