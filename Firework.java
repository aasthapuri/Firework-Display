package eecs2030.lab4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Firework {

	/**
	 * The random number generator used by the class.
	 */
	private static Random rng = new Random();

	/**
	 * The number of fireworks created.
	 */
	private static int numberOfFireworksCreated = 0;

	/**
	 * The number of particles created.
	 */
	private static int numberOfParticlesCreated = 0;

	/**
	 * The particles for this firework.
	 */
	private List<Particle> particles;

	/**
	 * Initializes this firework to have zero particles.
	 */
	private Firework() {
		this.particles = new ArrayList<Particle>();
		Firework.numberOfFireworksCreated++;
	}

	/**
	 * Returns a yellow firework made up of a single particle that travels
	 * straight up.
	 * 
	 * @param position
	 *            the starting position of the firework
	 * @param speed
	 *            the starting speed of the firework
	 * @return a yellow firework made up of a single particle that travels
	 *         straight up
	 */
	public static Firework pearl(Point2 position, double speed) {
		Firework Fire = new Firework();
		Vector2 obj = new Vector2(0.0, 1.0);
		obj.multiply(speed);
		Particle py = new Particle(position, obj, 5, Color.YELLOW);
		Fire.particles.add(py);
		Firework.numberOfParticlesCreated += Fire.particles.size();
		return Fire;
	}

	/**
	 * Returns a yellow firework made up of n particles that travel straight up.
	 * The particles are randomly spread out around the given position and
	 * travel with a speed that is randomly distributed around the given speed.
	 * See the Lab 4 document for details.
	 * 
	 * @param n
	 *            the number of particles in the firework
	 * @param position
	 *            the average starting position of the particles
	 * @param speed
	 *            the average starting speed of the particles
	 * @return a yellow firework made up of n particles that travel straight up
	 */
	public static Firework pearls(int n, Point2 position, double speed) {
		Firework Fire = new Firework();
		for (int i = 0; i < n; i++) {
			double vlt = (Firework.rng.nextDouble() - 0.5) * 10 + position.getX();
			double vba = (Firework.rng.nextDouble() - 0.5) * 30 + position.getY();
			Point2 obj = new Point2(vlt, vba);
			Vector2 velo = new Vector2(0.0, 1.0);
			double t = Firework.rng.nextGaussian() * (0.05 * speed) + speed;
			velo.multiply(t);
			Particle py = new Particle(obj, velo, 5, Color.YELLOW);
			Fire.particles.add(py);
		}
		Firework.numberOfParticlesCreated += Fire.particles.size();
		return Fire;
	}

	/**
	 * Returns a blue firework made up of 36 particles moving outward in a
	 * circle centered on the given position. The particles are arranged evenly
	 * on the perimeter of a circle (i.e., every 10 degrees).
	 * 
	 * @param position
	 *            the starting position of each particle in the firework
	 * @param speed
	 *            the speed of each particle
	 * @return a blue firework made up of 36 particles moving outward in a
	 *         circle centered on the given position
	 */
	public static Firework ring(Point2 position, double speed) {
		Firework fire = new Firework();
		for (int i = 0; i < 360; i += 10) {
			Vector2 obj = Vector2.dirVector(i);
			double t = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
			obj.multiply(t);
			Particle py = new Particle(position, obj, 3, Color.BLUE);
			fire.particles.add(py);
		}
		Firework.numberOfParticlesCreated += fire.particles.size();
		return fire;
	}

	/**
	 * Returns a firework made up of a pair of rings. The outer blue ring and
	 * the inner red ring are both made up of 36 particles moving outward in a
	 * circle centered on the given position. The inner red ring has particles
	 * that move at half of the speed of the particles in the blue ring. The
	 * particles are arranged evenly on the perimeter of a circle (i.e., every
	 * 10 degrees).
	 * 
	 * @param position
	 *            the starting position of each particle in the firework
	 * @param speed
	 *            the speed of each particle in the blue ring
	 * @return a firework made up of a pair rings as described above
	 */
	public static Firework rings(Point2 position, double speed) {
		Firework fire = new Firework();
		for (int i = 0; i < 360; i += 10) {
			Vector2 obj = Vector2.dirVector(i);
			double s = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
			obj.multiply(s);
			Particle pO = new Particle(position, obj, 3, Color.BLUE);
			fire.particles.add(pO);
		}
		for (int i = 0; i < 360; i += 10) {
			Vector2 vtc = Vector2.dirVector(i);
			double t = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
			vtc.multiply(0.5 * t);
			Particle pI = new Particle(position, vtc, 2, Color.RED);
			fire.particles.add(pI);
		}
		Firework.numberOfParticlesCreated += fire.particles.size();
		return fire;
	}

	/**
	 * Creates a firework made up of n magenta particles that travel primarily
	 * upwards. The particles travel in a direction that is randomly distributed
	 * around the vertical direction with a speed that is randomly distributed
	 * around the given speed.
	 * 
	 * @param n
	 *            the number of particles in the fountain
	 * @param position
	 *            the starting position of the particles
	 * @param speed
	 *            the starting average speed of the particles
	 * @return a firework made up of n magenta particles that travel primarily
	 *         upwards
	 */
	public static Firework fountain(int n, Point2 position, double speed) {
		Firework fire = new Firework();
		for (int i = 0; i < n; i++) {
			double q = Firework.rng.nextGaussian() * 2 + 90;
			Vector2 obj = Vector2.dirVector(q);
			double x = Firework.rng.nextGaussian() * (0.05 * speed) + speed;
			obj.multiply(x);
			Particle pY = new Particle(position, obj, 10, Color.MAGENTA);
			fire.particles.add(pY);
		}
		Firework.numberOfFireworksCreated += fire.particles.size();
		return fire;
	}

	/**
	 * Creates a firework that forms the pistil pattern; see the Lab 4 document
	 * for details.
	 * 
	 * @param position
	 *            the starting position of the particles
	 * @param speed
	 *            the starting average speed of the particles
	 * @return a firework forming the pistil pattern
	 */
	public static Firework pistil(Point2 position, double speed) {
		Firework fire = new Firework();
		for (int i = 0; i < 360; i += 10) {
			Vector2 type = Vector2.dirVector(i);
			double s = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
			type.multiply(s);
			Particle pO = new Particle(position, type, 3, Color.BLUE);
			fire.particles.add(pO);
		}
		for (int i = 0; i < 500; i++) {
			double q = Firework.rng.nextDouble() * 360 + 0;
			Vector2 type = Vector2.dirVector(q);
			double x = Firework.rng.nextDouble() * (0.3 * speed) + 0;
			type.multiply(x);
			Particle pA = new Particle(position, type, 2, Color.GREEN);
			fire.particles.add(pA);
		}
		Firework.numberOfFireworksCreated += fire.particles.size();
		return fire;
	}

	/**
	 * Returns the number of fireworks created.
	 * 
	 * @return the number of fireworks created
	 */
	public static int getNumberOfFireworksCreated() {
		return Firework.numberOfFireworksCreated;
	}

	/**
	 * Returns the number of particles created.
	 * 
	 * @return the number of particles created
	 */
	public static int getNumberOfParticlesCreated() {
		return Firework.numberOfParticlesCreated;
	}

	/**
	 * Updates all of the firework particles. Causes every particle in the
	 * firework to move.
	 * 
	 * @param dt
	 *            the amount of time over which to move the firework particles
	 */
	public void update(double z) {
		for (Particle mn : this.particles) {
			mn.move(z);
		}
	}

	/**
	 * Returns true if at least one particle in the firework is alive, and false
	 * otherwise.
	 * 
	 * @return true if at least one particle in the firework is alive, and false
	 *         otherwise
	 */
	public boolean isAlive() {
		boolean type = false;
		for (Particle mn : this.particles) {
			if (mn.isAlive())
				type = true;
		}
		return type;
	}

	/**
	 * Returns the number of particles in this firework.
	 * 
	 * @return the number of particles in this firework
	 */
	public int getNumberOfParticles() {
		return this.particles.size();
	}

	/**
	 * Returns a list of particles making up this firework. Modifying the list
	 * or the particles in the list will modify the firework.
	 * 
	 * @return a list of particles making up this firework
	 */
	public List<Particle> getParticles() {
		// use aggregation when implementing this method
		return this.particles;
		

	}
}