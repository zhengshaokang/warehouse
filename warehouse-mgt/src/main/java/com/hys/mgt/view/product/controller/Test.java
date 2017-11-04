package com.hys.mgt.view.product.controller;

import java.util.ArrayList;
import java.util.List;

import com.hys.dal.select.ProductStatus;
import com.mysql.fabric.xmlrpc.base.Array;



public class Test {
	public static void main(String[] args) {
		ArrayList<String> d1 = new ArrayList<String>();
		
		List<ArrayList> detectionStockAreaToStock = new ArrayList<ArrayList>();
		detectionStockAreaToStock.add(d1);
		
		List<ArrayList> detectionNonStockAreaToStock = new ArrayList<ArrayList>();
		
		
		
		ArrayList<ArrayList> lists = new ArrayList<ArrayList>();
		for(int i = 0; i < detectionStockAreaToStock.size(); i ++) {
			for(int j = 0; j < detectionNonStockAreaToStock.size(); j ++) {
				if(detectionStockAreaToStock.get(i).get(0).equals(detectionNonStockAreaToStock.get(j).get(0)) && detectionStockAreaToStock.get(i).get(1).equals(detectionNonStockAreaToStock.get(j).get(1))) {
					ArrayList list = new ArrayList();
					list.add(detectionStockAreaToStock.get(i).get(0));
					list.add(detectionStockAreaToStock.get(i).get(1));
					list.add(detectionNonStockAreaToStock.get(j).get(2));
					list.add(detectionStockAreaToStock.get(i).get(3));
					list.add(detectionStockAreaToStock.get(i).get(4));
					list.add(detectionStockAreaToStock.get(i).get(5));
					//getListByOther(detectionStockAreaToStock.get(i), list);
					lists.add(list);
				}
			}
		}
	}
}
