# Subtitles Sync Java

This small program allows to shift the subtitles of the files `.ass`.

The shift is done in milliseconds.
# Usage
Download the latest `.jar` file from this [Github releases page](https://github.com/meddouribadis/subtitles_sync_java/releases) of this repository.

Place the .jar file in a folder, then create a subfolder for subtitles where you will place your .ass files to be modified (format: 001.ass, 002.ass, 010.ass, 100.ass).

From a terminal the command :
`java -jar .\Subtitles_Sync.jar first_file last_file delay`

If I want to shift my files from 1 to 5 with a 50ms delay, then I should use this command :
`java -jar .\Subtitles_Sync.jar 1 5 50`