package sorters;

public class MergeSort implements ISorter
{
	private void divide(int[] vector, int start, int end)
	{
		if(start < end)
		{
			int half = (start + end) / 2;
			divide(vector, start, half);
			divide(vector, half+1, end);
			merge(vector, start, half, end);	
		}
	}
	
	private void merge(int[] vector, int start, int half, int end)
	{
		int i = start;
		int j = half + 1;
		int k = 0;
		int smaller;
		int[] orderedVector = new int[end - start + 1];
		
		while((i <= half) || (j <= end))
		{
			if (i > half)
			{
				smaller = vector[j];
				j++;
			}
			else if (j > end)
			{
				smaller = vector[i];
				i++;
			}
			else
			{
				if(vector[i] < vector[j])
				{
					smaller = vector[i];
					i++;
				}
				else
				{
					smaller = vector[j];
					j++;
				}
			}
			
			orderedVector[k++] = smaller;
		}
		
		vector = orderedVector;
	}
	
	@Override
	public int[] sort(int[] vector)
	{
		divide(vector, 0, vector.length - 1);
		return vector;
	}
}
