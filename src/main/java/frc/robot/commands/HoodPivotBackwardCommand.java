package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;

/**
 * Puts hood backwards (in speaker shooting position).
 * In encoder positions, moves from approx -48.2 to 0.
 */
public class HoodPivotBackwardCommand extends Command {
    private Hood m_hood;

    public HoodPivotBackwardCommand(Hood hood){
        m_hood = hood;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // If hood passes within 20% of target, it slows
        if(Math.abs(m_hood.getPivotEncoderPosition()) < 0.2*Math.abs(HoodConstants.PIVOT_DISTANCE)){
            m_hood.runPivotHoodMotor(HoodConstants.SLOW_PIVOT_MOTOR_SPEED);
        } else {
            m_hood.runPivotHoodMotor(HoodConstants.PIVOT_MOTOR_SPEED);
        }
        // System.out.println(m_hood.getPivotEncoderPosition()); // for testing
    }

    @Override
    public void end(boolean interrupted) {
        m_hood.pivotMotorOff();
    }

    @Override
    public boolean isFinished() {
        // returns true if encoder position is within 0.5 of target position
        return Math.abs(m_hood.getPivotEncoderPosition()) <= 0.5;
    }
}
