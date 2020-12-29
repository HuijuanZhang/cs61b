import java.lang.Math;

public class Planet {
	private final static double G = 6.67e-11;


	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) 
			+ (yyPos-p.yyPos)*(yyPos-p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		return G * mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
	}

	public double calcForceExertedByX(Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos-xxPos) / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos-yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netForce = 0;
		for (Planet p : allPlanets){
			if (this.equals(p)) continue;
			netForce += this.calcForceExertedByX(p);
		}

		return netForce;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netForce = 0;
		for (Planet p : allPlanets){
			if (this.equals(p)) continue;
			netForce += this.calcForceExertedByY(p);
		}

		return netForce;
	}

	public void update(double dt, double fX, double fY) {
		xxVel += fX * dt / mass;
		yyVel += fY * dt / mass;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		String imageToDraw = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,imageToDraw);
	}
}





