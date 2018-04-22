package cn.joe.test;

public class test {

	public static void main(String[] args) {
		// Random random = new Random();
		int arr[] = new int[10];
		int arr1[] = new int[10];
		Boolean equals = equals(arr, arr1);
	}

	/**
	 * 用来比较两个数组长度和元素是否相等
	 * @param arr  第一个数组
	 * @param arr1 第二个数组
	 * @return 相等返回true 不相等则为false
	 */
	private static Boolean equals(int[] arr, int[] arr1) {
		if (arr.length == arr1.length) { // 判断长度是否相等
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] != arr1[i]){
					return false;
				}
			}
			return true;	
		}
		return false;
	}

}
