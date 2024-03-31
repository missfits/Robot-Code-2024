package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RightClimber;

import frc.robot.Constants.ClimberConstants;

/**
 * Runs climber motor in one direction (does not necessarily correspond to the direction the climber is going)
 */
public class ClimberDownRightCommand extends Command {
    private RightClimber m_climber;

    public ClimberDownRightCommand(RightClimber climber){
        m_climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // m_climber.runLeftMotor(ClimberConstants.CLIMBER_MOTOR_SPEED);
        m_climber.runRightMotor(-ClimberConstants.CLIMBER_MOTOR_SPEED);
        // System.out.println(m_climber.getEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.rightMotorOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
