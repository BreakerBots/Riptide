/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.tshirt;

import frc.team5104.main.Controls;
import frc.team5104.module.Module;
import frc.team5104.module.tshirt.TShirtSystems.valve.ValveState;
import frc.team5104.module.tshirt.Turret.TurretState;
import frc.team5104.util.console;
import frc.team5104.util.console.c;
import frc.team5104.util.console.t;

class TShirtController extends Module.Controller {
	
	double targetPressure = 10; //PSI
	double nextPressurePrintOut = 0;
	
	enum FireMode { automatic, manual };
	FireMode fireMode = FireMode.manual;
	
	protected void update() {
		//Temp
		//console.log("pressure: " + TShirtSystems.pressureSensor.getPressure());
		
		//Firing
		if (Controls.TShirt.fire.getPressed()) {
			TShirtSystems.valve.toggleState();
			console.log(c.TSHIRT, TShirtSystems.valve.state == ValveState.EXHAUST ? "Firing!" : "Filling!");
			(TShirtSystems.valve.state == ValveState.EXHAUST ? Controls.TShirt.fireRumble : Controls.TShirt.fillRumble).start();
			nextPressurePrintOut = 1;
		}
		
		//Pressure Print Out
		if (false) {//TShirtSystems.valve.state == ValveState.FILL) {
			if (TShirtSystems.pressureSensor.getPressure() >= nextPressurePrintOut) {
				console.log(c.TSHIRT, "current pressure: " + TShirtSystems.pressureSensor.getPressure());
				nextPressurePrintOut += 1;
			}
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
	}
		
	protected void idle() {
		TShirtSystems.turret.stop();
		TShirtSystems.pitch.stop();
		TShirtSystems.valve.setState(ValveState.EXHAUST);
	}
}
