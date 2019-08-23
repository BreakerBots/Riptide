/*BreakerBots Robotics Team 2019*/
package frc.team5104.main;

import frc.team5104.util.Controller.Control;
import frc.team5104.util.Controller.ControlList;
import frc.team5104.util.Controller.Controllers;
import frc.team5104.util.Controller.Rumble;

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
		public static final Control rotateTurret = new Control(ControlList.RightJoystickX);
		public static final Control rotatePitch = new Control(ControlList.RightJoystickY);
		
		public static final Control fire = new Control(ControlList.RightBumper);
		public static final Control	pressureIncrease = new Control(ControlList.DirectionPadUp);
		public static final Control	pressureDecrease = new Control(ControlList.DirectionPadDown);
		public static final Control toggleFireMode = new Control(ControlList.List, Controllers.Main, false, 0.6, 500);
		
		public static final Rumble fireRumble = new Rumble(1, true, false, 1000);
		public static final Rumble fillRumble = new Rumble(1, false, false, 1000);
		public static final Rumble pressureRumble = new Rumble(1, false, false, 500);
		public static final Rumble toggleFireModeRumble = new Rumble(1, true, true, 500);
	}
}
