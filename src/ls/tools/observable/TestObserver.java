package ls.tools.observable;

public class TestObserver {

	public static void main(String[] args) {
		
		ProductList observable = ProductList.getInstance();
		TBObserver tbObserver = new TBObserver();
		JDObserver jdObserver = new JDObserver();
		TMObserver tmObserver = new TMObserver();
		observable.addObserver(jdObserver);
		observable.addObserver(tbObserver);
		observable.addObserver(tmObserver);
		observable.addProduct("新增产品1");
	}

}
