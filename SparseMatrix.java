package math;

public class SparseMatrix
  implements Matrix
{
    public static class List
    { 
        public static class Node
        {
            Element e;
            Node nextrow;
            Node nextcolumn;
        
            public Node(int i, int j, double v)
            {
               e = new Element(i, j, v);
               nextrow = nextcolumn = this;
            }
      
            public Element getElement()
            {
                return e;
            }
         
        } // Node
  
        Node sentinel;
        int rows;
        int columns;
      
        public List(Size s)
        {
            sentinel = new Node(-1, -1, 0);
            rows = s.m;
            columns = s.n;
            Node temp = sentinel;
            
            for (int i = 0; i < rows; i++) //Insere as sentinelas das linhas
            {
                Node node = new Node(i, -1, 0);       
                node.nextrow = temp.nextrow;
                temp.nextrow = node;
                temp = temp.nextrow;
                
            }
            
            temp = sentinel;
            
            for (int j = 0; j < columns; j++) //Insere as sentinelas das colunas
            {
                Node node = new Node(-1, j, 0);   
                node.nextcolumn = temp.nextcolumn;
                temp.nextcolumn = node;
                temp = temp.nextcolumn;
            }
        } // Construtor dos dados da matrix com lista ligada
        
        public void add(int i, int j, double v)
        {
            if (v != 0)
            {
                Node Row = sentinel;
                Node Col = sentinel;      
                Node tempRow;
                Node tempCol;
                
                while (Row.e.i != i)
                    Row = Row.nextrow;
                tempRow = Row;
                
                while (Col.e.j != j)
                    Col = Col.nextcolumn;
                tempCol = Col;
                
                while (tempRow.e.j != j)
                {
                    tempRow = tempRow.nextcolumn;
                    
                    if (tempRow == Row || tempRow.nextcolumn.e.j > j)
                        break;
                }
                
                if (tempRow.e.j == j) // Checa se a posição já foi inserida, caso sim atualiza seu valor
                    tempRow.e.value = v;
                
                else // Caso em que nó não existia na lista
                {
                    Node node = new Node(i, j, v);
                    
                    node.nextcolumn = tempRow.nextcolumn;
                    tempRow.nextcolumn = node;
                    
                    while (tempCol.e.i < i)
                    {   
                        tempCol = tempCol.nextrow;
                        
                        if (tempCol == Col)
                            break;
                    }
                        
                    node.nextrow = tempCol.nextrow;
                    tempCol.nextrow = node;
                }
            }
        } // Adiciona nó na lista
        
        public void remove(int i, int j)
        {
            Node Row, Col, tempRow, tempCol;
            Row = Col = sentinel;
            
            while (Row.e.i != i)
                Row = Row.nextrow;
            tempCol = Row;
                
            while (Col.e.j != j)
                Col = Col.nextcolumn;
            tempRow = Col;
            
            while (tempCol.nextcolumn.e.j != j)
            {
                tempCol = tempCol.nextcolumn;
                    
                if (tempCol == Row)
                    break;
            }
                
            if (tempCol.nextcolumn.e.j == j)
            {
                while (tempRow.nextrow.e.i != i)
                    tempRow = tempRow.nextrow;
                 
                tempCol.nextcolumn = tempCol.nextcolumn.nextcolumn;
                tempRow.nextrow = tempRow.nextrow.nextrow;
            }
        } // Remove nó da lista caso exista e seu valor seja zero
    
    } // List
  
  Size size;
  List data;
  
  public SparseMatrix(int m, int n)
  {
    size = new Size(m, n);
    data = new List(size);
  } // Construtor

  public SparseMatrix(Matrix m)
  {
    size = m.size();
    data = new List(size);
    for(int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            data.add(i, j, m.get(i, j));
  } // Construtor de cópia

  @Override
  public Matrix add(double s)
  {
    SparseMatrix r = new SparseMatrix(size.m, size.n);
    
    for(int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            r.data.add(i, j, this.get(i, j) + s);
            
    return r;
  }

  @Override
  public Matrix add(Matrix m)
    throws DimensionsDoNotAgreeException
  {
    Size s = m.size();
    
    if (size.m != s.m || size.n != s.n)
        throw new DimensionsDoNotAgreeException();

    SparseMatrix r = new SparseMatrix(s.m, s.n);
    double sum;
    
    for(int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++) 
        {
            sum = this.get(i,j) + m.get(i, j);
            r.data.add(i, j, sum);
        }
    
    return r;
  }

  @Override
  public Matrix sub(double s)
  {
    SparseMatrix r = new SparseMatrix(size.m, size.n);
    
    for(int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            r.data.add(i, j, this.get(i, j) - s);
            
    return r;
  }

  @Override
  public Matrix sub(Matrix m)
    throws DimensionsDoNotAgreeException
  {
    Size s = m.size();
    
    if (size.m != s.m || size.n != s.n)
        throw new DimensionsDoNotAgreeException();

    SparseMatrix r = new SparseMatrix(s.m, s.n);
    double sum;
    
    for(int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++) 
        {
            sum = this.get(i,j) - m.get(i, j);
            r.data.add(i, j, sum);
        }
    
    return r;
  }

  @Override
  public Matrix mul(double s)
  {
    SparseMatrix r = new SparseMatrix(size.m, size.n);
    
    for(int i = 0; i < size.m; i++)
        for (int j = 0; j < size.n; j++)
            r.data.add(i, j, this.get(i, j) * s);
            
    return r;
  }

  @Override
  public Matrix mul(Matrix m)
    throws DimensionsDoNotAgreeException
  {
    Size s = m.size();
    
    if(size.n != s.m)
        throw new DimensionsDoNotAgreeException();
    
    SparseMatrix r = new SparseMatrix(size.m, s.n);  
    double mult = 0;
    
    for(int i = 0; i < size.m; i++)
    {
        for(int j = 0; j < s.n; j++) 
        {
          for(int k = 0; k < size.n; k++)
          {
               mult += this.get(i, k) * m.get(k, j);
          }   
          r.set(i, j, mult);
          mult = 0;
        }
    }
      
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
                r.data[i] += get(i,j) * v.data[i];
    
    return r;
  }

  @Override
  public Size size()
  {
    return size;
  }

  @Override
  public double get(int i, int j)
  {
    List.Node Row = data.sentinel;
    List.Node Col;
    
    while(Row.e.i != i)
        Row = Row.nextrow;
    Col = Row.nextcolumn;
   
    while (Col.e.j != j)
    {
        Col = Col.nextcolumn;
        if (Col == Row)
            break;
    }
    
    if (Col.e.j == j)
        return Col.e.value;
    else
         return 0;
  }
  
  @Override
  public Vector getRow(int i)
  {
    Vector v = new Vector(size.n);
    
    for (int j = 0; j < size.n; j++)
    {
        v.data[j] = get(i, j);
    }
    
    return v;
  }

  @Override
  public Vector getColumn(int j)
  {
    Vector v = new Vector(size.m);
    
    for (int i = 0; i < size.m; i++)
    {
        v.data[i] = get(i, j);
    }
    
    return v;
  }

  @Override
  public void set(int i, int j, double s)
  {
    if (s != 0)
        data.add(i, j, s);
    else
        data.remove(i,j);
  }

  @Override
  public void setRow(int i, Vector v)
    throws DimensionsDoNotAgreeException
  {
    if(size.n != v.size())
        throw new DimensionsDoNotAgreeException();
    
    for (int j = 0; j < size.n; j++)
    {
        if (v.data[j] != 0)
            this.data.add(i, j, v.data[j]);
        else
            this.data.remove(i,j);
    }
  }

  @Override
  public void setColumn(int j, Vector v)
    throws DimensionsDoNotAgreeException
  {
    if(size.m != v.size())
        throw new DimensionsDoNotAgreeException();
    
    for (int i = 0; i < size.m; i++)
    {
        if (v.data[i] != 0)
            this.data.add(i, j, v.data[i]);
        else
            this.data.remove(i,j);
    }
  }
 
  @Override
  public MatrixIterator iterator()
  {
    SparseMatrixIterator itr = new SparseMatrixIterator(this, new Element(0, 0, this.get(0,0)));
    
    return itr;
  }

} // SparseMatrix
