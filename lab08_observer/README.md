# Lab 08 Observer Pattern

Maven project for Java 21. The main class is `edu.spu.se411.lab08.App`.

## Run

Place the `lab08_observer` folder inside `se411_mylabs`, next to `lab07_polymorphism`.
Open the terminal in `lab08_observer` and run:

```powershell
mvn clean package exec:java
Get-Content .\logs\App\log4j\log.out
```

The simulation runs for about 10 seconds and finishes with `BUILD SUCCESS`.
Each round displays temperature and humidity updates from both observers.
The Logger observer also writes the updates to `logs/App/log4j/log.out`.

## Classes

- `Observer` and `Subject` use the interfaces from the lab sheet.
- `Sensor` implements registration, removal, notification and cloning.
- `TemperatureSensor` and `HumiditySensor` inherit the shared sensor behavior.
- `DashboardObserver` prints notifications with `System.out.printf`.
- `LoggerObserver` prints notifications and writes them using SLF4J.
- `App` uses `Sensor` and `Observer` references to demonstrate polymorphism.

## Main program checks

1. Run 10 reading changes using a `while` loop, with a one-second pause.
2. Unregister the dashboard from temperature and show that only the logger is notified.
3. Clone the temperature sensor. Initially the clone has no observers.
4. Register separate observers on the clone and update it independently.
5. Update the original and show that its observers still work independently.
6. Set the same reading again and show that no change notification is sent.

`super.clone()` copies the sensor state. Replacing the observer list with a new
empty `ArrayList` prevents the clone from sharing or inheriting the original observers.

The Maven dependencies and `log4j.properties` follow the lab annex.

## Commit and push

Run these commands from the `se411_mylabs` folder after checking the output:

```powershell
git add -- lab08_observer/pom.xml lab08_observer/src lab08_observer/README.md lab08_observer/.gitignore
git commit -m "Complete Lab08 Observer Pattern"
git push origin main
```
