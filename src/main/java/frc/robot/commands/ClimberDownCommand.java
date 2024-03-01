package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

import frc.robot.Constants.ClimberConstants;

/**
 * Puts climber down
 * Stops at specified encoder position to prevent overshoot
 */
public class ClimberDownCommand extends Command {
    private Climber m_climber;

    public ClimberDownCommand(Climber climber){
        m_climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // If hood passes within 20% of target, it slows
        if(Math.abs(m_climber.getEncoderPosition()) < 0.2*Math.abs(ClimberConstants.TELESCOPE_DISTANCE)){
            m_climber.runMotor(ClimberConstants.SLOW_CLIMBER_MOTOR_SPEED);
        }else{
            m_climber.runMotor(ClimberConstants.CLIMBER_MOTOR_SPEED);
        }
        System.out.println(m_climber.getEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.motorOff();
    }

    @Override
    public boolean isFinished() {
        // returns true if encoder position is within 0.5 of target position
        return Math.abs(m_climber.getEncoderPosition()) <= 0.5;
    }
}
