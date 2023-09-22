package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.IntakeConstants;

public class IntakeForwardCommand extends Command {
    private Intake m_intake;
    private float m_timeout;
    private Timer m_timer = new Timer();
    
    public IntakeForwardCommand(Intake intake, float timeout){
        m_intake = intake;
        m_timeout = timeout;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        System.out.println("INTAKE SUCK COMMAND STARTED");
        m_timer.start();
    }  

    @Override
    public void execute() {
        m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_FORWARD);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.intakeOff();
    }

    @Override
    public boolean isFinished() {
        if (m_timeout == 0) {
            return false;
        }
        return (m_timer.get() > m_timeout);
    
    }
}
