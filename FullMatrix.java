package math;

public class FullMatrix
  implements Matrix
{
    double[][] data;
    Size size;
    
    public FullMatrix(int m, int n)
  {
    size = new Size(m, n);
    data = new double[m][n];
  } // Construtor

  public FullMatrix(Matrix m)
  {
    size = m.size();
    data = new double[size.m][size.n];
    
    for (int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            data[i][j] = m.get(i, j);
  } // Construtor cópia

  @Override
  public Matrix add(double s)
  {
   FullMatrix r = new FullMatrix(size.m, size.n);
   for (int i = 0; i < size.m; i++)
       for (int j = 0; j < size.n; j++)
           r.data[i][j] = data[i][j] + s;
  
   return r;
  }

  @Override
  public Matrix add(Matrix m)
    throws DimensionsDoNotAgreeException
  {
    Size s = m.size();
    
    if (size.m != s.m || size.n != s.n)
        throw new DimensionsDoNotAgreeException();
    
    FullMatrix r = new FullMatrix(size.m, size.n);
    for (int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            r.data[i][j] = this.data[i][j] + m.get(i, j);
    
    return r;
  }

  @Override
  public Matrix sub(double s)
  {
   FullMatrix r = new FullMatrix(size.m, size.n);
   for (int i = 0; i < size.m; i++)
       for (int j = 0; j < size.n; j++)
           r.data[i][j] = data[i][j] - s;
  
   return r;
  }

  @Override
  public Matrix sub(Matrix m)
    throws DimensionsDoNotAgreeException
  {
    Size s = m.size();
    
    if (size.m != s.m || size.n != s.n)
        throw new DimensionsDoNotAgreeException();
    
    FullMatrix r = new FullMatrix(size.m, size.n);
    for (int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            r.data[i][j] = this.data[i][j] - m.get(i, j);
    
    return r;
  }

  @Override
  public Matrix mul(double s)
  {
    FullMatrix r = new FullMatrix(this.size.m, this.size.n);
   for (int i = 0; i < this.size.m; i++)
       for (int j = 0; j < this.size.n; j++)
           r.data[i][j] = this.data[i][j] * s;
  
   return r;
  }

  @Override
  public Matrix mul(Matrix m)
    throws DimensionsDoNotAgreeException
  { 
    Size s = m.size();
    
    if(size.n != s.m)
        throw new DimensionsDoNotAgreeException();
    
    FullMatrix r = new FullMatrix(size.m, s.n);  
    
    for(int i = 0; i < size.m; i++)
      for(int j = 0; j < s.n; j++)
         for(int k = 0; k < size.n; ++k)
             r.data[i][j] += data[i][k] * m.get(k, j);
  
    return r;
  }

  @Override
  public Vector mul(Vector v)
    throws DimensionsDoNotAgreeException
  {
    if(size.n != v.size())
        throw new DimensionsDoNotAgreeException();
    
    Vector r = new Vector(size.m);
    
    for(int i = 0; i < size.m; i++)
        for(int j = 0; j < v.size(); j++)
           r.data[i] += this.data[i][j] + v.data[i];
    
    return r;
  }

  @Override
  public Size size()
  {
    return this.size;
  }

  @Override
  public double get(int i, int j) 
  {
    return this.data[i][j];
  }

  @Override
  public Vector getRow(int i)      
  {
    Vector r = new Vector(size.m);
    
    System.arraycopy(data[i], 0, r.data, 0, size.n);
      
    return r;
  }

  @Override
  public Vector getColumn(int j)
  {
    Vector r = new Vector(size.m);
    
    for(int i = 0; i < size.m; i++)
        r.data[i] = data[i][j];
    
    return r;
  }

  @Override
  public void set(int i, int j, double s)
  {
    this.data[i][j] = s;
  }

  @Override
  public void setRow(int i, Vector v)
    throws DimensionsDoNotAgreeException
  {
    if(size.n != v.size())
        throw new DimensionsDoNotAgreeException();
    
    System.arraycopy(v.data, 0, this.data[i], 0, size.n);
  }

  @Override
  public void setColumn(int j, Vector v)
    throws DimensionsDoNotAgreeException
  {
    if(size.m != v.size())
        throw new DimensionsDoNotAgreeException();
    
    for (int i = 0; i < size.m; i++)
        data[i][j] = v.data[i];
  }

  @Override
  public MatrixIterator iterator()
  {   
    MatrixIterator iterator =  new FullMatrixIterator(size, data);
    
    return iterator;
  }

} // FullMatrix
