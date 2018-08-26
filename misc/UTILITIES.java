public class UTILITIES {

	static final double EPS = 10e-6;

	public static int lower_bound (int[] arr, int x) {
		int low = 0, high = arr.length, mid = -1;

		while (low < high) {
			mid = (low + high) / 2;

			if (arr[mid] >= x)
				high = mid;
			else
				low = mid + 1;
		}

		return low;
	}

	public static int upper_bound (int[] arr, int x) {
		int low = 0, high = arr.length, mid = -1;

		while (low < high) {
			mid = (low + high) / 2;

			if (arr[mid] > x)
				high = mid;
			else
				low = mid + 1;
		}

		return low;
	}

	public static int gcd (int a, int b) {
		return b == 0 ? a : gcd (b, a % b);
	}

	public static int lcm (int a, int b) {
		return a * b / gcd (a, b);
	}

	public static int fast_pow_mod (int b, int x, int mod) {
		if (x == 0) return 1;
		if (x == 1) return b;
		if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;

		return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
	}

	public static int fast_pow (int b, int x) {
		if (x == 0) return 1;
		if (x == 1) return b;
		if (x % 2 == 0) return fast_pow (b * b, x / 2);

		return b * fast_pow (b * b, x / 2);
	}
}