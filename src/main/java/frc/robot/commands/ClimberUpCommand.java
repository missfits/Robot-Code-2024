package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LeftClimber;
import frc.robot.subsystems.RightClimber;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.ClimberConstants;

/**
 * Runs climber motor in opposite direction as ClimberDown
 */
public class ClimberUpCommand extends Command {
    private LeftClimber m_leftClimber;
    private RightClimber m_rightClimber;
    private CommandXboxController m_xbox;

    public ClimberUpCommand(LeftClimber leftClimber, RightClimber rightClimber, CommandXboxController xbox){
        m_leftClimber = leftClimber;
        m_rightClimber = rightClimber;
        m_xbox = xbox;
        addRequirements(leftClimber, rightClimber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (m_xbox.x().getAsBoolean()) {
            m_leftClimber.runLeftMotor(-ClimberConstants.CLIMBER_MOTOR_SPEED);
            m_rightClimber.runRightMotor(ClimberConstants.CLIMBER_MOTOR_SPEED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_leftClimber.leftMotorOff();
        m_rightClimber.rightMotorOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
