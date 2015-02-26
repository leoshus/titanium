package com.sdw.soft.test;

import java.util.Map;
import java.util.Map.Entry;

public class SpringTest {

	private Map<String,String> mixVos;

	public Map<String, String> getMixVos() {
		return mixVos;
	}

	public void setMixVos(Map<String, String> mixVos) {
		this.mixVos = mixVos;
	}
	
	
	public void print(){
		for(Entry<String, String> entry : mixVos.entrySet()){
			System.out.println("key="+entry.getKey()+"-------value="+entry.getValue());
		}
		mixVos.clear();
	}
}
