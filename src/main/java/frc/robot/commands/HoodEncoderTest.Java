package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

import frc.robot.Constants.HoodConstants;

/**
 * Puts hood backwards (in speaker shooting position).
 */
public class HoodEncoderTest extends Command {
    private double m_encoderStart;
    private Hood m_hood;

    public HoodEncoderTest(Hood hood){
        m_hood = hood;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
      System.out.println(m_hood.getPivotEncoderPosition());
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
