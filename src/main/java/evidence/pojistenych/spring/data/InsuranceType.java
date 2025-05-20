package evidence.pojistenych.spring.data;

public enum InsuranceType {
    ZIVOTNI("Životní"),
    CESTOVNI("Cestovní"),
    URAZOVE("Úrazové"),
    MAJETKOVE("Majetkové"),
    ODPOVEDNOSTI("Odpovědnosti");

    private final String displayName;

    InsuranceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
