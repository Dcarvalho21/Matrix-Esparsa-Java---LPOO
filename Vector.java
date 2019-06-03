package math;

public class Vector
{ 
   
   double[] data;
   int size;

   public Vector(int m)
   {
       size = m;
       data = new double[m];
   }

   public int size()
   {
       return size;
   }
   
   public void set(int i, double x)
   {
       data[i] = x;
   }
   
   public double get(int i)
   {
       return data[i];
   }

} // Vector
