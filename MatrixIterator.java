package math;

import java.util.Iterator;
import java.util.NoSuchElementException;
import math.Matrix.Element;

public interface MatrixIterator
  extends Iterator<Matrix.Element>
{
    @Override
    boolean hasNext();
    
    @Override
    Element next() throws NoSuchElementException;

} // MatrixIterator
