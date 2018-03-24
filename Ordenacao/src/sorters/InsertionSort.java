package sorters;

public class InsertionSort implements ISorter
{

	@Override
	public int[] sort(int[] vector)
	{
		int pivot;
		int j;
		
		for(int i = 1; i < vector.length; i++)
		{
			pivot = vector[i];
			for(j = i - 1; (j >= 0) && (vector[j] > pivot); j--)
				vector[j + 1] = vector[j];
			vector[j + 1] = pivot;
		}
		
		return vector;
	}

}
