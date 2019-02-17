package ls.tools.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductList extends Observable {

	//产品列表
	private List<String> productList=null;
	//类唯一实例
	private static ProductList instance;
	//构建方法私有化
	private ProductList(){
		
	}
	/**
	 * 取得唯一实例
	 * @return 产品列表唯一实例
	 */
	public static ProductList getInstance(){
		if(instance==null){
			instance=new ProductList();
			instance.productList=new ArrayList<String>();
		}
		return instance;
	}
	/**
	 * 增加观察者(电商接口)
	 * @param observer 观察者
	 */
	public void addProductListObserver(Observer observer){
		this.addObserver(observer);
	}
	/**
	 * 新增产品
	 * @param newProduct 新产品
	 */
	public void addProduct(String newProduct){
		productList.add(newProduct);
		System.out.println("产品列表新增了产品"+newProduct);
		//设置被观察对象发生变化
		this.setChanged();
		//通知观察者，并传递新产品
		this.notifyObservers(newProduct);
	}
}
