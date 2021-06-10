/*Project: IPlaner
 *Package: PACKAGE_NAME
 *Description: class for reading files
 *Author: Christoph Menzinger
 *Last Change:  29.05.2021
 */

package data;

import java.io.*;
import java.util.ArrayList;

public class CsvReader {

    ArrayList<String> data = new ArrayList<>();

    /**
     * @param filePath relative path of file you want to read in
     *                 <p>
     *                 Reads a csv-file and puts the lines in an ArrayList for continuing editing
     */
    public CsvReader(String filePath) {
        BufferedReader bufferedReader;
        String singleLine;
        String current = System.getProperty("user.dir");
        File file = new File(current, filePath);
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((singleLine = bufferedReader.readLine()) != null) {
                this.data.add(singleLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(String data) {
        this.data.add(data);
    }
}
