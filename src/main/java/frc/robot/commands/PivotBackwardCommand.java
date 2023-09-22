package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class PivotBackwardCommand extends Command {
    private Intake m_intake;
    public PivotBackwardCommand(Intake intake){
        m_intake = intake;
    }

    @Override
    public void initialize() {
    }  

    @Override
    public void execute() {
        m_intake.runPivotIntakeMotor(-0.2);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.pivotMotorOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
