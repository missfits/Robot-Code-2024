package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;

public class PivotForwardCommand extends Command{
    private double m_encoderStart;
    private Hood m_hood;

    public PivotForwardCommand(Hood hood){
        m_hood = hood;
    }

    @Override
    public void initialize() {
        m_encoderStart = m_hood.getPivotEncoderPosition();
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
        return m_hood.getPivotEncoderPosition() >= m_encoderStart + HoodConstants.PIVOT_DELTA;
        // return m_hood.getPivotEncoderPosition() >= IntakeConstants.PIVOT_DOWN_POSITION;
    }
}
