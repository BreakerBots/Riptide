/*BreakerBots Robotics Team 2019*/
package frc.team5104.main;

import frc.team5104.util.Controller.Control;
import frc.team5104.util.Controller.ControlList;

/**
 * All the controls for the robot
 */
public class Controls {
	//Drive
	public static class Drive {
		public static final Control turn = new Control(ControlList.LeftJoystickX);
		public static final Control forward = new Control(ControlList.RightTrigger);
		public static final Control reverse = new Control(ControlList.LeftTrigger);
	}
	
	//Hatch
	public static class TShirt {
		public static final Control fire = new Control(ControlList.RightBumper);
		public static final Control rotateTurret = new Control(ControlList.RightJoystickX);
		public static final Control rotatePitch = new Control(ControlList.RightJoystickY);
	}
}
