package week.second;

public class tsp_eample {
	static int[] arr = { 1, 2, 3, 4 };
	static final int SIZE = 4;

	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void print_arr() {
		for (int i = 0; i < SIZE; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	static void perm1(int k, int n) {
		for (int i = k; i < n; i++) {
			swap(k, i);
			print_arr();
		}

		System.out.println("=======================");
	}

	static void perm2(int k, int n) {
		for (int i = k; i < n; i++) {
			swap(k, i);
			print_arr();
			swap(k, i);
		}

		System.out.println("=======================");
	}

	/*
	 * static void perm(int k, int n){ for(int i=k;i<n;i++){ swap(k,i); //
	 * print_arr(); for(int j=k+1;j<n;j++){ swap(k+1,j); print_arr();
	 * swap(k+1,j); } swap(k,i); System.out.println("=======================");
	 * } }
	 */

	static void perm(int k, int n) {
		if (k == n) {
			print_arr();
			return;
		}

		for (int i = k; i < n; i++) {
			swap(k, i);
			perm(k + 1, n);
			swap(k, i);
		}
	}

	public static void main(String[] args) {
		perm(0, SIZE);
//		 perm1(0,SIZE);
		 System.out.println("=======================");
//		 perm2(0,SIZE);
	}
}
