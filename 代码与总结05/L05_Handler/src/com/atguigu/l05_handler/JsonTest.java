package com.atguigu.l05_handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
1. 将json格式的字符串{}转换为Java对象, 使用原生API
2. 将json格式的字符串{}转换为Java对象, 使用GSON
3. 将json格式的字符串[]转换为Java对象的List, 使用原生API
4. 将json格式的字符串[]转换为Java对象的List, 使用GSON

5. 将Java对象转换为json字符串{}, 使用GSON
6. 将Java对象的List转换为json字符串[], 使用GSON
 */
public class JsonTest extends AndroidTestCase{

	/*
	 * 1. 将json格式的字符串{}转换为Java对象, 使用原生API
	 */
	public void testJsonToObject() throws JSONException {
		String jsonString = "{\"id\":2, \"name\":\"大虾\", \"price\":12.3,\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"}";
		
		//将json字符串封装为JSONObject对象
		JSONObject jsonObject = new JSONObject(jsonString);
		//从对象中根据key得到对应的value
		int id = jsonObject.getInt("id");
		String name = jsonObject.getString("name");
		double price = jsonObject.getDouble("price");
		String imagePath = jsonObject.getString("imagePath");
		//封装ShopInfo对象
		ShopInfo shopInfo = new ShopInfo(id, name, price, imagePath);
		
		Log.e("TAG", shopInfo.toString());
	}
	
	/*
	 * 1. 将json格式的字符串{}转换为Java对象, 使用GSON
	 */
	public void testJsonToObject2()  {
		String jsonString = "{\"id\":3, \"name\":\"大虾\", \"price\":12.3,\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"}";
		
		ShopInfo shopInfo = new Gson().fromJson(jsonString, ShopInfo.class);
		
		Log.e("TAG", shopInfo.toString());
	}
	
	
	/*
	 * 3. 将json格式的字符串[]转换为Java对象的List, 使用原生API
	 */
	public void testJsonToList() throws JSONException {
		String jsonString = "[{\"id\":3, \"name\":\"大虾\", \"price\":12.3,\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"},"
				+ "{\"id\":5, \"name\":\"大虾2\", \"price\":128.3,\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f2.jpg\"}]";
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		
		//1. 将json字符串包装JSONArray对象
		JSONArray jsonArray = new JSONArray(jsonString);
		//2. 遍历JSONArray对象所有元素(JSONObject), 并将每个元素封装为shopInfo, 并添加到List
		for(int i=0;i<jsonArray.length();i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			//从对象中根据key得到对应的value
			int id = jsonObject.getInt("id");
			String name = jsonObject.getString("name");
			double price = jsonObject.getDouble("price");
			String imagePath = jsonObject.getString("imagePath");
			//封装ShopInfo对象
			ShopInfo shopInfo = new ShopInfo(id, name, price, imagePath);
			list.add(shopInfo);
		}
		
		Log.e("TAG", list.toString());
	}
	
	/*
	 * 4. 将json格式的字符串[]转换为Java对象的List, 使用GSON
	 */
	public void testJsonToList2() throws JSONException {
		String jsonString = "[{\"id\":4, \"name\":\"大虾\", \"price\":12.3,\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"},"
				+ "{\"id\":6, \"name\":\"大虾2\", \"price\":128.3,\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f2.jpg\"}]";
		
		List<ShopInfo> list = new Gson().fromJson(jsonString, new TypeToken<List<ShopInfo>>(){}.getType());
		
		Log.e("TAG", list.toString());
	}
	
	/*
		5. 将Java对象转换为json字符串{}, 使用GSON
	*/
	public void testObjectToJson() {
		ShopInfo info = new ShopInfo(3, "KK", 1000, "http://www.sina.com");
		String json = new Gson().toJson(info);
		Log.e("TAG", json);
	}
	
	
	/*
		6. 将Java对象的List转换为json字符串[], 使用GSON
	*/
	
	public void testListToJson() {
		
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		list.add(new ShopInfo(3, "KK", 1000, "http://www.sina.com"));
		list.add(new ShopInfo(4, "KK2", 2000, "http://www.sina.com222"));
		
		String json = new Gson().toJson(list);
		
		Log.e("TAG", json);
	}
	
	public void testJsonToMap() {
		String jsonString = "{\"my name\":\"大虾\", \"1\":12}";
		Map<String, Object> map = new Gson().fromJson(jsonString, new TypeToken<Map<String, Object>>(){}.getType());
		Log.e("TAG", map.toString());
	}
}
