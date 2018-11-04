package ls.tools.sort;

public class Sort {

	//选择排序
	static void selectSort(){
		int[] arr={1,3,2,45,65,33,12};
        System.out.println("交换之前：");
        for(int num:arr){
            System.out.print(num+" ");
        }        
        //选择排序的优化
        for(int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
            int k = i;
            for(int j = k + 1; j < arr.length; j++){// 选最小的记录
                if(arr[j] < arr[k]){ 
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if(i != k){  //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }    
        }
        System.out.println();
        System.out.println("交换后：");
        for(int num:arr){
            System.out.print(num+" ");
        }
	}
	//冒泡
	static void maoSort(){
		int[] arr={6,3,8,2,9,1};
		System.out.println("排序前数组为：");
		for(int num:arr){
			System.out.println(num+" ");
		}
		for(int i=0;i<arr.length-1;i++){//外层循环控制排序趟数
			for(int j=0;j<arr.length-1-i;j++){//内层循环控制每一趟排序多少次
				if(arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
		System.out.println();
		System.out.println("排序后的数组为：");
		for (int i : arr) {
			System.out.println(i+" ");
		}
	}
	//插入
	static void insertSort(){
		int[] arr={6,3,8,2,9,1};
		for(int index = 1; index<arr.length; index++){//外层向右的index，即作为比较对象的数据的index
            int temp = arr[index];//用作比较的数据
            int leftindex = index-1;
            while(leftindex>=0 && arr[leftindex]>temp){//当比到最左边或者遇到比temp小的数据时，结束循环
                arr[leftindex+1] = arr[leftindex];
                leftindex--;
            }
            arr[leftindex+1] = temp;//把temp放到空位上
        }
	}

}
