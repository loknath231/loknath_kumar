import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class NoteTransposer {
    // Constants for the piano keyboard range and notes per octave
    private static final int FIRST_NOTE_OCTAVE = -3;
    private static final int FIRST_NOTE_NUMBER = 10;
    private static final int LAST_NOTE_OCTAVE = 5;
    private static final int LAST_NOTE_NUMBER = 1;
    private static final int NOTES_PER_OCTAVE = 12;

    public static void main(String[] args) {
        // Check if the correct number of arguments are provided
        if (args.length != 3) {
            System.err.println("Usage: java -jar NoteTransposer.jar <inputFile> <semitone> <outputFile>");
            return;
        }
        // Parse command line arguments
        String inputFile = args[0];
        int semitone = Integer.parseInt(args[1]);
        String outputFile = args[2];

        try {
            // Read notes from the input file
            List<int[]> notes = readNotes(inputFile);
            // Transpose the notes by the specified number of semitones
            List<int[]> transposedNotes = transposeNotes(notes, semitone);
            // Check if the transposed notes are within the valid range
            if (!areNotesInRange(transposedNotes)) {
                System.err.println("Error: Transposed notes are out of range.");
                return;
            }
            // Write the transposed notes to the output file
            writeNotes(outputFile, transposedNotes);
        } catch (IOException e) {
            // Handle file I/O exceptions
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Reads a list of notes from the specified JSON file.
     *
     * @param inputFile the path to the input JSON file
     * @return a list of notes
     * @throws IOException if an I/O error occurs
     */
    private static List<int[]> readNotes(String inputFile) throws IOException {
        Gson gson = new Gson();
        Type notesType = new TypeToken<List<int[]>>(){}.getType();
        try (FileReader reader = new FileReader(inputFile)) {
            return gson.fromJson(reader, notesType);
        }
    }

    /**
     * Transposes each note by the specified number of semitones.
     *
     * @param notes the list of notes to transpose
     * @param semitone the number of semitones to transpose each note
     * @return the list of transposed notes
     */
    private static List<int[]> transposeNotes(List<int[]> notes, int semitone) {
        for (int[] note : notes) {
            int totalSemitones = note[0] * NOTES_PER_OCTAVE + note[1] + semitone;
            note[0] = totalSemitones / NOTES_PER_OCTAVE;
            note[1] = totalSemitones % NOTES_PER_OCTAVE;
            if (note[1] <= 0) {
                note[1] += NOTES_PER_OCTAVE;
                note[0] -= 1;
            }
        }
        return notes;
    }

    /**
     * Checks if all notes are within the valid piano keyboard range.
     *
     * @param notes the list of notes to check
     * @return true if all notes are within range, false otherwise
     */
    private static boolean areNotesInRange(List<int[]> notes) {
        for (int[] note : notes) {
            if (note[0] < FIRST_NOTE_OCTAVE || 
               (note[0] == FIRST_NOTE_OCTAVE && note[1] < FIRST_NOTE_NUMBER) ||
               note[0] > LAST_NOTE_OCTAVE || 
               (note[0] == LAST_NOTE_OCTAVE && note[1] > LAST_NOTE_NUMBER)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Writes the list of notes to the specified JSON file.
     *
     * @param outputFile the path to the output JSON file
     * @param notes the list of notes to write
     * @throws IOException if an I/O error occurs
     */
    private static void writeNotes(String outputFile, List<int[]> notes) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(outputFile)) {
            gson.toJson(notes, writer);
        }
    }
}
