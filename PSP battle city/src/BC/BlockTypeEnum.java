package BC;

public enum BlockTypeEnum {

    BLANK(0), BRICK(1);

    private final int value;

    private BlockTypeEnum(int value) {
        this.value = value;
    }

    public static BlockTypeEnum getTypeFromInt(int value) {
        return BlockTypeEnum.values()[value];
    }

}
