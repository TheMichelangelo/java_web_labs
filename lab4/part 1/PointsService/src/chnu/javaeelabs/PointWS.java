package chnu.javaeelabs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math; 

public class PointWS {
	private Connection getConnection() throws Exception {
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        return DriverManager.getConnection("'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine");
	}
	
	public List<String> getPoints(){
		System.out.println();
		try {
			List<String> points = new ArrayList<String>();
			Connection con = getConnection();
			ResultSet rs = con.createStatement().executeQuery("Select name,m from point");
			while(rs.next()) {
				points.add("Point name: "+rs.getString(1)+" mass "+rs.getInt(2));
			}
			rs.close();
			con.close();
			return points;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
	
	private double getDistance(Point a,Point b)
	{
		return Math.sqrt(Math.pow(b.getCoord_x()-a.getCoord_x(),2)+
				Math.pow(b.getCoord_y()-a.getCoord_y(),2)+
				Math.pow(b.getCoord_z()-a.getCoord_z(),2));
	}
	
	public List<Point> getPointsWithMaxDistance(int limit){
		System.out.println();
		try {
			List<Point> pointsLimited = new ArrayList<Point>();
			Connection con = getConnection();
			ResultSet rs = con.createStatement().executeQuery("Select name,coord_x,coord_y,"
					+ "coord_z,m from point limit "+ limit);
			while(rs.next()) {
				Point p = new Point();
				p.setName(rs.getString(1));
				p.setCoord_x(rs.getInt(2));
				p.setCoord_y(rs.getInt(3));
				p.setCoord_z(rs.getInt(4));
				p.setM(rs.getInt(5));
				pointsLimited.add(p);
			}
			rs.close();
			con.close();
			
			double maxDistance=0;
			List<Point> pointsWithMaxDistance= new ArrayList<Point>();
			for(int i=0;i<pointsLimited.size();i++)
				for(int j=i+1;j<pointsLimited.size();j++)
			{
				double tmpDistance=getDistance(pointsLimited.get(i),pointsLimited.get(j));
				if(maxDistance<tmpDistance)
				{
					pointsWithMaxDistance.clear();
					pointsWithMaxDistance.add(pointsLimited.get(i));
					pointsWithMaxDistance.add(pointsLimited.get(j));
					maxDistance=tmpDistance;
				}
			}
			return pointsWithMaxDistance;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
}
