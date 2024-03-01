package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.HoodConstants;

/**
 * Puts climber up.
 * Stops at a specified encoder position to prevent overshoot
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
        // If hood pass 80% of pivot distance, it slows
        if(Math.abs(m_climber.getEncoderPosition()) > 0.8*Math.abs(ClimberConstants.TELESCOPE_DISTANCE)){
            m_climber.runMotor(-ClimberConstants.SLOW_CLIMBER_MOTOR_SPEED);
        }else{
            m_climber.runMotor(-ClimberConstants.CLIMBER_MOTOR_SPEED);
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
        return m_climber.getEncoderPosition() <= ClimberConstants.TELESCOPE_DISTANCE;
    }
}
