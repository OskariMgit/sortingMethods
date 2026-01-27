// https://www.gregorygaines.com/blog/how-to-use-function-pointers-in-java/
import java.util.Arrays;
import java.util.Random;
public class FunctionRef {


    public static int[] makeRandomArray(int n)
    {
        int[] array = new int[n];
        Random gen = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = gen.nextInt(n);
        }
        return array;
    }

    public static void printArray(int[] array)
    {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }
    public static void bubbleSort(int[] data){
        int n = data.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    
                    // Swap arr[j] and arr[j+1]
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    public static void shellSort(int[] data) {

        int n = data.length;

        // Start with a large gap, then reduce it step by step
        for (int gap = n / 2; gap > 0; gap /= 2) {

            // Perform a "gapped" insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                
                // Current element to be placed correctly
                int temp = data[i]; 
                int j = i;

                // Shift earlier elements that are greater than temp
                while (j >= gap && data[j - gap] > temp) {
                    data[j] = data[j - gap];
                    j -= gap;
                }

                // Place temp in its correct position
                data[j] = temp;
            }
        }
    }

    static int partition(int[] data, int low, int high) {
        
        // choose the pivot
        int pivot = data[high];
        
        // index of smaller element and indicates 
        // the right position of pivot found so far
        int i = low - 1;

        // traverse arr[low..high] and move all smaller
        // elements to the left side. Elements from low to 
        // i are smaller after every iteration
        for (int j = low; j <= high - 1; j++) {
            if (data[j] < pivot) {
                i++;
                swap(data, i, j);
            }
        }
        
        // Move pivot after smaller elements and
        // return its position
        swap(data, i + 1, high);  
        return i + 1;
    }

    
    static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            
            // pi is the partition return index of pivot
            int pi = partition(data, low, high);

            // recursion calls for smaller elements
            // and greater or equals elements
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
 
    }

    static void merge(int data[], int l, int m, int r){
        
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = data[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = data[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                data[k] = L[i];
                i++;
            }
            else {
                data[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            data[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            data[k] = R[j];
            j++;
            k++;
        }

    }

    public static void mergeSort(int data[], int l, int m, int r){
        
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = data[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = data[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                data[k] = L[i];
                i++;
            }
            else {
                data[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            data[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            data[k] = R[j];
            j++;
            k++;
        }
    }

    private interface FunctionPointer {
        // Method signatures of pointed method
        void methodSignature(int[] arr);
    }

    public static void sortMethodA(int[] arr) {
        System.out.println("Bubblesort");
        bubbleSort(arr);
    }

    public static void sortMethodB(int[] arr) {
        System.out.println("Quicksort");
        quickSort(arr, 0, arr.length - 1);
    }

    public static void sortMethodC(int[] arr) {
        System.out.println("Insertionsort");
        insertionSort(arr);
    }

    public static void sortMethodD(int[] arr) {
        System.out.println("Shellsort");
        shellSort(arr);
    }

    public static void sortMethodE(int[] arr) {
        System.out.println("Mergesort");
        mergeSort(arr, 0, arr.length / 2, arr.length - 1);
    }

    public static void sortMethodF(int[] arr) {
        Arrays.sort(arr);
        System.out.println("Arrays.sort");
    } 
    public static void SortMethodTester(FunctionPointer methodToBeTested, int n)
    {
        // Tehdään satunnainen taulukko.
        // Oikeastaan taulukon luomismetodi (satunnainen, nouseva, laskeva)
        // voitaisiin antaa myös parametrina.
        int[] array = makeRandomArray(n);

        // otetaan alkuaika
        long start = System.nanoTime();

        // Kutsutaan parametrina annettua testattavaa metodia.
        methodToBeTested.methodSignature(array);

        // otetaan loppuaika
        long end = System.nanoTime();

        long kesto = end - start;
        long kestoMs = kesto / 1_000_000;

        // tulostetaan kestoaika
        System.out.println(kesto + " ns (" + kestoMs + " ms)");
    }

public static void main(String[] args) {
    // Taulukko FunctionPointer-viitteistä
    FunctionPointer[] methods = {
        FunctionRef::sortMethodA,
        FunctionRef::sortMethodB,
        FunctionRef::sortMethodC,
        FunctionRef::sortMethodD,
        FunctionRef::sortMethodE,
        FunctionRef::sortMethodF
    };
    

    
    // Testataan kaikki metodit silmukassa
    for (int i = 0; i < methods.length; i++) {
        SortMethodTester(methods[i], 1000000);
    }
}}