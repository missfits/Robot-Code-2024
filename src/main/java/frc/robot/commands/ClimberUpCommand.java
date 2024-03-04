package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

import frc.robot.Constants.ClimberConstants;

/**
 * Runs climber motor in opposite direction as ClimberDown
 */
public class ClimberUpCommand extends Command {
    private Climber m_climber;

    public ClimberUpCommand(Climber climber){
        m_climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_climber.runMotor(ClimberConstants.CLIMBER_MOTOR_SPEED);
    //     System.out.println(m_climber.getEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.motorOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
