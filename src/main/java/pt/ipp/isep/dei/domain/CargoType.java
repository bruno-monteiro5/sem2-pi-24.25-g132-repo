package pt.ipp.isep.dei.domain;

public enum CargoType {
    ALUMINUM(35000, 1910, 1300),
    AUTOS(49000, 1900, 800),
    BAUXITE(28000, 1910, 1600),
    CATTLE(37000, 1800, 800),
    CEMENT(38000, 1884, 800),
    CHEMICALS(37000, 1882, 800),
    COAL(28000, 1800, 1600),
    COFFEE(37000, 1800, 800),
    COTTON(36000, 1800, 1100),
    DIESEL(39000, 1890, 1100),
    FERTILIZER(35000, 1905, 1300),
    FOOD(49000, 1800, 600),
    GOODS(42000, 1800, 800),
    GRAIN(28000, 1800, 1600),
    GRAVEL(28000, 1884, 1600),
    IRON(28000, 1800, 1600),
    LOGS(28000, 1800, 1600),
    LUMBER(35000, 1800, 1300),
    MAIL(65000, 1800, 300),
    MILK(51000, 1800, 300),
    OIL(37000, 1860, 1100),
    PAPER(46000, 1800, 800),
    PASSENGERS(56000, 1800, 500),
    PRODUCE(37000, 1800, 800),
    PULPWOOD(42000, 1800, 600),
    RUBBER(28000, 1900, 1600),
    STEEL(35000, 1856, 1300),
    SUGAR(37000, 1800, 800),
    TIRES(35000, 1900, 1300),
    URANIUM(43000, 1950, 800),
    WASTE(48000, 1950, 800),
    WOOL(36000, 1800, 1100),
    ALCOHOL(40000, 1800, 1100),
    AMMUNITION(38000, 1900, 1200),
    TROOPS(56000, 1900, 500),
    WEAPONS(38000, 1900, 1200),
    OTHER(20000, 1800, 1000);

    private final double baseValue;
    private final int introYear;
    private final int maxDeliveryDays;

    CargoType(double baseValue, int introYear, int maxDeliveryDays) {
        this.baseValue = baseValue;
        this.introYear = introYear;
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public int getIntroYear() {
        return introYear;
    }

    public int getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public boolean isAvailableIn(int year) {
        return year >= introYear;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
