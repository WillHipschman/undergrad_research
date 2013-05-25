package collection.visualizer.common;

import java.util.Comparator;

import collection.visualizer.common.comparators.AComparator;

import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.controller.Control;
import collection.visualizer.examples.linear.ALinearVisualizer;

import bus.uigen.ObjectEditor;
import bus.uigen.shapes.ATextModel;

@SuppressWarnings({"rawtypes","unchecked"})
public class Algorithms {
	
	public static Comparator c = new AComparator();
	public static void bubbleSort(ListenableVector list) {

		int j;
		boolean flag = true;

		while (flag) {
			flag = false;
			for (j = 0; j < list.size() - 1; j++)
				if (c.compare(list.get(j), list.get(j + 1)) > 0) {
					list.swap(j, j + 1);
					flag = true;
				}
		}
	}

	public static void insertionSort(ListenableVector list) {

		int j;
		for (int p = 1; p < list.size(); p++) {
			for (j = p; j > 0 && c.compare(list.get(j), list.get(j - 1)) < 0; j--) {
				list.swap(j, j - 1);
			}
		}
	}
	public static void shellSort(ListenableVector list) {
		int j;
		for (int gap = list.size() / 2; gap > 0; gap = gap == 2 ? 1
				: (int) (gap / 2.2))
			for (int i = gap; i < list.size(); i++) {
				Object temp = list.get(i);
				for (j = i; j >= gap && c.compare(temp, list.get(j - gap)) < 0; j -= gap)
					list.set(j, list.get(j - gap));
				list.set(j, temp);
			}
	}

	// Better than insertion sort
	/*
	 * public static <AnyType extends Comparable<? super AnyType>> void
	 * shellSort(List<AnyType> list) { int j; for(int gap=list.size()/2;gap>0;
	 * gap = gap == 2 ? 1 : (int) ( gap / 2.2 )) for(int
	 * i=gap;i<list.size();i++) { AnyType temp=list.get(i);
	 * for(j=i;j>=gap&&temp.compareTo(list.get(j-gap))<0;j-=gap)
	 * list.set(j,list.get(j-gap)); list.set(j, temp); } }
	 */
	// Always sorts in O(NLogN)
	public static void mergeSort(ListenableVector<Comparable> list) {
		ListenableVector tempArray = new AListenableVector();

		ACollectionVisualizer visualizer = new ALinearVisualizer(
				new ATextModel());
		Control controller = visualizer.getController();

		// visualizer.alignVertical(true);
		try {
			visualizer.visualize(tempArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ObjectEditor.edit(controller);
		ObjectEditor.edit(visualizer);

		for (Object a : list)
			// This must be pre-filled otherwise IndexOutOfBoundsExceptions are
			// thrown
			tempArray.add(a);
		mergeSort(list, tempArray, 0, list.size() - 1);
	}

	private static void mergeSort(ListenableVector list,
			ListenableVector tempArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(list, tempArray, left, center);
			mergeSort(list, tempArray, center + 1, right);
			merge(list, tempArray, left, center + 1, right);
		}
	}

	private static void merge(ListenableVector list,
			ListenableVector tempArray, int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (c.compare(list.get(leftPos), list.get(rightPos)) <= 0)
				tempArray.set(tempPos++, list.get(leftPos++));
			else
				tempArray.set(tempPos++, list.get(rightPos++));
		while (leftPos <= leftEnd)
			tempArray.set(tempPos++, list.get(leftPos++));
		while (rightPos <= rightEnd)
			tempArray.set(tempPos++, list.get(rightPos++));
		for (int i = 0; i < numElements; i++, rightEnd--)
			list.set(rightEnd, tempArray.get(rightEnd));
	}

	public static void quickSort(ListenableVector list) {

		quickSort(list, 0, list.size() - 1);
	}

	public static void quickSort(ListenableVector list, int left, int right) {

		if (left < right) {
			int pivot = median3(list, left, right);
			int i = left, j = right - 1;

			for (;;) {
				while (c.compare(list.get(++i), list.get(pivot)) < 0) {
				}
				while (c.compare(list.get(--j), list.get(pivot)) < 0) {
				}
				if (i < j)
					swapReferences(list, i, j);
				else
					break;
			}
			swapReferences(list, i, right - 1);
			quickSort(list, left, i - 1);
			quickSort(list, i + 1, right);
		}
	}

	private static int median3(ListenableVector list, int left, int right) {
		int center = (left + right) / 2;
		if (c.compare(list.get(center), list.get(left)) < 0)
			swapReferences(list, left, center);
		if (c.compare(list.get(right), list.get(left)) < 0)
			swapReferences(list, left, right);
		if (c.compare(list.get(right), list.get(center)) < 0)
			swapReferences(list, center, right);
		swapReferences(list, center, right - 1);
		return right - 1;
	}

	private static void swapReferences(ListenableVector list, int lhs, int rhs) {
		list.swap(lhs, rhs);
	}

	public static void randomize(ListenableVector list) {
		int i = 0;
		while (i < list.size() - 2) {
			list.swap(i, (int) Math.round(Math.random() + i + 1));
			i++;
		}
	}
}