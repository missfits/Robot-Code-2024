package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;


/**
 * Puts hood forward (in amp shooting position).
 * In encoder position, moves the hood from 0 to approximately -48.2.
 */
public class HoodPivotForwardCommand extends Command{
    private Hood m_hood;

    public HoodPivotForwardCommand(Hood hood){
        m_hood = hood;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
        // m_hood.resetPivotEncoderPosition();
    }  

    @Override
    public void execute() {
        // If hood pass 80% of pivot distance, it slows
        if(Math.abs(m_hood.getPivotEncoderPosition()) > 0.8*Math.abs(HoodConstants.PIVOT_DISTANCE)){
            m_hood.runPivotHoodMotor(-HoodConstants.SLOW_PIVOT_MOTOR_SPEED);
        }else{
            m_hood.runPivotHoodMotor(-HoodConstants.PIVOT_MOTOR_SPEED);
        }
        System.out.println(m_hood.getPivotEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
        m_hood.pivotMotorOff();
        // m_hood.resetPivotEncoderPosition();
    }

    @Override
    public boolean isFinished() {
        return m_hood.getPivotEncoderPosition() <= HoodConstants.PIVOT_DISTANCE;
    }
}
