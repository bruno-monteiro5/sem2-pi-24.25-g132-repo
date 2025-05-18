package pt.ipp.isep.dei.domain;

public enum LocomotiveType {
        /**
         * The Threvithick 1.
         */
        THREVITHICK_1("Threvithick",1800,1836,10000,6000,7543,"Steam","Extremely Poor","Extremely Poor",10),
        /**
         * The Stephenson rocket.
         */
        STEPHENSON_ROCKET("Stephenson Rocket",1800,1836,10000,6000,7992,"Steam","Extremely Poor","Extremely Poor",10),
        /**
         * The John bull.
         */
        JOHN_BULL("John Bull",1831,1853,23000,7000,9256,"Steam","Poor","Below Average",15),
        /**
         * The Dewitt clinton.
         */
        DEWITT_CLINTON("Dewitt Clinton",1833,1855,18000,5000,7187,"Steam","Extremely Poor","Poor",20),
        /**
         * The Prussian.
         */
        PRUSSIAN("Prussian",1837,1857,35000,8000,10822,"Steam","Extemely Poor","Below Average",30),
        /**
         * The American c.
         */
        AMERICAN_C("American C",1848,1885,46000,5000,12606,"Steam","Poor","Above Average",42),
        /**
         * The Iron duke.
         */
        IRON_DUKE("Iron Duke",1855,1895,46000,5000,30736,"Steam","Poor","Below Average",54),
        /**
         * A 3 train type.
         */
        A3("A3",1865,1910,67000,6000,0,"Steam","Poor","Good",30),
        /**
         * The Eight wheeler.
         */
        EIGHT_WHEELER("Eight Wheeler",1868,1904,59000,5000,17808,"Steam","Below Average","Above Average",48),
        /**
         * The Vulcan.
         */
        VULCAN("Vulcan",1872,1900,32000,4000,6393,"Steam","Very Poor","Below Average",30),
        /**
         * The Consolidation.
         */
        CONSOLIDATION("Consolidation",1877,1905,5100,8000,19512,"Steam","Below Aerage","Good",40),
        /**
         * The Truck shay.
         */
        TRUCK_SHAY("Truck Shay",1882,1930,43000,17000,18227,"Steam","Poor","Good",15),
        /**
         * The Mastodon.
         */
        MASTODON("Mastodon",1890,1911,60000,13000,22395,"Steam","Very Poor","Poor",45),
        /**
         * The Ten wheeler.
         */
        TEN_WHEELER("Ten Wheeler",1892,1911,66000,11000,21727,"Steam","Below Average","Below Average",50),
        /**
         * Mogul train type.
         */
        MOGUL("Mogul",1895,1915,83000,12000,25131,"Steam","Average","Average",50),
        /**
         * Bobo train type.
         */
        BOBO("Bobo",1895,1935,85000,6000,33116,"Electric","Poor","Good",55),
        /**
         * The Atlantic.
         */
        ATLANTIC("Atlantic",1902,1929,93000,18000,43556,"Steam","Fast","Above Average",80),
        /**
         * The Camelback.
         */
        CAMELBACK("Camelback",1905,1932,75000,9000,15000,"Steam","Poor","Very Good",30),
        /**
         * The Pacific.
         */
        PACIFIC("Pacific",1908,1945,119000,21000,62516,"Steam","Above Average","Average",95),
        /**
         * The Gass g 10.
         */
        GASS_G10("Gass G10",1910,19309,98000,38000,50521,"Steam","Above Average","Average",50),
        /**
         * The Prairie.
         */
        PRAIRIE("Prairie",1912,1953,85000,11000,34157,"Steam","Below Average","Good",60),
        /**
         * The D 16 sb.
         */
        D16SB("D16sb",1914,1934,65000,9000,21024,"Steam","Poor","Very Good",45),
        /**
         * The Class 13 h.
         */
        CLASS_13H("Class 13H",1917,1940,102000,36000,46289,"Steam","Above Average","Good",40),
        /**
         * The Usra 0 6 0.
         */
        USRA_0_6_0("Usra 0-6-0",1918,1942,90000,19000,29993,"Steam","Poor","Good",40),
        /**
         * The Mikado.
         */
        MIKADO("Mikado",1919,1950,133000,32000,51072,"Steam","Above Average","Good",55),
        /**
         * The Be 4 6 ii.
         */
        BE_4_6_II("Be 4-6-||",1920,1950,61000,11000,14701,"Electric","Fast","Very Good",35),
        /**
         * The Class b 12.
         */
        CLASS_B12("Class-B12",1923,1939,146000,14000,32407,"Steam","Fast","Above Average",71),
        /**
         * The Ee 3 3.
         */
        EE3_3("Ee3-3",1923,1948,47000,7000,11880,"Electric","Very Poor","Good",30),
        /**
         * The Class 1045.
         */
        CLASS_1045("Class-1045",1927,1950,95000,6000,11636,"Electric","Fast","Above Average",40),
        /**
         * The Usra 0 8 0.
         */
        USRA_0_8_0("Usra-0-8-0",1930,1945,98000,18000,32236,"Steam","Below Average","Above Average",45),
        /**
         * The Classa 4 mallard.
         */
        CLASSA4_MALLARD("Classa4 Mallard",1935,1955,200000,19000,55136,"Steam","Above Average","Below Average",126),
        /**
         * The Gg 1.
         */
        GG1("GG1",1935,1970,285000,19000,42721,"Electric","Above Average","Outstanding",100),
        /**
         * The Class e 18.
         */
        CLASS_E18("Class E18",1936,1966,97000,16000,27493,"Electric","Fast","Good",93),
        /**
         * The Hudson.
         */
        HUDSON("Hudson",1937,1953,210000,25000,54017,"Steam","Below Average","Average",80),
        /**
         * The Daylight.
         */
        DAYLIGHT("Daylight",1937,1952,230000,30000,63611,"Steam","Average","Below Average",80),
        /**
         * The Streamliner.
         */
        STREAMLINER("Steamliner",1938,1955,255000,28000,112234,"Steam","Above Average","Poor",103),
        /**
         * The Ae 8 14.
         */
        AE_8_14("Ae 8-14",1939,1955,210000,24000,47906,"Electric","Fast","Above Average",68),
        /**
         * The Class 1020.
         */
        CLASS1020("Class1020",1941,1981,119000,18000,21545,"Electric","Fast","Very Good",56),
        /**
         * The Big boy.
         */
        BIG_BOY("Big Boy",1941,1955,375000,75000,88040,"Steam","Fast","Good",85),
        /**
         * T 1 train type.
         */
        T1("T1",1945,4000,284000,28000,78213,"Steam","Fast","Good",85),
        /**
         * The F 3 a b 8.
         */
        F3A_B8("F3A-B8",1945,1968,265000,16000,52150,"Diesel","Fast","Above Average",85),
        /**
         * Alco pa 1 train type.
         */
        ALCO_PA_1("Alco-Pa-1",1946,1990,210000,16000,52800,"Diesel","Average","Average",90),
        /**
         * The F 9.
         */
        F9("F9",1949,1970,337000,18000,63000,"Diesel","Above Average","Above Average",110),
        /**
         * Gp 9 train type.
         */
        GP9("GP9",1954,1980,235000,11000,48028,"Diesel","Average","Good",71),
        /**
         * The E 69.
         */
        E69("E69",1955,1970,86000,8000,12837,"Electric","Very Fast","Below Average",31),
        /**
         * The Tgv tsc.
         */
        TGV_TSC("TGV-TSC",1957,1973,850000,80000,12837,"Electric","Above Average","Below Average",155),
        /**
         * The Gp 18.
         */
        GP18("GP18",1958,1975,245000,15000,55131,"Diesel","Above Average","Very Good",83),
        /**
         * The V 200.
         */
        V200("V200",1959,1985,160000,19000,53878,"Diesel","Above Average","Below Average",87),
        /**
         * Penn e 44 train type.
         */
        PENN_E44("Penn-E44",1960,1985,370000,22000,37971,"Electric","Fast","Average",70),
        /**
         * The Class 55 deltic.
         */
        CLASS_55_DELTIC("Class-55-Deltic",1961,1981,480000,15000,52155,"Diesel","Below Average","Very Good",100),
        /**
         * The Shinkansen bullet.
         */
        SHINKANSEN_BULLET("Shinkansen Bullet",1966,2002,650000,66000,66680,"Electric","Virtually Instante","Average",130),
        /**
         * The Fp 45.
         */
        FP45("FP45",1968,1999,366000,14000,65740,"Diesel","Above Average","Above Average",106),
        /**
         * Sd 45 train type.
         */
        SD45("SD45",1972,1994,280000,21000,26009,"Diesel","Average","Average",65),
        /**
         * The Sdp 40.
         */
        SDP40("SDP40",1973,1989,292000,18000,61966,"Diesel","Above Average","Poor",103),
        /**
         * The E 60 cp.
         */
        E60CP("E60CP",1973,1998,310000,19000,38043,"Electric","Very fast","Average",85),
        /**
         * Tgv tsc 2 train type.
         */
        TGV_TSC2("TGV-TSC+",1973,9999,750000,72000,81664,"Electric","Fast","Average",145),
        /**
         * Class e 111 train type.
         */
        CLASS_E111("Class-E111",1974,9999,390000,17000,38016,"Electric","Average","Good",85),
        /**
         * The E 656 fs.
         */
        E656_FS("E656-FS",1975,1995,226000,17000,37734,"Electric","Average","Above Average",93),
        /**
         * The Dash 9.
         */
        DASH_9("Dash-9",1993,9999,478000,18000,68410,"Diesel","Above Average","Good",70),
        /**
         * The Amd 103.
         */
        AMD_103("AMD-103",1993,9999,425000,25000,88068,"Diesel","Very Fast","Above Average",105),
        /**
         * The Thalys bullet.
         */
        THALYS_BULLET("Thalys Bullet",1994,9999,1000000,78000,111756,"Electric","Ultra Fast","Very Good",175),
        /**
         * The Eurostar tsc.
         */
        EUROSTAR_TSC("Eurostar-TSC",1998,9999,1600000,65000,96135,"Electric","Ultra Fast","Very Good",175),
        /**
         * The Class 232.
         */
        CLASS_232("Class-232",1997,9999,492000,35000,72625,"Diesel","Very Fast","Very Good",75),
        /**
         * The Db 18 201 tsc.
         */
        DB18_201_TSC("DB18-201-TSC",1998,9999,400000,75000,144560,"Steam","Very Fast","Above Average",120),
        /**
         * The Brenner tsc.
         */
        BRENNER_TSC("Brenner-TSC",1999,9999,1250000,170000,69292,"Electric","Above Average","Good",140),
        /**
         * The Mag lev tbx 1.
         */
        MAG_LEV_TBX_1("Mag-Lev-TBX-1",2008,9999,2500000,200000,305609,"Electric","Instant","Above Average",280);


        private String name;
        private int startYear;
        private int endYear;
        private double price;
        private double maintenance;
        private double fuelCost;
        private String fuelType;
        private String acceleration;
        private String reliability;
        private double topSpeed;

        LocomotiveType(String name, int startYear, int endYear, double price, double maintenance, double fuelCost, String fuelType, String acceleration, String reliability, double topSpeed){
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

        /**
         * Gets start year.
         *
         * @return the start year
         */
        public int getStartYear() {
                return startYear;
        }

        /**
         * Gets end year.
         *
         * @return the end year
         */
        public int getEndYear() {
                return endYear;
        }

        /**
         * Gets cost.
         *
         * @return the cost
         */
        public double getPrice() {
                return price;
        }

        /**
         * Gets maintenance.
         *
         * @return the maintenance
         */
        public double getMaintenance() {
                return maintenance;
        }

        /**
         * Gets fuel cost.
         *
         * @return the fuel cost
         */
        public double getFuelCost() {
                return fuelCost;
        }

        /**
         * Gets fuel type.
         *
         * @return the fuel type
         */
        public String getFuelType() {
                return fuelType;
        }

        /**
         * Gets aceleration.
         *
         * @return the aceleration
         */
        public String getAcceleration() {
                return acceleration;
        }

        /**
         * Gets reliability.
         *
         * @return the reliability
         */
        public String getReliability() {
                return reliability;
        }

        /**
         * Gets top speed.
         *
         * @return the top speed
         */
        public double getTopSpeed() {
                return topSpeed;
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
                return name;
        }
}

