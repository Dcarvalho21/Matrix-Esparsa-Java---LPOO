package math;

import java.util.NoSuchElementException;
import math.Matrix.Element;
import math.Matrix.Size;

public class SparseMatrixIterator 
    implements MatrixIterator
{
    SparseMatrix m;
    Element current;
    Size size;
    
    public SparseMatrixIterator(SparseMatrix m, Element element)
    {
        this.current = element;
        this.m = m;
        this.size = m.size();
    }
    
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
    {
        if (current.i >= size.m || current.j >= size.n)
            throw new NoSuchElementException();
        
        Element e;
        e = current;
        
        if(e.j != size.n-1)
            current = new Element(e.i, e.j+1, m.get(e.i, e.j+1));
        if (e.j == size.n-1 && e.i != size.m-1)
            current = new Element(e.i+1, 0, m.get(e.i+1, 0));
        if (e.j == size.n-1 && e.i == size.m-1)  
        {
            e.i = size.m-1;
            e.j = size.n-1;
            current = new Element(size.m, size.n, 0);
        }
        
        return e;
    }

} // SparsematrixIterator
