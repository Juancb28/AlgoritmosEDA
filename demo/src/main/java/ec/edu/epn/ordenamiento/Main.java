package ec.edu.epn.ordenamiento;

import com.github.javafaker.Faker;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        double millisTimeStart;

        Faker faker = new Faker(Locale.forLanguageTag("es")); // idioma español
        List<Estudiante> lista = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            String nombre = faker.name().firstName();
            String apellido = faker.name().lastName();
            int nota = faker.number().numberBetween(5, 10);
            lista.add(new Estudiante(nombre, apellido, nota));
        }

        for (Estudiante estudiante : lista) {
            System.out.println(estudiante);
        }

        System.out.println("-------------------------------------------------------------------------------");

        millisTimeStart = System.currentTimeMillis();

        // sortList(lista); // Por defecto con Comparator y Collections.sort
        // quickSort(lista, 0, lista.size() - 1); // Quicksort (inestable)
        // mergeSort(lista, 0, lista.size() - 1); // MergeSort (estable)
        // bubbleSort(lista); // Bubble Sort (estable)
        // insertionSort(lista); // Insertion Sort (estable)
        // selectionSort(lista); // Selection Sort (inestable)

        System.out.println("\nHa transcurrido " + (System.currentTimeMillis() - millisTimeStart) / 1000.0 + "s.\n");

        for (Estudiante estudiante : lista) {
            System.out.println(estudiante);
        }

    }

    private static void sortList(List<Estudiante> estudiantes) {
        estudiantes.sort(Comparator
                .comparingInt(Estudiante::getNota)
                .reversed()
                .thenComparing(Estudiante::getApellido));
    }

    // QuickSort (inestable)
    private static void quickSort(List<Estudiante> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<Estudiante> list, int low, int high) {
        Estudiante pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getNota() > pivot.getNota() ||
                    (list.get(j).getNota() == pivot.getNota() &&
                            list.get(j).getApellido().compareTo(pivot.getApellido()) < 0)) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // MergeSort (estable)
    private static void mergeSort(List<Estudiante> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static void merge(List<Estudiante> list, int left, int mid, int right) {
        List<Estudiante> leftList = new ArrayList<>(list.subList(left, mid + 1));
        List<Estudiante> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            Estudiante a = leftList.get(i);
            Estudiante b = rightList.get(j);
            if (a.getNota() > b.getNota() ||
                    (a.getNota() == b.getNota() && a.getApellido().compareTo(b.getApellido()) <= 0)) {
                list.set(k++, a);
                i++;
            } else {
                list.set(k++, b);
                j++;
            }
        }

        while (i < leftList.size())
            list.set(k++, leftList.get(i++));
        while (j < rightList.size())
            list.set(k++, rightList.get(j++));
    }

    // Bubble Sort (estable)
    private static void bubbleSort(List<Estudiante> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Estudiante a = list.get(j);
                Estudiante b = list.get(j + 1);
                if (a.getNota() < b.getNota() ||
                        (a.getNota() == b.getNota() && a.getApellido().compareTo(b.getApellido()) > 0)) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    // Insertion Sort (estable)
    private static void insertionSort(List<Estudiante> list) {
        for (int i = 1; i < list.size(); i++) {
            Estudiante key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).getNota() < key.getNota() ||
                    (list.get(j).getNota() == key.getNota() &&
                            list.get(j).getApellido().compareTo(key.getApellido()) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Selection Sort (inestable)
    private static void selectionSort(List<Estudiante> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                Estudiante a = list.get(j);
                Estudiante b = list.get(maxIdx);
                if (a.getNota() > b.getNota() ||
                        (a.getNota() == b.getNota() && a.getApellido().compareTo(b.getApellido()) < 0)) {
                    maxIdx = j;
                }
            }
            Collections.swap(list, i, maxIdx);
        }
    }
}
