/*BreakerBots Robotics Team 2019*/
package frc.team5104.module.drive;

/**
 * A simple class for sending/saving drivetrain movement signals.
 */
public class DriveSignal {
	/**
	 * Unit that the left & right speeds can be in.
	 * feetPerSecond: uses PID directly on the talons to achieve the specified velocity
	 * percentOutput: directly set the percent output on the talons [from -1 (full speed reverse) to 1 (full speed forward)]
	 */
	public static enum DriveUnit {
		percentOutput,
		voltage
	}

	// Robot Drive Signal Variables
	public double leftSpeed;
	public double rightSpeed;
	public double feedForward = Double.NaN;
	public DriveUnit unit;
	
	/**
	 * Creates a Robot Drive Signal with the specified speeds in percentOutput
	 * @param leftSpeed  Percent output (-1 to 1) for the left  motors of the drive train to run
	 * @param rightSpeed Percent output (-1 to 1) for the right motors of the drive train to run
	 */
	public DriveSignal(double leftSpeed, double rightSpeed) {
		this(leftSpeed, rightSpeed, DriveUnit.percentOutput);
	}
	
	/**
	 * Creates a Robot Drive Signal with the specified speed in specified unit
	 * @param leftSpeed  Speed for the left  motors of the drive train to run
	 * @param rightSpeed Speed for the right motors of the drive train to run
	 * @param unit The unit for the left & right motor speeds to be in (percentOutput or feetPerSecond)
	 */
	public DriveSignal(double leftSpeed, double rightSpeed, DriveUnit unit) {
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.unit = unit;
	}
	
	public String toString() {
		return  "l: " + leftSpeed + ", " +
				"r: " + rightSpeed;
	}
}
