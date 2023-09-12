package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class OI {


    public static CommandXboxController m_driverXbox = new CommandXboxController(
        OperatorConstants.DRIVER_XBOX_PORT);

    public static CommandXboxController m_coPilotXbox = new CommandXboxController(
        OperatorConstants.COPILOT_XBOX_PORT);
    
    
    // TO DO: write getter functions

}
