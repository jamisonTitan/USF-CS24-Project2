import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties props = new Properties();
        MyList<String[]> list = new MyList<String[]>();
        Scanner scanner = new Scanner(System.in);

        try (InputStream input = new FileInputStream("./config.properties")) {

            props.load(input);

            loadFiles(list, props);

            // System.out.println(props.getProperty("ListType"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Name of the Person (or EXIT to quit): ");
        String name = scanner.nextLine();
        System.out.println("Gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.println("State of Birth (two-letter state code): ");
        String state = scanner.nextLine();

        MyList<String[]> validData = list.removeIfDoesNotContain(state).removeIfDoesNotContain(gender)
                .removeIfDoesNotContain(name);
        int[] ages = new int[validData.size()];
        int[] numOfOccurences = new int[validData.size()];
        for (int i = 0; i < validData.size(); i++) {
            if (validData.get(i) != null) {
                ages[i] = 2021 - Integer.parseInt(validData.get(i)[2].replaceAll("\\D", ""));
                numOfOccurences[i] = Integer.parseInt(validData.get(i)[4].replaceAll("\\D", ""));

            }
        }
        System.out.println(Arrays.toString(ages));
        System.out.println(ages[maxOfArr(numOfOccurences)]);
    }

    static int maxOfArr(int[] arr) {
        int out = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (out < arr[i]) {
                out = arr[i];
                index = i;
            }
        }
        return index;
    }

    static void loadFiles(MyList<String[]> list, Properties props) throws IOException {
        File statesFolder = new File(props.getProperty("Directory") + "/namesbystate");
        File[] listOfFiles = statesFolder.listFiles();

        int index = 0;
        for (File file : listOfFiles) {
            // System.out.println(file.getName());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                list.add(index, str.split(","));
                index++;
            }

        }
    }

}
