package com.icss.yierjia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.icss.yierjia.entity.Item;
import com.icss.yierjia.entity.Order;
import com.icss.yierjia.vo.OrderItemVo;
import com.icss.yierjia.vo.OrderVo;



public class OrderDao extends BaseDao{
	/**
	 * ����̼�id�б�
	 * @param idArr
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getSid(String[] idArr) throws Exception {
		List<Integer> list=new ArrayList<>();
		this.openConnection();
		String sql="select s_id from item where i_id in (";
		String ids="";
		for(int i=0;i<idArr.length;i++){
			if (i==idArr.length-1) {
				ids=ids+idArr[i];
			}else {
				ids=ids+idArr[i]+",";
			}
		}
		sql += ids;
		sql += ") group by s_id";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			list.add(rs.getInt(1));
		}
		return list;
	}
	/**
	 * ��Ӷ���
	 * @param uid
	 * @param sid
	 * @param tt
	 * @param address
	 * @param name
	 * @param phone
	 * @throws Exception
	 */
	public void addOrder(int uid,int sid,Timestamp tt,String address,String name,String phone,String oid) throws Exception{
		this.openConnection();
		String sql="insert into orders(u_id,s_id,o_date,o_state,o_fcharge,address,name,phone,o_id) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, uid);
		pst.setInt(2, sid);
		pst.setTimestamp(3, tt);
		pst.setString(4, "0");
		pst.setDouble(5, 0);
		pst.setString(6, address);
		pst.setString(7, name);
		pst.setString(8, phone);
		pst.setString(9, oid);
		pst.execute();
		pst.close();
	}
	/**
	 * ���ɶ�����
	 * @throws Exception
	 */
	public void addOrderTerm(int iid,String oid,int icount,double oiprice) throws Exception {
		this.openConnection();
		String sql="insert into order_item (i_id,o_id,i_count,o_i_price) values(?,?,?,?)";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, iid);
		pst.setString(2, oid);
		pst.setInt(3, icount);
		pst.setDouble(4, oiprice);
		pst.execute();
		pst.close();
	}
	
	/**
	 * ���ö����۸�
	 * @param orderid
	 * @param totalPrice
	 * @throws Exception
	 */
	public void setTotalPrice(String orderid, double totalPrice) throws Exception {
		openConnection();
		String sql = "update orders set o_price=? where o_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setDouble(1, totalPrice);
		pst.setString(2, orderid);
		pst.execute();
		pst.close();
	}
	/**
	 * ����ָ���û������ж���
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public List<Order> selectAllByUid(int uid) throws Exception{
		openConnection();
		List<Order> list = new ArrayList<Order>();
		Order order = null;
		String sql = "select * from orders where u_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, uid);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			order = new Order();
			order.setAddress(rs.getString("address"));
			order.setName(rs.getString("name"));
			order.setOcdate(rs.getTimestamp("o_cdate"));
			order.setOid(rs.getString("o_id"));
			order.setUid(rs.getInt("u_id"));
			order.setSid(rs.getInt("s_id"));
			order.setOdate(rs.getTimestamp("o_date"));
			order.setOstate(rs.getString("o_state"));
			order.setOcdes(rs.getString("o_cdes"));
			order.setOprice(rs.getDouble("o_price"));
			order.setOfcharge(rs.getDouble("o_fcharge"));
			order.setPhone(rs.getString("phone"));
			list.add(order);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ��ѯָ���û������ж�����
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public List<String> selectOidsByUid(int uid) throws Exception{
		openConnection();
		List<String> list = new ArrayList<String>();
		String oid = null;
		String sql = "select o_id from orders where u_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, uid);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			oid = new String(rs.getString(1));
			list.add(oid);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ����orderVO����
	 * @param oid
	 * @param orderVo
	 * @throws Exception
	 */
	public void setOrderVoByOid(String oid,OrderVo orderVo) throws Exception{
		openConnection();
		String sql = "select * from orders where o_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, oid);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			orderVo.setAddress(rs.getString("address"));
			orderVo.setName(rs.getString("name"));
			orderVo.setOcdate(rs.getTimestamp("o_cdate"));
			orderVo.setOid(rs.getString("o_id"));
			orderVo.setUid(rs.getInt("u_id"));
			orderVo.setSid(rs.getInt("s_id"));
			orderVo.setOdate(rs.getTimestamp("o_date"));
			orderVo.setOstate(rs.getString("o_state"));
			orderVo.setOcdes(rs.getString("o_cdes"));
			orderVo.setOprice(rs.getDouble("o_price"));
			orderVo.setOfcharge(rs.getDouble("o_fcharge"));
			orderVo.setPhone(rs.getString("phone"));
		}
		rs.close();
		pst.close();
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
		}
		rs.close();
		pst.close();
		return item;
	}
	
	/**
	 * ���ݶ����Ż�ȡ������(OrderItemVo)
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public List<OrderItemVo> selectByOid(String oid) throws Exception{
		openConnection();
		List<OrderItemVo> list = new ArrayList<OrderItemVo>();
		OrderItemVo orderItem = null;
		String sql = "select t1.*,t2.i_count from item t1,order_item t2 where t2.i_id = t1.i_id and o_id = ?;";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, oid);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			orderItem = new OrderItemVo();
			orderItem.setIid(rs.getInt("i_id"));
			orderItem.setSid(rs.getInt("s_id"));
			orderItem.setItype(rs.getString("i_type"));
			orderItem.setIname(rs.getString("i_name"));
			orderItem.setCurrentprice(rs.getDouble("current_price"));
			orderItem.setIdesc(rs.getString("i_desc"));
			orderItem.setOriginalprice(rs.getDouble("original_price"));
			orderItem.setIimg(rs.getString("i_img"));
			orderItem.setIcount(rs.getInt("i_count"));
			list.add(orderItem);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ��ѯ���ж�������
	 * @return
	 * @throws Exception
	 */
	public int selectOrderCount() throws Exception{
		openConnection();
		int count = 0;
		String sql = "select count(*) from orders";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pst.close();
		return count;
	}
	
	/**
	 * ��ҳ�������ж�����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<Order> selectAllOrders(int firstResult,int maxResult) throws Exception {
		openConnection();
		List<Order> list = new ArrayList<Order>();
		Order order = null;
		String sql = "select * from orders limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, firstResult);
		pst.setInt(2, maxResult);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			order = new Order();
			order.setAddress(rs.getString("address"));
			order.setName(rs.getString("name"));
			order.setOcdate(rs.getTimestamp("o_cdate"));
			order.setOid(rs.getString("o_id"));
			order.setUid(rs.getInt("u_id"));
			order.setSid(rs.getInt("s_id"));
			order.setOdate(rs.getTimestamp("o_date"));
			order.setOstate(rs.getString("o_state"));
			order.setOcdes(rs.getString("o_cdes"));
			order.setOprice(rs.getDouble("o_price"));
			order.setOfcharge(rs.getDouble("o_fcharge"));
			order.setPhone(rs.getString("phone"));
			list.add(order);
		}
		rs.close();
		pst.close();
		return list;
	}
	
	/**
	 * ����ָ��״̬�������� 0δ����1�Ѹ���2�ѷ���3���4ȡ��
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public int selectOrderCountByState(int state) throws Exception{
		openConnection();
		int count = 0;
		String sql = "select count(*) from orders where o_state=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pst.close();
		return count;
	}
	
	/**
	 * ��ҳ��ѯָ��״̬������Ϣ
	 * @param state
	 * @param firstResult
	 * @param maxResult
	 * @return
	 * @throws Exception
	 */
	public List<Order> selectOrdersByState(int state,int firstResult,int maxResult) throws Exception{
		openConnection();
		Order order = null;
		List<Order> list = new ArrayList<Order>();
		String sql = "select * from orders where o_state=? limit ?,?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, state);
		pst.setInt(2, firstResult);
		pst.setInt(3, maxResult);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			order = new Order();
			order.setAddress(rs.getString("address"));
			order.setName(rs.getString("name"));
			order.setOcdate(rs.getTimestamp("o_cdate"));
			order.setOid(rs.getString("o_id"));
			order.setUid(rs.getInt("u_id"));
			order.setSid(rs.getInt("s_id"));
			order.setOdate(rs.getTimestamp("o_date"));
			order.setOstate(rs.getString("o_state"));
			order.setOcdes(rs.getString("o_cdes"));
			order.setOprice(rs.getDouble("o_price"));
			order.setOfcharge(rs.getDouble("o_fcharge"));
			order.setPhone(rs.getString("phone"));
			list.add(order);
		}
		rs.close();
		pst.close();
		return list;
	}
	/**
	 * ��ȡ���ж�������
	 * @return
	 * @throws Exception
	 */
	public int getOrderCount() throws Exception {
		int totalCount=0;
		this.openConnection();
		String sql="select count(*) from orders";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		if (rs.next()) {
			totalCount=rs.getInt(1);
		}
		rs.close();
		pst.close();
		return totalCount;
	}
}
