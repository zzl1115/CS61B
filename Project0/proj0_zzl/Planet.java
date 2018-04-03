public class Planet {
   double xxPos;
   double yyPos;
   double xxVel;
   double yyVel;
   double mass;
   String imgFileName;
   static double G = 6.67E-11;
   public Planet(double xP, double yP, double xV, double yV, double m, String img) {
      xxPos = xP;
      yyPos = yP;
      xxVel = xV;
      yyVel = yV;
      mass = m;
      imgFileName = img;
   }
   
   public Planet(Planet p) {
      this.xxPos = p.xxPos;
      this.yyPos = p.yyPos;
      this.xxVel = p.xxVel;
      this.yyVel = p.yyVel;
      this.mass = p.mass;
      this.imgFileName = p.imgFileName;
   }
   
   public double calcDistance(Planet p) {
      double x, y;
      x = this.xxPos - p.xxPos;
      y = this.yyPos - p.yyPos;
      return Math.sqrt(x*x + y*y);
   }
   
   public double calcForceExertedBy(Planet p) {
      return G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
   }
   
   public double calcForceExertedByX(Planet p) {
      return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
   }
   public double calcForceExertedByY(Planet p) {
      return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
   }
   
   public double calcNetForceExertedByX(Planet[] ps) {
      double NetForceX = 0;
      for(int i = 0; i < ps.length; i++) {
         if(!this.equals(ps[i]))
          NetForceX += this.calcForceExertedByX(ps[i]);
      }
      return NetForceX;
   }
   public double calcNetForceExertedByY(Planet[] ps) {
      double NetForceY = 0;
      for(int i = 0; i < ps.length; i++) {
         if(!this.equals(ps[i]))
           NetForceY += this.calcForceExertedByY(ps[i]);
      }
      return NetForceY;
   }
   
   public void update(double time, double xforce, double yforce) {
      double xacc, yacc;
      xacc = xforce / this.mass;
      yacc = yforce / this.mass;
      this.xxVel += xacc * time;
      this.yyVel += yacc * time;
      this.xxPos += this.xxVel * time;
      this.yyPos += this.yyVel * time; 
   }
   public void draw() {
      StdDraw.picture(this.xxPos, this.yyPos, "/images/" + imgFileName);
   }
}
