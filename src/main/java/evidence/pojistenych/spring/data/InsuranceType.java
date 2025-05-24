package evidence.pojistenych.spring.data;

/**
 * Výčet typů pojištění s jejich názvy pro zobrazení.
 */
public enum InsuranceType {
    ZIVOTNI("Životní"),
    CESTOVNI("Cestovní"),
    URAZOVE("Úrazové"),
    MAJETKOVE("Majetkové"),
    ODPOVEDNOSTI("Odpovědnosti");

    private final String displayName;

    /**
     * Konstruktor pro nastavení názvu typu pojištění.
     *
     * @param displayName název typu pojištění pro zobrazení
     */
    InsuranceType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Vrátí název typu pojištění pro zobrazení.
     *
     * @return název typu pojištění
     */
    public String getDisplayName() {
        return displayName;
    }
}
