package com.icss.yierjia.biz;

import java.util.List;

import com.icss.yierjia.dao.ShopDao;
import com.icss.yierjia.entity.Shop;
import com.icss.yierjia.util.PageResult;

public class ShopBiz {
	/**
	 * ���ݹؼ��ֲ��ҵ��̲���ҳ��ʾ
	 * @param keyWords
	 * @return
	 * @throws Exception 
	 */
	public void setPageResult(String keyWords,PageResult<Shop> pr) throws Exception{
		List<Shop> list=null;
		ShopDao sd=new ShopDao();
		//��ȡ��ǰҳ
		int currentPage=pr.getCurrentPage();
		//���㱾ҳ��ʼ����
		int start=(currentPage-1)*12;
		try {
			//��ȡ��ҳ�б�
			list=sd.selectShopByKeyWords(keyWords, start);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=sd.getTotalCount(keyWords);
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%12==0?totalCount/12:totalCount/12+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			sd.closeConnection();
		}
	}
	
	/**
	 * ����sid�����̼���Ϣ
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public Shop findBySid(int sid) throws Exception{
		Shop shop = null;
		ShopDao dao = new ShopDao();
		try {
			shop = dao.selectBySid(sid);
		} catch (Exception e) {
			throw e;
		} finally{
			dao.closeConnection();
		}
		return shop;
	}
	
	/**
	 * ͨ��״̬��ѯ�����б�  0δ���1���ͨ��2��ɾ��3ȫ��
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public List<Shop> findShopsByState(String state) throws Exception{
		List<Shop> list = null;
		ShopDao dao = new ShopDao();
		if (state.equals("3")) {
			list = dao.selectAllShops();
		}else{
			list = dao.selectAllShopsByState(state);
		}
		return list;
	}
	
	/**
	 * ��ѯ�����̼Ҳ���ҳ��ʾ
	 * @return
	 * @throws Exception 
	 */
	public void shopList(PageResult<Shop> pr,int pageMax) throws Exception{
		ShopDao sd=new ShopDao();
		try {
			//��ȡ��ǰҳ
			int currentPage=pr.getCurrentPage();
			//������ʼ����
			int start = (currentPage-1)*pageMax;
			//��ȡ��ҳ�б�
			List<Shop> list=sd.selectAllShops(start,pageMax);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=sd.selectShopCount();
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%pageMax==0?totalCount/pageMax:totalCount/pageMax+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			sd.closeConnection();
		}
	}
	
	/**
	 * ��ѯָ��״̬�̼Ҳ���ҳ��ʾ
	 * @return
	 * @throws Exception 
	 */
	public void shopStateList(PageResult<Shop> pr,int pageMax,String state) throws Exception{
		ShopDao sd=new ShopDao();
		try {
			//��ȡ��ǰҳ
			int currentPage=pr.getCurrentPage();
			//������ʼ����
			int start = (currentPage-1)*pageMax;
			//��ȡ��ҳ�б�
			List<Shop> list=sd.selectShopsByState(state,start,pageMax);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=sd.selectShopCountByState(state);
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%pageMax==0?totalCount/pageMax:totalCount/pageMax+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			sd.closeConnection();
		}
	}
	/**
	 * �����̼�״̬
	 * @param sid
	 * @param state
	 * @throws Exception 
	 */
	public void changeShopState(int sid,String state) throws Exception{
		ShopDao sd=new ShopDao();
		try {
			sd.changeShopState(sid, state);
		} catch (Exception e) {
			throw e;
		}finally{
			sd.closeConnection();
		}
	}
}
