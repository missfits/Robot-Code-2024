package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;

/**
 * Backup hood command (in case encoder values break)
 * Runs hood backwards with no stop condition, REQUIRES MANUAL STOP by releasing trigger
 */
public class HoodPivotBackwardBackup extends Command {
    private Hood m_hood;

    public HoodPivotBackwardBackup(Hood hood){
        m_hood = hood;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_hood.runPivotHoodMotor(HoodConstants.PIVOT_MOTOR_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_hood.pivotMotorOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
