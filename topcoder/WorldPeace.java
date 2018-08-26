public class WorldPeace {

	public static long numGroups (int k, int[] countries) {
		long low = 0, high = 0, mid = 0, lim = 0;

		for (int c = 0; c < countries.length; ++c)
			lim += countries[c];

		lim /= k;
		high = lim;

		while (low <= high) {
			mid = (low + high) / 2;

			if (predicate (countries, mid, k)) {
				if (!predicate (countries, mid + 1, k))
					break;

				low = mid + 1;
			}
			else
				high = mid - 1;
		}

		return mid;
	}

	public static boolean predicate (int[] countries, long n, int k) {
		long count = 0;

		for (int c = 0; c < countries.length; ++c)
			count += Math.min (countries[c], n);

		return count >= n * k;
	}

	public static void main (String[] t) {
		System.out.println (numGroups (4, new int[]{4, 4, 4, 4, 4})); // 5
		System.out.println (numGroups (5, new int[]{1, 2, 3, 4, 5, 6})); // 3
		System.out.println (numGroups (2, new int[]{1000000000, 1000000000, 1000000000, 1000000000, 1000000000,
		                                            1000000000})); // 3000000000
		System.out.println (numGroups (7, new int[]{96, 17, 32, 138, 112, 50, 7, 19, 412, 23, 14, 50, 47, 343, 427,
		                                            22, 39})); // 166
		System.out.println (numGroups (10, new int[]{638074479, 717901019, 910893151, 924124222, 991874870,
		                                             919392444, 729973192, 607898881, 838529741, 907090878,
		                                             632877562, 678638852, 749258866, 949661738, 784641190,
		                                             815740520, 689809286, 711327114, 658017649, 636727234,
		                                             871088534, 964608547, 867960437, 964911023, 642411618,
		                                             868318236, 793328473, 849540177, 960039699, 998262224,
		                                             775720601, 634685437, 743766982, 826321850, 846671921,
		                                             712570181, 676890302, 814283264, 958273130, 899003369,
		                                             909973864, 921987721, 978601888, 633027021, 896400011,
		                                             725078407, 662183572, 629843174, 617774786, 695823011})); //
		// 3983180234
	}
}