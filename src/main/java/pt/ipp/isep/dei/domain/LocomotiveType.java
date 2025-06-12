package pt.ipp.isep.dei.domain;

public enum LocomotiveType {

        THREVITHICK_1("Threvithick",1800,1836,10000,6000,7543,new Steam(),"Extremely Poor","Extremely Poor",10),
        STEPHENSON_ROCKET("Stephenson Rocket",1800,1836,10000,6000,7992,new Steam(),"Extremely Poor","Extremely Poor",10),
        JOHN_BULL("John Bull",1831,1853,23000,7000,9256,new Steam(),"Poor","Below Average",15),
        DEWITT_CLINTON("Dewitt Clinton",1833,1855,18000,5000,7187,new Steam(),"Extremely Poor","Poor",20),
        PRUSSIAN("Prussian",1837,1857,35000,8000,10822,new Steam(),"Extemely Poor","Below Average",30),
        AMERICAN_C("American C",1848,1885,46000,5000,12606,new Steam(),"Poor","Above Average",42),
        IRON_DUKE("Iron Duke",1855,1895,46000,5000,30736,new Steam(),"Poor","Below Average",54),
        A3("A3",1865,1910,67000,6000,0,new Steam(),"Poor","Good",30),
        EIGHT_WHEELER("Eight Wheeler",1868,1904,59000,5000,17808,new Steam(),"Below Average","Above Average",48),
        VULCAN("Vulcan",1872,1900,32000,4000,6393,new Steam(),"Very Poor","Below Average",30),
        CONSOLIDATION("Consolidation",1877,1905,5100,8000,19512,new Steam(),"Below Aerage","Good",40),
        TRUCK_SHAY("Truck Shay",1882,1930,43000,17000,18227,new Steam(),"Poor","Good",15),
        MASTODON("Mastodon",1890,1911,60000,13000,22395,new Steam(),"Very Poor","Poor",45),
        TEN_WHEELER("Ten Wheeler",1892,1911,66000,11000,21727,new Steam(),"Below Average","Below Average",50),
        MOGUL("Mogul",1895,1915,83000,12000,25131,new Steam(),"Average","Average",50),
        BOBO("Bobo",1895,1935,85000,6000,33116,new Electric(),"Poor","Good",55),
        ATLANTIC("Atlantic",1902,1929,93000,18000,43556,new Steam(),"Fast","Above Average",80),
        CAMELBACK("Camelback",1905,1932,75000,9000,15000,new Steam(),"Poor","Very Good",30),
        PACIFIC("Pacific",1908,1945,119000,21000,62516,new Steam(),"Above Average","Average",95),
        GASS_G10("Gass G10",1910,1930,98000,38000,50521,new Steam(),"Above Average","Average",50),
        PRAIRIE("Prairie",1912,1953,85000,11000,34157,new Steam(),"Below Average","Good",60),
        D16SB("D16sb",1914,1934,65000,9000,21024,new Steam(),"Poor","Very Good",45),
        CLASS_13H("Class 13H",1917,1940,102000,36000,46289,new Steam(),"Above Average","Good",40),
        USRA_0_6_0("Usra 0-6-0",1918,1942,90000,19000,29993,new Steam(),"Poor","Good",40),
        MIKADO("Mikado",1919,1950,133000,32000,51072,new Steam(),"Above Average","Good",55),
        BE_4_6_II("Be 4-6-||",1920,1950,61000,11000,14701,new Electric(),"Fast","Very Good",35),
        CLASS_B12("Class-B12",1923,1939,146000,14000,32407,new Steam(),"Fast","Above Average",71),
        EE3_3("Ee3-3",1923,1948,47000,7000,11880,new Electric(),"Very Poor","Good",30),
        CLASS_1045("Class-1045",1927,1950,95000,6000,11636,new Electric(),"Fast","Above Average",40),
        USRA_0_8_0("Usra-0-8-0",1930,1945,98000,18000,32236,new Steam(),"Below Average","Above Average",45),
        CLASSA4_MALLARD("Classa4 Mallard",1935,1955,200000,19000,55136,new Steam(),"Above Average","Below Average",126),
        GG1("GG1",1935,1970,285000,19000,42721,new Electric(),"Above Average","Outstanding",100),
        CLASS_E18("Class E18",1936,1966,97000,16000,27493,new Electric(),"Fast","Good",93),
        HUDSON("Hudson",1937,1953,210000,25000,54017,new Steam(),"Below Average","Average",80),
        DAYLIGHT("Daylight",1937,1952,230000,30000,63611,new Steam(),"Average","Below Average",80),
        STREAMLINER("Steamliner",1938,1955,255000,28000,112234,new Steam(),"Above Average","Poor",103),
        AE_8_14("Ae 8-14",1939,1955,210000,24000,47906,new Electric(),"Fast","Above Average",68),
        CLASS1020("Class1020",1941,1981,119000,18000,21545,new Electric(),"Fast","Very Good",56),
        BIG_BOY("Big Boy",1941,1955,375000,75000,88040,new Steam(),"Fast","Good",85),
        T1("T1",1945,4000,284000,28000,78213,new Steam(),"Fast","Good",85),
        F3A_B8("F3A-B8",1945,1968,265000,16000,52150,new Diesel(),"Fast","Above Average",85),
        ALCO_PA_1("Alco-Pa-1",1946,1990,210000,16000,52800,new Diesel(),"Average","Average",90),
        F9("F9",1949,1970,337000,18000,63000,new Diesel(),"Above Average","Above Average",110),
        GP9("GP9",1954,1980,235000,11000,48028,new Diesel(),"Average","Good",71),
        E69("E69",1955,1970,86000,8000,12837,new Electric(),"Very Fast","Below Average",31),
        TGV_TSC("TGV-TSC",1957,1973,850000,80000,12837,new Electric(),"Above Average","Below Average",155),
        GP18("GP18",1958,1975,245000,15000,55131,new Diesel(),"Above Average","Very Good",83),
        V200("V200",1959,1985,160000,19000,53878,new Diesel(),"Above Average","Below Average",87),
        PENN_E44("Penn-E44",1960,1985,370000,22000,37971,new Electric(),"Fast","Average",70),
        CLASS_55_DELTIC("Class-55-Deltic",1961,1981,480000,15000,52155,new Diesel(),"Below Average","Very Good",100),
        SHINKANSEN_BULLET("Shinkansen Bullet",1966,2002,650000,66000,66680,new Electric(),"Virtually Instante","Average",130),
        FP45("FP45",1968,1999,366000,14000,65740,new Diesel(),"Above Average","Above Average",106),
        SD45("SD45",1972,1994,280000,21000,26009,new Diesel(),"Average","Average",65),
        SDP40("SDP40",1973,1989,292000,18000,61966,new Diesel(),"Above Average","Poor",103),
        E60CP("E60CP",1973,1998,310000,19000,38043,new Electric(),"Very fast","Average",85),
        TGV_TSC2("TGV-TSC+",1973,9999,750000,72000,81664,new Electric(),"Fast","Average",145),
        CLASS_E111("Class-E111",1974,9999,390000,17000,38016,new Electric(),"Average","Good",85),
        E656_FS("E656-FS",1975,1995,226000,17000,37734,new Electric(),"Average","Above Average",93),
        DASH_9("Dash-9",1993,9999,478000,18000,68410,new Diesel(),"Above Average","Good",70),
        AMD_103("AMD-103",1993,9999,425000,25000,88068,new Diesel(),"Very Fast","Above Average",105),
        THALYS_BULLET("Thalys Bullet",1994,9999,1000000,78000,111756,new Electric(),"Ultra Fast","Very Good",175),
        EUROSTAR_TSC("Eurostar-TSC",1998,9999,1600000,65000,96135,new Electric(),"Ultra Fast","Very Good",175),
        CLASS_232("Class-232",1997,9999,492000,35000,72625,new Diesel(),"Very Fast","Very Good",75),
        DB18_201_TSC("DB18-201-TSC",1998,9999,400000,75000,144560,new Steam(),"Very Fast","Above Average",120),
        BRENNER_TSC("Brenner-TSC",1999,9999,1250000,170000,69292,new Electric(),"Above Average","Good",140),
        MAG_LEV_TBX_1("Mag-Lev-TBX-1",2008,9999,2500000,200000,305609,new Electric(),"Instant","Above Average",280);

        private final String name;
        private final int startYear;
        private final int endYear;
        private final double price;
        private final double maintenance;
        private final double fuelCost;
        private final FuelType fuelType;
        private final String acceleration;
        private final String reliability;
        private final double topSpeed;

        LocomotiveType(String name, int startYear, int endYear, double price, double maintenance, double fuelCost,
                       FuelType fuelType, String acceleration, String reliability, double topSpeed) {
                this.name = name;
                this.startYear = startYear;
                this.endYear = endYear;
                this.price = price;
                this.maintenance = maintenance;
                this.fuelCost = fuelCost;
                this.fuelType = fuelType;
                this.acceleration = acceleration;
                this.reliability = reliability;
                this.topSpeed = topSpeed;
        }

        public String getName() {
                return name;
        }

        public int getStartYear() {
                return startYear;
        }

        public int getEndYear() {
                return endYear;
        }

        public double getPrice() {
                return price;
        }

        public double getMaintenance() {
                return maintenance;
        }

        public double getFuelCost() {
                return fuelCost;
        }

        public FuelType getFuelType() {
                return fuelType;
        }

        public String getAcceleration() {
                return acceleration;
        }

        public String getReliability() {
                return reliability;
        }

        public double getTopSpeed() {
                return topSpeed;
        }
}
