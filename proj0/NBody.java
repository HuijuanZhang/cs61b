public class NBody{
	public static double readRadius(String f){
		In in = new In(f);
		int firstItem = in.readInt();
		double secondItem = in.readDouble();
		return secondItem;
	}

	public static Planet[] readPlanets(String f) {
		In in = new In(f);
		int firstItem = in.readInt();
		double secondItem = in.readDouble();

		Planet[] planets = new Planet[firstItem];
		for(int i = 0; i < firstItem; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}

		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = NBody.readRadius(filename);
		Planet[] planets = NBody.readPlanets(filename);

		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-radius,radius);
		StdDraw.enableDoubleBuffering();


		double [] xForces = new double[planets.length];
		double [] yForces = new double[planets.length];

		double time = 0;
		while(time < T){
			for(int i=0; i < planets.length;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < planets.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}


			StdDraw.clear();
			StdDraw.picture(0,0,imageToDraw);

			for(Planet p : planets){
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

		StdAudio.play("./audio/2001.mid");

		
	}
}