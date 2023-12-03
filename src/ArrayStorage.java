/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if(size == storage.length){
            return;
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                resume = storage[i];
                break;
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        storage[index] = null;

        if (index != storage.length - 1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (size == 0) {
            return new Resume[0];
        }

        Resume[] resume = new Resume[size];
        for (int i = 0; i < size; i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    int size() {
        return size;
    }
}
