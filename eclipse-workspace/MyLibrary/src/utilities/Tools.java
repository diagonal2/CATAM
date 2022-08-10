package utilities;

public class Tools {
	
	public static void mergeSort(double[] ori, int length) {
		
		if (length < 2) return;
		int midpt = length / 2;
		
		double[] left = new double[midpt];
		System.arraycopy(ori, 0, left, 0, midpt);
		
		double[] right = new double[length - midpt];
		System.arraycopy(ori, midpt, right, 0, length - midpt);
		
		mergeSort(left, midpt);
		mergeSort(right, length - midpt);
		
		int l = 0, r = 0, o = 0;
		while (l < midpt && r < length - midpt)
			if (left[l] < right[r]) ori[o++] = left[l++];
			else ori[o++] = right[r++];
		while (l < midpt)
			ori[o++] = left[l++];
		while (r < length - midpt)
			ori[o++] = right[r++];
		
	}
	
	public static byte[] extractBinary(long number, int length) {
		
		byte[] digits = new byte[length];
		
		for (int i = 0; i < length; i++)
			digits[i] = (byte) ((number & (1 << i)) >> i);
		
		return digits;
		
	}
	
}
