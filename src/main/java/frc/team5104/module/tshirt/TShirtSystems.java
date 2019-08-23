/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team5104.main.Ports;
import frc.team5104.module.Module;
import frc.team5104.util.TalonFactory;
import frc.team5104.util.console;
import frc.team5104.util.console.c;
import frc.team5104.util.console.t;

class TShirtSystems extends Module.Systems {
	
	//Talons
	private static TalonSRX Talon_Turret;
	private static TalonSRX Talon_Pitch;
	//private static TalonSRX Talon_Revolver;
	private static Solenoid Valve;
	
	//Module Attached
	public boolean isModuleAttached() {
		Talon_Turret = TalonFactory.getTalon(Ports.TSHIRT_TALON_TURRET);
		return Talon_Turret.getFirmwareVersion() > 0;
	}
	
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
			return Talon_Turret.getSelectedSensorPosition();
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
	
	static class pressureSensor {
		static double getPressure() {
			return Talon_Pitch.getSelectedSensorPosition();
		}
		
		static double getPressureVelocity() {
			return Talon_Pitch.getSelectedSensorVelocity() * 10 /* Convert PSI/100ms to PSI/sec */;
		}
	}
	
	//static class revolver {}
	
	protected void init() {
		Valve = new Solenoid(Ports.TSHIRT_VALVE);
		
		Talon_Turret = TalonFactory.getTalon(Ports.TSHIRT_TALON_TURRET);
		Talon_Pitch = TalonFactory.getTalon(Ports.TSHIRT_TALON_PITCH);
		
		//Talon_Revolver = TalonFactory.getTalon...
		
		console.log(c.TSHIRT, t.INFO, "Initialized");
	}
}