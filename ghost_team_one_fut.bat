cd src\com\gto\phoebe
javac domain/*.java logic/*.java skeleton/*.java ui/*.java
cd ..
cd ..
cd ..
javac Main.java

jar cfm ghost_team_one.jar MANIFEST.mf com/gto/phoebe/logic/*.class com/gto/phoebe/domain/*.class com/gto/phoebe/skeleton/*.class com/gto/phoebe/ui/*.class Main.class
java -jar ghost_team_one.jar

 