package sorters;

public class BubbleSort implements ISorter
{
	@Override
	public int[] sort(int[] vector)
	{
		Integer temp;
		
		for(int i = 0; i < vector.length; i++)
		{
			for(int j = i+1; j < vector.length; j++)
			{
				if(vector[i] > vector[j])
				{
					temp = vector[i];
					vector[i] = vector[j];
					vector[j] = temp;
				}
			}
		}
		
		return vector;
	}
}
