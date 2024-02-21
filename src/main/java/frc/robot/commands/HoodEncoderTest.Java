package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;

/**
 * A tester command that prints the hood encoder position at all times
 */
public class HoodEncoderTest extends Command {
    private Hood m_hood;

    public HoodEncoderTest(Hood hood){
        m_hood = hood;
        addRequirements(hood);
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
