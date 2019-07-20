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
		public static final Control _turn = new Control(ControlList.LeftJoystickX);
		public static final Control _forward = new Control(ControlList.RightTrigger);
		public static final Control _reverse = new Control(ControlList.LeftTrigger);
	}
	
	//Hatch
	public static class TShirt {
		public static final Control _fire = new Control(ControlList.RightBumper);
		public static final Control _rotateYaw = new Control(ControlList.RightJoystickX); //Turret
		public static final Control _rotatePitch = new Control(ControlList.RightJoystickY);
	}
}
