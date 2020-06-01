package Lab2;
import java.util.HashMap;

/**
 * �����  ������  �����  ��������  �  ��������
 * ������,  �  �������������  ��������  ��������,  �����������  ��� 
���������������� ��������� ������ �* 
 **/
public class AStarState
{
    /** ������ �� �����, �� ������� �������� �������� A***/
    private Map2D map;
    
    // ���������� ��� �������� � �������� ������.
    private HashMap<Location, Waypoint> openWaypoints;
    private HashMap<Location, Waypoint> closedWaypoints;



    /**
     * ������������� ������ ��������� ������� ��� ���������� ��������� A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
        this.openWaypoints = new HashMap<Location, Waypoint>();
        this.closedWaypoints = new HashMap<Location, Waypoint>();
    }

    /**���������� �����, �� ������� �������� �������� A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     *���� ����� ��������� ��� ������� � ������ �������� ������ � ���������� 
     *������  ��  �������  �  ����������  ����� ����������
     * ����  �  "��������"  ������  ���  ������,  �������  ���������� NULL.     
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (openWaypoints.isEmpty())
        	return null;
        else {
        	Waypoint minCostWaypoint = null;
        	float minCost = Float.MAX_VALUE;
        	for (Waypoint wp : openWaypoints.values()) {
        		float totalCost = wp.getTotalCost();
        		if (totalCost < minCost) {
        			minCostWaypoint = wp;
        			minCost = totalCost;
        		}
        	}
        	return minCostWaypoint;
        }
    }

    /**
     *���� ����� ���������  ���������  �������  
     *������  �  ��� ������, ���� ������������ ������� ���� �����. 
     * ���� � ������ ��������� ������ � ��������� ����� ��� ������� ��� ������� ��������������, 
     * �� ����������� ����� �������.
     * ����  �  ������  ���������  ������  ���  ����  �������  ���  ���� �������, 
     * ����������� ����� ������� ������ � ��� ������, ���� ��������� ���� �� �����  �������  
     * ������  ���������  ����  ��  �������.   
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location loc = newWP.getLocation();
        if (!openWaypoints.containsKey(loc)) {
        	openWaypoints.put(loc, newWP);
        	return true;
        }
        else {
        	Waypoint oldWP = openWaypoints.get(loc);
        	if (newWP.getPreviousCost() < oldWP.getPreviousCost()) {
        		openWaypoints.put(loc, oldWP);
        		return true;
        	}
        	return false;
        }
    }


    /**���������� ������� ���������� �������� ������**/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     *���������� ������� �� ������ ��������� ������ � ����� ���������  ������. 
    **/
    public void closeWaypoint(Location loc)
    {
        Waypoint wp = openWaypoints.remove(loc);
        closedWaypoints.put(loc, wp);
    }

    /**
     * ����������  ��������  true,  ����  ��������� ��������������  
     * �����������  �  ������  ��������  ������,  �  false  �  ��������� ������.
     **/
    public boolean isLocationClosed(Location loc)
    {
    	return (closedWaypoints.keySet().contains(loc));
    }
}

