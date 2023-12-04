/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size >= storage.length) {
            return;
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                index = i;
                storage[index] = null;
                break;
            }
        }

        if (index == -1) {
            return;
        }

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
