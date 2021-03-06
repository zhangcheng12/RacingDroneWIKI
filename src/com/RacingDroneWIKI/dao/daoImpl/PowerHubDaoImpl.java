package com.RacingDroneWIKI.dao.daoImpl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.RacingDroneWIKI.dao.dao.PowerHubDao;
import com.RacingDroneWIKI.javaBean.PowerHub;

public class PowerHubDaoImpl implements PowerHubDao {
	private  java.sql.Connection connection;
	public PowerHubDaoImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	public boolean addPowerHub(PowerHub ph) {
		String sql="INSERT INTO `racingdronewiki`.`power_hub`"
				+ " (`ph_model`, `ph_use_alone`, `ph_img`, "
				+ "`ph_reference_price`, `ph_anufacturer`,"
				+ " `ph_mounting_hole_spacing`, `ph_weight`, `ph_length`, "
				+ "`ph_width`, `ph_thickness`, `ph_5vbec`, `ph_9vbec`, "
				+ "`ph_12vbec`, `ph_max_current`, `ph_pin_definition_diagram`, "
				+ "`ph_extra_pictures`, `ph_caption`) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        pstmt.setString(1, ph.getModel());
	        pstmt.setBoolean(2, ph.isUseAlone());
	        pstmt.setString(3, ph.getImgUrl()); 
	        pstmt.setInt(4, ph.getReferencePrice());
	        pstmt.setString(5, ph.getAnufacturer());
	        pstmt.setFloat(6, ph.getMountingHoleSpacing());
	        pstmt.setFloat(7, ph.getWeight());
	        pstmt.setFloat(8, ph.getLength());
	        pstmt.setFloat(9, ph.getWidth());
	        pstmt.setFloat(10, ph.getThickness());
	        pstmt.setBoolean(11, ph.isBec5v());
	        pstmt.setBoolean(12, ph.isBec9v());
	        pstmt.setBoolean(13, ph.isBec12v());
	        pstmt.setInt(14, ph.getMaxCurrent());
	        pstmt.setString(15, ph.getPinDefinitionDiagram());
	        pstmt.setObject(16, ph.getExtraPictures());
	        pstmt.setString(17, ph.getCaption()); 
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
		return true;
	}

	public boolean deletPowerHub(PowerHub ph) {
		// TODO: implement
		return false;
	}

	public boolean updataPowerHub(PowerHub ph) {
		String sql="UPDATE `racingdronewiki`.`power_hub` SET `ph_model`=?,"
				+ " `ph_use_alone`=?, `ph_img`=?, `ph_reference_price`=?,"
				+ " `ph_anufacturer`=?, `ph_mounting_hole_spacing`=?, "
				+ "`ph_weight`=?, `ph_length`=?, `ph_width`=?,"
				+ " `ph_thickness`=?, `ph_5vbec`=?, `ph_9vbec`=?,"
				+ " `ph_12vbec`=?, `ph_max_current`=?, `ph_pin_definition_diagram`=?,"
				+ " `ph_extra_pictures`=?, `ph_caption`=? WHERE ?;";
		PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) connection.prepareStatement(sql);
	        pstmt.setString(1, ph.getModel());
	        pstmt.setBoolean(2, ph.isUseAlone());
	        pstmt.setString(3, ph.getImgUrl()); 
	        pstmt.setInt(4, ph.getReferencePrice());
	        pstmt.setString(5, ph.getAnufacturer());
	        pstmt.setFloat(6, ph.getMountingHoleSpacing());
	        pstmt.setFloat(7, ph.getWeight());
	        pstmt.setFloat(8, ph.getLength());
	        pstmt.setFloat(9, ph.getWidth());
	        pstmt.setFloat(10, ph.getThickness());
	        pstmt.setBoolean(11, ph.isBec5v());
	        pstmt.setBoolean(12, ph.isBec9v());
	        pstmt.setBoolean(13, ph.isBec12v());
	        pstmt.setInt(14, ph.getMaxCurrent());
	        pstmt.setString(15, ph.getPinDefinitionDiagram());
	        pstmt.setObject(16, ph.getExtraPictures());
	        pstmt.setString(17, ph.getCaption()); 
	        pstmt.setString(18, ph.getModel());
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<PowerHub> findAll() {
		PreparedStatement pstmt;
		ResultSet resSet;
		List<PowerHub> result=new LinkedList<>();
		String sql="SELECT * FROM racingdronewiki.power_hub;";
		try {
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			resSet=pstmt.executeQuery();
			while(resSet.next())
			{
				PowerHub ph=new PowerHub(resSet.getString(1), resSet.getString(3),
						resSet.getInt(4), resSet.getString(5),
						null, resSet.getString(17),
						resSet.getBoolean(2), resSet.getFloat(6),
						resSet.getFloat(7), resSet.getFloat(8), 
						resSet.getFloat(9), resSet.getFloat(10),
						resSet.getBoolean(11), resSet.getBoolean(12), 
						resSet.getBoolean(13), resSet.getInt(14),
						resSet.getString(15));
				Blob inBlob=resSet.getBlob(16);
				if(inBlob!=null)
				{
					InputStream is=inBlob.getBinaryStream();                //获取二进制流对象  
	                BufferedInputStream bis=new BufferedInputStream(is);    //带缓冲区的流对象  
	                byte[] buff=new byte[(int) inBlob.length()]; 
	                bis.read(buff, 0, buff.length);          //一次性全部读到buff中  
	                ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buff));  
	                LinkedList<String> ls=(LinkedList<String>) in.readObject();
	                ph.setExtraPictures(ls);
				}
				result.add(ph);
			} 
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(!result.iterator().hasNext())
			return null;
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<PowerHub> findByModel(String model) {
		PreparedStatement pstmt;
		ResultSet resSet;
		List<PowerHub> result=new LinkedList<>();
		String sql="SELECT * FROM racingdronewiki.power_hub WHERE `ph_model`like '%"+model+"%';";
		try {
			pstmt = (PreparedStatement) connection.prepareStatement(sql);
			resSet=pstmt.executeQuery();
			while(resSet.next())
			{
				PowerHub ph=new PowerHub(resSet.getString(1), resSet.getString(3),
						resSet.getInt(4), resSet.getString(5),
						null, resSet.getString(17),
						resSet.getBoolean(2), resSet.getFloat(6),
						resSet.getFloat(7), resSet.getFloat(8), 
						resSet.getFloat(9), resSet.getFloat(10),
						resSet.getBoolean(11), resSet.getBoolean(12), 
						resSet.getBoolean(13), resSet.getInt(14),
						resSet.getString(15));
				Blob inBlob=resSet.getBlob(16);
				if(inBlob!=null)
				{
					InputStream is=inBlob.getBinaryStream();                //获取二进制流对象  
	                BufferedInputStream bis=new BufferedInputStream(is);    //带缓冲区的流对象  
	                byte[] buff=new byte[(int) inBlob.length()]; 
	                bis.read(buff, 0, buff.length);          //一次性全部读到buff中  
	                ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buff));  
	                LinkedList<String> ls=(LinkedList<String>) in.readObject();
	                ph.setExtraPictures(ls);
				}
				result.add(ph);
			} 
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(!result.iterator().hasNext())
			return null;
		return result;
	}

}