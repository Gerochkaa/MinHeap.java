import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
        heap.add(Integer.MIN_VALUE);
    }

    public void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;

        // Move up the heap if necessary
        while (index > 1 && heap.get(index) < heap.get(index / 2)) {
            // Swap the current element with its parent
            int temp = heap.get(index);
            heap.set(index, heap.get(index / 2));
            heap.set(index / 2, temp);
            index = index / 2; 
        }
    }

    public boolean contains(int value) {
        for (int i = 1; i < heap.size(); i++) {
            if (heap.get(i) == value) {
                return true;
            }
        }
        return false;
    }

    public void printElementsVisited(int value) {
        System.out.println("Elements visited before finding " + value + ":");
        for (int i = 1; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
            if (heap.get(i) == value) {
                break;
            }
        }
        System.out.println();
    }

    public void delete(int value) {
        int index = heap.indexOf(value);
        if (index == -1) {
            System.out.println(value + " not found in the heap.");
            return;
        }

        heap.set(index, heap.get(heap.size() - 1)); // Replace the value with the last element
        heap.remove(heap.size() - 1); // Remove the last element

        // Heapify down
        heapifyDown(index);
    }

    private void heapifyDown(int index) {
        while (2 * index < heap.size()) {
            int minChild = 2 * index; // Left child

            // Find the smaller child (left or right)
            if (2 * index + 1 < heap.size() && heap.get(2 * index + 1) < heap.get(minChild)) {
                minChild = 2 * index + 1; // Right child
            }

            // Swap with the smaller child if necessary
            if (heap.get(index) > heap.get(minChild)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(minChild));
                heap.set(minChild, temp);
                index = minChild; // Update index to the smaller child
            } else {
                break;
            }
        }
    }

    public void printHeap() {
        System.out.println("Heap:");
        for (int i = 1; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        // Insert elements
        minHeap.insert(15);
        minHeap.insert(40);
        minHeap.insert(30);
        minHeap.insert(50);
        minHeap.insert(10);
        minHeap.insert(100);
        minHeap.insert(40);

        // Print the heap
        minHeap.printHeap();

        // Check if 40 is in the heap and print elements visited before finding it
        int valueToFind = 40;
        if (minHeap.contains(valueToFind)) {
            minHeap.printElementsVisited(valueToFind);
        } else {
            System.out.println(valueToFind + " is not in the heap.");
        }

        // Delete 10
        minHeap.delete(10);
        minHeap.printHeap();
    }
}
