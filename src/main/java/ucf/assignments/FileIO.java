package ucf.assignments;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileIO {


    public void writeTempFile(ArrayList<String> items) throws IOException {
        Path filePath = Path.of(createTempFile());
        try   {
            for(int i = 0; i < items.size(); i++) {
                Files.write((filePath), items.get(i).getBytes(), StandardOpenOption.APPEND);
                Files.write((filePath), "\n".getBytes(), StandardOpenOption.APPEND);
            }
        }
        catch(Exception ignored)   {

        }


    }

    public void removeOldFile(String oldFilePath)   {
        File oldFile = new File(oldFilePath);
        oldFile.delete();

    }

    public void renameFile(String oldPath, String newPath)   {
        File newFile = new File(newPath);
        newFile.renameTo(new File(oldPath));
    }

    public String createTempFile() throws IOException {
        File newFile = new File("temp.txt");
        newFile.createNewFile();
        return newFile.getAbsolutePath();
    }

}
