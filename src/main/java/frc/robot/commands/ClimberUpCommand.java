package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.ClimberConstants;

/**
 * Runs climber motor in opposite direction as ClimberDown only in "pit mode"
 */
public class ClimberUpCommand extends Command {
    private Climber m_climber;
    private CommandXboxController m_xbox;
    private SendableChooser<Boolean> m_chooser;

    public ClimberUpCommand(Climber climber, CommandXboxController xbox, SendableChooser<Boolean> chooser){
        m_climber = climber;
        m_xbox = xbox;
        m_chooser = chooser;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (m_xbox.x().getAsBoolean() && m_chooser.getSelected()) {
            m_climber.runLeftMotor(-ClimberConstants.CLIMBER_MOTOR_SPEED);
            m_climber.runRightMotor(ClimberConstants.CLIMBER_MOTOR_SPEED);
        }
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
