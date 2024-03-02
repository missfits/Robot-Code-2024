package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Camdisplay;

/** An example command that uses an example subsystem. */
public class CamDisplayCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Camdisplay m_cam;

  public CamDisplayCommand(Camdisplay camdisplay) {
    m_cam = camdisplay;
    //addRequirements(camdisplay);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_cam.robotInit();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}


    

