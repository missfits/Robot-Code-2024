package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;


/**
 * Puts hood forward (in amp shooting position).
 */
public class HoodPivotForwardCommand extends Command{
    private double m_encoderStart;
    private Hood m_hood;

    public HoodPivotForwardCommand(Hood hood){
        m_hood = hood;
    }

    @Override
    public void initialize() {
        m_hood.resetPivotEncoderPosition();
    }  

    @Override
    public void execute() {
        // If hood pass 80% of pivot distance, it slows
        if(Math.abs(m_hood.getPivotEncoderPosition()) > 0.8*HoodConstants.PIVOT_DISTANCE){
            m_hood.runPivotHoodMotor(HoodConstants.SLOW_PIVOT_MOTOR_SPEED);
        }else{
            m_hood.runPivotHoodMotor(HoodConstants.PIVOT_MOTOR_SPEED);
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
