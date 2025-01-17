Explanation
Imports: We import necessary classes for JSON processing (Gson, TypeToken) and for handling file operations (FileReader, FileWriter).

Constants: Constants are defined for the range of the piano keyboard and the number of notes per octave.

Main Method:

Argument Check: Checks if exactly three arguments are provided (input file, semitone, and output file).
Parsing Arguments: Parses the input file path, semitone value, and output file path.
Reading Notes: Reads notes from the input JSON file using the readNotes method.
Transposing Notes: Transposes the notes by the specified number of semitones using the transposeNotes method.
Range Check: Checks if the transposed notes are within the valid range using the areNotesInRange method.
Writing Notes: Writes the transposed notes to the output JSON file using the writeNotes method.
readNotes Method: Reads a list of notes from a JSON file and returns it as a List<int[]>.

transposeNotes Method: Transposes each note by the specified number of semitones. Adjusts the octave and note number accordingly.

areNotesInRange Method: Checks if all notes are within the valid piano keyboard range. Returns true if all notes are within range, false otherwise.

writeNotes Method: Writes the list of notes to a JSON file.

This code ensures that the transposed notes stay within the valid range of the piano keyboard and handles file reading/writing and JSON processing efficiently.

----- Step to Run -----
-----------------
1. unzip YourFirstName_YourLastName.zip and gradle clean build from project NoteTransposer
2. java -jar build/libs/NoteTransposer.jar input.json -3 output.json

-----------------
command used to ZIP : zip -r YourFirstName_YourLastName.zip NoteTransposer#   l o k n a t h _ k u m a r  
 