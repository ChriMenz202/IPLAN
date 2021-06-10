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

    public CsvWriter(File file, String line) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file.getPath(), true));
            bw.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
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
