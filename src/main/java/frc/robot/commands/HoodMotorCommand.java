package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;

/**
 * Puts hood backwards (in speaker shooting position).
 */
public class HoodMotorCommand extends Command {
    private Hood m_hood;

    public HoodMotorCommand(Hood hood){
        m_hood = hood;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_hood.runHoodMotor(HoodConstants.HOOD_MOTOR_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_hood.hoodOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
