package parte1;

import java.util.Scanner;

/**
 *
 * @author GoingS
 */

class Point{
    private float x, y;
    
    public Point(){
        x = 0;
        y = 0;
    }
    
    public Point(float novoX, float novoY){
        x = novoX;
        y = novoY;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void move(float dx, float dy){
        x = x + dx;
        y = y + dy;  
    }
    
    public double distance(float x2, float y2){
        float dx = x - x2;
        float dy = y - y2;
        
        return Math.sqrt(dx*dx + dy*dy);
        
    }
  
    
}

class Circle{
    private Point p;
    private float radius;
    
    public Circle(){
        p = new Point(0,0);
        radius = 0;
    }
    
    public Circle(float x, float y, float rad){
        p = new Point(x,y);
        radius = rad;
    }
    
    public double getArea(){
        return 3.141593 * radius*radius;
    }
    
    public double getDiameter(){
        return 6.283185 * radius;
    }
}


public class Parte1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in); 
        int rad;
        Circle c1;
        
        System.out.println("Digite o raio do circulo: ");
        rad = input.nextInt();
        c1 = new Circle(0,0, rad);
        
        System.out.printf("A area do circulo e: %f\nO diametro do circulo e: %f\n", c1.getArea(),c1.getDiameter());
        
    }
    
}
