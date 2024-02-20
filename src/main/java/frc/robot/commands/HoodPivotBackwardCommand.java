package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;

/**
 * Puts hood backwards (in speaker shooting position).
 */
public class HoodPivotBackwardCommand extends Command {
    private double m_encoderStart;
    private Hood m_hood;

    public HoodPivotBackwardCommand(Hood hood){
        m_hood = hood;
    }

    @Override
    public void initialize() {
        m_hood.resetPivotEncoderPosition();
    }

    @Override
    public void execute() {
        if(Math.abs(m_hood.getPivotEncoderPosition()) > 0.8*HoodConstants.PIVOT_DISTANCE){
            m_hood.runPivotHoodMotor(-HoodConstants.SLOW_PIVOT_MOTOR_SPEED);
        }else{
            m_hood.runPivotHoodMotor(-HoodConstants.PIVOT_MOTOR_SPEED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_hood.pivotMotorOff();
        m_hood.resetPivotEncoderPosition();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_hood.getPivotEncoderPosition()) >=  HoodConstants.PIVOT_DISTANCE;
    }
}
