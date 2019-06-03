package math;

import java.util.NoSuchElementException;
import math.Matrix.Element;
import math.Matrix.Size;

public class FullMatrixIterator 
    implements MatrixIterator
{
    public Size size;
    public Element current;
    public double[][] data;
    
    public FullMatrixIterator(Size size, double[][] data)
    {
        this.size = size;
        this.data = data;
        current = new Element(0, 0, data[0][0]);
    } // Construtor
    
    @Override
    public boolean hasNext()
    {
        if(current.i < size.m || current.j < size.n)
            return true;
        else
            return false;
    }
    
    @Override
    public Element next()
    throws NoSuchElementException
    {
        if (current.i >= size.m || current.j >= size.n)
            throw new NoSuchElementException();
        
        Element e;
        e = current;
        
        if(e.j != size.n-1)
            current = new Element(e.i, e.j+1, data[e.i][e.j+1]);
        if (e.j == size.n-1 && e.i != size.m-1)
            current = new Element(e.i+1, 0, data[e.i+1][0]);
        if (e.j == size.n-1 && e.i == size.m-1)  
        {
            e.i = size.m-1;
            e.j = size.n-1;
            current = new Element(size.m, size.n, 0);
        }
        
        return e;
    }

} // FullMatrixIterator
