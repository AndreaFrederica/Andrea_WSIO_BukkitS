package cc.sirrus.andrea.tools;


public class JsonStringBuilder {
    StringBuilder stringBuilder;
    Boolean head = true;
    String type;

    // type 为 dict 或者 list
    public JsonStringBuilder(String... type) {
        if (type.length != 1) {
            this.type = "dict";
        } else {
            this.type = type[0];
        }
        this.stringBuilder = new StringBuilder();
        if (this.type.equals("dict")) {
            this.stringBuilder.append("{");
        } else {
            this.stringBuilder.append("[");
        }
    }

    public void append(String key, Object value, boolean... RAW_STRING) {
        boolean DO_RAW_STRING = RAW_STRING.length == 1;
        if (this.type.equals("dict")) {
            // dict 类型
            if (this.head) {
                this.stringBuilder.append("\"").append(key).append("\":");
                this.head = false;
            } else {
                this.stringBuilder.append(",\"").append(key).append("\":");
            }
            addValItem(value, DO_RAW_STRING);
        } else {
            // list 类型
            if (this.head) {
                addValItem(value, DO_RAW_STRING);
                this.head = false;
            } else {
                this.stringBuilder.append(",");
                addValItem(value, DO_RAW_STRING);
            }
        }
    }

    public void append(Object value, boolean... RAW_STRING) {
        boolean DO_RAW_STRING = RAW_STRING.length == 1;
        if (this.type.equals("list")) {
            if (this.head) {
                addValItem(value, DO_RAW_STRING);
                this.head = false;
            } else {
                this.stringBuilder.append(",");
                addValItem(value, DO_RAW_STRING);
            }
        }
    }

    private void addValItem(Object value, boolean DO_RAW_STRING) {
        String type = CommonTools.type(value);
        //ProjectTools.broadcast(type);
        switch (type) {
            case "java.lang.String":
                if (DO_RAW_STRING) {
                    this.stringBuilder.append(value);
                } else {
                    this.stringBuilder.append("\"").append(value).append("\"");
                }
                break;
            case "java.lang.Integer":
                this.stringBuilder.append(value.toString());
                break;
            case "null":
                this.stringBuilder.append("null");
                break;
            case "java.util.UUID":
                this.stringBuilder.append("\"").append(value.toString()).append("\"");
                break;
            case "tools.cc.sirrus.andrea.JsonStringBuilder":
                this.stringBuilder.append(value.toString());
                break;
            case  "java.net.InetSocketAddress":
                this.stringBuilder.append("\"").append(String.valueOf(value)).append("\"");
                break;
        }
    }

    public String toString() {
        if (this.type.equals("dict")) {
            return this.stringBuilder.append("}").toString();
        } else {
            return this.stringBuilder.append("]").toString();
        }
    }
}

