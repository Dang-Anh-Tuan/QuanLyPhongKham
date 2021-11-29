package qlpk.entity.enums;

public enum HinhThuc {
    TIEM("tiem", 1), UONG("uong", 2);
    private final String type;
    private final int value;

    HinhThuc(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
