package io.github.juniqlim.maskingjson;

class FindedStringValueInJson {
    private final StringBuffer sourceJson;
    private final String targetName;

    FindedStringValueInJson(StringBuffer sourceJson, String targetName) {
        this.sourceJson = sourceJson;
        this.targetName = "\"" + targetName + "\"";
    }

    Result value() {
        int targetNameIndex = sourceJson.indexOf(targetName);
        int startIndex = sourceJson.indexOf("\"", targetNameIndex + targetName.length()) + 1;
        int endIndex = sourceJson.indexOf("\"", startIndex);
        String targetValue = sourceJson.substring(startIndex, endIndex);
        return new Result(startIndex, endIndex, targetValue);
    }

    static class Result {
        private final int startIndex;
        private final int endIndex;
        private final String findedString;

        public Result(int startIndex, int endIndex, String findedString) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.findedString = findedString;
        }

        public int startIndex() {
            return startIndex;
        }

        public int endIndex() {
            return endIndex;
        }

        public String findedString() {
            return findedString;
        }
    }
}
