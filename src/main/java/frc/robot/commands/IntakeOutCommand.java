package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.Constants.IntakeConstants;


/**
 * Spits out note.
 */
public class IntakeOutCommand extends Command {
    private Intake m_intake;
    
    public IntakeOutCommand(Intake intake){
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        // System.out.println("INTAKE SUCK COMMAND STARTED");
    }  

    @Override
    public void execute() {
        m_intake.runIntakeMotor(IntakeConstants.INTAKE_MOTOR_SPEED_OUT);
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
