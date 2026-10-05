SE411 Lab 07 - Polymorphism in Java

This independent Maven project implements the travel agency booking system
specified in the Lab 07 handout. It is configured for Java 21.

SETUP AND RUN

1. Copy the lab07_polymorphism folder beside lab06_logging inside:
   C:\Users\ADMIN\OneDrive\سطح المكتب\labs_se411\se411_mylabs

2. Open PowerShell and run:
   cd "C:\Users\ADMIN\OneDrive\سطح المكتب\labs_se411\se411_mylabs\lab07_polymorphism"
   mvn clean package exec:java

3. View the file logger output:
   Get-Content .\logs\App\log4j\log.out

Expected missing-information and invalid-argument messages are deliberate
demonstrations. They are caught and logged; they do not mean the build failed.

DESIGN

Booking is an abstract superclass. It stores the booking ID, customer name,
travel date, and destination. It also provides common range validation.
FlightBooking, TrainBooking, and CarRentalBooking inherit from Booking and
override calculateTotalPrice().

App.computeTotalPrice(Booking booking) works with every booking subtype. The
Booking array demonstrates runtime polymorphism without casting or instanceof.

SeatClass is an enum containing STANDARD and FIRST_CLASS.
GlobalConfiguration contains shared prices and all required range limits.
MissingInformationException and InvalidArgumentException extend Exception.

Luggage weight and distance use Double; rental days use Integer. Their initial
null value represents information that has not been supplied. In particular,
missing luggage weight is different from the valid value zero.

Setters reject invalid values before changing the booking. Price calculation
throws MissingInformationException when a required later value is absent.

SAMPLE RATES

The handout does not specify numeric rates. This solution uses example values:
extra luggage 20.00 per kg; standard train 0.50 per km; first class 0.80 per km.
All three rates can be changed in GlobalConfiguration.java.

EXPECTED DEMONSTRATIONS

1. Missing luggage weight, train distance, and rental days: 3 caught exceptions.
2. Invalid luggage weights -1 and 41, train distances 0 and 2001, and rental
   days 0 and 31: 6 caught exceptions.
3. Valid bookings:
   F001 flight:       500 + (10 x 20) = 700.00
   T001 standard:    300 x 0.50      = 150.00
   T002 first class: 300 x 0.80      = 240.00
   C001 car rental:  150 x 4         = 600.00
4. Inclusive boundaries:
   Flight 0 and 40 kg:        500.00 and 1300.00
   Train 1 and 2000 km:         0.50 and 1000.00
   Car rental 1 and 30 days:  150.00 and 4500.00

LOGGING

SLF4J and the log4j12 dependency are configured exactly as specified in the
handout. Maven redirects slf4j-log4j12 2.0.16 to slf4j-reload4j 2.0.16; a
relocation warning is expected. The original log4j.properties format is used.
Official explanation: https://www.slf4j.org/manual.html

Application start, application stop, and caught exceptions with stack traces
are written to logs/App/log4j/log.out. The log appends on subsequent runs.
The handout's copied Lab06 mainClass is corrected to the Lab07 App class.

GIT

After a successful run, execute from inside lab07_polymorphism:
   cd ..
   git add -- lab07_polymorphism
   git diff --cached --stat
   git commit -m "Complete Lab07 polymorphism and booking system"
   git push origin main

The local .gitignore excludes generated target and logs folders.

VERIFICATION

Maven clean package exec:java completed successfully in the preparation
environment using Java 17 with -Dmaven.compiler.release=17. All 10 printed
prices, 9 expected exceptions, and start/stop file log entries were verified.
The delivered pom.xml retains release 21 for your installed JDK. No generated
classes, build folders, or generated logs are included in this archive.
