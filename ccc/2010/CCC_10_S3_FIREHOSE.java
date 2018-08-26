import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CCC_10_S3_FIREHOSE {

	static final int CIRCUMFERENCE = 1000000;
	static int[] houses;

	//given hose of length K find min number of hydrants needed to cover all houses
	public static int hydrants (int K) {
		int hydrants = houses.length, end, count;

		//trial and error: set each house as starting point to determine best arrangement of hoses
		//(as long as house [i] is at a lesser or equal position to the hose that will be placed at house [i] - K in
		// the for loop)
		for (int i = 0; i < houses.length && houses[i] <= houses[0] + K * 2; ++i) {
			count = 1;
			end = houses[i];//set one hose at house [i] - K

			//add hoses when neccessary to cover other houses
			for (int j = i + 1; j < houses.length && houses[i] > houses[j] + K * 2 - CIRCUMFERENCE; ++j) {
				if (houses[j] > end) {//outside range of current hose, add new hose at houses [j] + K
					++count;
					end = houses[j] + K * 2;
				}
			}

			hydrants = Math.min (hydrants, count);
		}

		return hydrants;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int H = Integer.parseInt (in.readLine ());
		houses = new int[H];

		for (int h = 0; h < H; ++h) {
			houses[h] = Integer.parseInt (in.readLine ());
		}

		Arrays.sort (houses);
		int K = Integer.parseInt (in.readLine ());

		int low = 0, mid = CIRCUMFERENCE / 2, high = CIRCUMFERENCE, result;

		while (low <= high) {
			mid = low + (high - low) / 2;
			result = hydrants (mid);

			if (result > K) {//hose length is too short
				low = mid + 1;
			}
			else {//hose length is too long/just right
				high = mid - 1;
			}
		}

		//print out min hose length
		System.out.print (low);
	}
}