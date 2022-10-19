package io.github.juniqlim.maskingjson;

public class BytesJson {
    private final byte[] bytes;
    private int currentIndex;

    public BytesJson(byte[] bytes) {
        this.bytes = bytes;
        this.currentIndex = 0;
    }

    public String valueOfName(String name) {
        byte[] findbytes = ("\"" + name + "\"").getBytes();
        do {
            int indexOf = new Bytes(bytes).indexOf(findbytes, this.currentIndex);
            if (indexOf == -1) {
                return "";
            }
            this.currentIndex = currentIndex + indexOf + findbytes.length;
        } while (!isStringValueNext());

        int endIndex = new Bytes(this.bytes).indexOf('"', currentIndex);
        return new String(this.bytes, currentIndex, endIndex - currentIndex);
    }

    private void moveIndexOfAfterTarget(byte[] target) {
        currentIndex = new Bytes(bytes).indexOf(target, currentIndex) + target.length;
    }

    private boolean isStringValueNext() {
        return isColonNext() && isDoubleQuoteNext();
    }

    private boolean isColonNext() {
        while (bytes[currentIndex] == ' ' || bytes[currentIndex] == '\n' || bytes[currentIndex] == '\t') {
            currentIndex++;
        }
        if (bytes[currentIndex] == ':') {
            currentIndex++;
            return true;
        }
        return false;
    }

    private boolean isDoubleQuoteNext() {
        while (bytes[currentIndex] == ' ' || bytes[currentIndex] == '\n' || bytes[currentIndex] == '\t') {
            currentIndex++;
        }
        if (bytes[currentIndex] == '\"') {
            currentIndex++;
            return true;
        }
        return false;
    }
}
