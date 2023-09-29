package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.Constants.IntakeConstants;

public class DefaultIntakeCommand extends Command{
    private Intake m_intake;
    public DefaultIntakeCommand(Intake intake){
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
    }  

    @Override
    public void execute() {
        m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_DEFAULT); // default value 0.6, lower to prevent cubes from breaking
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.intakeOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
