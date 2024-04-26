import java.io.*;
import java.util.*;

public class Words {
    List<String> words = new ArrayList<>();
    List<Integer> level = new ArrayList<>();
    Random random = new Random();
    Scanner input = new Scanner(System.in);
    File file = new File("./单词.csv");
    int times;
    int exam_level;

    public static void main(String[] args) throws IOException {
        Words words1 = new Words();
        words1.run();
    }
    public Words() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(",");
            words.add(line[0]);
            level.add(Integer.valueOf(line[1]));
        }
        System.out.println("How many words do you want?");
        times = input.nextInt();

        System.out.println("Words Level?(0-10)");
        exam_level = input.nextInt();
    }

    public void run() throws IOException {
        System.out.println("<--------------begin test----------------->");
        for (int i = 0; i < times; i++){
            int index = getIndex();

            System.out.println(words.get(index));
            String c = input.next();
            if (c.equals("c")){
                this.level.set(index, this.level.get(index) - 1);
                continue;
            }
            if (c.equals("q")){
                break;
            }
            if (c.equals("n")){
                this.level.set(index, this.level.get(index) + 1);
            }
        }

        FileWriter fileWriter = new FileWriter(file);

        for (int i = 0; i < words.size(); i++){
            fileWriter.append(words.get(i)).append(String.valueOf(',')).append(String.valueOf(this.level.get(i)))
                    .append(System.lineSeparator());
        }

        fileWriter.flush();
    }


    public int getIndex(){
        int index;
        int one_level;
        do {
            index = Math.abs(random.nextInt()) % words.size();
            one_level = level.get(index);
        } while (one_level < exam_level);

        return index;
    }
}
