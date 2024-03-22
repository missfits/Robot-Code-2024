package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Hood;

/**
 * zeros hood encoder in "pit mode" only
 */
public class ZeroHoodEncoderCommand extends Command {
    private Hood m_hood;
    private CommandXboxController m_xbox;
    private SendableChooser<Boolean> m_chooser;

    public ZeroHoodEncoderCommand(Hood hood, CommandXboxController xbox, SendableChooser<Boolean> chooser){
        m_hood = hood;
        m_xbox = xbox;
        m_chooser = chooser;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (m_xbox.y().getAsBoolean() && m_chooser.getSelected()) {
           m_hood.resetPivotEncoderPosition();
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
