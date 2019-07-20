/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team5104.main.Ports;
import frc.team5104.module.Module;

public class TShirtSystems extends Module.Systems {
	//Talons
	//private static TalonSRX Talon_Yaw;
	//private static TalonSRX Talon_Pitch;
	//private static CANifier CANifier;
	private static Solenoid Valve;
	
	protected static class valve {
		static enum ValveState {
			FILL,
			EXHAUST
		};
		static ValveState state = ValveState.EXHAUST;
		
		static void toggleState() {
			if (state == ValveState.FILL) state = ValveState.EXHAUST;
			else state = ValveState.FILL;
			update();
		}

		static void setState(ValveState valve_state) {
			state = valve_state;
			update();
		}
		
		static void update() {
			if (state == ValveState.FILL) Valve.set(true);
			else Valve.set(false);
		}
	}
	
	protected void init() {
		Valve = new Solenoid(Ports.TSHIRT_VALVE);
		
		//Talon_Yaw = TalonFactory.getTalon(Ports.TSHIRT_TALON_YAW...
		//Talon_Pitch = TalonFactory.getTalon(...
		
		//CANifier = new CANifier(...
	}
}