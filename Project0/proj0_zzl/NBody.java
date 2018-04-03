public class NBody {
   public static double readRadius(String txt) {
      In in = new In(txt);
      int planetnum = in.readInt();
      double radius = in.readDouble();
      return radius;
   }
   
   public static Planet[] readPlanets(String txt) {
      In in = new In(txt);
      int planetnum = in.readInt();
      double radius = in.readDouble();
      Planet[] ps = new Planet[planetnum];
      for(int i = 0; i < planetnum; i++) {
           double xxPos = in.readDouble();
           double yyPos = in.readDouble();
           double xxVel = in.readDouble();
           double yyVel = in.readDouble();
           double mass = in.readDouble();
           String img = in.readString();
           Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
           ps[i] = planet;
    /*     ps[i].xxPos = in.readDouble();
           ps[i].yyPos = in.readDouble();
           ps[i].xxVel = in.readDouble();
           ps[i].yyVel = in.readDouble();
           ps[i].mass = in.readDouble();
           ps[i].imgFileName = in.readString();*/
      }
      return ps;
   }
   
	public static void BGMloop(String musicfile){
		StdAudio.loop("./audio/" + musicfile);
	}   
   
   
   public static void main(String[] args) {
      Double T = Double.parseDouble(args[0]);
      Double dt = Double.parseDouble(args[1]);
      String filename = args[2];
      
      double radius = readRadius("./data/planets.txt");
      StdDraw.setScale(-radius, radius);
      StdDraw.clear();
      StdDraw.picture(0, 0, "./images/starfield.jpg");
      
      Planet[] planets = NBody.readPlanets("./data/planets.txt");
      for(int i = 0; i < planets.length; i++) {
         planets[i].draw();
      }
      
      BGMloop("2001.mid");
      
      double time = 0;
      while(time < T) {
         StdDraw.clear();
         StdDraw.enableDoubleBuffering();
         StdDraw.picture(0, 0, "./images/starfield.jpg");
         double[] xForce = new double[planets.length];
         double[] yForce = new double[planets.length];
         for(int i = 0; i < planets.length; i++) {
            xForce[i] = planets[i].calcNetForceExertedByX(planets);
            yForce[i] = planets[i].calcNetForceExertedByY(planets);
         }
         for(int i = 0; i < planets.length; i++) {
            planets[i].update(dt, xForce[i], yForce[i]);
         }
         for(int i = 0; i < planets.length; i++) {
            planets[i].draw();
         }
         StdDraw.show();
         StdDraw.pause(10);
         time += dt;  	    
      }
      StdOut.printf("%d\n", planets.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < planets.length; i++) {
	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
}	
   }
}  
