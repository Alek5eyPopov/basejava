/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int size = size();
        if(size == storage.length){
            return;
        }
        storage[size] = r;
    }

    Resume get(String uuid) {
        Resume resume = null;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            if (storage[i].uuid == uuid) {
                resume = storage[i];
                break;
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int index = -1;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            if (storage[i].uuid == uuid) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        storage[index] = null;

        if (index == storage.length - 1) {
            return;
        }

        for (int i = index + 1; i < storage.length; i++) {
            storage[index] = storage[i];
            index++;
            if (storage[i] == null) {
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
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
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            size++;
        }
        return size;
    }
}
