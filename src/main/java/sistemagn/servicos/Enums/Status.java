package sistemagn.servicos.Enums;

public enum Status {
    ABERTO(1),
    FECHADO(2);
    private int code;

    private Status(int code) {
    }

    public int getCode() {
        return this.code;
    }

    public int StatusCode(int code) {

        for (Status value : Status.values()) {
            if (code == value.getCode()) {
                return code;
            }
        }
        throw new IllegalArgumentException("STATUS INVALIDO");
    }
}
