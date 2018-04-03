package sorters;

public class QuickSort implements ISorter
{
	@Override
	public int[] sort(int[] vector)
	{
		quickSort(vector, 0, vector.length - 1);
		return vector;
	}
	
	private void quickSort(int[] vector, int startIndex, int endIndex)
	{
		int pivotIndex = (startIndex + endIndex) / 2;
		int pivot = vector[pivotIndex];
		int i = startIndex, j = endIndex;
		
		while (i <= j)
		{
			for(; (vector[i] < pivot && i <= endIndex); i++);
			for(; (vector[j] > pivot && j >= startIndex); j--);
			
			if(i <= j)
			{
				swap(vector, i, j);
				i++;
				j--;
			}
		}
		
		if(i < endIndex)
			quickSort(vector, startIndex, i);
		if(j > startIndex)
			quickSort(vector, j, endIndex);
	}
	
	private void swap(int[] vector, int index1, int index2)
	{
		int tempValue = vector[index1];
		vector[index1] = vector[index2];
		vector[index2] = tempValue;
	}
}
