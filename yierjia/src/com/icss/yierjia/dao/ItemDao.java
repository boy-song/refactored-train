package com.icss.yierjia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.icss.yierjia.entity.Item;

public class ItemDao extends BaseDao{
	/**
	 * ��������ʳƷ 
	 * @return
	 * @throws Exception 
	 */
	public List<Item> findFood() throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like '%ʳƷ%'";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * �������з�װ
	 * @return
	 * @throws Exception
	 */
	public List<Item> findClothes() throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like '%��װ%'";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * �������������Ʒ
	 * @return
	 * @throws Exception
	 */
	public List<Item> findDigital() throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like '%����%'";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ����ǰָ��������ʳƷ 
	 * @return
	 * @throws Exception 
	 */
	public List<Item> findFoodTopN(int n) throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like '%ʳƷ%' limit 0,?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, n);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ����ǰָ��������װ
	 * @return
	 * @throws Exception
	 */
	public List<Item> findClothesTopN(int n) throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like '%��װ%' limit 0,?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, n);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ����ǰָ�����������Ʒ
	 * @return
	 * @throws Exception
	 */
	public List<Item> findDigitalTopN(int n) throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like '%����%' limit 0,?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, n);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ���ݹؼ��ֲ�����Ʒ
	 * @param keyWords
	 * @return
	 * @throws Exception 
	 */
	public List<Item> selectItemByKeyWords(String keyWords,int start) throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and (i_name like ? or i_desc like ? or i_type like ?) limit ?,12";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, "%"+keyWords+"%");
		pst.setString(2, "%"+keyWords+"%");
		pst.setString(3, "%"+keyWords+"%");
		pst.setInt(4, start);
		ResultSet rs=pst.executeQuery();
		while (rs.next()) {
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ��ȡ���Ϲؼ��ֵ���Ʒ������
	 * @return
	 * @throws Exception
	 */
	public int getTotalCount(String keyWords) throws Exception{
		int totalCount=0;
		this.openConnection();
		String sql="select count(*) from item where i_state=0 and (i_name like ? or i_desc like ? or i_type like ?)";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, "%"+keyWords+"%");
		pst.setString(2, "%"+keyWords+"%");
		pst.setString(3, "%"+keyWords+"%");
		ResultSet rs=pst.executeQuery();
		if (rs.next()) {
			totalCount=rs.getInt(1);
		}
		rs.close();
		pst.close();
		return totalCount;
	}
	/**
	 * ������Ʒid��ѯ��Ʒ
	 * @param iid
	 * @return
	 * @throws Exception 
	 */
	public Item selectItemById(int iid) throws Exception{
		Item item=null;
		this.openConnection();
		String sql="select * from item where i_id=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, iid);
		ResultSet rs=pst.executeQuery();
		if (rs.next()) {
			item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
		}
		rs.close();
		pst.close();
		return item;
	}
	
	/**
	 * ������������Ʒ
	 * @param keyWords
	 * @return
	 * @throws Exception 
	 */
	public List<Item> selectItemByType(String type,int start) throws Exception{
		List<Item> list=new ArrayList<Item>();
		this.openConnection();
		String sql="select * from item where i_state=0 and i_type like ? limit ?,12";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, "%"+type+"%");
		pst.setInt(2, start);
		ResultSet rs=pst.executeQuery();
		while (rs.next()) {
			Item item=new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ��ȡ������͵���Ʒ������
	 * @return
	 * @throws Exception
	 */
	public int getTypeTotalCount(String type) throws Exception{
		int totalCount=0;
		this.openConnection();
		String sql="select count(*) from item where i_state=0 and i_type like ?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, "%"+type+"%");
		ResultSet rs=pst.executeQuery();
		if (rs.next()) {
			totalCount=rs.getInt(1);
		}
		rs.close();
		pst.close();
		return totalCount;
	}
	
	/**
	 * ͨ���̼�id������Ʒ��Ϣ
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public List<Item> pageItemsBySid(int sid,int firstResult,int maxResult) throws Exception{
		List<Item> list = new ArrayList<Item>();
		Item item = null;
		openConnection();
		String sql = "select * from item where s_id=? limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, sid);
		pst.setInt(2, firstResult);
		pst.setInt(3, maxResult);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			item = new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ͨ���̼�id������Ʒ����
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public int selectCountBySid(int sid) throws Exception{
		int count  = 0;
		openConnection();
		String sql = "select count(*) from item where s_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, sid);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pst.close();
		return count;
	}
	
	/**
	 * ����������Ʒ
	 * @return
	 * @throws Exception
	 */
	public List<Item> selectAllItems() throws Exception{
		openConnection();
		List<Item> list = new ArrayList<Item>();
		Item item = null;
		String sql = "select * from item";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			item = new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ͨ��״̬������Ʒ
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public List<Item> selectItemsByState(String state) throws Exception{
		openConnection();
		List<Item> list = new ArrayList<Item>();
		Item item = null;
		String sql = "select * from item where i_state=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, state);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			item = new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ��ҳ����������Ʒ
	 * @return
	 * @throws Exception
	 */
	public List<Item> selectAllItems(int start,int pageMax) throws Exception{
		openConnection();
		List<Item> list = new ArrayList<Item>();
		Item item = null;
		String sql = "select * from item limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, start);
		pst.setInt(2, pageMax);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			item = new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ��ȡ������Ʒ����
	 * @return
	 * @throws Exception
	 */
	public int selectItemCount() throws Exception {
		int totalCount=0;
		this.openConnection();
		String sql="select count(*) from item";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		if (rs.next()) {
			totalCount=rs.getInt(1);
		}
		rs.close();
		pst.close();
		return totalCount;
		
	}
	/**
	 * ͨ��״̬������Ʒ
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public List<Item> selectItemsByState(String state,int start,int pageMax) throws Exception{
		openConnection();
		List<Item> list = new ArrayList<Item>();
		Item item = null;
		String sql = "select * from item where i_state=? limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, state);
		pst.setInt(2,start);
		pst.setInt(3, pageMax);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			item = new Item();
			item.setIid(rs.getInt("i_id"));
			item.setSid(rs.getInt("s_id"));
			item.setIname(rs.getString("i_name"));
			item.setItype(rs.getString("i_type"));
			item.setCurrentprice(rs.getDouble("current_price"));
			item.setOriginalprice(rs.getDouble("original_price"));
			item.setIdesc(rs.getString("i_desc"));
			item.setIimg(rs.getString("i_img"));
			item.setIstate(rs.getString("i_state"));
			list.add(item);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ��ȡָ��״̬��Ʒ����
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public int selectItemCountByState(String state) throws Exception {
		int totalCount=0;
		this.openConnection();
		String sql="select count(*) from item where i_state=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, state);
		ResultSet rs=pst.executeQuery();
		if (rs.next()) {
			totalCount=rs.getInt(1);
		}
		rs.close();
		pst.close();
		return totalCount;
		
	}
	/**
	 * �޸���Ʒ״̬
	 * @param parseInt
	 * @param state
	 * @throws Exception 
	 */
	public void changeItemState(int iid, String state) throws Exception {
		this.openConnection();
		String sql="update item set i_state=? where i_id=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, state);
		pst.setInt(2,iid);
		pst.execute();
		pst.close();
	}

}

