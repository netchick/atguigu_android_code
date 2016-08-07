package com.atguigu.debug;

import java.util.ArrayList;
import java.util.List;

public class DebugTest1 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		updateList(list);
		System.out.println(list);
	}

	private static void updateList(List<Integer> list) {
		list.remove(2);
		System.out.println("------");
	}
}
