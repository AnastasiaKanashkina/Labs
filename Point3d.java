package Lab1;
 	/**
* ���������� ����� �����.
**/
	public class Point3d {
	    
	    /** ���������� X */
	    private double xCoord;
	    
	    /** ���������� Y */
	    private double yCoord;
	    
	    /** ���������� Z */
	    private double zCoord;
	
	    /** ����������� ������������� (x, y, z) */
	    public Point3d(double x, double y, double z) {
	        xCoord = x;
	        yCoord = y;
	        zCoord = z;
	    }
	
	    /** ����������� �� ���������. */
	    public Point3d() {
	        // �������� ����������� � ����� ����������� � ���������� ��������.
	        this(0, 0, 0);
	    }
	    
	    /** ��������� �� ��������� ���� �����. */
	    public boolean equals(Object o) {
	    	if (o.getClass() == Point3d.class) {
	    		Point3d pt = (Point3d) o;
	        	return (xCoord == pt.getX()) && (yCoord == pt.getY()) && (zCoord == pt.getZ());
	    	}
	    	else 
	    		return false;
	    }
	    
	    /** ������ ���������� ����� ����� �������. */
	    public double distanceTo(Point3d pt) {
	    	double deltaX = xCoord - pt.getX();
	    	double deltaY = yCoord - pt.getY();
	    	double deltaZ = zCoord - pt.getZ();
	    	return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
	    }
	
	    /** ����������� ���������� X. */
	    public double getX() {
	        return xCoord;
	    }
	
	    /** ����������� ���������� Y. */
	    public double getY() {
	        return yCoord;
	    }
	    
	    /** ����������� ���������� Z. */
	    public double getZ() {
	        return zCoord;
	    }
	
	    /** ��������� �������� ���������� X. */
	    public void setX(double val) {
	        xCoord = val;
	    }
	
	    /** ��������� �������� ���������� Y. */
	    public void setY(double val) {
	        yCoord = val;
	    }
	    
	    /** ��������� �������� ���������� Z. */
	    public void setZ(double val) {
	        zCoord = val;
	    }
	}