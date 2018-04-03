package main;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import domain.Question;
import domain.Tag;
import handlers.QuestionHandler;
import handlers.TagHandler;
import sorters.BubbleSort;
import sorters.ISorter;
import sorters.InsertionSort;
import sorters.MergeSort;
import sorters.QuickSort;
import sorters.SelectionSort;

public class Main
{
	public static void main(String[] args)
	{
		final long startTime = System.nanoTime();
		
//		writeQuestionsToCsvFile();
//		writeTagsToCsvFile();
		
		int[] vector = getRandomIntegerVector(10);
		System.out.println("Original\n" + Arrays.toString(vector));
		bubbleSort(vector);
		selectionSort(vector);
		insertionSort(vector);
		mergeSort(vector);
		quickSort(vector);

		final long finishTime= System.nanoTime();
		printElapsedTime(startTime, finishTime);
	}
	
	public static void printElapsedTime(long startNanoTime, long finishNanoTime)
	{
		final long duration = finishNanoTime - startNanoTime;
		long totalSegundos = TimeUnit.NANOSECONDS.toSeconds(duration);
		int minutos = (int)(totalSegundos / 60);
		long segundos = minutos == 0 ? totalSegundos : totalSegundos % minutos;
		System.out.println("Tempo decorrido: " + minutos + "m " + segundos + "s");
	}
	
	public static void writeQuestionsToCsvFile()
	{
		File questionFile = new File("E:\\UFJF\\4º Período\\Estruturas de Dados 2\\UFJF_ED2\\Ordenacao\\io_files\\OriginalFiles\\Questions_Original.csv");
		QuestionHandler questionHandler = new QuestionHandler();
		List<Question> questionList = questionHandler.getEntries(questionFile);
		File targetFile = new File("io_files/Questions_Output.csv");
		System.out.println("Escrevendo dados no arquivo de saída " + targetFile.getName() + "...");
		questionHandler.writeToCsvFile(questionList, targetFile);
	}
	
	public static void writeTagsToCsvFile()
	{
		File tagFile = new File("io_files/Tags.csv");
		TagHandler tagHandler = new TagHandler();
		List<Tag> tagList = tagHandler.getEntries(tagFile);
		File outputFile = new File("io_files/Tags_Output.csv");
		tagHandler.writeToCsvFile(tagList, outputFile);
	}
	
	public static int[] getRandomIntegerVector(int size)
	{
		Random randomizer = new Random();
		int vector[] = new int[size];
		
		for(int i = 0; i < size; i++)
			vector[i] = randomizer.nextInt((size * 2) - 1) - size;
		
		return vector;
	}
	
	public static void copyVectorValues(int[] source, int[] target)
	{
		if(source.length != target.length)
		{
			System.out.println("Impossível copiar valores. Os vetores têm tamanhos diferentes.");
			return;
		}
		
		for(int i = 0; i < source.length; i++)
			target[i] = source[i];
	}
	
	public static void sort(ISorter sorter, int[] vector)
	{
		sorter.sort(vector);
		System.out.println(sorter.getClass().getSimpleName() + "\n" + Arrays.toString(vector));
	}
	
	public static void bubbleSort(int[] vector)
	{
		BubbleSort sorter = new BubbleSort();
		int[] copyVector = new int[vector.length];
		copyVectorValues(vector, copyVector);
		Main.sort(sorter, copyVector);
	}
	
	public static void selectionSort(int[] vector)
	{
		SelectionSort sorter = new SelectionSort();
		int[] copyVector = new int[vector.length];
		copyVectorValues(vector, copyVector);
		Main.sort(sorter, copyVector);
	}
	
	public static void insertionSort(int[] vector)
	{
		InsertionSort sorter = new InsertionSort();
		int[] copyVector = new int[vector.length];
		copyVectorValues(vector, copyVector);
		Main.sort(sorter, copyVector);
	}

	public static void mergeSort(int[] vector)
	{
		MergeSort sorter = new MergeSort();
		int[] copyVector = new int[vector.length];
		copyVectorValues(vector, copyVector);
		Main.sort(sorter, copyVector);
	}

	public static void quickSort(int[] vector)
	{
		QuickSort sorter = new QuickSort();
		int[] copyVector = new int[vector.length];
		copyVectorValues(vector, copyVector);
		Main.sort(sorter, copyVector);
	}
}
