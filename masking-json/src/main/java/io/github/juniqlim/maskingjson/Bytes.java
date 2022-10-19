package io.github.juniqlim.maskingjson;

class Bytes {
    private final byte[] bytes;

    Bytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int indexOf(char target, int fromIndex) {
        int i = fromIndex;
        while (i < bytes.length) {
            if (bytes[i] == target) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int indexOf(byte[] target) {
        int i = 0;
        int j = 0;
        while (i < bytes.length && j < target.length) {
            if (bytes[i] == target[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == target.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public int indexOf(byte[] target, int fromIndex) {
        int i = fromIndex;
        int j = 0;
        while (i < bytes.length && j < target.length) {
            if (bytes[i] == target[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == target.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
