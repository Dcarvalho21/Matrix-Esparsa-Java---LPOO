package math;

import math.Matrix.Element;

public class Test {
    public static void main(String [ ] args)
    {
        try
        {
            FullMatrix m1 = new FullMatrix(4, 4), m2 = new FullMatrix(4, 4), r;
            SparseMatrix s1 = new SparseMatrix(4, 4), s2 = new SparseMatrix(4, 4), sr;
            Vector v1 = new Vector(4), vr;
            MatrixIterator itr;
            Element e;
            
            System.out.println("FullMatrix test: begin\n");
            
            for(int i = 0; i < 4; i++)
                v1.set(i,i);
            
            for (int i = 0; i < m1.size.m; i++)
                m1.setRow(i, v1);
            
            for(int i = 0; i < 4; i++)
                v1.set(i, 3);
            
            for(int i = 0; i < m2.size.m; i++)
                m2.setRow(i, v1);
            
            itr = m1.iterator();
            
            System.out.println("Matrix m1:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            itr = m2.iterator();
            
            System.out.println("Matrix m2:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            System.out.println("Vector v1:");
            for(int i = 0; i < v1.size(); i++)
            {
                System.out.print(v1.get(i)+" ");
            }
            System.out.println("\n");
            
            r = (FullMatrix) m1.add(5); // Testa método add(double)
            itr = r.iterator();
            
            System.out.println("m1.add(5):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            r = (FullMatrix) m1.add(m2); // Testa método add(Matrix)
            itr = r.iterator();
            
            System.out.println("m1.add(m2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
           
            r = (FullMatrix) m1.sub(5); // Testa método sub(double)
            itr = r.iterator();
            
            System.out.println("m1.sub(5):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
           
            r = (FullMatrix) m1.sub(m2); // Testa método sub(Matrix)
            itr = r.iterator();
            
            System.out.println("m1.sub(m2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            r = (FullMatrix) m1.mul(2); // Testa método mul(double)
            itr = r.iterator();
            
            System.out.println("m1.mul(2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                   System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            r = (FullMatrix) m1.mul(m2); // Testa método mul(Matrix)
            itr = r.iterator();
            
            System.out.println("m1.mul(m2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            vr =  m1.mul(v1); // Testa método mul(Vector)
       
            System.out.println("m1.mul(v1):");
            for(int i = 0; i < vr.size(); i++)
            {
               System.out.println(vr.get(i));
            }
            System.out.println();
            
            itr = m2.iterator();
            
            System.out.println("Matrix m2:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            m2.set(0, 0, 10); // Testa método set(int, int, double)
            
            System.out.println("m2.set(0,0,10):");
            System.out.print("m2.get(0,0): ");
            System.out.println(m2.get(0, 0)+"\n"); // Testa método get(int, int)
            
            v1 = m2.getRow(2); // Testa método getRow(int)
            
            System.out.print("m2.getRow(2): ");
            for(int i = 0; i < v1.size(); i++)
            {
               System.out.print(v1.get(i)+" ");
            }
            System.out.println("\n");
            
            m2.setColumn(1, vr); // Testa método setColumn(int, Vector) 
            
            vr = m2.getColumn(1); // Testa método getColumn(int)
            
            System.out.println("m2.setColumn(1, m1.mul(v1))");
            System.out.println("m2.getColumn(1):");
            for(int i = 0; i < vr.size(); i++)
            {
               System.out.println(vr.get(i));
            }
            System.out.println();
            
            System.out.println("FullMatrix test: end\n");
        ////////////////////////////////////////////////////////////////////////
            
            System.out.println("SparseMatrix test: begin\n");
            
             for(int i = 0; i < 4; i++)
                v1.set(i,i);
            
            for (int i = 2; i < s1.size.m; i++)
                s1.setColumn(i, v1);
            
            for(int i = 0; i < 4; i++)
                v1.set(i, 5);
            
            for(int i = 0; i < s2.size.m; i++)
                s2.setRow(i, v1);
            
            itr = s1.iterator();
            
            System.out.println("Matrix s1:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            itr = s2.iterator();
            
            System.out.println("Matrix s2:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
           
            System.out.println("Vector v1:");
            for(int i = 0; i < v1.size(); i++)
            {
                System.out.print(v1.get(i)+" ");
            }
            System.out.println("\n");
            
            
            sr = (SparseMatrix) s1.add(3); // Testa método add(double)
            itr = sr.iterator();
            
            System.out.println("s1.add(3):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            
            sr = (SparseMatrix) s1.add(s2); // Testa método add(Matrix)
            itr = sr.iterator();
            
            System.out.println("s1.add(s2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            sr = (SparseMatrix) s2.sub(5); // Testa método sub(double)
            itr = sr.iterator();
            
            System.out.println("s2.sub(5):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            sr = (SparseMatrix) s1.sub(s2); // Testa método sub(matrix)
            itr = sr.iterator();
            
            System.out.println("s1.sub(s2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
        
            sr = (SparseMatrix) s1.mul(4); // Testa método mul(double)
            itr = sr.iterator();
            
            System.out.println("s1.mul(4):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
        
            sr = (SparseMatrix) s1.mul(s2); // Testa método mul(Matrix)
            itr = sr.iterator();
            
            System.out.println("s1.mul(s2):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
        
             vr = s1.mul(v1); // Testa método mul(Vector)
           
            System.out.println("s1.mul(v1):");
            for(int i = 0; i < vr.size(); i++)
            {
               System.out.println(vr.get(i));
            }
            System.out.println();
        
            itr = s2.iterator();
            
            System.out.println("Matrix s2:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            s1.set(0, 0, 10); // Testa método set(int, int, double)
            
            System.out.println("s1.set(0,0,10)");
            System.out.print("s1.get(0,0): ");
            System.out.println(m2.get(0, 0)+"\n"); // Testa método get(int, int)
            
            s2.setRow(1, vr); // Testa método setRow(int) 
            
            v1 = s2.getRow(1); // Testa método getRow(int)
            
            System.out.println("s2.setRow(1, m1.mul(v1))");
            System.out.print("s2.getRow(1): ");
            for(int i = 0; i < v1.size(); i++)
            {
               System.out.print(v1.get(i)+" ");
            }
            System.out.println("\n");
            
            vr = s2.getColumn(3); //Testa método getColumn(int, Vector)
            
            System.out.println("s2.getColumn(3):");
            for(int i = 0; i < vr.size(); i++)
            {
               System.out.println(vr.get(i));
            }
            System.out.println();
            
            System.out.println("SparseMatrix test: end\n");
        ////////////////////////////////////////////////////////////////////////
            System.out.println("FullMatrix & SparseMatrix test: begin\n");
            
            itr = m1.iterator();
            
            System.out.println("Matrix m1:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            itr = s1.iterator();
            
            System.out.println("Matrix s1:");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            r = (FullMatrix) m1.add(s1); // Testa FullMatrix + SparseMatrix
            
            itr = r.iterator();
            
            System.out.println("m1.add(s1):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
        
            sr = (SparseMatrix) s1.add(m1); // Testa SparseMatrix + FullMatrix
            
            itr = sr.iterator();
            
            System.out.println("s1.add(m1):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
        
            r = (FullMatrix) m1.sub(s1); // Testa FullMatrix - SparseMatrix
            
            itr = r.iterator();
            
            System.out.println("m1.sub(s1):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            sr =  (SparseMatrix) s1.sub(m1); // Testa SparselMatrix - FullMatrix
            
            itr = sr.iterator();
            
            System.out.println("s1.sub(m1):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            r = (FullMatrix) m1.mul(s1); // Testa FullMatrix * SparseMatrix
            
            itr = r.iterator();
            
            System.out.println("m1.mul(s1):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            sr = (SparseMatrix) s1.mul(m1); // Testa SparseMatrix * FullMatrix
            
            itr = sr.iterator();
            
            System.out.println("s1.mul(m1):");
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            m1 = new FullMatrix(s1); // Testa construtor de cópia FullMatrix
            
            System.out.println("m1 = new FullMatrix(s1)");
            itr = m1.iterator();
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            s1 = new SparseMatrix(m1); // Testa construtor de cópia SparseMatrix
            
            System.out.println("s1 = new SparseMatrix(m1)");
            itr = s1.iterator();
            while(itr.hasNext())
            {
                e = itr.next();
                if(e.j == 0 && e.i != 0)
                    System.out.println();
                System.out.print(e.value+" ");
            }
            System.out.println("\n");
            
            System.out.println("FullMatrix & SparseMatrix test: end\n");
        }
        
        catch(DimensionsDoNotAgreeException d)
        {
            //Do nothing
        }
        
    }
}
