/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import frc.team5104.main.Controls;
import frc.team5104.main.RobotState;
import frc.team5104.module.Module;
import frc.team5104.module.tshirt.TShirtSystems.valve.ValveState;
import frc.team5104.module.tshirt.Turret.TurretState;
import frc.team5104.util.Buffer;
import frc.team5104.util.console;
import frc.team5104.util.console.c;
import frc.team5104.util.console.t;

class TShirtController extends Module.Controller {
	
	double targetPressure = 10; //PSI
	double lastPressure = 0; //PSI
	Buffer pressureVelocityBuffer = new Buffer(50, 0.0); //PSI/second
	boolean readyToFire = false;
	
	enum FireMode { automatic, manual };
	FireMode fireMode = FireMode.manual;
	
	protected void update() {
		//Pressure Velocity
		pressureVelocityBuffer.update((TShirtSystems.pressureSensor.getPressure() - lastPressure) / RobotState.getDeltaTime());
		
		//Firing
		if (Controls.TShirt.fire.getPressed()) {
			TShirtSystems.valve.toggleState();
			console.log(c.TSHIRT, TShirtSystems.valve.state == ValveState.EXHAUST ? "Firing!" : "Filling!");
			(TShirtSystems.valve.state == ValveState.EXHAUST ? Controls.TShirt.fireRumble : Controls.TShirt.fillRumble).start();
			readyToFire = false;
		}
		
		//Pressure Print Out
		if (TShirtSystems.valve.state == ValveState.FILL) {
//			if (pressureVelocityBuffer.getDoubleOutput() < 0.01 && !readyToFire) {
//				Controls.TShirt.readyFireRumble.start();
//				console.log(c.TSHIRT, "ready to fire");
//				readyToFire = true;
//			}
//			console.log(String.format("%.2f", pressureVelocityBuffer.getDoubleOutput()));
		}
		
		//Auto Fire (TODO: add fill error, rsl status, and led display status)
		if (TShirtSystems.valve.state == ValveState.FILL && fireMode == FireMode.automatic) {
			//fire when ready
			if (TShirtSystems.pressureSensor.getPressure() >= targetPressure) {
				TShirtSystems.valve.setState(ValveState.EXHAUST);
				console.log(c.TSHIRT, "Firing Automatically!");
				Controls.TShirt.fireRumble.start();
			}
		}
		
		//Switch Fire Mode
		if (Controls.TShirt.toggleFireMode.getHeldEvent()) {
			Controls.TShirt.toggleFireModeRumble.start();
			if (fireMode == FireMode.manual) fireMode = FireMode.automatic;
			else fireMode = FireMode.manual;
			console.log(c.TSHIRT, "changed to " + fireMode.name() + " fire mode");
		}
		
		//Pressure
		if (Controls.TShirt.pressureIncrease.getPressed()) {
			if (fireMode == FireMode.automatic) {
				targetPressure += 2;
				console.log(c.TSHIRT, "target pressure: " + targetPressure);
				Controls.TShirt.pressureRumble.start();
			}
			else {
				console.log(c.TSHIRT, t.WARNING, "you are in manual mode!");
			}
		}
		if (Controls.TShirt.pressureDecrease.getPressed()) {
			if (fireMode == FireMode.automatic) {
				targetPressure -= 2;
				console.log(c.TSHIRT, "target pressure: " + targetPressure);
				Controls.TShirt.pressureRumble.start();
			}
			else {
				console.log(c.TSHIRT, t.WARNING, "you are in manual mode!");
			}
		}
		
		//Turret
		if (Turret.state == TurretState.MANUAL) {
			TShirtSystems.turret.setSpeed(Controls.TShirt.rotateTurret.getAxis() * TShirtConstants.TURRET_MAX_VOLTAGE);
		}
		
		//Pitch
		TShirtSystems.pitch.setSpeed(Controls.TShirt.rotatePitch.getAxis() * 
				(Controls.TShirt.rotatePitch.getAxis() < 0 ? TShirtConstants.PITCH_UP_MAX_VOLTAGE :
					TShirtConstants.PITCH_DOWN_MAX_VOLTAGE));
		
		//Last Pressure
		lastPressure = TShirtSystems.pressureSensor.getPressure();
	}
		
	protected void idle() {
		TShirtSystems.turret.stop();
		TShirtSystems.pitch.stop();
		TShirtSystems.valve.setState(ValveState.EXHAUST);
	}
}
