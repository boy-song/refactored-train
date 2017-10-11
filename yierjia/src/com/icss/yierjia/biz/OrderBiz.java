package com.icss.yierjia.biz;

import java.sql.Timestamp;
import java.util.List;

import com.icss.yierjia.dao.OrderDao;
import com.icss.yierjia.dao.OrderItemDao;
import com.icss.yierjia.dao.ShopcartDao;
import com.icss.yierjia.entity.Item;
import com.icss.yierjia.entity.Order;
import com.icss.yierjia.util.OrderId;
import com.icss.yierjia.util.PageResult;
import com.icss.yierjia.vo.OrderItemVo;
import com.icss.yierjia.vo.OrderVo;

public class OrderBiz {
	public void createOrder(String[] idArr,String[] countArr,int uid,Timestamp tt,String address,String name,String phone) throws Exception {
		OrderDao od=new OrderDao();
		ShopcartDao shopcartDao = new ShopcartDao();
		try {
			//������
			od.beginTransaction();
			//��ȡ�̵�id���̵�����
			List<Integer> list=od.getSid(idArr);
			int scount=list.size();
			for (int i = 0; i < scount; i++) {
				//��ʼ�������۸�
				double totalPrice = 0.0;
				//���ɶ�����
				String orderid=OrderId.getOrderId(uid);
				//��Ӷ���
				od.addOrder(uid,list.get(i).intValue(), tt, address, name, phone,orderid);
				//��Ӷ�����
				for(int j=0;j<idArr.length;j++){
					//��ѯ��Ʒ��Ϣ
					Item item = od.selectItemById(Integer.parseInt(idArr[j]));
					//��Ӷ�����
					if (list.get(i).intValue()==item.getSid()) {
						//����
						int count = Integer.parseInt(countArr[j]);
						//����
						double price = item.getCurrentprice();
						od.addOrderTerm(Integer.parseInt(idArr[j]), orderid, Integer.parseInt(countArr[j]), item.getCurrentprice());
						//�ӹ��ﳵ��ɾ��ָ����Ʒ
						shopcartDao.deleteShopcartItem(uid, idArr[j]);
						//������۸�
						totalPrice += count * price;
					}
				}
				//���붩���ܼ�
				od.setTotalPrice(orderid,totalPrice);
			}
			//�ύ
			od.commit();
		} catch (Exception e) {
			od.rollback();
			throw e;
		}finally{
			od.closeConnection();
		}
	}
	
	/**
	 * ����ָ���û������ж�����Ϣ
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public List<Order> findAllByUid(int uid) throws Exception{
		List<Order> list = null;
		OrderDao dao = new OrderDao();
		try {
			list = dao.selectAllByUid(uid);
		} catch (Exception e) {
			throw e;
		} finally{
			dao.closeConnection();
		}
		return list;
	}
	

	/**
	 * ����List<OrderVo>
	 * @param list
	 * @param uid
	 * @throws Exception
	 */
	public void setOrderVo(List<OrderVo> list,int uid) throws Exception {
		OrderDao orderDao = new OrderDao();
		OrderVo orderVo = null;
		List<OrderItemVo> orderItems = null;
		try {
			//��ȡ���ж�����
			List<String> oids = orderDao.selectOidsByUid(uid);
			//����������
			for (String oid : oids) {
				orderVo = new OrderVo();
				//����order����
				orderDao.setOrderVoByOid(oid, orderVo);
				//����list����
				orderItems = orderDao.selectByOid(oid);
				orderVo.setOrderItems(orderItems);
				//��ӽ�List<OrderVo>
				list.add(orderVo);
			}
		} catch (Exception e) {
			orderDao.rollback();
			throw e;
		} finally{
			orderDao.closeConnection();
		}
		
	}
	
	/**
	 * ��ѯ���ж�������ҳ��ʾ
	 * @return
	 * @throws Exception 
	 */
	public void orderList(PageResult<Order> pr,int pageMax) throws Exception{
		OrderDao od=new OrderDao();
		try {
			//��ȡ��ǰҳ
			int currentPage=pr.getCurrentPage();
			//������ʼ����
			int start = (currentPage-1)*pageMax;
			//��ȡ��ҳ�б�
			List<Order> list=od.selectAllOrders(start,pageMax);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=od.getOrderCount();
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%pageMax==0?totalCount/pageMax:totalCount/pageMax+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			od.closeConnection();
		}
	}
	
	/***
	 * ����OrderVo
	 * @param oid
	 * @param ov
	 * @throws Exception
	 */
	public void setOrderVO(String oid,OrderVo ov) throws Exception{
		OrderDao od=new OrderDao();
		OrderItemDao oi=new OrderItemDao();
		try {
			//���ݶ���id��ѯ�����������õ�ov��
			od.setOrderVoByOid(oid,ov);
			//���ݶ���id��ѯ���������б������õ�ov��
			List<OrderItemVo> list=oi.selectByOid(oid);
			ov.setOrderItems(list);
		} catch (Exception e) {
			throw e;
		}finally{
			od.closeConnection();
		}
	}
}
