package Lab1;
import java.util.*;


public class Lab1 {
	   
		public static double getDouble() {
			Scanner scanner=new Scanner(System.in);
			double d=scanner.nextDouble();
			return d;	        			
	    }
	    
	    private static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
	    	// ������ ������� ������������ �� ������� ������
	    	double sideA = p1.distanceTo(p2);
	    	double sideB = p2.distanceTo(p3);
	    	double sideC = p1.distanceTo(p3);
	    	double s = 0.5 * (sideA + sideB + sideC);
	    	return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
	    }
	    
	    public static void main(String[] args) {
	    	// ������ � ������������ ��������� �����
	    	System.out.println("������� ���������� (x, y, z) �����1");
	    	double point1X = getDouble();
	    	double point1Y = getDouble();
	    	double point1Z = getDouble();
	    	
	    	System.out.println("������� ���������� (x, y, z) �����2");
	    	double point2X = getDouble();
	    	double point2Y = getDouble();
	    	double point2Z = getDouble();
	    	
	    	System.out.println("������� ���������� (x, y, z) �����3");
	    	double point3X = getDouble();
	    	double point3Y = getDouble();
	    	double point3Z = getDouble();
	    	
	    	// ��������  ����  ��������  ����  Point3d
	    	Point3d point1 = new Point3d(point1X, point1Y, point1Z);
	    	Point3d point2 = new Point3d(point2X, point2Y, point2Z);
	    	Point3d point3 = new Point3d(point3X, point3Y, point3Z);
	    	
	    	// ���� ��� ����� �����, ����� ��������� � ������������� ������� �������
	    	if (point1.equals(point2) || point2.equals(point3) || point1.equals(point3)) {
	    		System.out.println("��� ����� �����; ������� ������������ �� ����� ���� ����������.");
	    		return;
	    	}
	    	
	    	// ������ � ����� ������� ������������ ������������
	    	else {
	    		String output = String.format("������� ������������ ����� %f", computeArea(point1, point2, point3));
	    		System.out.println(output);
	    	}
	    	
	    }
	}