Highlands
=========

A mod for Minecraft which adds:

- Over 40 new biomes
- 14 new trees
- A variety of useful small plants
- Highlands and Highlands Large Biomes World Types
- Biome-specific ore generation (some biomes have more ores!)
- 256 block high Mountains
- More realistic biome placement
- Villages in more biomes
- Better oceans and islands
- Tons of options to customize

...and much more!

Setting up the Project/Contributing
====================================

If you would like to contribute to the project, follow the steps below to get started:

IntelliJ Users
---------------

1. Clone the repo on to your local system.
2. Open a command prompt window from the location you cloned the project.
3. Run 'gradlew setupDecompWorkspace'.  This will install all the assets needed for development.
4. Then, run 'gradlew idea' to generate IntelliJ files.  DO NOT run it with 'setupDecompWorkspace', that causes problems.
5. Open Intellij and select Import Project.
6. Find the project (either the folder it is in or its build.gradle file) and import it.
7. Make whatever changes you want to, and submit your pull!!


Eclipse Users
--------------

1. Clone the repo on to your local system.
2. Open a command prompt window from the location you cloned the project.
3. Run 'gradlew setupDecompWorkspace'.  This will install all the assets needed for development.
4. Then, run 'gradlew eclipse' to generate the eclipse files.  DO NOT run it with 'setupDecompWorkspace', that causes problems.
5. Open eclipse and select import from the file tab.
6. Under the general category, select the 'Exsiting Projects Into Workspace' option.
7. Find the workspace folder and select it.
8. Make whatever changes you would like, just don't distribute them!

Building the Project
====================

If you want buggy as heck alpha/beta builds for your Minecraft world, do this:

1. Download the project.
2. Open a command prompt window from the workspace folder.
3. Run 'gradlew build'.
4. Find the Highlands-version jar in the build/libs/ folder located in your workspace folder.
5. You have a usable and, if not an official verison, buggy copy of the mod.
