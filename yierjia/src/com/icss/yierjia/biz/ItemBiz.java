package com.icss.yierjia.biz;

import java.util.List;

import com.icss.yierjia.dao.ItemDao;
import com.icss.yierjia.entity.Item;
import com.icss.yierjia.util.IndexList;
import com.icss.yierjia.util.PageResult;

public class ItemBiz {
	/**
	 * ��������ʳƷ 
	 * @return
	 * @throws Exception 
	 */
	public void setIndexList(IndexList il) throws Exception{
		ItemDao id=new ItemDao();
		try {
			List<Item> foodlist=id.findFoodTopN(7);
			il.setFoodList(foodlist);
			
			List<Item> clothesList=id.findClothesTopN(9);
			il.setClothesList(clothesList);
			
			List<Item> digitalList=id.findDigitalTopN(12);
			il.setDigitalList(digitalList);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
	}
	/**
	 * ���ݹؼ��ֲ�����Ʒ����ҳ��ʾ
	 * @param keyWords
	 * @return
	 * @throws Exception 
	 */
	public void setPageResult(String keyWords,PageResult<Item> pr) throws Exception{
		List<Item> list=null;
		ItemDao id=new ItemDao();
		//��ȡ��ǰҳ
		int currentPage=pr.getCurrentPage();
		//���㱾ҳ��ʼ����
		int start=(currentPage-1)*12;
		try {
			//��ȡ��ҳ�б�
			list=id.selectItemByKeyWords(keyWords, start);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=id.getTotalCount(keyWords);
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%12==0?totalCount/12:totalCount/12+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
	}
	/**
	 * ������Ʒid��ѯ��Ʒ
	 * @param iid
	 * @return
	 * @throws Exception 
	 */
	public Item selectItemById(int iid) throws Exception{
		Item item=null;
		ItemDao id=new ItemDao();
		try {
			item=id.selectItemById(iid);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
		return item;
	}
	
	/**
	 * �������������Ʒ����ҳ��ʾ
	 * @param type
	 * @param pr
	 * @throws Exception
	 */
	public void searchItemsByType(String type,PageResult<Item> pr) throws Exception{
		List<Item> list=null;
		ItemDao id=new ItemDao();
		//��ȡ��ǰҳ
		int currentPage=pr.getCurrentPage();
		//���㱾ҳ��ʼ����
		int start=(currentPage-1)*12;
		try {
			//��ȡ��ҳ�б�
			list=id.selectItemByType(type, start);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=id.getTypeTotalCount(type);
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%12==0?totalCount/12:totalCount/12+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
	}
	
	public void setPageResultBySid(int sid,PageResult<Item> page) throws Exception{
		ItemDao dao = new ItemDao();
		//ÿҳ��ʾ9��
		int maxResult = 9;
		//�ӵڼ�����ʼ����
		int currentPage = page.getCurrentPage();
		int firstResult = (currentPage - 1) * maxResult;
		try {
			//������Ʒ�б�,��ӵ�page
			List<Item> list = dao.pageItemsBySid(sid,firstResult,maxResult);
			page.setList(list);
			//����������
			int count = dao.selectCountBySid(sid);
			page.setTotalCount(count);
			//�����ҳ��
			int totalPage = count%maxResult==0?count/maxResult:count/maxResult+1;
			page.setTotalPage(totalPage);
			
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		
	}
	/**
	 * ͨ��״̬������Ʒ�б�0�ϼ�1�¼�2����
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public List<Item> findItemsByState(String state) throws Exception{
		List<Item> list = null;
		ItemDao dao = new ItemDao();
		try {
			if (state.equals("2")) {
				list = dao.selectAllItems();
			}else {
				list = dao.selectItemsByState(state);
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			dao.closeConnection();
		}
		return list;
	}
	
	/**
	 * ��ѯ�����̼Ҳ���ҳ��ʾ
	 * @return
	 * @throws Exception 
	 */
	public void itemList(PageResult<Item> pr,int pageMax) throws Exception{
		ItemDao id=new ItemDao();
		try {
			//��ȡ��ǰҳ
			int currentPage=pr.getCurrentPage();
			//������ʼ����
			int start = (currentPage-1)*pageMax;
			//��ȡ��ҳ�б�
			List<Item> list=id.selectAllItems(start,pageMax);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=id.selectItemCount();
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%pageMax==0?totalCount/pageMax:totalCount/pageMax+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
	}
	
	/**
	 * ��ѯָ��״̬�̼Ҳ���ҳ��ʾ
	 * @return
	 * @throws Exception 
	 */
	public void itemStateList(PageResult<Item> pr,int pageMax,String state) throws Exception{
		ItemDao id=new ItemDao();
		try {
			//��ȡ��ǰҳ
			int currentPage=pr.getCurrentPage();
			//������ʼ����
			int start = (currentPage-1)*pageMax;
			//��ȡ��ҳ�б�
			List<Item> list=id.selectItemsByState(state,start,pageMax);
			pr.setList(list);
			
			//��ȡ������
			int totalCount=id.selectItemCountByState(state);
			pr.setTotalCount(totalCount);
			
			//������ҳ��
			int totalPage=totalCount%pageMax==0?totalCount/pageMax:totalCount/pageMax+1;
			pr.setTotalPage(totalPage);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
	}
	/**
	 * �޸���Ʒ״̬
	 * @param parseInt
	 * @param state
	 * @throws Exception 
	 */
	public void changeItemState(int iid, String state) throws Exception {
		ItemDao id=new ItemDao();
		try {
			id.changeItemState(iid, state);
		} catch (Exception e) {
			throw e;
		}finally{
			id.closeConnection();
		}
		
	}
}
