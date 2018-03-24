package sorters;

public class SelectionSort implements ISorter
{

	@Override
	public int[] sort(int[] vector)
	{
		int size = vector.length;
		Integer smallestElementIndex;
		Integer temp;
		for(int i = 0; i < size-1; i++)
		{
			smallestElementIndex = i;
			for(int j = i+1; j < size; j++)
			{
				if(vector[j] < vector[smallestElementIndex])
					smallestElementIndex = j;
			}
			temp = vector[i];
			vector[i] = vector[smallestElementIndex];
			vector[smallestElementIndex] = temp;
		}
		
		return vector;
	}

}
