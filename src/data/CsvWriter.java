/*Project: IPLAN
 *Package: data
 *Description: class for writing in files
 *Author: Christoph Menzinger
 *Last Change:  05.06.2021
 */

package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    /**
     * Writes in a csv file
     * @param file we want to write in
     * @param line string we want to write in the file
     */
    public CsvWriter(File file, String line) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file.getPath(), true));
            bw.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bw != null;
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
