/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team5104.main.Ports;
import frc.team5104.module.Module;
import frc.team5104.util.TalonFactory;

class TShirtSystems extends Module.Systems {
	//Talons
	private static TalonSRX Talon_Turret;
	private static TalonSRX Talon_Pitch;
	//private static TalonSRX Talon_Revolver;
	//private static CANifier CANifier;
	private static Solenoid Valve;
	
	static class valve {
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
	
	static class turret {
		static void setSpeed(double voltage) {
			Talon_Turret.set(ControlMode.PercentOutput, voltage/Talon_Turret.getBusVoltage());
		}
		static void stop() { setSpeed(0); }
		
		static double getRotation() {
			return -1;
		}
		
		static boolean leftLimitHit() {
			return true;
		}
		
		static boolean rightLimitHit() {
			return true;
		}
	}
	
	static class pitch {
		static void setSpeed(double voltage) {
			Talon_Pitch.set(ControlMode.PercentOutput, voltage/Talon_Pitch.getBusVoltage());
		}
		static void stop() { setSpeed(0); }
		
		static double getRotation() {
			return -1;
		}
		
		static boolean lowerLimitHit() {
			return true;
		}
		
		static boolean upperLimitHit() {
			return true;
		}
	}
	
	//static class revolver {}
	
	protected void init() {
		Valve = new Solenoid(Ports.TSHIRT_VALVE);
		
		Talon_Turret = TalonFactory.getTalon(Ports.TSHIRT_TALON_TURRET);
		Talon_Pitch = TalonFactory.getTalon(Ports.TSHIRT_TALON_PITCH);
		
		//CANifier = new CANifier(...
		//Talon_Revolver = TalonFactory.getTalon...
	}
}