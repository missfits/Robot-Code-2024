package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;

// import java.lang.Math.*;

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
        double xJoy = m_humanControl.getDriverXBoxLeftJoyY();
        double yJoy = m_humanControl.getDriverXBoxRightJoyX();
        double x_val = Math.abs(xJoy) > 0.1 ? xJoy : 0.0;
        double y_val = Math.abs(yJoy) > 0.1 ? yJoy : 0.0;

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
