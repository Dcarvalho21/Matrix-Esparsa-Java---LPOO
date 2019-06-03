package math;

public interface Matrix
{
  public static class Size
  {
    public int m;
    public int n;

    Size(int m, int n)
    {
      this.m = m;
      this.n = n;
    }

  } // Size

  public static class Element
  {
    public int i;
    public int j;
    public double value;

    Element(int i, int j, double value)
    {
      this.i = i;
      this.j = j;
      this.value = value;
    }

  } // Element

  Matrix add(double s);
  Matrix add(Matrix m) throws DimensionsDoNotAgreeException;
  Matrix sub(double s);
  Matrix sub(Matrix m) throws DimensionsDoNotAgreeException;
  Matrix mul(double s);
  Matrix mul(Matrix m) throws DimensionsDoNotAgreeException;
  Vector mul(Vector v) throws DimensionsDoNotAgreeException;
  Size size();
  double get(int i, int j);
  Vector getRow(int i);
  Vector getColumn(int j);
  void set(int i, int j, double s);
  void setRow(int i, Vector v) throws DimensionsDoNotAgreeException;
  void setColumn(int j, Vector v) throws DimensionsDoNotAgreeException;
  MatrixIterator iterator();

} // Matrix
